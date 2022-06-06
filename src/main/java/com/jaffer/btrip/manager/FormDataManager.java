package com.jaffer.btrip.manager;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.FormDataPO;
import com.jaffer.btrip.beans.entity.TripFormDO;
import com.jaffer.btrip.mapper.FormDataPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class FormDataManager {

    @Autowired
    private FormDataPOMapper formDataPOMapper;

    public void addFormData(String corpId, String userId, String processInstanceId, TripFormDO tripFormDO) {
       formDataPOMapper.insert(this.buildFormDataPO(corpId, userId, processInstanceId, tripFormDO));
    }

    private FormDataPO buildFormDataPO(String corpId, String userId, String processInstanceId, TripFormDO tripFormDO) {
        FormDataPO formDataPO = new FormDataPO();

        formDataPO.setCorpId(corpId);
        formDataPO.setGmtCreate(new Date());
        formDataPO.setGmtModified(new Date());
        formDataPO.setCreator(userId);
        formDataPO.setProcinstId(processInstanceId);
        formDataPO.setContent(JSON.toJSONString(tripFormDO));

        return formDataPO;
    }
}
