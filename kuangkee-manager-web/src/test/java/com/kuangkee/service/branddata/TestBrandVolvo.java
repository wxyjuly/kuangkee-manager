package com.kuangkee.service.branddata ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kuangkee.common.utils.IDUtils;
import com.kuangkee.common.utils.excel.poi.builder.VolvoBuilder;
import com.kuangkee.common.utils.excel.poi.vo.BrandArticleImportBean;
import com.kuangkee.search.pojo.BrandVolvo;
import com.kuangkee.service.IBrandVolvoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestBrandVolvo {
	
	@Autowired
	IBrandVolvoService brandVolvoService ;
	
	@Test
	public void testImportAllData() throws IOException {
//		List<BrandArticleImportBean> records = VolvoBuilder.buildVolvoBean() ;
		List<BrandArticleImportBean> records = VolvoBuilder.getImportBeanByPath(VolvoBuilder.MID_EXCEL_PATH);
		List<BrandVolvo> volvos = new ArrayList<>(records.size()) ;
		BrandVolvo volvo = null ;
		Long id = 0l;
		for (BrandArticleImportBean xlsBean : records) {
			id = IDUtils.genItemId();
			volvo = new BrandVolvo() ;
			BeanUtils.copyProperties(xlsBean, volvo);
			volvo.setId(id);
			volvos.add(volvo) ;
		}
		boolean result = brandVolvoService.saveAllData(volvos) ;
		
		System.out.println(result);
	}
	
}