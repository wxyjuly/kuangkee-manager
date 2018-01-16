package com.kuangkee.service;

import java.util.List;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.search.pojo.Article;

public interface IArticleService {

	/**
	 * getArticleListByPage:分页查询品牌. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getArticleListByPage(int page, int rows, ArticleReq record);
	
	/**
	 * getAllArticle: 按条件查询全部用户搜索数据;用于导出. <br/>
	 * @author Leon Xi
	 * @return
	 */
	List<Article> getAllArticle(ArticleReq record) ;

	/**
	 * getArticleById: 通过ArticleId获取Article. <br/>
	 * @author Leon Xi
	 * @param ArticleId
	 * @return
	 */
	Article getArticleById(Integer ArticleId) ;
	
	/**
	 * insertItem: 增加一条数据. <br/>
	 * @author Leon Xi
	 * @param record
	 * @return
	 */
	KuangkeeResult insertArticle(Article record) ;
	
	/**
	 * @deprecated Need not implements Now.
	 * insertArticleList: 批量增加Article. <br/>
	 * @author Leon Xi
	 * @param ArticleList
	 * @return
	 */
	KuangkeeResult insertArticleList(List<Article> ArticleList) ;
	
	/**
	 * updateArticleStatus: 更新Article状态;搜索日志不能更新. <br/>
	 * @Deprecated 
	 * @author Leon Xi
	 * @param Article
	 * @return
	 */
	KuangkeeResult updateArticleStatus(Article record) ;
}
