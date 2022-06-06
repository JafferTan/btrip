package com.jaffer.btrip.beans.entity.request;

import com.jaffer.btrip.beans.entity.InvoiceInfo;
import com.jaffer.btrip.beans.entity.TripFormDO;
import lombok.Data;

@Data
public class EvectionFormRQ {

    private String corpId;

    private String userId;

    private Long deptId;

    private String bizKey;

    private TripFormDO tripFormDO;

    private String processInstanceId;
}
