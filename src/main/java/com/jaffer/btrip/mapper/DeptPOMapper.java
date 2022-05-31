package com.jaffer.btrip.mapper;

import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.beans.entity.DeptPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
@Mapper
public interface DeptPOMapper {
    long countByExample(DeptPOExample example);

    int deleteByExample(DeptPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeptPO record);

    int insertSelective(DeptPO record);

    List<DeptPO> selectByExample(DeptPOExample example);

    DeptPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeptPO record, @Param("example") DeptPOExample example);

    int updateByExample(@Param("record") DeptPO record, @Param("example") DeptPOExample example);

    int updateByPrimaryKeySelective(DeptPO record);

    int updateByPrimaryKey(DeptPO record);

    List<Long> findSubDeptIdsByMask(@Param("corpId") String corpId, @Param("mask") String mask);
}