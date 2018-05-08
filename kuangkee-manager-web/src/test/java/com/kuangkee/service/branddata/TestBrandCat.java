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
import com.kuangkee.common.utils.check.MatchUtil;
import com.kuangkee.common.utils.excel.poi.builder.CATBuilder;
import com.kuangkee.common.utils.excel.poi.vo.BrandArticleImportBean;
import com.kuangkee.search.pojo.BrandVolvo;
import com.kuangkee.service.IBrandVolvoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestBrandCat {
	
	@Autowired
	IBrandVolvoService brandVolvoService ;
	
//	@Test
	public void testImportCat() throws IOException {
		buildCatBean(CATBuilder.MID_EXCEL_PATH) ;
		buildCatBean(CATBuilder.CID_EXCEL_PATH) ;
		buildCatBean(CATBuilder.FMI_EXCEL_PATH) ;
	}
	
	@Test
	public void testNull() {
		
	}

	@SuppressWarnings("unused")
	private void copyAndSaveRDB(List<BrandArticleImportBean> records,String path) throws IOException {

		List<BrandVolvo> volvos = new ArrayList<>(records.size()) ;
		BrandVolvo volvo = null ;
		String partId = getPartIdByPath(path) ;
		Long id = 0l;
		for (BrandArticleImportBean xlsBean : records) {
			id = IDUtils.genItemId();
			volvo = new BrandVolvo() ;
			BeanUtils.copyProperties(xlsBean, volvo);
			
			volvo.setId(id);
			volvo.setBrandId(CATBuilder.BRAND_ID);
			volvo.setBrandName(CATBuilder.BRAND_NAME);
			volvo.setPartid(partId);
			
			volvos.add(volvo) ;
		}
		boolean result = brandVolvoService.saveAllData(volvos) ;
	}
	
	public List<BrandArticleImportBean> buildCatBean(String partPathName) throws IOException {
		
		List<BrandArticleImportBean> MIDBeans = null ;  
		//获取三个Bean的Excel数据
		MIDBeans = CATBuilder.getImportBeanByPath(partPathName) ;
		List<BrandArticleImportBean> buildBeans = null ;
		BrandArticleImportBean buildBean ;
		String errorCode = "" ;
		String title = "" ;
		buildBeans = new ArrayList<>(5000) ;
		String partId = getPartIdByPath(partPathName) ;
		for (BrandArticleImportBean midBean : MIDBeans) {
			buildBean = new BrandArticleImportBean() ;
			String midErrorCode = midBean.getErrorCode() ;
			
			if(!MatchUtil.isEmpty(midErrorCode)) {
				errorCode =  midErrorCode ;
			} else {
				continue ; //跳过存在空值的数据
			}
			buildBean.setErrorCodeOriginal(errorCode) ; //拼接原始ID
			buildBean.setErrorCode(errorCode); //拼接Id
			
			String midTitle = midBean.getTitle() ;
			
			if(!MatchUtil.isEmpty(midTitle)) {
					title =  midTitle ;
			} else {
				continue ; //跳过存在空值的数据
			}
			buildBean.setPartId(partId);
			buildBean.setTitle(title);
			buildBeans.add(buildBean) ;
		}
		copyAndSaveRDB(buildBeans,partPathName); //保存最后一次
		
		return buildBeans ;
	}
	
	public static String getPartIdByPath(String path) {
		String ret = "1" ;
		if(CATBuilder.MID_EXCEL_PATH.equals(path)) {
			ret = "1" ; //部位1
		} else if(CATBuilder.CID_EXCEL_PATH.equals(path)) {
			ret = "2" ; //部位2
		} else if(CATBuilder.FMI_EXCEL_PATH.equals(path)) {
			ret = "3" ; //部位3
		}
		return ret ;
	}
	
}