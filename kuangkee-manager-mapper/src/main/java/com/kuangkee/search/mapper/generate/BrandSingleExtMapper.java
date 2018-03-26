package com.kuangkee.search.mapper.generate;

import java.util.List;

import com.kuangkee.search.pojo.BrandSingle;

public interface BrandSingleExtMapper {

    /**
     * 批量插入
     */
    int insertBatchSelective(List<BrandSingle> records);

}