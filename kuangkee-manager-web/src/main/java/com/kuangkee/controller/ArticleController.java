package com.kuangkee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuangkee.common.pojo.KuangkeeResult;
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
	
}
