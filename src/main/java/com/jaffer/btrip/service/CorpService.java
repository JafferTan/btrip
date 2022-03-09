package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.util.BtripResult;

public interface CorpService {
    /**
     * 注册企业
     * @param corpName
     * @param phoneNumber
     * @param userName
     * @return
     */
    BtripResult<Boolean> registerCorp(String corpName, String phoneNumber, String userName);

    /**
     * 获取企业详情
     * @param corpId
     * @return
     */
    BtripResult<CorpPO> getCorpDetailByCorpId(String corpId);

    /**
     * 删除企业
     * @param corpId
     * @return
     */
    BtripResult<Boolean> deleteCorpByCorpId(String corpId);
}
