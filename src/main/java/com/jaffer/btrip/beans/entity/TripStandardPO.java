package com.jaffer.btrip.beans.entity;

import java.util.Date;

public class TripStandardPO {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String status;

    private String tripStandardName;

    private String flightLimit;

    private String trainLimit;

    private String hotelLimit;

    private String corpId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTripStandardName() {
        return tripStandardName;
    }

    public void setTripStandardName(String tripStandardName) {
        this.tripStandardName = tripStandardName == null ? null : tripStandardName.trim();
    }

    public String getFlightLimit() {
        return flightLimit;
    }

    public void setFlightLimit(String flightLimit) {
        this.flightLimit = flightLimit == null ? null : flightLimit.trim();
    }

    public String getTrainLimit() {
        return trainLimit;
    }

    public void setTrainLimit(String trainLimit) {
        this.trainLimit = trainLimit == null ? null : trainLimit.trim();
    }

    public String getHotelLimit() {
        return hotelLimit;
    }

    public void setHotelLimit(String hotelLimit) {
        this.hotelLimit = hotelLimit == null ? null : hotelLimit.trim();
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }
}