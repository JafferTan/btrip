package com.jaffer.btrip.manager;

import com.google.common.collect.Lists;
import com.jaffer.btrip.beans.entity.TripStandardPO;
import com.jaffer.btrip.beans.entity.TripStandardPOExample;
import com.jaffer.btrip.beans.entity.TripStandardRQ;
import com.jaffer.btrip.enums.RowStatusEnum;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.helper.TripStandardServiceHelper;
import com.jaffer.btrip.mapper.TripStandardPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Component
public class TripStandardManager {

    @Autowired
    private TripStandardServiceHelper tripStandardServiceHelper;

    @Autowired
    private TripStandardPOMapper tripStandardPOMapper;

    @Transactional
    public Boolean createTripStandard(TripStandardRQ tripStandardRQ) {
        TripStandardPO tripStandardPO = tripStandardServiceHelper.convert2TripStandardPO(tripStandardRQ);
        tripStandardPO.setGmtCreate(new Date());
        int insert = tripStandardPOMapper.insert(tripStandardPO);
        if (insert < 0) {
            throw new BizException("新建差旅标准失败");
        }
        return true;
    }

    @Transactional
    public Boolean editTripStandard(TripStandardRQ tripStandardRQ) {
        TripStandardPO tripStandardPO = tripStandardServiceHelper.convert2TripStandardPO(tripStandardRQ);
        int update = tripStandardPOMapper.updateByPrimaryKeySelective(tripStandardPO);
        if (update < 0) {
            throw new BizException("编辑差旅标准失败");
        }
        return true;
    }

    public List<TripStandardPO> getCorpAllTripStandard(String corpId) {
        TripStandardPOExample tripStandardPOExample = new TripStandardPOExample();
        TripStandardPOExample.Criteria criteria = tripStandardPOExample.createCriteria().andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        List<TripStandardPO> tripStandardPOS = tripStandardPOMapper.selectByExample(tripStandardPOExample);
        if (CollectionUtils.isEmpty(tripStandardPOS)) {
            return Lists.newArrayList();
        }
        return tripStandardPOS;
    }

    @Transactional
    public Boolean deleteTripStandard(String corpId, Long standardId) {
        TripStandardPOExample tripStandardPOExample = new TripStandardPOExample();
        TripStandardPOExample.Criteria criteria = tripStandardPOExample.createCriteria().andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andIdEqualTo(standardId);

        TripStandardPO tripStandardPO = new TripStandardPO();
        tripStandardPO.setStatus(RowStatusEnum.DELETE.getStatus());

        int update = tripStandardPOMapper.updateByExampleSelective(tripStandardPO, tripStandardPOExample);
        if (update <= 0) {
            throw new BizException("删除差旅标准失败");
        }
        return true;
    }


}
