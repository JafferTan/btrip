package com.jaffer.btrip.beans.entity;

import lombok.Data;

@Data
public class UserMaintainRQ {

    private String userId;

    private String userName;

    private Long deptId;

    private String phoneNumber;

    private String corpId;
}
