package com.kuangkee.service;

import java.util.List;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.KuangkeeResult;
import com.kuangkee.common.pojo.req.UserSearchLogReq;
import com.kuangkee.search.pojo.UserSearchLog;

public interface IUserSearchLogService {

	/**
	 * getUserSearchLogListByPage:分页查询品牌. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getUserSearchLogListByPage(int page, int rows, UserSearchLogReq record);
	
	/**
	 * getAllUserSearchLog: 按条件查询全部用户搜索数据;用于导出. <br/>
	 * @author Leon Xi
	 * @return
	 */
	List<UserSearchLog> getAllUserSearchLog(UserSearchLogReq record) ;

	/**
	 * getUserSearchLogById: 通过UserSearchLogId获取UserSearchLog. <br/>
	 * @author Leon Xi
	 * @param UserSearchLogId
	 * @return
	 */
	UserSearchLog getUserSearchLogById(Integer UserSearchLogId) ;
	
	/**
	 * insertItem: 增加一条数据. <br/>
	 * @author Leon Xi
	 * @param record
	 * @return
	 */
	KuangkeeResult insertUserSearchLog(UserSearchLog record) ;
	
	/**
	 * @deprecated Need not implements Now.
	 * insertUserSearchLogList: 批量增加UserSearchLog. <br/>
	 * @author Leon Xi
	 * @param UserSearchLogList
	 * @return
	 */
	KuangkeeResult insertUserSearchLogList(List<UserSearchLog> UserSearchLogList) ;
	
	/**
	 * updateUserSearchLogStatus: 更新UserSearchLog状态;搜索日志不能更新. <br/>
	 * @Deprecated 
	 * @author Leon Xi
	 * @param UserSearchLog
	 * @return
	 */
	KuangkeeResult updateUserSearchLogStatus(UserSearchLog record) ;
}
