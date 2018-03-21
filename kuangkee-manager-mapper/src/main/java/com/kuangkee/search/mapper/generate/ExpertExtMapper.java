package com.kuangkee.search.mapper.generate;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuangkee.common.pojo.req.ExpertBrands;
import com.kuangkee.common.pojo.req.ExpertReq;
import com.kuangkee.search.pojo.Expert;

public interface ExpertExtMapper {
	
	List<ExpertBrands> selectExpertBrands(ExpertReq record);
	
	int updateExpertExtBySelective(@Param("record") Expert record) ;
    
}