package com.kuangkee.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kuangkee.common.utils.json.JsonUtils;
import com.kuangkee.service.PictureService;

/**
 * 上传图片处理
 * ClassName: PictureController <br/>
 * date: 2018年2月27日 上午10:20:47 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile) {
		Map result = pictureService.uploadPicture(uploadFile);
		//为了保证功能的兼容性，需要把Result转换成json格式的字符串。
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
