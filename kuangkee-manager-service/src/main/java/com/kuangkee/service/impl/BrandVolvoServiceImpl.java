package com.kuangkee.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kuangkee.common.utils.SearchResult;
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
	
	/**
	 * buildCartExactlyExample:拼接Cart字符编码. <br/>
	 * @author Leon Xi
	 * @param mid
	 * @param cid
	 * @param fmi
	 * @return
	 */
	private BrandVolvoExample buildCartExactlyExample(String mid, String cid, String fmi) {
		BrandVolvoExample example = new BrandVolvoExample() ;
		BrandVolvoExample.Criteria criteria = example.createCriteria() ;
		
		//不为空会查询
		if(!MatchUtil.isEmpty(mid)) {
			criteria.andErrorCodeEqualTo(mid);
			criteria.andPartidEqualTo("1") ;
		}
		if(!MatchUtil.isEmpty(cid)) {
			criteria.andErrorCodeEqualTo(cid);
			criteria.andPartidEqualTo("2") ;
		}
		if(!MatchUtil.isEmpty(fmi)) {
			criteria.andErrorCodeEqualTo(fmi);
			criteria.andPartidEqualTo("3") ;
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

	@Override
	public SearchResult<BrandVolvo> qryBrandCartByPart(String mid, String cid, String fmi) {
		
		SearchResult<BrandVolvo> result = new SearchResult<>();
		List<BrandVolvo> multiBrandCat = new ArrayList<>() ;
		
		List<BrandVolvo> list = null ;
		// part 01
		if(!MatchUtil.isEmpty(mid)) {
			BrandVolvoExample example = buildCartExactlyExample(mid, null, null) ;
			try {
				list = brandVolvoMapper.selectByExample(example);
				if(!MatchUtil.isEmpty(list)) {
					multiBrandCat.addAll(list) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		// part 02
		if(!MatchUtil.isEmpty(cid)) {
			BrandVolvoExample example = buildCartExactlyExample(null, cid, null) ;
			try {
				list = brandVolvoMapper.selectByExample(example);
				if(!MatchUtil.isEmpty(list)) {
					multiBrandCat.addAll(list) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		// part 03
		if(!MatchUtil.isEmpty(fmi)) {
			BrandVolvoExample example = buildCartExactlyExample(null, null, fmi) ;
			try {
				list = brandVolvoMapper.selectByExample(example);
				if(!MatchUtil.isEmpty(list)) {
					multiBrandCat.addAll(list) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		Collections.sort(multiBrandCat);
//		List<BrandVolvo> results = merge2One(multiBrandCat) ;
		List<BrandVolvo> results = buildPartId(multiBrandCat);
		result.setResult(results);
		result.setCurPage(1);
		result.setRecordCount(1);
		
		return result ;
	}
	
	/**
	 * 
	 * merge2One:多个结果合并成一个. <br/>
	 *
	 * @author Leon Xi
	 * @param multiBrandCat
	 * @return
	 */
	private List<BrandVolvo> merge2One(List<BrandVolvo> multiBrandCat) {
		String SEPARATOR = " -- " ;
		List<BrandVolvo> result = new ArrayList<>(1) ;
		BrandVolvo brandVolvo = new BrandVolvo() ; 
		
		for (BrandVolvo tmpData : multiBrandCat) {
			String partId = tmpData.getPartid() ;
			String title = tmpData.getTitle() ;
			if("1".equals(partId)) {
				brandVolvo.setErrorCode(partId+"(MID)");
//				brandVolvo.setTitle("[" + title + "]");
				brandVolvo.setTitle(title);
			}
			if("2".equals(partId)) {
				brandVolvo.setErrorCode(brandVolvo.getErrorCode() + ":"+partId+"(CID)");
//				brandVolvo.setTitle(brandVolvo.getTitle() + SEPARATOR + "[" + title + "]");
				brandVolvo.setTitle(brandVolvo.getTitle() + SEPARATOR + title);
			}
			if("3".equals(partId)) {
				brandVolvo.setErrorCode(brandVolvo.getErrorCode() + "-"+partId+"(FMI)");
//				brandVolvo.setTitle(brandVolvo.getTitle() + SEPARATOR + "[" + title + "]");
				brandVolvo.setTitle(brandVolvo.getTitle() + SEPARATOR + title );
			}
		}
		result.add(brandVolvo) ;
		return result ;
	}
	
	/**
	 * buildPartId:多个结果合并成一个. <br/>
	 * @author Leon Xi
	 * @param multiBrandCat
	 * @return
	 */
	private List<BrandVolvo> buildPartId(List<BrandVolvo> multiBrandCat) {
		List<BrandVolvo> results = new ArrayList<>(multiBrandCat.size()) ;
		
		for (BrandVolvo tmpData : multiBrandCat) {
			String partId = tmpData.getPartid() ;
			String errorCode = tmpData.getErrorCode() ;
			if("1".equals(partId)) {
				tmpData.setErrorCode(errorCode+"(MID)");
			}
			if("2".equals(partId)) {
				tmpData.setErrorCode(errorCode+"(CID)");
			}
			if("3".equals(partId)) {
				tmpData.setErrorCode(errorCode+"(FMI)");
			}
			results.add(tmpData) ;
		}
		return results ;
	}
	

}
