package com.kuangkee.service.brand ;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
			System.out.println(brand.getBrandId()+"->"+brand.getBrandName());
		}
	}

	/**
	 * 
	 * testQryById:Spring Junit标准写法. <br/>
	 *
	 * @author Leon Xi
	 */
//	@Test
//	public void testQryById() {
//		long itemId= 536563L;
//		TbItem item = itemService.getItemById(itemId) ;
////		TbItem getItemById(long itemId)
//		System.out.println(item.toString());
//	}
//	
//	@Test
//	public void testPageHelper() {
//		//创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		//从spring容器中获得Mapper的代理对象
//		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
//		//执行查询，并分页
//		TbItemExample example = new TbItemExample();
//		//分页处理
//		PageHelper.startPage(2, 10);
//		List<TbItem> list = mapper.selectByExample(example);
//		//取商品列表
//		for (TbItem tbItem : list) {
//			System.out.println(tbItem.getTitle());
//		}
//		//取分页信息
//		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
//		long total = pageInfo.getTotal();
//		System.out.println("共有商品："+ total);
//	}
}