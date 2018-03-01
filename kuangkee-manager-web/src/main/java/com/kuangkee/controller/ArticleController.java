package com.kuangkee.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants;
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
		KuangkeeResult kuangkeeResult = articleSolrService.importAllArticles2Solr(null) ;
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
	public EUDataGridResult getArticleListBack(Integer page, Integer rows, ArticleReq record) {
		EUDataGridResult result = articleService.queryArticleListByPageBack(page, rows, record);
		return result;
	}
	
	/**
	 * articleInStock:文章可搜索. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/article/instock")
	@ResponseBody
	public KuangkeeResult articleInStock(@RequestParam(value="ids", defaultValue="0")String ids) {
		if(MatchUtil.isEmpty(ids)) {
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR); 
		}
		
		List<Long> idLists = new ArrayList<>() ;
		if(ids.indexOf(",")!=-1) {
			String [] tmpIds = ids.split(",") ;
			for (String tmp : tmpIds) {
				idLists.add(Long.parseLong(tmp)) ;
			}
		} else {
			idLists.add(Long.parseLong(ids)) ;
		}
		
		ArticleReq  record = new ArticleReq() ;
		record.setIsSearchable("1");
		record.setIdLists(idLists);
		KuangkeeResult result = articleService.updateArticlesByIds(record);
		
		
		importSolrInfos(record); //更新solr数据
		return result;
	}
	
	/**
	 * articleUnStock:文章不可搜索. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/article/unstock")
	@ResponseBody
	public KuangkeeResult articleUnStock(@RequestParam(value="ids", defaultValue="0")String ids) {
		if(MatchUtil.isEmpty(ids)) {
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR); 
		}
		
		List<Long> idLists = new ArrayList<>() ;
		if(ids.indexOf(",")!=-1) {
			String [] tmpIds = ids.split(",") ;
			for (String tmp : tmpIds) {
				idLists.add(Long.parseLong(tmp)) ;
			}
		} else {
			idLists.add(Long.parseLong(ids)) ;
		}
		
		ArticleReq  record = new ArticleReq() ;
		record.setIsSearchable("0");
		record.setIdLists(idLists);
		KuangkeeResult result = articleService.updateArticlesByIds(record);
		
		delSolrInfos(record); //删除solr对应数据
		return result;
	}
	
	/**
	 * articleUnStock:文章不可搜索. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/article/delete")
	@ResponseBody
	public KuangkeeResult articleDel(@RequestParam(value="ids", defaultValue="0")String ids) {
		if(MatchUtil.isEmpty(ids)) {
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR); 
		}
		
		List<Long> idLists = new ArrayList<>() ;
		if(ids.indexOf(",")!=-1) {
			String [] tmpIds = ids.split(",") ;
			for (String tmp : tmpIds) {
				idLists.add(Long.parseLong(tmp)) ;
			}
		} else {
			idLists.add(Long.parseLong(ids)) ;
		}
		
		ArticleReq  record = new ArticleReq() ;
		record.setIdLists(idLists);
		KuangkeeResult result = articleService.updateArticlesByIds(record);
		
		delSolrInfos(record); //删除solr对应数据
		
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
	public KuangkeeResult updateArtilce(ArticleReq req, String content) throws Exception {
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
	
	/**
	 * importSolrInfos:更新solr文章数据. <br/>
	 * @author Leon Xi
	 * @param record
	 */
	private void importSolrInfos(ArticleReq record) {
		try {
			log.info("更新solr数据："+record.getIdLists());
			ArticleReq req = new ArticleReq() ; //新建，避免搜索条件影响
			
			List<Long> idLists = record.getIdLists() ;  
			req.setIdLists(idLists);
			articleSolrService.importAllArticles2Solr(req) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * delSolrInfos:更新solr文章数据. <br/>
	 * @author Leon Xi
	 * @param record
	 */
	private void delSolrInfos(ArticleReq record) {
		try {
			log.info("更新solr数据："+record.getIdLists());
			ArticleReq req = new ArticleReq() ; //新建，避免搜索条件影响
			
			List<Long> idLists = record.getIdLists() ;  
			req.setIdLists(idLists);
			articleSolrService.delArticles2Solr(req) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
