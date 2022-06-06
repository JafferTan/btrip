package com.jaffer.btrip.mapper;

import com.jaffer.btrip.beans.entity.TripFormMappingPO;
import com.jaffer.btrip.beans.entity.TripFormMappingPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TripFormMappingPOMapper {
    long countByExample(TripFormMappingPOExample example);

    int deleteByExample(TripFormMappingPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TripFormMappingPO record);

    int insertSelective(TripFormMappingPO record);

    List<TripFormMappingPO> selectByExample(TripFormMappingPOExample example);

    TripFormMappingPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TripFormMappingPO record, @Param("example") TripFormMappingPOExample example);

    int updateByExample(@Param("record") TripFormMappingPO record, @Param("example") TripFormMappingPOExample example);

    int updateByPrimaryKeySelective(TripFormMappingPO record);

    int updateByPrimaryKey(TripFormMappingPO record);
}