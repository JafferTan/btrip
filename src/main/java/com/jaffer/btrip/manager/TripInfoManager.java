package com.jaffer.btrip.manager;

import com.jaffer.btrip.beans.entity.TripInfo;
import com.jaffer.btrip.beans.entity.TripInfoPO;
import com.jaffer.btrip.enums.RowStatusEnum;
import com.jaffer.btrip.mapper.TripInfoPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TripInfoManager {

    @Autowired
    private TripInfoPOMapper tripInfoPOMapper;

    public void addTripInfos(String corpId, String userId,String processInstanceId, List<TripInfo> tripInfoList) {
        for(TripInfo tripInfo : tripInfoList) {
            TripInfoPO tripInfoPO = new TripInfoPO();
            tripInfoPO.setCorpId(corpId);
            tripInfoPO.setUserId(userId);
            tripInfoPO.setIsDelete(RowStatusEnum.NORMAL.getStatus());
            tripInfoPO.setGmtCreate(new Date());
            tripInfoPO.setGmtModified(new Date());
            tripInfoPO.setDepartureCity(tripInfo.getDepartureCity());
            tripInfoPO.setArrivalCity(tripInfo.getArrivalCity());
            tripInfoPO.setDepartureTime(tripInfo.getDepartureTime());
            tripInfoPO.setArrivalTime(tripInfo.getArrivalTime());
            tripInfoPO.setProcessInstanceId(processInstanceId);

            tripInfoPOMapper.insert(tripInfoPO);
        }
    }
}
