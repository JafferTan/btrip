package com.jaffer.btrip.helper;

import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.enums.RowStatusEnum;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CorpServiceHelper {

    public CorpPO buildCorpPO(String corpName){
        CorpPO corpPO = new CorpPO();
        corpPO.setCorpId("btrip" + UUID.randomUUID().toString().replace("-",""));
        corpPO.setCorpName(corpName);
        corpPO.setGmtCreate(new Date());
        corpPO.setGmtModified(new Date());
        corpPO.setStatus(RowStatusEnum.NORMAL.getStatus());
        return corpPO;
    }
}
