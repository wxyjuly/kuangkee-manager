package com.kuangkee.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.common.utils.DateTimeUtil;
import com.kuangkee.common.utils.IDUtils;
import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants;
import com.kuangkee.search.mapper.generate.ArticleMapper;
import com.kuangkee.search.pojo.Article;
import com.kuangkee.search.pojo.ArticleExample;
import com.kuangkee.service.IArticleService;
import com.kuangkee.service.solr.IArticleSolrService;

/**
 * 文章管理Service ClassName: ArticleServiceImpl <br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * 
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class ArticleServiceImpl implements IArticleService {

	private static final Logger logger = LoggerFactory.getLogger("ArticleServiceImpl.class");

	@Autowired
	private ArticleMapper articleMapper ;
	
	@Autowired
	private IArticleSolrService articleSolrService ;
	

	@Override
	public List<Article> getArticleListByPageCommon(int page, int rows, ArticleReq record) {
		// 添加分页
		PageHelper.startPage(page, rows);
		ArticleExample example = buildArticleExample(record);
		// 查询文章列表
		example.setOrderByClause("update_time desc");
//		List<Article> list = articleMapper.selectByExample(example);
		List<Article> list = articleMapper.selectByExampleWithBLOBs(example) ;
		
		return list;
	}

	@Override
	public EUDataGridResult queryArticleListByPageBack(int page, int rows, ArticleReq record) {
		List<Article> list = getArticleListByPageCommon(page, rows, record);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());

		return result;
	}

	@Override
	public SearchResult<Article> queryArticleListByPageFront(int page, int rows, ArticleReq record) {
		SearchResult<Article> result = new SearchResult<>();
		List<Article> list = getArticleListByPageCommon(page, rows, record);
		// 创建一个返回值对象
		// 取记录总条数
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		result.setCurPage(page);
		result.setRecordCount(pageInfo.getTotal());
		result.setPageCount(pageInfo.getPageNum());

		result.setResult(list);

		return result;
	}

	/**
	 * 
	 * buildArticleExample:构造查询参数. <br/>
	 *
	 * @author Leon Xi
	 * @return
	 */
	private ArticleExample buildArticleExample(ArticleReq record) {
		ArticleExample example = new ArticleExample();
		if(!MatchUtil.isEmpty(record)) {
			ArticleExample.Criteria criteria = example.createCriteria();
			Long articleId = record.getArticleId();
			
			List<Long> idLists = record.getIdLists() ;
			
			String brandId = record.getBrandId(); // 品牌ID
			String brandName = record.getBrandName();
			String errorCode = record.getErrorCode();
			String title = record.getTitle();
	
			String subTitle = record.getSubTitle();
//			String url = record.getUrl();
			String sourceUrl = record.getSourceUrl();
			// String content = record.getContent() ;
			String isSearchable = record.getIsSearchable();
	
			String creater = record.getCreater();
			String createrDesc = record.getCreaterDesc();
			String readTimes = record.getReadTimes();
	
			String startDate = record.getSearchStartDate();
			String endDate = record.getSearchEndDate();
	
			if (!MatchUtil.isEmpty(articleId)) {
				criteria.andArticleIdEqualTo(articleId);
			}
			
			if (!MatchUtil.isEmpty(idLists)) {
				criteria.andArticleIdIn(idLists);
			}
			
			if (!MatchUtil.isEmpty(brandId)) {
				criteria.andBrandIdEqualTo(brandId);
			}
			if (!MatchUtil.isEmpty(brandName)) {
				criteria.andBrandNameLike(brandName);
			}
			if (!MatchUtil.isEmpty(errorCode)) {
				errorCode = errorCode.trim();
				criteria.andErrorCodeLike("%" + errorCode + "%");
			}
			if (!MatchUtil.isEmpty(title)) {
				criteria.andTitleLike(title);
			}
	
			if (!MatchUtil.isEmpty(subTitle)) {
				criteria.andSubTitleLike(subTitle);
			}
			if (!MatchUtil.isEmpty(sourceUrl)) {
				criteria.andSourceUrlLike(sourceUrl);
			}
			// if(!MatchUtil.isEmpty(content)) {
			// criteria.andContentLike(content) ;
			// }
			if (!MatchUtil.isEmpty(isSearchable)) {
				criteria.andIsSearchableEqualTo(isSearchable);
			}
	
			if (!MatchUtil.isEmpty(creater)) {
				criteria.andCreaterLike(creater);
			}
			if (!MatchUtil.isEmpty(createrDesc)) {
				criteria.andCreaterDescLike(createrDesc);
			}
			if (!MatchUtil.isEmpty(readTimes)) {
				criteria.andReadTimesGreaterThan(readTimes);
			}
	
			// test
			if (!MatchUtil.isEmpty(startDate)) {
				criteria.andCreateTimeGreaterThan(DateTimeUtil.strToDate(startDate));
			}
			if (!MatchUtil.isEmpty(endDate)) {
				criteria.andCreateTimeLessThan(DateTimeUtil.strToDate(endDate));
			}
		}
		return example;
	}

	@Override
	public List<Article> getAllArticle(ArticleReq record) {
		ArticleExample example = buildArticleExample(record);
		return articleMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Article getArticleById(Long articleId) {
		// 添加查询条件
		ArticleExample example = new ArticleExample();
		ArticleExample.Criteria criteria = example.createCriteria();
		if (MatchUtil.isEmpty(articleId)) { // check param
			logger.error("getArticleById(Integer articleId)入参为空【articleId】->{}", articleId);
			return null;
		}
		criteria.andArticleIdEqualTo(articleId);

		List<Article> list = articleMapper.selectByExampleWithBLOBs(example) ;
		if (list != null && list.size() > 0) {
			Article item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public KuangkeeResult insertArticle(Article record) {
		// 生成文章ID
		Long articleId = IDUtils.genItemId();
		record.setArticleId(articleId);
		// '文章状态，1-在Solr中显示，0-在solr中不显示',
		record.setIsSearchable("1");
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());

		int cnt = articleMapper.insertSelective(record);
		if (cnt > 0) {
			importArticle2Solr(record) ; //更新article到solr.
			return KuangkeeResult.ok();
		} else {
			logger.error("insertArticle(Article record)->{},{},{}", record, Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG);

			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG);
		}
	}

	@Override
	public KuangkeeResult updateArticle(Article record) {

		// '文章状态，1-在Solr中显示，0-在solr中不显示',
		record.setIsSearchable("1");
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());

		int cnt = articleMapper.updateByPrimaryKeySelective(record);
		if (cnt > 0) {
			importArticle2Solr(record) ; //更新article到solr.
			return KuangkeeResult.ok();
		} else {
			logger.error("updateArticle(Article record)->{},{},{}", record, Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_UPDATE_ERROR_MSG);

			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_UPDATE_ERROR_MSG);
		}
	}
	
	/**
	 * importArticle2Solr:若isSearchable为1，更新数据. <br/>
	 * @author Leon Xi
	 * @param record
	 */
	private void importArticle2Solr(Article record) {
		if(!MatchUtil.isEmpty(record)
				&& "1".equals(record.getIsSearchable())) { //若可更新，进行更新
			
			try {
				List<Article> articles = new ArrayList<>() ;
				articles.add(record) ;
				articleSolrService.importArticles2SolrByList(articles) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public KuangkeeResult insertArticleList(List<Article> ArticleList) {
		return null;
	}

	@Override
	public KuangkeeResult updateArticleStatus(Article record) {
		if (MatchUtil.isEmpty(record) || MatchUtil.isEmpty(record.getArticleId())) {
			logger.error("updateArticleStatus(Article record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR);

			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR);
		}
		int cnt = articleMapper.updateByPrimaryKey(record);

		if (cnt > 0) {
			return KuangkeeResult.ok();
		} else {
			return KuangkeeResult.build(Constants.KuangKeeResultConst.SUC_CODE, "更新失败");
		}
	}

	@Override
	public KuangkeeResult updateArticlesByIds(ArticleReq req) {
		if(MatchUtil.isEmpty(req) || MatchUtil.isEmpty(req.getIsSearchable())) {
			logger.error("updateArticlesByIds(Article record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR);

			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR);
		}
		Article record = new Article() ;
		BeanUtils.copyProperties(req, record) ;
		
		ArticleExample example = buildArticleIdsExample(req) ;
		int cnt = articleMapper.updateByExampleSelective(record, example) ;
		return KuangkeeResult.ok(cnt);
	}

	@Override
	public KuangkeeResult delArticleByIds(ArticleReq req) {
		
		if(MatchUtil.isEmpty(req) || MatchUtil.isEmpty(req.getIsSearchable())) {
			logger.error("delArticleByIds(Article record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR);
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR);
		}
		
		ArticleExample example = buildArticleIdsExample(req);
		int cnt = articleMapper.deleteByExample(example) ;
		return KuangkeeResult.ok(cnt);
	}

	private ArticleExample buildArticleIdsExample(ArticleReq req) {
		ArticleExample example = new ArticleExample() ;
		List<Long> idLists = req.getIdLists() ;
		if (!MatchUtil.isEmpty(idLists)) {
			ArticleExample.Criteria criteria = example.createCriteria() ;
			criteria.andArticleIdIn(idLists) ;
		}
		return example;
	}

}
