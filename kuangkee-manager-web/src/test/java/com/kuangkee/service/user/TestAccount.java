package com.kuangkee.service.user ;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kuangkee.search.pojo.Account;
import com.kuangkee.service.IAccountService;

/**
 * 测试用户搜索日志
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestAccount {
	
	@Autowired
	IAccountService accountService ;
	
	/**
	 * testGetAccountById:通过uId查询用户明细. <br/>
	 * @author Leon Xi
	 */
	@Test
	public void testGetAccountById() {
		Long uId = 1L ;
		
		Account accountRecord = accountService.getAccountByUId(uId) ;
		System.err.println(accountRecord);
	}
	
}