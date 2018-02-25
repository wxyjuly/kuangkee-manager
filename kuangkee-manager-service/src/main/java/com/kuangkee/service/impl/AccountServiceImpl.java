package com.kuangkee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
