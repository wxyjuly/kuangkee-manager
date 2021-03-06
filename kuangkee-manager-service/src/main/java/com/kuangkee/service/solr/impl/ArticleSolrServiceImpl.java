package com.kuangkee.service.solr.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants.KuangKeeResultConst;
import com.kuangkee.common.utils.exception.ExceptionUtil;
import com.kuangkee.search.mapper.generate.ArticleMapper;
import com.kuangkee.search.pojo.Article;
import com.kuangkee.service.IArticleService;
import com.kuangkee.service.solr.IArticleSolrService;

@Service
public class ArticleSolrServiceImpl implements IArticleSolrService {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleSolrServiceImpl.class) ;

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private IArticleService articleService ;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public KuangkeeResult importAllArticles2Solr(ArticleReq req) {
		int cnt = 0 ;
		try {
			//查询所有文章列表
//			List<Article> list = articleMapper.selectByExampleWithBLOBs(null) ;
			List<Article> list = articleService.getAllArticle(req) ;
			if(MatchUtil.isEmpty(list)) {
				return KuangkeeResult.build(KuangKeeResultConst.ERROR_CODE, 
						KuangKeeResultConst.DB_QUERY_EMPTY_MSG);
			}
			cnt = list.size() ;
			log.info("List<Article> size-->{}", cnt);
			//把文章信息写入索引库
			for (Article article : list) {
				//创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", article.getArticleId());
				document.setField("article_title", article.getTitle());
				document.setField("article_error_code", article.getErrorCode());
				document.setField("article_sub_title", article.getSubTitle());
				document.setField("article_img_search_small", article.getImgSearchSmall());
				document.setField("article_img_content_small", article.getImgContentSmall());
				document.setField("article_img_content_big", article.getImgContentBig());
				document.setField("article_content", article.getContent());
				
				//写入索引库
				solrServer.add(document);
			}
			//提交修改
			solrServer.commit();
			log.info("List<Article> size:-->{}-->Committed...", cnt) ;
		} catch (Exception e) { 
			e.printStackTrace();
			return KuangkeeResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KuangkeeResult.ok("共更新了："+cnt+"数据！");
	}

	@Override
	public KuangkeeResult importArticles2SolrByList(List<Article> articles) {
		int cnt = 0 ;
		try {
			//查询所有文章列表
			if(MatchUtil.isEmpty(articles)) {
				return KuangkeeResult.build(KuangKeeResultConst.ERROR_CODE, 
						KuangKeeResultConst.DB_QUERY_EMPTY_MSG);
			}
			cnt = articles.size() ;
			log.info("List<Article> size-->{}", cnt);
			//把文章信息写入索引库
			for (Article article : articles) {
				//创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", article.getArticleId());
				document.setField("article_title", article.getTitle());
				document.setField("article_error_code", article.getErrorCode());
				document.setField("article_sub_title", article.getSubTitle());
				document.setField("article_img_search_small", article.getImgSearchSmall());
				document.setField("article_img_content_small", article.getImgContentSmall());
				document.setField("article_img_content_big", article.getImgContentBig());
				document.setField("article_content", article.getContent());
				
				//写入索引库
				solrServer.add(document);
			}
			//提交修改
			solrServer.commit();
			log.info("List<Article> size:-->{}-->Committed...", cnt) ;
		} catch (Exception e) { 
			e.printStackTrace();
			return KuangkeeResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KuangkeeResult.ok("共更新了："+cnt+"数据！");
	}

	@Override
	public KuangkeeResult delArticles2Solr(ArticleReq req) {
		int cnt = 0 ;
		try {
			//查询所有文章列表
			if(MatchUtil.isEmpty(req)||MatchUtil.isEmpty(req.getIdLists())) {
				return KuangkeeResult.build(KuangKeeResultConst.ERROR_CODE, 
						KuangKeeResultConst.DB_QUERY_EMPTY_MSG);
			}
			
			List<String> ids = transLong2StringList(req.getIdLists());
			cnt = ids.size() ;
			log.info("ids:{} ;size:-->{}-->before commit...", ids, cnt) ;
			//写入索引库
			
			solrServer.deleteById(ids) ;
			//提交修改
			solrServer.commit();
			
			log.info("ids:{} ;size:-->{}-->Committed...", ids, cnt) ;
		} catch (Exception e) { 
			e.printStackTrace();
			return KuangkeeResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KuangkeeResult.ok("共删了："+cnt+"数据！");
	}

	public List<String> transLong2StringList(List<Long> idLists){
		List<String> strList = new ArrayList<>() ;
		if (MatchUtil.isEmpty(idLists)) {
			return null ;
		} else {
			for (Long tmp : idLists) {
				if(!MatchUtil.isEmpty(tmp)) {
					strList.add(String.valueOf(tmp)) ;
				}
			}
		}
		return strList ;
	}
}
