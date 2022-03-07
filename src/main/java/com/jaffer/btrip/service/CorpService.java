package com.jaffer.btrip.service;

import com.jaffer.btrip.util.BtripResult;

public interface CorpService {
    BtripResult<Boolean> registerCorp(String corpName, String phoneNumber, String userName);
}
