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
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants;
import com.kuangkee.search.mapper.BrandMapper;
import com.kuangkee.search.pojo.Brand;
import com.kuangkee.search.pojo.BrandExample;
import com.kuangkee.service.IBrandService;

/**
 * 品牌管理Service
 * ClassName: BrandServiceImpl <br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class BrandServiceImpl implements IBrandService {
	
	private static final Logger logger = LoggerFactory.getLogger("BrandServiceImpl.class") ;

	@Autowired
	public BrandMapper brandMapper ;

	@Override
	public EUDataGridResult getBrandListByPage(int page, int rows) {
		//查询商品列表
		BrandExample example = new BrandExample();
		//分页处理
		PageHelper.startPage(page, rows);
		
		List<Brand> list = brandMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<Brand> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public Brand getBrandById(Integer brandId) {
		//添加查询条件
		BrandExample example = new BrandExample();
		BrandExample.Criteria criteria = example.createCriteria() ;
		if(MatchUtil.isEmpty(brandId)) { //check param
			logger.error("getBrandById(Integer brandId)入参为空(->{brandId}", brandId) ;
			return null ;
		}
		criteria.andBrandIdEqualTo(brandId);
		
		List<Brand> list = brandMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Brand item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public KuangkeeResult insertBrand(Brand record) {
		int cnt = brandMapper.insert(record) ;
		
		if(cnt>0) {
			return KuangkeeResult.ok();
		} else {
			logger.error("insertBrand(Brand record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG) ;
			
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG) ;
		}
	}

	@Override
	public KuangkeeResult insertBrandList(List<Brand> brandList) {
		// TODO --Need implements
		return null;
	}

	@Override
	public KuangkeeResult updateBrandStatus(Brand record) {
		if(MatchUtil.isEmpty(record) 
				|| MatchUtil.isEmpty(record.getBrandId())) {
			logger.error("updateBrandStatus(Brand record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR) ;
			
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR) ;
		}
		int cnt =  brandMapper.updateByPrimaryKey(record) ;
		
		if(cnt>0) {
			return KuangkeeResult.ok();
		} else {
			return KuangkeeResult.build(500, "更新失败") ;
		}
	}

	@Override
	public List<Brand> getAllBrand() {
		BrandExample example = new BrandExample() ;
		return brandMapper.selectByExample(example) ;
	}

}
