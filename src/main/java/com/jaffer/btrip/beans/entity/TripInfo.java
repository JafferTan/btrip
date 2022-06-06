package com.jaffer.btrip.beans.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TripInfo {

    private String vehicleType;

    private Boolean isOneWay;

    private String departureCity;

    private String arrivalCity;

    private Date departureTime;

    private Date arrivalTime;


}
