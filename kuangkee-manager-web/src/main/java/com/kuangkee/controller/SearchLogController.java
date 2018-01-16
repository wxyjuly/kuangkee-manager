package com.kuangkee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuangkee.common.pojo.EUDataGridResult;
import com.kuangkee.common.pojo.req.UserSearchLogReq;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.service.IUserSearchLogService;

/**
 * 管理用户记录搜索
 * ClassName: SearchLogController <br/>
 * date: 2018年1月15日 下午11:31:53 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@RequestMapping("/search")
@Controller
public class SearchLogController {

	@Autowired
	private IUserSearchLogService userSearchLogService;
	
	/**
	 * Visit URL: 
	 * http://127.0.0.1:8080/kuangkee-manager/search/getUserSearchLogListByPage
	 * getUserSearchLogListByPage:查询用户所有数据. <br/>
	 * @author Leon Xi
	 * @param record
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getUserSearchLogListByPage")
	@ResponseBody
	public EUDataGridResult getUserSearchLogListByPage(
			UserSearchLogReq record,
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="rows", defaultValue="20") int rows) {
		if(MatchUtil.isEmpty(record)) {
			return new EUDataGridResult() ;
		}
		EUDataGridResult result = userSearchLogService.getUserSearchLogListByPage(page, rows, record) ;
		
		return result;
	}
/*	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		TaotaoResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}*/
}
