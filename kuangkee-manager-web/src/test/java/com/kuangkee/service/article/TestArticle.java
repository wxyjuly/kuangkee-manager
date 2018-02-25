package com.kuangkee.service.article ;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kuangkee.common.pojo.KuangkeeResult;
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
		KuangkeeResult kuangkeeResult = articleSolrService.importAllArticles2Solr() ;
		System.out.println(kuangkeeResult);
	}
	
	/**
	 * testGetArtilceById:通过文章Id查询文章明细. <br/>
	 * @author Leon Xi
	 */
	@Test
	public void testGetArtilceById() {
		Article article = new Article(); 
		article.setArticleId(1);
		
		Article articleR = articleService.getArticleById(article.getArticleId()) ;
		System.err.println(articleR);
	}
	
}