package com.kuangkee.search.mapper.generate;

import com.kuangkee.search.pojo.BrandSingle;
import com.kuangkee.search.pojo.BrandSingleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BrandSingleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand_single_code
     *
     * @mbggenerated Mon Mar 26 21:20:35 CST 2018
     */
    int countByExample(BrandSingleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand_single_code
     *
     * @mbggenerated Mon Mar 26 21:20:35 CST 2018
     */
    int deleteByExample(BrandSingleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand_single_code
     *
     * @mbggenerated Mon Mar 26 21:20:35 CST 2018
     */
    int insert(BrandSingle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand_single_code
     *
     * @mbggenerated Mon Mar 26 21:20:35 CST 2018
     */
    int insertSelective(BrandSingle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand_single_code
     *
     * @mbggenerated Mon Mar 26 21:20:35 CST 2018
     */
    List<BrandSingle> selectByExample(BrandSingleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand_single_code
     *
     * @mbggenerated Mon Mar 26 21:20:35 CST 2018
     */
    int updateByExampleSelective(@Param("record") BrandSingle record, @Param("example") BrandSingleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand_single_code
     *
     * @mbggenerated Mon Mar 26 21:20:35 CST 2018
     */
    int updateByExample(@Param("record") BrandSingle record, @Param("example") BrandSingleExample example);
}