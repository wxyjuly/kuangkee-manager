/**
 * Project Name:kuangkee-manager-service
 * File Name:WechatService.java
 * Package Name:com.kuangkee.service.wechat
 * Date:2018年6月25日上午11:24:59
 * Copyright (c) 2018, 【Leon Xi】 All Rights Reserved.
 *
*/

package com.kuangkee.service.wechat;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuangkee.common.pojo.common.wechat.AccessTokenInfo;
import com.kuangkee.common.pojo.common.wechat.WechatOpenId;
import com.kuangkee.common.pojo.common.wechat.WechatUserInfo;
import com.kuangkee.common.pojo.common.wechat.Wechat_Constants;
import com.kuangkee.common.utils.httpclient.HttpClientUtil;
import com.kuangkee.common.utils.json.JsonUtils;

/**
 * ClassName:WechatService <br/>
 * Date:     2018年6月25日 上午11:24:59 <br/>
 * @author   Leon Xi
 * @version  v1.0
 * @see 	 
 */
public class WechatService implements IWechatService {
	
	Logger log = LoggerFactory.getLogger(WechatService.class) ;

	@Override
	public AccessTokenInfo getAndRefreshAccessToken(HttpRequest request, String type) {
		AccessTokenInfo accessTokenInfo = new AccessTokenInfo() ;
		
		try {
			String accessTokenURL = Wechat_Constants.WECHAT_TOKEN_URL ;
			String data = HttpClientUtil.doPost(accessTokenURL) ;
			log.info(data);
			AccessTokenInfo accessToken = JsonUtils.jsonToPojo(data, AccessTokenInfo.class) ;
			log.info("AccessToken-data->{};{}", accessToken.getAccess_token() , accessToken.getExpires_in());
		} catch (Exception e) {
			accessTokenInfo = null ;
			log.info("{}"+e.toString());
		}
		return accessTokenInfo ;
	}

	@Override
	public WechatOpenId getUserOpenId(String code) {
		WechatOpenId openIdInfo= null ;
		try {
			String data = HttpClientUtil.doPost(Wechat_Constants.WECHAT_CODE_URL) ;
			openIdInfo = JsonUtils.jsonToPojo(data, WechatOpenId.class);
			log.info("wechant openId:{}", openIdInfo.getOpenId());
		} catch (Exception e) {
			openIdInfo = null ;
			log.info("{}"+e.toString());
		}
		return openIdInfo;
	}

	@Override
	public WechatUserInfo getUserInfo(String openId, String accessToken) {
		
		WechatUserInfo userInfo = null ;
		try {
			String openIdURL = Wechat_Constants.WECHAT_USERINFO_URL  
							 + "&access_token=" + accessToken 
					         + "&openid=" + openId ;
			String data = HttpClientUtil.doPost(openIdURL) ;
			log.info(data) ;
			
			userInfo = JsonUtils.jsonToPojo(data, WechatUserInfo.class) ;
		} catch (Exception e) {
			userInfo = null ;
			log.info("{}"+e.toString());
		}
		return userInfo;
	}

}

