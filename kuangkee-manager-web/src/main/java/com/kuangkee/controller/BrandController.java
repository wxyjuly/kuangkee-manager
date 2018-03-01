package com.kuangkee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.search.pojo.Brand;
import com.kuangkee.service.IBrandService;

/**
 * 品牌查询
 * ClassName: SearchLogController <br/>
 * date: 2018年1月15日 下午11:31:53 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Controller
public class BrandController {
	private static final Logger log = LoggerFactory.getLogger(BrandController.class) ;
	
	@Autowired
	IBrandService brandService ;
	
	/**
	 * getItemList:列表查询文章. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
//	@RequestMapping("/article/list")
//	@ResponseBody
//	public EUDataGridResult getItemList(Integer page, Integer rows, ArticleReq record) {
//		EUDataGridResult result = articleService.queryArticleListByPageBack(page, rows, record);
//		return result;
//	}
	
	/**
	 * 
	 * qryAllBrands:获取文章. <br/>
	 * @author Leon Xi
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/article/qryBrands", method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private List<Brand> qryAllBrands() throws Exception {
		return brandService.getAllBrand() ;
	}
	
	
	
}
