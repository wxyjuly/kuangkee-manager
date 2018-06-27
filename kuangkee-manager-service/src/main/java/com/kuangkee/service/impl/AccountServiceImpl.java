package com.kuangkee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.dialect.oracle.ast.clause.FlashbackQueryClause;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.search.mapper.generate.AccountMapper;
import com.kuangkee.search.pojo.Account;
import com.kuangkee.search.pojo.AccountExample;
import com.kuangkee.service.IAccountService;

/**
 * 用户管理Service ClassName: AccountServiceImpl <br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class AccountServiceImpl implements IAccountService {

	private static final Logger logger = LoggerFactory.getLogger("AccountServiceImpl.class");

	@Autowired
	public AccountMapper accountMapper;

	@Override
	public Account getAccountByUId(Long uId) {

		// 添加查询条件
		AccountExample example = new AccountExample();
		AccountExample.Criteria criteria = example.createCriteria();
		if (MatchUtil.isEmpty(uId)) { // check param
			logger.error("getAccountByUId(Integer uId)入参为空【uId】->{}", uId);
			return null;
		}
		criteria.andIdEqualTo(uId);

		List<Account> list = accountMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Account item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public Account getAccountByAccountInfo(Account account) {
		String openId = null ;
		// 添加查询条件
		AccountExample example = new AccountExample();
		AccountExample.Criteria criteria = example.createCriteria();
		
		if (MatchUtil.isEmpty(account)
				|| MatchUtil.isEmpty(account.getOpenid())) { // check param
				
			logger.error("getAccountByAccountInfo(Integer openId)入参为空【openId】->{}", openId);
			return null;
		}
		
		openId = account.getOpenid() ;
		criteria.andOpenidEqualTo(openId) ;

		List<Account> list = accountMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Account item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public boolean saveAccountInfo(Account account) {
		
		int cnt = accountMapper.insertSelective(account) ;
		if(cnt>0) {
			return true ;
		}
		return false;
	}

	@Override
	public boolean updateAccountInfo(Account account) {
		
		AccountExample example = accountSelectiveExample(account);
		
		if(MatchUtil.isEmpty(example)) 
			return false ;
		
		int cnt = accountMapper.updateByExampleSelective(account, example);
		if(cnt>0) {
			return true ;
		}
		return false;
	}

	private AccountExample accountSelectiveExample(Account account) {
		AccountExample example = new AccountExample();
		AccountExample.Criteria criteria = example.createCriteria();
		
		if (MatchUtil.isEmpty(account)) { // check param
			logger.error("updateAccountInfo(Integer)入参为空【account】->{}", account);
			return null ;
		}
		
		String openId = account.getOpenid() ;
		String country = account.getCountry() ;
		String province = account.getProvince() ;
		String city = account.getCity() ;
		String groupId = account.getGroupid() ;
		
		if(!MatchUtil.isEmpty(openId)) {
			criteria.andOpenidEqualTo(openId) ;
		}
		if(!MatchUtil.isEmpty(country)) {
			criteria.andCountryEqualTo(country) ;
		}
		if(!MatchUtil.isEmpty(province)) {
			criteria.andProvinceEqualTo(province) ;
		}
		if(!MatchUtil.isEmpty(city)) {
			criteria.andCityEqualTo(city) ;
		}
		if(!MatchUtil.isEmpty(groupId)) {
			criteria.andGroupidEqualTo(groupId) ;
		}
		
		String headImgUrl = account.getHeadimgurl() ;
		String language = account.getLanguage() ;
		String nickName = account.getNickname() ;
		String phone = account.getPhone() ;
		String remark = account.getRemark() ;	
		
		if(!MatchUtil.isEmpty(headImgUrl)) {
			criteria.andHeadimgurlEqualTo(headImgUrl) ;
		}
		if(!MatchUtil.isEmpty(language)) {
			criteria.andLanguageEqualTo(language) ;
		}
		if(!MatchUtil.isEmpty(nickName)) {
			criteria.andNicknameEqualTo(nickName) ;
		}
		if(!MatchUtil.isEmpty(phone)) {
			criteria.andPhoneEqualTo(phone) ;
		}
		if(!MatchUtil.isEmpty(remark)) {
			criteria.andRemarkEqualTo(remark) ;
		}
		
		String sex = account.getSex() ;
		String subscribe = account.getSubscribe() ;
		String subscribeTime = account.getSubscribeTime() ;
		String tagidList = account.getTagidList() ;
		String unionId = account.getUnionid() ;
				
		if(!MatchUtil.isEmpty(sex)) {
			criteria.andSexEqualTo(sex) ;
		}
		if(!MatchUtil.isEmpty(subscribe)) {
			criteria.andSubscribeEqualTo(subscribe) ;
		}
		if(!MatchUtil.isEmpty(subscribeTime)) {
			criteria.andSubscribeEqualTo(subscribeTime) ;
		}
		if(!MatchUtil.isEmpty(tagidList)) {
			criteria.andTagidListEqualTo(tagidList) ;
		}
		if(!MatchUtil.isEmpty(unionId)) {
			criteria.andUnionidEqualTo(unionId) ;
		}
		return example;
	}

}
