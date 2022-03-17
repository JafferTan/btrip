package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.HotelLimitDO;
import com.jaffer.btrip.beans.entity.TripStandardPO;
import com.jaffer.btrip.beans.entity.TripStandardRQ;
import com.jaffer.btrip.enums.FlightLimitEnum;
import com.jaffer.btrip.enums.TrainLimitEnum;
import com.jaffer.btrip.util.BtripResult;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class TripStandardTest extends AbsTest{
    public static final String testCorpId = "btrip2ad044b586ce4a7bbfc9d152e7e54392";

    @Autowired
    private TripStandardService tripStandardService;
    @Test
    public void testCreate() {
        TripStandardRQ tripStandardRQ = new TripStandardRQ();
        tripStandardRQ.setTripStandardName("差旅标准111");
        tripStandardRQ.setCorpId(testCorpId);
        List<String> arr1 = new ArrayList<>();
        arr1.add(FlightLimitEnum.ECONOMY_CLASS.getName());
        List<String> arr2 = new ArrayList<>();
        arr2.add(TrainLimitEnum.BUSINESS_CLASS.getName());
        tripStandardRQ.setFlightLimitList(arr1);
        tripStandardRQ.setTrainLimitList(arr2);
        HotelLimitDO hotelLimitDO = new HotelLimitDO();
        hotelLimitDO.setLevelOneLimit(500);
        hotelLimitDO.setLevelTwoLimit(300);
        hotelLimitDO.setLevelThreeLimit(200);
        hotelLimitDO.setLevelFourLimit(150);
        tripStandardRQ.setHotelLimitDO(hotelLimitDO);

        BtripResult<Boolean> result = tripStandardService.addOrEditTripStandard(tripStandardRQ);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testEdit() {
        TripStandardRQ tripStandardRQ = new TripStandardRQ();
        tripStandardRQ.setTripStandId(1L);
        tripStandardRQ.setTripStandardName("差旅标准111");
        tripStandardRQ.setCorpId(testCorpId);
        List<String> arr1 = new ArrayList<>();
        arr1.add(FlightLimitEnum.ECONOMY_CLASS.getName());
        List<String> arr2 = new ArrayList<>();
        arr2.add(TrainLimitEnum.BUSINESS_CLASS.getName());
        tripStandardRQ.setFlightLimitList(arr1);
        tripStandardRQ.setTrainLimitList(arr2);
        HotelLimitDO hotelLimitDO = new HotelLimitDO();
        hotelLimitDO.setLevelOneLimit(10000);
        hotelLimitDO.setLevelTwoLimit(300);
        hotelLimitDO.setLevelThreeLimit(200);
        hotelLimitDO.setLevelFourLimit(150);
        tripStandardRQ.setHotelLimitDO(hotelLimitDO);
        BtripResult<Boolean> result = tripStandardService.addOrEditTripStandard(tripStandardRQ);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testSelect() {
        BtripResult<List<TripStandardPO>> corpAllTripStandard = tripStandardService.getCorpAllTripStandard(testCorpId);
        System.out.println(JSON.toJSONString(corpAllTripStandard));
    }

    @Test
    public void testDelete() {
        BtripResult<Boolean> result = tripStandardService.deleteTripStandard(testCorpId, 1L);
        System.out.println(JSON.toJSONString(result));
    }
}
