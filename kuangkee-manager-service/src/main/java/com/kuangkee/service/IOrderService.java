package com.kuangkee.service;

import java.util.List;

import com.kuangkee.common.pojo.req.OrderReq;
import com.kuangkee.common.utils.SearchResult;
import com.kuangkee.search.pojo.Order;

public interface IOrderService {
	
	/**
	 * getOrderListByPageFront:分页查询文章,前台使用. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @return
	 */
	SearchResult<Order> getOrderListByPageFront(int page, int rows, OrderReq record) ;
	
	/**
	 * getOrderListByPageCommon:通用方法，分页查询Order数据. <br/>
	 * @author Leon Xi
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	List<Order> getOrderListByPageCommon(int page, int rows, OrderReq record) ;

	/**
	 * getOrderById: 通过OrderId获取Order. <br/>
	 * @author Leon Xi
	 * @param orderId
	 * @return
	 */
	Order getOrderById(Long orderId) ;
	
	/**
	 * 
	 * saveOrderById:保存用户订单数据. <br/>
	 * @author Leon Xi
	 * @param order
	 * @return
	 */
	Order saveOrderById(Order order) ;
	
}
