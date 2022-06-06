package com.jaffer.btrip.manager;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.beans.entity.request.EvectionFormRQ;
import com.jaffer.btrip.enums.EvectionFormOwnType;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.util.BtripSessionUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class FormManager {


    @Autowired
    private WorkFlowManager workFlowManager;

    @Autowired
    private FormDataManager formDataManager;

    @Autowired
    private TripFormMappingManager tripFormMappingManager;

    @Autowired
    private TripInfoManager tripInfoManager;

    @Transactional
    public boolean createEvectionForm(EvectionFormRQ evectionFormRQ) {

        String processInstanceId = workFlowManager.createEvection(evectionFormRQ.getBizKey(), evectionFormRQ.getCorpId(), evectionFormRQ.getUserId(), evectionFormRQ.getTripFormDO());

        formDataManager.addFormData(evectionFormRQ.getCorpId(), evectionFormRQ.getUserId(), processInstanceId, evectionFormRQ.getTripFormDO());

        tripFormMappingManager.addTripFormMapping(evectionFormRQ.getCorpId(), evectionFormRQ.getUserId(), processInstanceId, EvectionFormOwnType.CREATOR);

        return true;
    }
}
