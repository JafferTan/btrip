package com.jaffer.btrip.service.impl;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.beans.entity.TripFormDO;
import com.jaffer.btrip.beans.entity.request.EvectionFormRQ;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.manager.FormManager;
import com.jaffer.btrip.service.FormService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.BtripSessionUtils;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private FormManager formManager;

    @Override
    public BtripResult<Boolean> createEvectionForm(EvectionFormRQ evectionFormRQ) {
        try {
            formManager.createEvectionForm(evectionFormRQ);
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            return BtripResultUtils.returnFailMsg("出现异常" + e.getMessage());
        }
    }

    @Override
    public BtripResult<TripFormDO> queryEvectionForm(EvectionFormRQ evectionFormRQ) {
        return null;
    }
}
