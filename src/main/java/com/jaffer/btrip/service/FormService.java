package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.TripFormDO;
import com.jaffer.btrip.beans.entity.request.EvectionFormRQ;
import com.jaffer.btrip.util.BtripResult;

public interface FormService {


    BtripResult<Boolean> createEvectionForm(EvectionFormRQ evectionFormRQ);


    BtripResult<TripFormDO> queryEvectionForm(EvectionFormRQ evectionFormRQ);
}
