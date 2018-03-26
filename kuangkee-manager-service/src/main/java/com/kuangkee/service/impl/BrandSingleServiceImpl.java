package com.kuangkee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.search.mapper.generate.BrandSingleExtMapper;
import com.kuangkee.search.mapper.generate.BrandSingleMapper;
import com.kuangkee.search.pojo.BrandSingle;
import com.kuangkee.search.pojo.BrandSingleExample;
import com.kuangkee.service.IBrandSingleService;

/**
 * volvo品牌管理<br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class BrandSingleServiceImpl implements IBrandSingleService {

	private static final Logger logger = LoggerFactory.getLogger("BrandSingleServiceImpl.class");

	@Autowired
	private BrandSingleMapper brandSingleMapper ;
	
	@Autowired
	private BrandSingleExtMapper brandSingleExtMapper ;

	@Override
	public List<BrandSingle> qryBrandSingleListByPageCommon(int page, int rows, BrandSingle record) {
		// 添加分页
		PageHelper.startPage(page, rows);
		BrandSingleExample example = buildExpertExample(record);
		
		List<BrandSingle> list = brandSingleMapper.selectByExample(example) ;
		return list;
	}
	
	private BrandSingleExample buildExpertExample(BrandSingle record) {
		BrandSingleExample example = new BrandSingleExample() ;
		BrandSingleExample.Criteria criteria = example.createCriteria() ;
		
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
	public boolean saveAllData(List<BrandSingle> records) {
		int cnt = brandSingleExtMapper.insertBatchSelective(records) ;
		if(cnt>0) {
			return true ;
		} else {
			return false;
		}
	}

	@Override
	public List<BrandSingle> qryByType(BrandSingle record) {
		BrandSingleExample example = buildExpertExample(record);
		
		List<BrandSingle> list = null ;
		try {
			list = brandSingleMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
