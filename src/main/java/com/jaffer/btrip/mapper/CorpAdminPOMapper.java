package com.jaffer.btrip.mapper;

import com.jaffer.btrip.beans.entity.CorpAdminPO;
import com.jaffer.btrip.beans.entity.CorpAdminPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CorpAdminPOMapper {
    long countByExample(CorpAdminPOExample example);

    int deleteByExample(CorpAdminPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CorpAdminPO record);

    int insertSelective(CorpAdminPO record);

    List<CorpAdminPO> selectByExample(CorpAdminPOExample example);

    CorpAdminPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CorpAdminPO record, @Param("example") CorpAdminPOExample example);

    int updateByExample(@Param("record") CorpAdminPO record, @Param("example") CorpAdminPOExample example);

    int updateByPrimaryKeySelective(CorpAdminPO record);

    int updateByPrimaryKey(CorpAdminPO record);
}