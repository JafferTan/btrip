package com.jaffer.btrip.mapper;

import com.jaffer.btrip.beans.entity.FormDataPO;
import com.jaffer.btrip.beans.entity.FormDataPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FormDataPOMapper {
    long countByExample(FormDataPOExample example);

    int deleteByExample(FormDataPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FormDataPO record);

    int insertSelective(FormDataPO record);

    List<FormDataPO> selectByExample(FormDataPOExample example);

    FormDataPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FormDataPO record, @Param("example") FormDataPOExample example);

    int updateByExample(@Param("record") FormDataPO record, @Param("example") FormDataPOExample example);

    int updateByPrimaryKeySelective(FormDataPO record);

    int updateByPrimaryKey(FormDataPO record);
}