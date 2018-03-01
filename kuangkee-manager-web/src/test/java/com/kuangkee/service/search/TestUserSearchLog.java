package com.kuangkee.service.search ;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.kuangkee.common.utils.DateTimeUtil;
import com.kuangkee.search.pojo.Brand;
import com.kuangkee.search.pojo.UserSearchLog;
import com.kuangkee.service.IBrandService;
import com.kuangkee.service.IUserSearchLogService;
import com.kuangkee.service.impl.UserSearchLogServiceImpl;

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
	
	@Autowired
	IUserSearchLogService userSearchLogService ;
	
	/**
	 * 按条件组合查询所有搜索记录，用于筛选和导出数据
	 */
	@Test
	public void testQryAllSearchLogByParams() {
	}

	/**
	 * 分页查询搜索日志
	 */
	@Test
	public void testQrySearchLogByPage() {
	}
	
	/**
	 * 记录搜索行为记录,插入一条
	 */
	@Test
	public void testInsertUserSearchLog() {
		UserSearchLog userSearchLog = new UserSearchLog() ;
		userSearchLog.setBrandId(003);
		userSearchLog.setBrandName("小松");
		userSearchLog.setIsMatch("0");
		userSearchLog.setLatitude("109.009");
		userSearchLog.setLongitude("120.009");
		userSearchLog.setOriginalContent("你好么#张三");
		userSearchLog.setSearchContent("挖掘机，牛逼啊");
		userSearchLog.setPhone("13608215714");
		userSearchLog.setStatus("1");
		userSearchLog.setUserId(1111);
		userSearchLog.setUserName("张三");
//		userSearchLog.setCreateTime(DateTimeUtil.strToDate("2018-02-06 11:28:36"));
		userSearchLog.setCreateTime(new Date());
		userSearchLog.setIp("127.0.99.121");
		
		userSearchLogService.insertUserSearchLog(userSearchLog) ;
	}

}