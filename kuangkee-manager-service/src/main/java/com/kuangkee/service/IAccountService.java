package com.kuangkee.service;

import com.kuangkee.search.pojo.Account;

public interface IAccountService {


	/**
	 * getAccountByUId: 通过uId获取Account. <br/>
	 * @author Leon Xi
	 * @param ArticleId
	 * @return
	 */
	Account getAccountByUId(Long uId) ;
	
	/**
	 * getAccountByAccountInfo:通过Account Info查询(openId)Account信息. <br/>
	 * @author Leon Xi
	 * @param account
	 * @return
	 */
	Account getAccountByAccountInfo(Account account) ;
	
	/**
	 * saveAccountInfo:保存用户信息. <br/>
	 * @author Leon Xi
	 * @param account
	 * @return
	 */
	boolean saveAccountInfo(Account account) ;
	
	/**
	 * updateAccountInfo:更新用户信息. <br/>
	 * @author Leon Xi
	 * @param account
	 * @return
	 */
	boolean updateAccountInfo(Account account) ;
	
}
