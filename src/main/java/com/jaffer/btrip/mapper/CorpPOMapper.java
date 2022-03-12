package com.jaffer.btrip.mapper;

import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.beans.entity.CorpPOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CorpPOMapper {
    long countByExample(CorpPOExample example);

    int deleteByExample(CorpPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CorpPO record);

    int insertSelective(CorpPO record);

    List<CorpPO> selectByExample(CorpPOExample example);

    CorpPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CorpPO record, @Param("example") CorpPOExample example);

    int updateByExample(@Param("record") CorpPO record, @Param("example") CorpPOExample example);

    int updateByPrimaryKeySelective(CorpPO record);

    int updateByPrimaryKey(CorpPO record);
}