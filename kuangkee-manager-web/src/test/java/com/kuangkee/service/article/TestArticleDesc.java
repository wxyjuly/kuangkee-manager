package com.kuangkee.service.article ;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kuangkee.search.pojo.Brand;
import com.kuangkee.service.IBrandService;

/**
 * 测试文章明细大字段
 * 第一版数据量不大，全部通过Article表完成。后续数据量上去后，再考虑拆分
 * @deprecated  
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml" })
public class TestArticleDesc {
//	@Autowired
//	ItemService itemService ;
	
	@Autowired
	IBrandService brandService ;
	
	@Test
	public void testQryAllBrand() {
		List<Brand> brands = brandService.getAllBrand() ;
		for (Brand brand : brands) {
			System.out.println(brand.getBrandId()+"->"+brand.getBrandName());
		}
	}

	/**
	 * 
	 * testQryById:Spring Junit鏍囧噯鍐欐硶. <br/>
	 *
	 * @author Leon Xi
	 */
//	@Test
//	public void testQryById() {
//		long itemId= 536563L;
//		TbItem item = itemService.getItemById(itemId) ;
////		TbItem getItemById(long itemId)
//		System.out.println(item.toString());
//	}
//	
//	@Test
//	public void testPageHelper() {
//		//鍒涘缓涓�涓猻pring瀹瑰櫒
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		//浠巗pring瀹瑰櫒涓幏寰桵apper鐨勪唬鐞嗗璞�
//		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
//		//鎵ц鏌ヨ锛屽苟鍒嗛〉
//		TbItemExample example = new TbItemExample();
//		//鍒嗛〉澶勭悊
//		PageHelper.startPage(2, 10);
//		List<TbItem> list = mapper.selectByExample(example);
//		//鍙栧晢鍝佸垪琛�
//		for (TbItem tbItem : list) {
//			System.out.println(tbItem.getTitle());
//		}
//		//鍙栧垎椤典俊鎭�
//		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
//		long total = pageInfo.getTotal();
//		System.out.println("鍏辨湁鍟嗗搧锛�"+ total);
//	}
}