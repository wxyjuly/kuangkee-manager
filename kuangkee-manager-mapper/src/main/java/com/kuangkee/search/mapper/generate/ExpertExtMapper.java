package com.kuangkee.search.mapper.generate;

import java.util.List;

import com.kuangkee.common.pojo.req.ExpertBrands;
import com.kuangkee.common.pojo.req.ExpertReq;

public interface ExpertExtMapper {
	
	List<ExpertBrands> selectExpertBrands(ExpertReq record);
    
}