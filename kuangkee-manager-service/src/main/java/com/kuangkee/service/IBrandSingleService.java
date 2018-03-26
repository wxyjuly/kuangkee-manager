package com.kuangkee.service;

import java.util.List;

import com.kuangkee.search.pojo.BrandSingle;

public interface IBrandSingleService {

	/**
	 * saveAllData: 批量保存数据. <br/>
	 * @author Leon Xi
	 * @param volvos
	 * @return
	 */
	boolean saveAllData(List<BrandSingle> records) ;
	
	/**
	 * qryByType:(这里用一句话描述这个方法的作用). <br/>
	 * @author Leon Xi
	 * @param volvo
	 * @return
	 */
	List<BrandSingle> qryByType(BrandSingle record) ;
	
	/**
	 * qryBrandSingleListByPageCommon:分页查询. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	List<BrandSingle> qryBrandSingleListByPageCommon(int page, int rows, BrandSingle record) ;
	
}
