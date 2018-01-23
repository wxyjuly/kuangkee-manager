package com.kuangkee.dao.solr;

import org.apache.solr.client.solrj.SolrQuery;

import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.search.pojo.Article;

public interface IArticleSolrSearchDao {
	/**
	 * 
	 * search: 文章搜索Dao,从Solr中搜索数据. <br/>
	 *
	 * @author Leon Xi
	 * @param query
	 * @return
	 * @throws Exception
	 */
	SearchResult<Article> search(SolrQuery query) throws Exception;
}
