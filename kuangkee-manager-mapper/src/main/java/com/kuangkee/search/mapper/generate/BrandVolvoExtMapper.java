package com.kuangkee.search.mapper.generate;

import java.util.List;

import com.kuangkee.search.pojo.BrandVolvo;

public interface BrandVolvoExtMapper {

    /**
     * 批量插入
     */
    int insertBatchSelective(List<BrandVolvo> records);

}