package com.kuangkee.service.solr.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.dao.solr.IArticleSolrSearchDao;
import com.kuangkee.search.pojo.Article;
import com.kuangkee.service.IArticleService;
import com.kuangkee.service.solr.IArticleSearchService;

/**
 * 搜索Service
 * ClassName: SearchServiceImpl <br/>
 * date: 2018年1月20日 下午3:49:53 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class ArticleSearchServiceImpl implements IArticleSearchService {

	@Autowired
	private IArticleSolrSearchDao articleSolrSearchDao;
	
	@Autowired
	private IArticleService articleService ;
	
	@Override
	public SearchResult<Article> searchArticleListFromSolrByPage(String qryStr, int page, int rows) throws Exception {
		//创建查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(qryStr);
		//设置分页
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		//设置默认搜素域
		query.set("df", "article_keywords");
		//设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("article_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		//执行查询
		SearchResult<Article> searchResult = articleSolrSearchDao.search(query);
		//计算查询结果总页数
		long recordCount = searchResult.getRecordCount();
		
		long pageCount = recordCount / rows;
		if (recordCount % rows > 0) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		searchResult.setRecordCount(recordCount);
		
		return searchResult;
	}

	@Override
	public Article qryArticleDetail(Article article) throws Exception {
		return articleService.getArticleById(article.getArticleId()) ;
	}

	@Override
	public SearchResult<Article> searchArticleListFromDBByPage(String errorCode, int page, int rows)
			throws Exception {

		ArticleReq record = new ArticleReq() ;
		record.setErrorCode(errorCode) ;
		
		return articleService.queryArticleListByPageFront(page, rows, record) ;
	}

}
