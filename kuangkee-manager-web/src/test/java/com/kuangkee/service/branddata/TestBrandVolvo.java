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
import com.kuangkee.common.utils.excel.poi.builder.VolvoBuilder;
import com.kuangkee.common.utils.excel.poi.common.POICommon;
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
		buildVolvoBean() ;
	}

	@SuppressWarnings("unused")
	private void copyAndSaveRDB(List<BrandArticleImportBean> records) throws IOException {

		List<BrandVolvo> volvos = new ArrayList<>(records.size()) ;
		BrandVolvo volvo = null ;
		Long id = 0l;
		for (BrandArticleImportBean xlsBean : records) {
			id = IDUtils.genItemId();
			volvo = new BrandVolvo() ;
			BeanUtils.copyProperties(xlsBean, volvo);
			
			volvo.setId(id);
			volvo.setBrandId(VolvoBuilder.BRAND_ID);
			volvo.setBrandName(VolvoBuilder.BRAND_NAME);
			
			volvos.add(volvo) ;
		}
//		System.out.println(volvos);
		boolean result = brandVolvoService.saveAllData(volvos) ;
	}
	
	public List<BrandArticleImportBean> buildVolvoBean() throws IOException {
		
		int tmpMaxIdLen = 0 ;  //计算id最大长度
		int tmpMaxContentLen = 0 ; //计算内容最大长度
		
		List<BrandArticleImportBean> MIDBeans = null ;  
		List<BrandArticleImportBean> SIDPIDBeans = null ;
		List<BrandArticleImportBean> FMIBeans = null ;
		
		//获取三个Bean的Excel数据
		MIDBeans = VolvoBuilder.getImportBeanByPath(VolvoBuilder.MID_EXCEL_PATH) ;
		SIDPIDBeans = VolvoBuilder.getImportBeanByPath(VolvoBuilder.SIDPID_EXCEL_PATH) ;
		FMIBeans = VolvoBuilder.getImportBeanByPath(VolvoBuilder.FMI_EXCEL_PATH) ;
		
System.err.println("MIDBeans->" + MIDBeans.size());
System.err.println("SIDPIDBeans->" + SIDPIDBeans.size());
System.err.println("FMIBeans->" + FMIBeans.size());
System.out.println("FMIBeans count->"+ MIDBeans.size() * FMIBeans.size() * SIDPIDBeans.size() );
		int excelPlusCnt = MIDBeans.size() * FMIBeans.size() * SIDPIDBeans.size() ;
//		System.exit(0);
		BrandArticleImportBean buildBean = null ;
		// 循环遍历生成数据
		String errorCode = null ;
		String title = null ;
		
		int beanInitLen = 0 ;
		if (excelPlusCnt > VolvoBuilder.MAX_INIT_BEAN_SIZE) {
			beanInitLen = VolvoBuilder.MAX_INIT_BEAN_SIZE ;
		} else {
			beanInitLen = excelPlusCnt;
		}
		
		int loopCnt = excelPlusCnt / VolvoBuilder.MAX_INIT_BEAN_SIZE + 1 ;    //循环次数
		int lastDataSize = excelPlusCnt % VolvoBuilder.MAX_INIT_BEAN_SIZE ;  //最后一次数据大小
		List<BrandArticleImportBean> buildBeans = null ;
		
System.err.println("dataCnt:" + excelPlusCnt + ";loopTime:"+loopCnt+";lastDataSize:"+lastDataSize);
		
		int arrayListLoopCnt = 0 ; //计数器
		int clearCnt = 0 ; // ArrayList清除操作
		
			buildBeans = new ArrayList<>(beanInitLen) ;
			for (BrandArticleImportBean midBean : MIDBeans) {
				for (BrandArticleImportBean cidBean : SIDPIDBeans) {
					for (BrandArticleImportBean fmiBean : FMIBeans) {
						
						if(arrayListLoopCnt == beanInitLen) {
							arrayListLoopCnt = 0 ; //重置
							clearCnt ++ ;
System.out.println("---循环次数["+loopCnt+"]-----clear:" + clearCnt +"--beanInitLen:"+beanInitLen+"--");
							//:TODO 触发插入数据库动作@20180322

							copyAndSaveRDB(buildBeans);
							buildBeans.clear(); 
						}
						buildBean = new BrandArticleImportBean() ;
						
						String midErrorCode = midBean.getErrorCode() ;
						String cidErrorCode = cidBean.getErrorCode() ;
						String fmiErrorCode = fmiBean.getErrorCode() ;
						
						if(!MatchUtil.isEmpty(midErrorCode)
								&& !MatchUtil.isEmpty(cidErrorCode)
								&& !MatchUtil.isEmpty(fmiErrorCode)) {
//							if(!(!MatchUtil.isEmpty(midErrorCode)
//									&& !MatchUtil.isEmpty(cidErrorCode)
//									&& !MatchUtil.isEmpty(fmiErrorCode))) {
							errorCode =  midErrorCode /*+ POICommon.SEPARATOR */
									+ cidErrorCode /*+ POICommon.SEPARATOR*/ 
									+ fmiErrorCode;
//System.err.println("为空数据:"+cidBean+
//									"\n->"+fmiBean+"\n\n");
						} else {
							continue ; //跳过存在空值的数据
						}
						
						buildBean.setErrorCode(errorCode); //拼接Id
						
						String midTitle = midBean.getTitle() ;
						String cidTitle = cidBean.getTitle() ;
						String fmiTitle = fmiBean.getTitle() ;
						
						if(!MatchUtil.isEmpty(midTitle)
								&& !MatchUtil.isEmpty(cidTitle)
								&& !MatchUtil.isEmpty(fmiTitle)) {
//						if(!(!MatchUtil.isEmpty(midTitle)
//								&& !MatchUtil.isEmpty(cidTitle)
//								&& !MatchUtil.isEmpty(fmiTitle))) {
								title =  midTitle + POICommon.SEPARATOR 
										+ cidTitle + POICommon.SEPARATOR 
										+ fmiTitle;
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
//						System.out.println(buildBean);
						arrayListLoopCnt++ ;
					}
				}
			}
			copyAndSaveRDB(buildBeans); //保存最后一次
		
System.out.println("tmpMaxIdLen:"+tmpMaxIdLen+";tmpMaxContentLen:"+tmpMaxContentLen);
		return buildBeans ;
	}
	
}