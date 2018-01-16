package com.kuangkee.service.impl ;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.UserSearchLogReq;
import com.kuangkee.common.utils.DateTimeUtil;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.constant.Constants;
import com.kuangkee.search.mapper.UserSearchLogMapper;
import com.kuangkee.search.pojo.UserSearchLog;
import com.kuangkee.search.pojo.UserSearchLogExample;
import com.kuangkee.service.IUserSearchLogService;

/**
 * 用户搜索管理Service
 * ClassName: UserSearchLogServiceImpl <br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class UserSearchLogServiceImpl implements IUserSearchLogService {
	
	private static final Logger logger = LoggerFactory.getLogger("UserSearchLogServiceImpl.class") ;

	@Autowired
	public UserSearchLogMapper userSearchLogMapper ;

	@Override
	public EUDataGridResult getUserSearchLogListByPage(int page, int rows, UserSearchLogReq record) {
		//添加分页
		PageHelper.startPage(page, rows);
		//查询商品列表
		UserSearchLogExample example = buildUserSearchLogExample(record);
		
		List<UserSearchLog> list = userSearchLogMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<UserSearchLog> pageInfo = new PageInfo<>(list);
		
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	/**
	 * 
	 * buildUserSearchLogExample:构造查询参数. <br/>
	 *
	 * @author Leon Xi
	 * @return
	 */
	private UserSearchLogExample buildUserSearchLogExample(UserSearchLogReq record) {
		UserSearchLogExample example = new UserSearchLogExample() ;
		UserSearchLogExample.Criteria criteria = example.createCriteria() ;
		
		String isMatch = record.getIsMatch() ; //是否有搜索结果
		Integer brandId = record.getBrandId() ; //品牌ID
		String searchContent = record.getSearchContent() ; //搜索内容
		String userName = record.getUserName() ; //用户姓名
		String phone = record.getPhone() ; //用户手机号
		String startDate = record.getSearchStartDate() ; //开始时间
		String endDate = record.getSearchEndDate() ; //结束时间
		
		if(!MatchUtil.isEmpty(isMatch)) {
			criteria.andIsMatchEqualTo(isMatch) ;
		}
		if(!MatchUtil.isEmpty(brandId)) {
			criteria.andBrandIdEqualTo(brandId) ;
		}
		if(!MatchUtil.isEmpty(searchContent)) {
			criteria.andSearchContentLike(searchContent) ;
		}
		if(!MatchUtil.isEmpty(userName)) {
			criteria.andUserNameLike(userName) ;
		}
		if(!MatchUtil.isEmpty(phone)) {
			criteria.andPhoneLike(phone) ;
		}
		//test
		if(!MatchUtil.isEmpty(startDate)) {
			criteria.andCreateTimeGreaterThan(DateTimeUtil.strToDate(startDate)) ;
		}
		if(!MatchUtil.isEmpty(endDate)) {
			criteria.andCreateTimeLessThan(DateTimeUtil.strToDate(endDate)) ;
		}
		return example;
	}

	@Override
	public List<UserSearchLog> getAllUserSearchLog(UserSearchLogReq record) {
		UserSearchLogExample example = buildUserSearchLogExample(record) ;
		return userSearchLogMapper.selectByExample(example) ;
	}
	
	@Override
	public UserSearchLog getUserSearchLogById(Integer userSearchLogId) {
		//添加查询条件
		UserSearchLogExample example = new UserSearchLogExample();
		UserSearchLogExample.Criteria criteria = example.createCriteria() ;
		if(MatchUtil.isEmpty(userSearchLogId)) { //check param
			logger.error("getUserSearchLogById(Integer UserSearchLogId)入参为空【userSearchLogId】->{}", userSearchLogId) ;
			return null ;
		}
		criteria.andLogIdEqualTo(userSearchLogId) ;
		
		List<UserSearchLog> list = userSearchLogMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			UserSearchLog item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public KuangkeeResult insertUserSearchLog(UserSearchLog record) {
		int cnt = userSearchLogMapper.insert(record) ;
		
		if(cnt>0) {
			return KuangkeeResult.ok();
		} else {
			logger.error("insertUserSearchLog(UserSearchLog record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG) ;
			
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.DB_INSERT_ERROR_MSG) ;
		}
	}

	@Override
	public KuangkeeResult insertUserSearchLogList(List<UserSearchLog> UserSearchLogList) {
		// TODO --Need implements
		return null;
	}

	@Override
	public KuangkeeResult updateUserSearchLogStatus(UserSearchLog record) {
		if(MatchUtil.isEmpty(record) 
				|| MatchUtil.isEmpty(record.getLogId())) {
			logger.error("updateUserSearchLogStatus(UserSearchLog record)->{},{}", Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR) ;
			
			return KuangkeeResult.build(Constants.KuangKeeResultConst.ERROR_CODE,
					Constants.KuangKeeResultConst.INPUT_PARAM_ERROR) ;
		}
		int cnt =  userSearchLogMapper.updateByPrimaryKey(record) ;
		
		if(cnt>0) {
			return KuangkeeResult.ok();
		} else {
			return KuangkeeResult.build(Constants.KuangKeeResultConst.SUC_CODE, "更新失败") ;
		}
	}

}
