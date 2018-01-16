package com.kuangkee.service ;

import java.util.List;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.search.pojo.Brand;

public interface IBrandService {

	/**
	 * getBrandListByPage:分页查询品牌. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getBrandListByPage(int page, int rows);
	
	/**
	 * getBrandById: 通过brandId获取Brand. <br/>
	 * @author Leon Xi
	 * @param brandId
	 * @return
	 */
	Brand getBrandById(Integer brandId) ;
	
	/**
	 * getAllBrand: 获取全部品牌. <br/>
	 * @author Leon Xi
	 * @return
	 */
	List<Brand> getAllBrand() ;
	
	/**
	 * insertItem: 增加一条数据. <br/>
	 * @author Leon Xi
	 * @param record
	 * @return
	 */
	KuangkeeResult insertBrand(Brand record) ;
	
	/**
	 * @deprecated Need not implements Now.
	 * insertBrandList: 批量增加Brand. <br/>
	 * @author Leon Xi
	 * @param brandList
	 * @return
	 */
	KuangkeeResult insertBrandList(List<Brand> brandList) ;
	
	/**
	 * updateBrandStatus: 更新Brand状态. <br/>
	 * @author Leon Xi
	 * @param brand
	 * @return
	 */
	KuangkeeResult updateBrandStatus(Brand record) ;
}
