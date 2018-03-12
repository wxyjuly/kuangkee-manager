package com.kuangkee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuangkee.common.pojo.req.OrderReq;
import com.kuangkee.common.utils.IDUtils;
import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.search.mapper.generate.OrderMapper;
import com.kuangkee.search.pojo.Order;
import com.kuangkee.search.pojo.OrderExample;
import com.kuangkee.service.IOrderService;

/**
 * 订单管理 <br/>
 * date: 2018年1月7日 下午7:43:25 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Service
public class OrderServiceImpl implements IOrderService {

	private static final Logger log = LoggerFactory.getLogger("OrderServiceImpl.class");

	@Autowired
	private OrderMapper orderMapper ;
	
	@Override
	public SearchResult<Order> getOrderListByPageFront(int page, int rows, OrderReq record) {
		SearchResult<Order> result = new SearchResult<>() ;
		
		List<Order> orders = getOrderListByPageCommon(page, rows, record) ;
		
		PageInfo<Order> pageInfo = new PageInfo<>(orders);
		result.setResult(orders);
		result.setCurPage(pageInfo.getPageNum());
		result.setPageCount(pageInfo.getPageSize());
		result.setRecordCount(pageInfo.getTotal());
		result.setPageSize(pageInfo.getPageSize());
		
		return result ;
	}
	
	private OrderExample buildOrderExample(OrderReq record) {
		OrderExample example = new OrderExample() ;
		example.setOrderByClause("create_date desc,update_date desc");
		return example;
	}
	
	@Override
	public List<Order> getOrderListByPageCommon(int page, int rows, OrderReq record) {
		// 添加分页
		PageHelper.startPage(page, rows);
		OrderExample example = buildOrderExample(record);
		List<Order> list = orderMapper.selectByExample(example);
		return list;
	}

	@Override
	public Order getOrderById(Long orderId) {
		// 添加查询条件
		OrderExample example = new OrderExample();
		OrderExample.Criteria criteria = example.createCriteria();
		if (MatchUtil.isEmpty(orderId)) { // check param
			log.error("getOrderById(Integer orderId)入参为空【orderId】->{}", orderId);
			return null;
		}
		criteria.andIdEqualTo(orderId) ;

		List<Order> list = orderMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Order item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public Order saveOrderById(Order order) {
		Long orderId = IDUtils.genItemId();
		order.setId(orderId) ;
		log.info("saveOrderById(order)->{}",order);
		int i= orderMapper.insertSelective(order) ;
		if(i>0) {
			return order;
		}
		return null;
	}

}
