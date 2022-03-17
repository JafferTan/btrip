package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.TripStandardPO;
import com.jaffer.btrip.beans.entity.TripStandardRQ;
import com.jaffer.btrip.util.BtripResult;

import java.util.List;

/**
 * 商旅审批标准设置
 */
public interface TripStandardService {

    /**
     * 编辑或新增商旅标准
     * @param tripStandardRQ
     * @return
     */
    BtripResult<Boolean> addOrEditTripStandard(TripStandardRQ tripStandardRQ);


    /**
     * 获取某个企业的差旅标准列表
     * @param corpId
     * @return
     */
    BtripResult<List<TripStandardPO>> getCorpAllTripStandard(String corpId);

    /**
     * 删除某个差旅标准
     */
    BtripResult<Boolean> deleteTripStandard(String corpId, Long standardId);

}
