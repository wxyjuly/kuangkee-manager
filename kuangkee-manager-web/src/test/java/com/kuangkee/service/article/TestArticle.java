package com.kuangkee.service.article ;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.search.pojo.Article;
import com.kuangkee.service.IArticleService;
import com.kuangkee.service.solr.IArticleSolrService;

/**
 * 测试用户搜索日志
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestArticle {
	
	@Autowired
	IArticleSolrService articleSolrService ;
	
	@Autowired
	IArticleService articleService ;
	
	/**
	 * 批量查询并插入文章
	 */
	@Test
	public void testArticleImportAll2Solr() {
		KuangkeeResult kuangkeeResult = articleSolrService.importAllArticles2Solr(null) ;
		System.out.println(kuangkeeResult);
	}
	
	/**
	 * testGetArtilceById:通过文章Id查询文章明细. <br/>
	 * @author Leon Xi
	 */
	@Test
	public void testGetArtilceById() {
		Article article = new Article(); 
		article.setArticleId(151970492958748L);
		
		Article articleR = articleService.getArticleById(article.getArticleId()) ;
		System.err.println(articleR);
	}
	
	@Test
	public void testQryArticleListBack() {
		int page = 0 ;
		int rows = 30 ;
		ArticleReq record = null ;
		EUDataGridResult result = articleService.queryArticleListByPageBack(page, rows, record) ;
		System.err.println(result.getTotal());
	}
	
	@Test
	public void testQryArticleListCommon() {
		int page = 1 ;  //参数不能为0
		int rows = 30 ;
		ArticleReq record = new ArticleReq() ;
		record.setBrandId("1");
		List<Article> result = articleService.getArticleListByPageCommon(page, rows, record) ;
		printList(result) ;
	}
	
	public static void printList(List<Article> list) {
		for (Article article : list) {
			System.err.println("->"+article);
		}
	}
	
	@Test
	public void testUpdateArticleBatchStatus() {
		String ids = "151970492958748,151970495456105" ;
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
		
		record.setIsSearchable(null);
		KuangkeeResult result2 = articleSolrService.importAllArticles2Solr(record) ;
		
		System.err.println(result.getData());
	}
}