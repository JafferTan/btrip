package com.jaffer.btrip.helper;

import com.jaffer.btrip.beans.entity.CorpAdminPO;
import com.jaffer.btrip.enums.CorpAdminEnum;
import com.jaffer.btrip.enums.RowStatusEnum;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CorpAdminServiceHelper {

    public CorpAdminPO buildCorpAdminPO(String corpId, String userId, CorpAdminEnum corpAdminEnum){
        CorpAdminPO corpAdminPO = new CorpAdminPO();
        corpAdminPO.setGmtCreate(new Date());
        corpAdminPO.setGmtModified(new Date());
        corpAdminPO.setStatus(RowStatusEnum.NORMAL.getStatus());
        corpAdminPO.setCorpId(corpId);
        corpAdminPO.setUserId(userId);
        corpAdminPO.setAdminType(corpAdminEnum.getAdminType());
        return corpAdminPO;
    }


}
