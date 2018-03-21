package com.kuangkee.service;

import java.util.List;

import com.kuangkee.common.pojo.req.ExpertBrands;
import com.kuangkee.common.pojo.req.ExpertReq;
import com.kuangkee.common.pojo.resp.ExpertResp;
import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.search.pojo.Expert;

public interface IExpertService {
	
	/**
	 * getExpertListByPageFront:分页查询文章,前台使用. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	SearchResult<Expert> getExpertListByPageFront(int page, int rows, ExpertReq record) ;
	
	/**
	 * getExpertListByPageCommon:通用方法. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	List<Expert> getExpertListByPageCommon(int page, int rows, ExpertReq record) ;

	/**
	 * getExpertById: 通过ExpertId获取Expert. <br/>
	 * @author Leon Xi
	 * @param ExpertId
	 * @return
	 */
	Expert getExpertById(Long ExpertId) ;
	
	/**
	 * getExpertBrands:通过专家Id，查询专家品牌. <br/>
	 * @author Leon Xi
	 * @param record
	 * @return
	 */
	List<ExpertBrands> getExpertBrands(ExpertReq record) ;
	
	/**
	 * 
	 * getExpertReq:获取专家列表和品牌数据. <br/>
	 * @author Leon Xi
	 * @return
	 */
	List<ExpertResp> getExpertReq(int page, int rows) ;
	
	boolean updateExpert(Expert record) ;
	
}
