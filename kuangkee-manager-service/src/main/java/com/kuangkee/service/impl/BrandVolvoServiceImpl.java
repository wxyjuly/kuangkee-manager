package com.kuangkee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.search.mapper.generate.BrandVolvoExtMapper;
import com.kuangkee.search.mapper.generate.BrandVolvoMapper;
import com.kuangkee.search.pojo.BrandVolvo;
import com.kuangkee.search.pojo.BrandVolvoExample;
import com.kuangkee.service.IBrandVolvoService;

/**
 * volvo品牌管理<br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class BrandVolvoServiceImpl implements IBrandVolvoService {

	private static final Logger logger = LoggerFactory.getLogger("ExpertServiceImpl.class");

	@Autowired
	private BrandVolvoMapper brandVolvoMapper ;
	
	@Autowired
	private BrandVolvoExtMapper brandVolvoExtMapper ;

	@Override
	public List<BrandVolvo> qryBrandVolvoListByPageCommon(int page, int rows, BrandVolvo record) {
		// 添加分页
		PageHelper.startPage(page, rows);
		BrandVolvoExample example = buildExpertExample(record);
		
		List<BrandVolvo> list = brandVolvoMapper.selectByExample(example) ;
		return list;
	}
	
	private BrandVolvoExample buildExpertExample(BrandVolvo record) {
		BrandVolvoExample example = new BrandVolvoExample() ;
		BrandVolvoExample.Criteria criteria = example.createCriteria() ;
		
		String errorCode = record.getErrorCode() ;
		String partId = record.getPartid() ;
		String title = record.getTitle() ;
		//不为空会查询
		if(!MatchUtil.isEmpty(errorCode)) {
			criteria.andErrorCodeLike("%"+errorCode+"%");
		}
		if(!MatchUtil.isEmpty(partId)) {
			criteria.andPartidEqualTo(partId);
		}
		if(!MatchUtil.isEmpty(title)) {
			criteria.andErrorCodeLike("%"+title+"%");
		}
		
		return example;
	}

	@Override
	public boolean saveAllData(List<BrandVolvo> records) {
		int cnt = brandVolvoExtMapper.insertBatchSelective(records) ;
		if(cnt>0) {
			return true ;
		} else {
			return false;
		}
	}

	@Override
	public List<BrandVolvo> qryByType(BrandVolvo record) {
		BrandVolvoExample example = buildExpertExample(record);
		
		List<BrandVolvo> list = null ;
		try {
			list = brandVolvoMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
