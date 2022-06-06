package com.jaffer.btrip.manager;

import com.jaffer.btrip.beans.entity.TripFormMappingPO;
import com.jaffer.btrip.enums.EvectionFormOwnType;
import com.jaffer.btrip.mapper.TripFormMappingPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TripFormMappingManager {

    @Autowired
    private TripFormMappingPOMapper tripFormMappingPOMapper;


    public void addTripFormMapping(String corpId, String userId, String processInstanceId, EvectionFormOwnType evectionFormOwnType) {

        TripFormMappingPO tripFormMappingPO = new TripFormMappingPO();
        tripFormMappingPO.setCorpId(corpId);
        tripFormMappingPO.setGmtCreate(new Date());
        tripFormMappingPO.setGmtModified(new Date());
        tripFormMappingPO.setUserId(userId);
        tripFormMappingPO.setProcessInstanceId(processInstanceId);
        tripFormMappingPO.setType(evectionFormOwnType.getType());

        tripFormMappingPOMapper.insert(tripFormMappingPO);

    }
}
