package com.kuangkee.search.mapper.generate;

import com.kuangkee.search.pojo.Account;
import com.kuangkee.search.pojo.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int countByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int deleteByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int insert(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int insertSelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    List<Account> selectByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    Account selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account
     *
     * @mbggenerated Sun Feb 25 09:52:42 CST 2018
     */
    int updateByPrimaryKey(Account record);
}