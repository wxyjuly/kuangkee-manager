package com.kuangkee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kuangkee.common.pojo.req.ExpertReq;
import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.search.mapper.artifact.ExpertExtMapper;
import com.kuangkee.search.mapper.generate.ExpertMapper;
import com.kuangkee.search.pojo.Expert;
import com.kuangkee.search.pojo.ExpertExample;
import com.kuangkee.search.pojo.ext.ExpertBrands;
import com.kuangkee.service.IExpertService;

/**
 * 专家管理 <br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * 
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class ExpertServiceImpl implements IExpertService {

	private static final Logger logger = LoggerFactory.getLogger("ExpertServiceImpl.class");

	@Autowired
	private ExpertMapper expertMapper ;
	
	@Autowired
	private ExpertExtMapper expertExtMapper ;

	@Override
	public SearchResult<Expert> getExpertListByPageFront(int page, int rows, ExpertReq record) {
		return null;
	}

	@Override
	public List<Expert> getExpertListByPageCommon(int page, int rows, ExpertReq record) {
		// 添加分页
		PageHelper.startPage(page, rows);
		ExpertExample example = buildExpertExample(record);
		List<Expert> list = expertMapper.selectByExampleWithBLOBs(example) ;
		return list;
	}
	
	private ExpertExample buildExpertExample(ExpertReq record) {
		ExpertExample example = new ExpertExample() ;
		example.setOrderByClause("cousult_times desc");
		return example;
	}
	
	@Override
	public Expert getExpertById(Long expertId) {
		// 添加查询条件
		ExpertExample example = new ExpertExample();
		ExpertExample.Criteria criteria = example.createCriteria();
		if (MatchUtil.isEmpty(expertId)) { // check param
			logger.error("getExpertById(Integer articleId)入参为空【expertId】->{}", expertId);
			return null;
		}
		criteria.andIdEqualTo(expertId) ;

		List<Expert> list = expertMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Expert item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public List<ExpertBrands> getExpertBrands(ExpertReq record) {
		return expertExtMapper.selectExpertBrands(record) ;
	}

}
