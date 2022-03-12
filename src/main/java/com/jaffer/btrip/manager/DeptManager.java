package com.jaffer.btrip.manager;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.beans.entity.DeptPOExample;
import com.jaffer.btrip.enums.BtripSpecialDeptEnum;
import com.jaffer.btrip.enums.RowStatusEnum;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.helper.DeptServiceHelper;
import com.jaffer.btrip.mapper.DeptPOMapper;
import com.jaffer.btrip.util.CodeZipUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
@Component
public class DeptManager {

    @Autowired
    private DeptPOMapper deptPOMapper;

    @Autowired
    private DeptServiceHelper deptServiceHelper;
    /**
     * 创建部门
     *
     * @param rq
     * @return
     */
    @Transactional
    public Boolean createDept(DeptMaintainRQ rq) {
        DeptPO deptPO = deptServiceHelper.convert2DeptPO(rq);
        deptPO.setGmtCreate(new Date());
        int insertRes = deptPOMapper.insert(deptPO);
        if (insertRes <= 0) {
            throw new BizException("新建部门失败");
        }
        this.setDeptRelationAndMask(deptPO);

        int updateRes = deptPOMapper.updateByPrimaryKeySelective(deptPO);
        if (updateRes <= 0) {
            throw new BizException("更新部门mask & relation 失败");
        }
        return true;
    }

    /**
     * 编辑部门
     *
     * @param rq
     * @return
     */
    @Transactional
    public Boolean editDept(DeptMaintainRQ rq) {
        DeptPO deptPO = deptServiceHelper.convert2DeptPO(rq);
        int updateRes = deptPOMapper.updateByPrimaryKeySelective(deptPO);
        if (updateRes <= 0) {
            throw new BizException("编辑部门信息失败");
        }
        return true;
    }

    /**
     * 将部门的mask & relation字段填充 - 新建部门时使用
     *
     * @param deptPO
     */
    private void setDeptRelationAndMask(DeptPO deptPO) {
        if (Objects.equals(BtripSpecialDeptEnum.ROOT_DEPT.getDeptId(), deptPO.getDeptPid())) {
            deptPO.setLevelRelation(deptPO.getId() + "|" + BtripSpecialDeptEnum.ROOT_DEPT.getDeptId().toString());
            deptPO.setLevelRelationMask(CodeZipUtil.genKey(deptPO.getId()));
        } else {
            DeptPO parentDept = this.getDeptByDeptId(deptPO.getCorpId(), deptPO.getDeptPid());
            if (Objects.isNull(parentDept)) {
                throw new BizException("父部门不存在");
            }
            deptPO.setLevelRelation(deptPO.getId() + "|" + parentDept.getLevelRelation());
            deptPO.setLevelRelationMask(parentDept.getLevelRelationMask() + CodeZipUtil.genKey(deptPO.getId()));
        }
    }

    /**
     * 根据部门ID获取部门
     *
     * @param corpId
     * @param deptId
     * @return
     */
    public DeptPO getDeptByDeptId(String corpId, Long deptId) {
        DeptPOExample deptPOExample = new DeptPOExample();
        DeptPOExample.Criteria criteria = deptPOExample.createCriteria().andCorpIdEqualTo(corpId).andIdEqualTo(deptId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        List<DeptPO> deptPOS = deptPOMapper.selectByExample(deptPOExample);
        if (CollectionUtils.isEmpty(deptPOS)) {
            return null;
        }
        return deptPOS.get(0);
    }

    public List<Long> findSubDeptIds(String corpId, String mask) {
        List<Long> subDeptIdsByMask = deptPOMapper.findSubDeptIdsByMask(corpId, mask);
        return subDeptIdsByMask;
    }

    @Transactional
    public Boolean logicDeleteDepts(String corpId, List<Long> deptList) {
        DeptPOExample deptPOExample = new DeptPOExample();
        DeptPOExample.Criteria criteria = deptPOExample.createCriteria().andCorpIdEqualTo(corpId).andIdIn(deptList);
        DeptPO deptPO = new DeptPO();
        deptPO.setStatus(RowStatusEnum.DELETE.getStatus());
        int i = deptPOMapper.updateByExampleSelective(deptPO, deptPOExample);
        if (i < deptList.size()) {
            throw new BizException("删除部门失败");
        }
        return true;
    }
}
