package com.kuangkee.service.solr;


import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.search.pojo.Article;

public interface IArticleSearchService {

	SearchResult<Article> search(String queryString, int page, int rows) throws Exception;
}
