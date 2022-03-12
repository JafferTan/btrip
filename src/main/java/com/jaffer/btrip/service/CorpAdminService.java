package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;

public interface CorpAdminService {

    BtripResult<UserPO> getCorpSuperAdminInfo(String corpId);
}
