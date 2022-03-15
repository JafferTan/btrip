package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.beans.entity.SubDeptVO;
import com.jaffer.btrip.enums.BtripSpecialDeptEnum;
import com.jaffer.btrip.manager.DeptManager;
import com.jaffer.btrip.manager.UserManager;
import com.jaffer.btrip.service.DeptService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.RedisLockUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    private static final String LOCK_KEY_MAINTAIN_DEPT = "LOCK_KEY_MAINTAIN_DEPT_%s";

    @Autowired
    private DeptManager deptManager;

    @Autowired
    private UserManager userManager;

    @Override
    public BtripResult<Boolean> createOrEditDept(DeptMaintainRQ rq) {

        String lockKey = String.format(LOCK_KEY_MAINTAIN_DEPT, rq.getCorpId());
        try {
            boolean lock = RedisLockUtils.tryLock(lockKey);
            if (BooleanUtils.isFalse(lock)) {
                return BtripResultUtils.returnFailMsg("多个用户正在维护部门信息，请稍后重试");
            }

            if (Objects.isNull(rq.getDeptId())) {
                deptManager.createDept(rq);
            } else {
                deptManager.editDept(rq);
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("createOrEditDept fail, rq:{}", rq, e);
            return BtripResultUtils.returnFailMsg("维护部门失败,失败原因:" + e.getMessage());
        } finally {
            RedisLockUtils.releaseLock(lockKey);
        }
    }

    @Override
    public BtripResult<DeptPO> getDeptDetailByDeptId(String corpId, Long deptId) {
        try {
            DeptPO dept = deptManager.getDeptByDeptId(corpId, deptId);
            if (Objects.isNull(dept)) {
                return BtripResultUtils.returnFailMsg("查询的部门不存在");
            }
            return BtripResultUtils.returnSuccess(dept);
        } catch (Exception e) {
            log.error("getDeptDetailByDeptId fail, corpId:{}, deptId:{}", corpId, deptId, e);
            return BtripResultUtils.returnFailMsg("查询的部门失败,失败原因:" + e.getMessage());
        }
    }

    @Override
    public BtripResult<Boolean> deleteDept(String corpId, Long deptId) {
        String lockKey = String.format(LOCK_KEY_MAINTAIN_DEPT, corpId);
        try {
            boolean lock = RedisLockUtils.tryLock(lockKey);
            if (BooleanUtils.isFalse(lock)) {
                return BtripResultUtils.returnFailMsg("多个用户正在维护部门信息，请稍后重试");
            }

            if (Objects.equals(deptId, BtripSpecialDeptEnum.ROOT_DEPT.getDeptId())) {
                return BtripResultUtils.returnFailMsg("特殊部门不能删除");
            }

            DeptPO dept = deptManager.getDeptByDeptId(corpId, deptId);
            if (Objects.isNull(dept)) {
                return BtripResultUtils.returnFailMsg("查询的部门不存在");
            }
            List<Long> subDeptIds = deptManager.findSubDeptIds(corpId, dept.getLevelRelationMask());

            Long userNumber = userManager.countUserByDeptIdList(subDeptIds);
            if (userNumber != 0) {
                return BtripResultUtils.returnFailMsg("部门人数大于0,不能删除");
            }
            deptManager.logicDeleteDepts(corpId, subDeptIds);
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("deleteDept fail, corpId:{}, deptId:{}", corpId, deptId, e);
            return BtripResultUtils.returnFailMsg("查询的部门失败,失败原因:" + e.getMessage());
        } finally {
            RedisLockUtils.releaseLock(lockKey);
        }
    }


    @Override
    public BtripResult<Set<String>> getDeptManagerIds(String corpId, Long deptId) {
        try {

            DeptPO dept = deptManager.getDeptByDeptId(corpId, deptId);
            if (Objects.isNull(dept)) {
                return BtripResultUtils.returnFailMsg("部门不存在");
            }

            Set<String> managerIdSet = new HashSet<>();
            String managerIds = dept.getManagerIds();
            if (!StringUtils.isEmpty(managerIds)) {
                String[] split = StringUtils.split(managerIds, "|");
                for (String s : split) {
                    boolean add = managerIdSet.add(s);
                }
            }

            return BtripResultUtils.returnSuccess(managerIdSet);
        } catch (Exception e) {
            log.error("getDeptManagerIds fail, corpId:{}, deptId:{}", corpId, deptId);
            return BtripResultUtils.returnFailMsg("获取部门主管信息异常,异常原因: " + e.getMessage());
        }
    }


    @Override
    public BtripResult<SubDeptVO> getSubDeptDetail(String corpId, Long deptId) {
        try {
            SubDeptVO subDeptDetail = deptManager.getSubDeptDetail(corpId, deptId);
            return BtripResultUtils.returnSuccess(subDeptDetail);
        } catch (Exception e) {
            log.error("getDeptSubDeptDetail fail, corpId:{}, deptId:{}", corpId, deptId);
            return BtripResultUtils.returnFailMsg("获取子部门信息异常,异常原因: " + e.getMessage());
        }
    }
}
