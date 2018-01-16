package com.kuangkee.service.demo ;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestDemo {
	@Autowired
//	ItemService itemService ;

	/**
	 * 
	 * testQryById:Spring Junit标准写法. <br/>
	 * 修改后，MyBatis扫包会报错
	 * @author Leon Xi
	 */
	@Test
	public void testQryById() {
//		long itemId= 536563L;
//		TbItem item = itemService.getItemById(itemId) ;
////		TbItem getItemById(long itemId)
//		System.out.println(item.toString());
	}
	
}