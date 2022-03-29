package com.jaffer.btrip.beans.entity;

import lombok.Data;

import java.util.List;

@Data
public class TripStandardRQ {

    private Long tripStandId;

    private String corpId;

    private String tripStandardName;

    private List<String> flightLimitList;

    private List<String> trainLimitList;



    /**
     * key: 级别
     * value:金额限制 x元
     */
    private HotelLimitDO hotelLimitDO;
}
