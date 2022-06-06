package com.jaffer.btrip.beans.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TripFormDO implements Serializable {

    private String corpId;

    private String userId;

    private String tripReason;

    private String processInstanceId;

    private Long deptId;

    private InvoiceInfo invoiceInfo;

    private String bizKey;

    private Date fromTime;

    private Date endTime;

    private String departure;

    private String destination;

    private List<TripInfo> tripInfoList = new ArrayList<>();

    private List<ApprovalInfo> approvalInfoList = new ArrayList<>();
}
