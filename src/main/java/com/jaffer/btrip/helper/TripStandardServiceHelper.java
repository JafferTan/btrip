package com.jaffer.btrip.helper;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.TripStandardPO;
import com.jaffer.btrip.beans.entity.TripStandardRQ;
import com.jaffer.btrip.enums.RowStatusEnum;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TripStandardServiceHelper {

    public TripStandardPO convert2TripStandardPO(TripStandardRQ tripStandardRQ) {

        TripStandardPO res = new TripStandardPO();
        res.setId(tripStandardRQ.getTripStandId());
        res.setCorpId(tripStandardRQ.getCorpId());
        res.setTripStandardName(tripStandardRQ.getTripStandardName());
        res.setGmtModified(new Date());
        res.setFlightLimit(JSON.toJSONString(tripStandardRQ.getFlightLimitList()));
        res.setHotelLimit(JSON.toJSONString(tripStandardRQ.getHotelLimitDO()));
        res.setStatus(RowStatusEnum.NORMAL.getStatus());
        res.setTrainLimit(JSON.toJSONString(tripStandardRQ.getTrainLimitList()));

        return res;
    }



}
