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
	
}
