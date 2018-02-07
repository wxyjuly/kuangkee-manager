package com.kuangkee.service.solr;


import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.search.pojo.Article;

public interface IArticleSearchService {
	
	/**
	 * 
	 * search:查询Solr列表. <br/>
	 *
	 * @author Leon Xi
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	SearchResult<Article> search(String queryString, int page, int rows) throws Exception ;
	
	
	
	/**
	 * 
	 * qryArticleDetail:查询文章明细. <br/>
	 * @author Leon Xi
	 * @param article
	 * @return
	 * @throws Exception
	 */
	Article qryArticleDetail(Article article) throws Exception ;
}
