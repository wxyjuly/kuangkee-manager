package com.kuangkee.service;

import java.util.List;

import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.search.pojo.BrandVolvo;

public interface IBrandVolvoService {

	/**
	 * saveAllData: 批量保存数据. <br/>
	 * @author Leon Xi
	 * @param volvos
	 * @return
	 */
	boolean saveAllData(List<BrandVolvo> records) ;
	
	
	/**
	 * 
	 * qryByType:(这里用一句话描述这个方法的作用). <br/>
	 * @author Leon Xi
	 * @param volvo
	 * @return
	 */
	List<BrandVolvo> qryByType(BrandVolvo record) ;
	
	/**
	 * 
	 * qryBrandVolvoListByPageCommon:分页查询. <br/>
	 *
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	List<BrandVolvo> qryBrandVolvoListByPageCommon(int page, int rows, BrandVolvo record) ;
	
	/**
	 * 
	 * qryBrandCartByPart: 通过组合编码搜索内容. <br/>
	 * @author Leon Xi
	 * @param mid
	 * @param cid
	 * @param fmi
	 * @return
	 */
	SearchResult<BrandVolvo> qryBrandCartByPart(String mid, String cid, String fmi) ;
	
}
