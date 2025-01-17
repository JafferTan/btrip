package com.jaffer.btrip.helper;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.enums.RowStatusEnum;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DeptServiceHelper {

    /**
     * 将rq转换成po对象
     *
     * @param rq
     * @return
     */
    public DeptPO convert2DeptPO(DeptMaintainRQ rq) {
        DeptPO deptPO = new DeptPO();
        deptPO.setGmtModified(new Date());
        deptPO.setDeptName(rq.getDeptName());
        deptPO.setCorpId(rq.getCorpId());
        deptPO.setId(rq.getDeptId());
        deptPO.setDeptPid(rq.getDeptPid());
        deptPO.setManagerId(rq.getManagerId());
        deptPO.setStatus(RowStatusEnum.NORMAL.getStatus());
        return deptPO;
    }

}
