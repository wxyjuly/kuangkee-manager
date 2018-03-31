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
import com.kuangkee.common.utils.excel.poi.builder.BrandInfos;
import com.kuangkee.common.utils.excel.poi.builder.OneBrandBuilder;
import com.kuangkee.common.utils.excel.poi.common.POICommon;
import com.kuangkee.common.utils.excel.poi.vo.BrandArticleImportBean;
import com.kuangkee.search.pojo.BrandSingle;
import com.kuangkee.service.IBrandSingleService;

/**
 * 保存相关的品牌数据
 * ClassName: TestBrandSingle <br/>
 * date: 2018年3月26日 下午4:03:10 <br/>
 * @author Leon Xi
 * @version v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestBrandSingle {
	
	@Autowired
	IBrandSingleService brandSingleService ;
	
	@Test
	public void testImportBrandDouShan() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_DOUSHAN_ID) ;
	}
	
	@Test
	public void testImportBrandJiaTeng() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_JIATENG_ID) ;
	}
	
	@Test
	public void testImportBrandKaiSi() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_KAISI_ID) ;
	}
	
	@Test
	public void testImportBrandKangMingSi() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_KANGMINGSI_ID) ;
	}
	
	@Test
	public void testImportBrandRiLi() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_RILI_ID) ;
	}
	
	@Test
	public void testImportBrandSanYi() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_SANYI_ID) ;
	}
	
	//Error
	@Test
	public void testImportBrandShengang() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_SHENGGANG_ID) ;
	}
	
	@Test
	public void testImportBrandXianDai() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_XIANDAI_ID) ;
	}
	
	@Test
	public void testImportBrandXiaoSong() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_XIAOSONG_ID) ;
	}
	@Test
	public void testImportBrandXuGong() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_XUGONG_ID) ;
	}
	@Test
	public void testImportBrandZhuYou() throws IOException {
		buildSingleBean(OneBrandBuilder.BRAND_ZHUYOU_ID) ;
	}
	
	private boolean copyAndSaveRDB(List<BrandArticleImportBean> records,
			String brandId,String brandName) throws IOException {

		List<BrandSingle> brandSingles = new ArrayList<>(records.size()) ;
		BrandSingle brandSingle = null ;
		Long id = 0l;
		for (BrandArticleImportBean xlsBean : records) {
			id = IDUtils.genItemId();
			brandSingle = new BrandSingle() ;
			BeanUtils.copyProperties(xlsBean, brandSingle);
			brandSingle.setId(id);
			brandSingle.setBrandId(brandId);
			brandSingle.setBrandName(brandName);
			brandSingle.setPartid("");
			brandSingle.setErrorCodeOriginal(xlsBean.getErrorCodeOriginal());
			
			brandSingles.add(brandSingle) ;
		}
		return brandSingleService.saveAllData(brandSingles) ;
	}
	
	public List<BrandArticleImportBean> buildSingleBean(String brand) throws IOException {
		
		int tmpMaxIdLen = 0 ;  //计算id最大长度
		int tmpMaxContentLen = 0 ; //计算内容最大长度
		
		List<BrandArticleImportBean> brandSingles = null ;  
		BrandInfos brandInfos = new BrandInfos().generateBrandInfosByBrand(brand) ;
		//获取三个Bean的Excel数据
		brandSingles = OneBrandBuilder.getImportBeanByPath(brandInfos.getBrandPath()) ;
		
System.err.println("brandSingles->" + brandSingles.size());
		int excelPlusCnt = brandSingles.size() ;
		BrandArticleImportBean buildBean = null ;
		// 循环遍历生成数据
		String errorCode = null ;
		String title = null ;
		
		int beanInitLen = 0 ;
		if (excelPlusCnt > OneBrandBuilder.MAX_INIT_BEAN_SIZE) {
			beanInitLen = OneBrandBuilder.MAX_INIT_BEAN_SIZE ;
		} else {
			beanInitLen = excelPlusCnt;
		}
		
		int loopCnt = excelPlusCnt / OneBrandBuilder.MAX_INIT_BEAN_SIZE + 1 ;    //循环次数
		int lastDataSize = excelPlusCnt % OneBrandBuilder.MAX_INIT_BEAN_SIZE ;  //最后一次数据大小
		List<BrandArticleImportBean> buildBeans = null ;
		
System.err.println("dataCnt:" + excelPlusCnt + ";loopTime:"+loopCnt+";lastDataSize:"+lastDataSize);
		
		int arrayListLoopCnt = 0 ; //计数器
		int clearCnt = 0 ; // ArrayList清除操作
		
			buildBeans = new ArrayList<>(beanInitLen) ;
			for (BrandArticleImportBean singleBean : brandSingles) {
						if(arrayListLoopCnt == beanInitLen) {
							arrayListLoopCnt = 0 ; //重置
							clearCnt ++ ;
System.out.println("---循环次数["+loopCnt+"]-----clear:" + clearCnt +"--beanInitLen:"+beanInitLen+"--");
							
							copyAndSaveRDB(buildBeans, brandInfos.getBrandId(), brandInfos.getBrandName());
							buildBeans.clear(); 
						}
						buildBean = new BrandArticleImportBean() ;
						
						String midErrorCode = singleBean.getErrorCode() ;
						if(!MatchUtil.isEmpty(midErrorCode)) {
							errorCode =  midErrorCode  ; 
						} else {
							continue ; //跳过存在空值的数据
						}
						
						buildBean.setErrorCodeOriginal(errorCode);
						buildBean.setErrorCode(POICommon.trimNotNoOrAlph(errorCode)) ; //拼接errorCode
						String midTitle = singleBean.getTitle() ;
						
						if(!MatchUtil.isEmpty(midTitle)) {
								title =  midTitle ;
						} else {
							continue ; //跳过存在空值的数据
						}
						buildBean.setTitle(title);
						
						//count Max length
						if(tmpMaxIdLen < errorCode.length()) {
							tmpMaxIdLen = errorCode.length() ;
						}
						if(tmpMaxContentLen < title.length()) {
							tmpMaxContentLen = title.length() ;
						}
						
						buildBeans.add(buildBean) ;
						arrayListLoopCnt++ ;
			}
			copyAndSaveRDB(buildBeans, brandInfos.getBrandId(), brandInfos.getBrandName());	
		
System.out.println("tmpMaxIdLen:"+tmpMaxIdLen+";tmpMaxContentLen:"+tmpMaxContentLen);
		return buildBeans ;
	}
	
}