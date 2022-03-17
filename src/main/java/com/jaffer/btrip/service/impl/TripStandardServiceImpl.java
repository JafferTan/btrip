package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.beans.entity.TripStandardPO;
import com.jaffer.btrip.beans.entity.TripStandardRQ;
import com.jaffer.btrip.manager.TripStandardManager;
import com.jaffer.btrip.service.TripStandardService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TripStandardServiceImpl implements TripStandardService {

    @Autowired
    private TripStandardManager tripStandardManager;

    @Override
    public BtripResult<Boolean> addOrEditTripStandard(TripStandardRQ tripStandardRQ) {
        try {
            if (Objects.isNull(tripStandardRQ.getTripStandId())) {
                tripStandardManager.createTripStandard(tripStandardRQ);
            } else {
                tripStandardManager.editTripStandard(tripStandardRQ);
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("addOrEditTripStandard fail, tripStandardRQ:{}", tripStandardRQ, e);
            return BtripResultUtils.returnFailMsg("维护差旅标准异常");
        }
    }


    @Override
    public BtripResult<List<TripStandardPO>> getCorpAllTripStandard(String corpId) {
        try {
            List<TripStandardPO> corpAllTripStandard = tripStandardManager.getCorpAllTripStandard(corpId);
            return BtripResultUtils.returnSuccess(corpAllTripStandard);
        } catch (Exception e) {
            log.error("getCorpAllTripStandard occurred exception, corpId:{}", corpId, e);
            return BtripResultUtils.returnFailMsg("查询差旅标准列表异常，异常原因" + e.getMessage());
        }
    }

    @Override
    public BtripResult<Boolean> deleteTripStandard(String corpId, Long standardId) {
        try {
            tripStandardManager.deleteTripStandard(corpId, standardId);
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("deleteTripStandard occurred exception, corpId:{}, standardId:{}", corpId, standardId, e);
            return BtripResultUtils.returnFailMsg("删除企业差旅标准失败");

        }
    }
}
