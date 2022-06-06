package com.jaffer.btrip.mapper;

import com.jaffer.btrip.beans.entity.TripInfoPO;
import com.jaffer.btrip.beans.entity.TripInfoPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TripInfoPOMapper {
    long countByExample(TripInfoPOExample example);

    int deleteByExample(TripInfoPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TripInfoPO record);

    int insertSelective(TripInfoPO record);

    List<TripInfoPO> selectByExample(TripInfoPOExample example);

    TripInfoPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TripInfoPO record, @Param("example") TripInfoPOExample example);

    int updateByExample(@Param("record") TripInfoPO record, @Param("example") TripInfoPOExample example);

    int updateByPrimaryKeySelective(TripInfoPO record);

    int updateByPrimaryKey(TripInfoPO record);
}