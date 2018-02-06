package com.kuangkee.service.solr.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants.KuangKeeResultConst;
import com.kuangkee.common.utils.exception.ExceptionUtil;
import com.kuangkee.search.mapper.generate.ArticleMapper;
import com.kuangkee.search.pojo.Article;
import com.kuangkee.service.IArticleService;
import com.kuangkee.service.solr.IArticleSolrService;


@Service
public class ArticleSolrServiceImpl implements IArticleSolrService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private IArticleService articleService ;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public KuangkeeResult importAllArticles2Solr() {
		try {
			
			//查询所有文章列表
			List<Article> list = articleMapper.selectByExample(null);
			if(MatchUtil.isEmpty(list)) {
				return KuangkeeResult.build(KuangKeeResultConst.ERROR_CODE, 
						KuangKeeResultConst.DB_QUERY_EMPTY_MSG);
			}
			//把文章信息写入索引库
			for (Article article : list) {
				//创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", article.getArticleId());
				document.setField("article_title", article.getTitle());
				document.setField("article_error_code", article.getErrorCode());
				document.setField("article_img_search_small", article.getImgSearchSmall());
				document.setField("article_img_content_small", article.getImgContentSmall());
				document.setField("article_img_content_big", article.getImgContentBig());
				document.setField("article_content", article.getContent());
				
				//写入索引库
				solrServer.add(document);
			}
			//提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return KuangkeeResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KuangkeeResult.ok();
	}

	@Override
	public KuangkeeResult importArticles2SolrByList(List<Article> articles) {
		
 		return null;
	}

}
