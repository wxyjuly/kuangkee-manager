package com.kuangkee.service;

import java.util.List;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.search.pojo.Article;

public interface IArticleService {

	/**
	 * getArticleListByPage:分页查询文章,后台使用. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult queryArticleListByPageBack(int page, int rows, ArticleReq record) ;
	
	/**
	 * getArticleListByPage:分页查询文章,前台使用. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	SearchResult<Article> queryArticleListByPageFront(int page, int rows, ArticleReq record) ;
	
	/**
	 * 
	 * getArticleListByPageCommon:通用方法. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	List<Article> getArticleListByPageCommon(int page, int rows, ArticleReq record) ;
	
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
	Article getArticleById(Long ArticleId) ;
	
	/**
	 * insertItem: 增加一条数据. <br/>
	 * @author Leon Xi
	 * @param record
	 * @return
	 */
	KuangkeeResult insertArticle(Article record) ;
	
	/**
	 * updateArticle:更新文章. <br/>
	 * @author Leon Xi
	 * @param record
	 * @return
	 */
	public KuangkeeResult updateArticle(Article record) ;
	
	/**
	 * updateArticlesByIds:通过Ids状态，更新数据. <br/>
	 * @author Leon Xi
	 * @param record
	 * @return
	 */
	public KuangkeeResult updateArticlesByIds(ArticleReq record) ;
	
	/**
	 * @deprecated Need not implements Now.
	 * insertArticleList: 批量增加Article. <br/>
	 * @author Leon Xi
	 * @param ArticleList
	 * @return
	 */
	KuangkeeResult insertArticleList(List<Article> ArticleList) ;
	
	/**
	 * @deprecated
	 * updateArticleStatus: 更新Article状态;搜索日志不能更新. <br/>
	 * @Deprecated 
	 * @author Leon Xi
	 * @param Article
	 * @return
	 */
	KuangkeeResult updateArticleStatus(Article record) ;
	
	/**
	 * delArticleByIds: ids批量删除. <br/>
	 * @Deprecated 
	 * @author Leon Xi
	 * @param Article
	 * @return
	 */
	KuangkeeResult delArticleByIds(ArticleReq req) ;
}
