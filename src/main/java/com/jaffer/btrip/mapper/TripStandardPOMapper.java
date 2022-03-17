package com.jaffer.btrip.mapper;

import com.jaffer.btrip.beans.entity.TripStandardPO;
import com.jaffer.btrip.beans.entity.TripStandardPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TripStandardPOMapper {
    long countByExample(TripStandardPOExample example);

    int deleteByExample(TripStandardPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TripStandardPO record);

    int insertSelective(TripStandardPO record);

    List<TripStandardPO> selectByExample(TripStandardPOExample example);

    TripStandardPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TripStandardPO record, @Param("example") TripStandardPOExample example);

    int updateByExample(@Param("record") TripStandardPO record, @Param("example") TripStandardPOExample example);

    int updateByPrimaryKeySelective(TripStandardPO record);

    int updateByPrimaryKey(TripStandardPO record);
}