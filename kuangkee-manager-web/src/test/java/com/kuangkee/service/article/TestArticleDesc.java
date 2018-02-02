package com.kuangkee.service.article ;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kuangkee.search.pojo.Brand;
import com.kuangkee.service.IBrandService;


/**
 * 测试文章明细大字段
 * 第一版数据量不大，全部通过Article表完成。后续数据量上去后，再考虑拆分
 * @deprecated  
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestArticleDesc {
//	@Autowired
//	ItemService itemService ;
	
	@Autowired
	IBrandService brandService ;
	
	@Test
	public void testQryAllBrand() {
		List<Brand> brands = brandService.getAllBrand() ;
		for (Brand brand : brands) {
			System.out.println(brand.getBrandId()+"->"+brand.getBrandName());
		}
	}

}