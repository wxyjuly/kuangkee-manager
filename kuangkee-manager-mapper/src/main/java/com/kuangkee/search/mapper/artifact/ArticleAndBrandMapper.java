package com.kuangkee.search.mapper.artifact;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuangkee.search.pojo.Article;
import com.kuangkee.search.pojo.ArticleExample;

public interface ArticleAndBrandMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int countByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int deleteByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int deleteByPrimaryKey(Integer articleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int insert(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int insertSelective(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    List<Article> selectByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    Article selectByPrimaryKey(Integer articleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Tue Jan 23 10:12:38 CST 2018
     */
    int updateByPrimaryKey(Article record);
}