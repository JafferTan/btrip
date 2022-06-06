package com.jaffer.btrip.manager;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.beans.entity.TripFormDO;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.util.BtripSessionUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class WorkFlowManager {

    @Autowired
    private RuntimeService runtimeService;


    public String createEvection(String bizKey, String corpId, String userId, TripFormDO tripFormDO) {

        Map<String, Object> hashMap = new HashMap<>();

        hashMap.put(WorkFlowKeyWordConstants.CORP_ID, corpId);
        hashMap.put(WorkFlowKeyWordConstants.USER_ID, userId);
        hashMap.put(WorkFlowKeyWordConstants.LEVEL, 0);
        hashMap.put(WorkFlowKeyWordConstants.FORM_DATA_JSON, JSON.toJSONString(tripFormDO));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(bizKey, hashMap);
        if (Objects.isNull(processInstance)) {
            throw new BizException("create workflow fail");
        }

        return processInstance.getId();

    }
}
