package com.kuangkee.service.wechat;

import org.apache.http.HttpRequest;

import com.kuangkee.common.pojo.common.wechat.AccessTokenInfo;
import com.kuangkee.common.pojo.common.wechat.WechatOpenId;
import com.kuangkee.common.pojo.common.wechat.WechatUserInfo;

/**
 * 微信服务接口
 * ClassName: IWechatService <br/>
 * date: 2018年6月25日 上午11:04:31 <br/>
 * @author Leon Xi
 * @version v1.0
 */
public interface IWechatService {
	
	/**
	 * 
	 * getAndRefreshAccessToken:获取或者刷新AccessToken. <br/>
	 * @author Leon Xi
	 * @param request
	 * @param type : 类型(1:session失效刷新，2：后台定时调度刷新)
	 * @return
	 */
	public AccessTokenInfo getAndRefreshAccessToken(HttpRequest request, String type) ;
	
	/**
	 * getUserOpenId:通过重定向code获取用户openId等信息. <br/>
	 * @author Leon Xi
	 * @param code
	 * @return
	 */
	public WechatOpenId getUserOpenId(String code) ;
	
	/**
	 * getUserInfo:获取用户微信基本信息. <br/>
	 * @author Leon Xi
	 * @param openId
	 * @param accessToken
	 * @return
	 */
	public WechatUserInfo getUserInfo(String openId, String accessToken) ;
	
}
