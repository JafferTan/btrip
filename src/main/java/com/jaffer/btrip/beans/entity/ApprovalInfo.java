package com.jaffer.btrip.beans.entity;

import lombok.Data;

@Data
public class ApprovalInfo {

    private String userId;

    private String userName;

    private Boolean approvalRes;

    private String remark;
}
