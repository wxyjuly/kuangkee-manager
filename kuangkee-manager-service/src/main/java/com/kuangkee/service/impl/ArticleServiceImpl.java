package com.kuangkee.service.impl ;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.ArticleReq;
import com.kuangkee.common.utils.DateTimeUtil;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants;
import com.kuangkee.search.mapper.ArticleMapper;
import com.kuangkee.search.pojo.Article;
import com.kuangkee.search.pojo.ArticleExample;
import com.kuangkee.service.IArticleService;

/**
 * 文章管理Service
 * ClassName: ArticleServiceImpl <br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class ArticleServiceImpl implements IArticleService {
	
	private static final Logger logger = LoggerFactory.getLogger("ArticleServiceImpl.class") ;

	@Autowired
	public ArticleMapper articleMapper ;

	@Override
	public EUDataGridResult getArticleListByPage(int page, int rows, ArticleReq record) {
		//添加分页
		PageHelper.startPage(page, rows);
		//查询商品列表
		ArticleExample example = buildArticleExample(record);
		
		List<Article> list = articleMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		
		result.setTotal(pageInfo.getTotal());
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
		ArticleExample example = new ArticleExample() ;
		ArticleExample.Criteria criteria = example.createCriteria() ;
		
		Integer articleId = record.getArticleId() ;
		String brandId = record.getBrandId() ; //品牌ID
		String brandName = record.getBrandName() ;
		String errorCode = record.getErrorCode() ;
		String title = record.getTitle() ;
		
		String subTitle = record.getSubTitle() ;
		String url = record.getUrl() ;
		String sourceUrl = record.getSourceUrl() ;
//		String content = record.getContent() ;
		String isSearchable = record.getIsSearchable() ;
		
		String creater = record.getCreater() ;
		String createrDesc = record.getCreaterDesc() ;
		String readTimes = record.getReadTimes() ;
		
		String startDate = record.getSearchStartDate() ;
		String endDate = record.getSearchEndDate() ;
		
		if(!MatchUtil.isEmpty(articleId)) {
			criteria.andArticleIdEqualTo(articleId) ;
		}
		if(!MatchUtil.isEmpty(brandId)) {
			criteria.andBrandIdEqualTo(brandId) ;
		}
		if(!MatchUtil.isEmpty(brandName)) {
			criteria.andBrandNameLike(brandName) ;
		}
		if(!MatchUtil.isEmpty(errorCode)) {
			criteria.andErrorCodeEqualTo(errorCode) ;
		}
		if(!MatchUtil.isEmpty(title)) {
			criteria.andTitleLike(title) ;
		}
		
		if(!MatchUtil.isEmpty(subTitle)) {
			criteria.andSubTitleLike(subTitle) ;
		}
		if(!MatchUtil.isEmpty(url)) {
			criteria.andUrlLike(url) ;
		}
		if(!MatchUtil.isEmpty(sourceUrl)) {
			criteria.andSourceUrlLike(sourceUrl) ;
		}
//		if(!MatchUtil.isEmpty(content)) {
//			criteria.andContentLike(content) ;
//		}
		if(!MatchUtil.isEmpty(isSearchable)) {
			criteria.andIsSearchableEqualTo(isSearchable) ;
		}
		
		if(!MatchUtil.isEmpty(creater)) {
			criteria.andCreaterLike(creater) ;
		}
		if(!MatchUtil.isEmpty(createrDesc)) {
			criteria.andCreaterDescLike(createrDesc) ;
		}
		if(!MatchUtil.isEmpty(readTimes)) {
			criteria.andReadTimesGreaterThan(readTimes) ;
		}
		
		//test
		if(!MatchUtil.isEmpty(startDate)) {
			criteria.andCreateTimeGreaterThan(DateTimeUtil.strToDate(startDate)) ;
		}
		if(!MatchUtil.isEmpty(endDate)) {
			criteria.andCreateTimeLessThan(DateTimeUtil.strToDate(endDate)) ;
		}
		return example;
	}

	@Override
	public List<Article> getAllArticle(ArticleReq record) {
		ArticleExample example = buildArticleExample(record) ;
		return articleMapper.selectByExample(example) ;
	}
	
	@Override
	public Article getArticleById(Integer articleId) {
		//添加查询条件
		ArticleExample example = new ArticleExample();
		ArticleExample.Criteria criteria = example.createCriteria() ;
		if(MatchUtil.isEmpty(articleId)) { //check param
			logger.error("getArticleById(Integer articleId)入参为空【articleId】->{}", articleId) ;
			return null ;
		}
		criteria.andArticleIdEqualTo(articleId) ;
		
		List<Article> list = articleMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Article item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public KuangkeeResult insertArticle(Article record) {
		int cnt = articleMapper.insert(record) ;
		
		if(cnt>0) {
			return KuangkeeResult.ok();
		} else {
			logger.error("insertArticle(Article record)->{},{},{}", 
					record, 
					Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG) ;
			
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG) ;
		}
	}

	@Override
	public KuangkeeResult insertArticleList(List<Article> ArticleList) {
		return null;
	}

	@Override
	public KuangkeeResult updateArticleStatus(Article record) {
		if(MatchUtil.isEmpty(record) 
				|| MatchUtil.isEmpty(record.getArticleId())) {
			logger.error("updateArticleStatus(Article record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR) ;
			
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR) ;
		}
		int cnt =  articleMapper.updateByPrimaryKey(record) ;
		
		if(cnt>0) {
			return KuangkeeResult.ok();
		} else {
			return KuangkeeResult.build(Constants.KuangKeeResultConst.SUC_CODE, "更新失败") ;
		}
	}

}
