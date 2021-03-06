package com.kuangkee.service.solr;

import java.util.List;

import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.search.pojo.Article;
/**
 * 处理数据库与solr之间的数据
 * ClassName: IArticleSolrService <br/>
 * date: 2018年1月23日 上午11:01:21 <br/>
 * @author Leon Xi
 * @version v1.0
 */
public interface IArticleSolrService {

	/**
	 * 
	 * importAllArticles:导入指定条件的文章数据到Solr. <br/>
	 * @author Leon Xi
	 * @return
	 */
	KuangkeeResult importAllArticles2Solr(ArticleReq req);
	
	/**
	 * 
	 * delArticles2Solr:删除指定Ids solr中文章数据. <br/>
	 * @author Leon Xi
	 * @return
	 */
	KuangkeeResult delArticles2Solr(ArticleReq req);
	
	/**
	 * 
	 * importArticles2SolrByList:导入集合中文章数据到Solr. <br/>
	 * @author Leon Xi
	 * @param articles
	 * @return
	 */
	KuangkeeResult importArticles2SolrByList(List<Article> articles);
}
