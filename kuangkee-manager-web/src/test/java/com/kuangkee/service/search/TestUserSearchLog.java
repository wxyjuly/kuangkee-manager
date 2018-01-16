package com.kuangkee.service.search ;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.kuangkee.search.pojo.Brand;
import com.kuangkee.service.IBrandService;

/**
 * 测试用户搜索记录日志
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestUserSearchLog {
	
	@Autowired
	IBrandService brandService ;
	
	/**
	 * 按条件组合查询所有搜索记录，用于筛选和导出数据
	 */
	@Test
	public void testQryAllSearchLogByParams() {
		List<Brand> brands = brandService.getAllBrand() ;
		for (Brand brand : brands) {
			System.out.println(brand.getBrandId()+"->"+brand.getBrandName());
		}
	}

	/**
	 * 分页查询搜索日志
	 */
	@Test
	public void testQrySearchLogByPage() {
		Integer pageNum = 1 ;
		Integer pageSize = 10  ;
		PageHelper.startPage(pageNum, pageSize);
		
		
		List<Brand> brands = brandService.getAllBrand() ;
		for (Brand brand : brands) {
			System.out.println(brand.getBrandId()+"->"+brand.getBrandName());
		}
	}
	
	/**
	 * 记录搜索行为记录,插入一条
	 */
	@Test
	public void testInsertSearchLog() {
		List<Brand> brands = brandService.getAllBrand() ;
		for (Brand brand : brands) {
			System.out.println(brand.getBrandId()+"->"+brand.getBrandName());
		}
	}

}