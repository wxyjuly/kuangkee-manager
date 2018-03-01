package com.kuangkee.dao.solr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.dao.solr.IArticleSolrSearchDao;
import com.kuangkee.search.pojo.Article;

/**
 * 文章搜索Dao,从Solr中搜索数据
 * ClassName: SearchDaoImpl <br/>
 * date: 2018年1月20日 下午12:18:08 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Repository
public class ArticleSolrSearchDaoImpl implements IArticleSolrSearchDao {
	
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult<Article> search(SolrQuery query) throws Exception {
		//返回值对象
		SearchResult<Article> result = new SearchResult<>();
		//根据查询条件查询索引库
		QueryResponse queryResponse = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		//文章列表
		List<Article> articles = new ArrayList<>();
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		//取文章列表
		Article article ;
		for (SolrDocument solrDocument : solrDocumentList) {
			//创建一文章对象
			article = new Article() ;
			
			String articleId = (String) solrDocument.get("id"); 
			if(MatchUtil.isEmpty(articleId)) {
				articleId = "0" ;
			}
			try {
				article.setArticleId(Long.parseLong(articleId)) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//取高亮显示的结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("article_title");
			String title = "";
			if (list != null && list.size()>0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("article_title");
			}
			article.setTitle(title);
			article.setErrorCode((String)solrDocument.get("article_error_code"));
			article.setSubTitle((String)solrDocument.get("article_sub_title"));
			article.setImgSearchSmall((String)solrDocument.get("article_img_search_small"));
			article.setImgContentSmall((String)solrDocument.get("article_img_content_small"));
			article.setImgContentBig((String)solrDocument.get("article_img_content_big"));
			article.setContent((String)solrDocument.get("article_content"));
			
			//添加的文章列表
			articles.add(article);
		}
		result.setResult(articles);
		return result;
	}

}
