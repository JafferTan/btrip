package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.TripFormDO;
import com.jaffer.btrip.beans.entity.TripInfo;
import com.jaffer.btrip.beans.entity.request.EvectionFormRQ;
import com.jaffer.btrip.enums.VehicleTypeEnum;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.util.BtripResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.crypto.Data;
import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class FormTest extends AbsTest {

    @Autowired
    private FormService formService;
    @Test
    public void testEvection() {
        EvectionFormRQ evectionFormRQ = this.buildEvectionFormRQ();
        BtripResult<Boolean> evectionForm = formService.createEvectionForm(evectionFormRQ);
        System.out.println(JSON.toJSONString(evectionForm));
    }

    private EvectionFormRQ buildEvectionFormRQ() {
        EvectionFormRQ evectionFormRQ = new EvectionFormRQ();
        evectionFormRQ.setCorpId("btrip31979f0b54204e64856d057054f9e1ce");
        evectionFormRQ.setUserId("btripdba7660ecec94ac192671ba41ae2e0b4");
        evectionFormRQ.setBizKey("evection");
        evectionFormRQ.setDeptId(6L);
        TripFormDO tripFormDO = new TripFormDO();
        tripFormDO.setTripReason("出差demo");
        tripFormDO.setFromTime(new Date());
        tripFormDO.setEndTime(new Date());
        tripFormDO.setDeparture("上海");
        tripFormDO.setDestination("杭州");

        TripInfo tripInfo = new TripInfo();
        tripInfo.setVehicleType(VehicleTypeEnum.FLIGHT.getType());
        tripInfo.setArrivalCity("杭州-余杭");
        tripInfo.setDepartureCity("上海-安亭");
        tripInfo.setDepartureTime(new Date());
        tripInfo.setArrivalTime(new Date());

        tripFormDO.getTripInfoList().add(tripInfo);

        evectionFormRQ.setTripFormDO(tripFormDO);

        return evectionFormRQ;
    }
}
