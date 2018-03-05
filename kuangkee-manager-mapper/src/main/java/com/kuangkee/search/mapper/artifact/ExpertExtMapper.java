package com.kuangkee.search.mapper.artifact;

import java.util.List;

import com.kuangkee.common.pojo.req.ExpertReq;
import com.kuangkee.search.pojo.ext.ExpertBrandsExt;
import com.kuangkee.search.pojo.ext.ExpertExt;
import com.kuangkee.search.pojo.ext.ExpertExtExample;

public interface ExpertExtMapper {
	
	List<ExpertBrandsExt> selectExpertBrands(ExpertReq record);
	
    int countByExample(ExpertExtExample example);

    List<ExpertExt> selectByExampleWithBLOBs(ExpertExtExample example);
    
}