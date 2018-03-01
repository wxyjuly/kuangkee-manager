package com.kuangkee.service.brand ;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.search.pojo.Brand;
import com.kuangkee.service.IBrandService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestBrand {
	
	@Autowired
	IBrandService brandService ;
	
	@Test
	public void testQryAllBrand() {
		List<Brand> brands = brandService.getAllBrand() ;
		for (Brand brand : brands) {
			System.err.println(brand.getId()
					+"->"+brand.getName()
					+"->"+brand.getOrders());
			
		}
	}
	
	@Test
	public void testGetBrandListByPage() {
		int page = 1 ;
		int rows = 10;
		EUDataGridResult result = brandService.getBrandListByPage(page, rows) ;
		if(MatchUtil.isEmpty(result)) {
			return ;
		}
		@SuppressWarnings("unchecked")
		List<Brand> brands = (List<Brand>) result.getRows() ;
		for (Brand brand : brands) {
			System.out.println(brand.getId()
					+"->"+brand.getName()
					+"->"+brand.getOrders());
		}
	}
	
}