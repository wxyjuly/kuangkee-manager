package com.kuangkee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants.KuangKeeResultConst;
import com.kuangkee.search.pojo.Article;
import com.kuangkee.service.IArticleService;
import com.kuangkee.service.solr.IArticleSolrService;

/**
 * 管理用户记录搜索
 * ClassName: SearchLogController <br/>
 * date: 2018年1月15日 下午11:31:53 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Controller
public class ArticleController {
	private static final Logger log = LoggerFactory.getLogger(ArticleController.class) ;
	
	@Autowired
	IArticleSolrService articleSolrService ;
	
	@Autowired
	IArticleService articleService ;
	
	/**
	 * Visit URL: 
	 * http://127.0.0.1:8080/kuangkee-manager-web/refreshSolrArticles
	 * refreshArticleImportAll2Solr: 更新solr缓存数据. <br/>
	 * @author Leon Xi
	 */
	@RequestMapping("/refreshSolrArticles")
	@ResponseBody
	public KuangkeeResult refreshArticleImportAll2Solr() {
		log.info("solr refreshArticleImportAll2Solr 更新开始...");
		KuangkeeResult kuangkeeResult = articleSolrService.importAllArticles2Solr() ;
		log.info("solr refreshArticleImportAll2Solr 更新完成...:{}", kuangkeeResult);
		return kuangkeeResult ;
	}
	
	/**
	 * getItemList:列表查询文章. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/article/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, ArticleReq record) {
		EUDataGridResult result = articleService.queryArticleListByPageBack(page, rows, record);
		return result;
	}
	
	/**
	 * 
	 * createArtilce:保存文章. <br/>
	 * @author Leon Xi
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/article/save", method=RequestMethod.POST)
	@ResponseBody
	private KuangkeeResult createArtilce(ArticleReq req, String content) throws Exception {
		Article record = new Article() ;
		if(MatchUtil.isEmpty(req)) {
			log.info("拷贝前:{}",req);
			return KuangkeeResult.build(KuangKeeResultConst.PARAM_ERROR_CODE, KuangKeeResultConst.INPUT_PARAM_ERROR);
		}
		BeanUtils.copyProperties(req, record) ;
		
		if (MatchUtil.isEmpty(record)) {
			log.info("拷贝后无数据:{}",record);
			return KuangkeeResult.build(KuangKeeResultConst.PARAM_ERROR_CODE, KuangKeeResultConst.INPUT_PARAM_ERROR);
		}
		KuangkeeResult result = articleService.insertArticle(record) ;
		return result;
	}
	
	/**
	 * createArtilce:更新文章. <br/>
	 * @author Leon Xi
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/article/update", method=RequestMethod.POST)
	@ResponseBody
	private KuangkeeResult updateArtilce(ArticleReq req, String content) throws Exception {
		Article record = new Article() ;
		if(MatchUtil.isEmpty(req)||
				MatchUtil.isEmpty(req.getArticleId())) {
			log.info("拷贝前:{}",req);
			return KuangkeeResult.build(KuangKeeResultConst.PARAM_ERROR_CODE, KuangKeeResultConst.INPUT_PARAM_ERROR);
		}
		BeanUtils.copyProperties(req, record) ;
		
		if (MatchUtil.isEmpty(record)) {
			log.info("拷贝后无数据:{}",record);
			return KuangkeeResult.build(KuangKeeResultConst.PARAM_ERROR_CODE, KuangKeeResultConst.INPUT_PARAM_ERROR);
		}
		KuangkeeResult result = articleService.updateArticle(record) ;
		return result;
	}
}
