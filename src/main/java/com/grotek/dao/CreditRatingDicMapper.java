package com.grotek.dao;

import java.util.List;

import com.grotek.pojo.CreditRatingDic;

public interface CreditRatingDicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_rating_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_rating_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int insert(CreditRatingDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_rating_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int insertSelective(CreditRatingDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_rating_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    CreditRatingDic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_rating_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int updateByPrimaryKeySelective(CreditRatingDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_rating_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int updateByPrimaryKey(CreditRatingDic record);
    
    List<CreditRatingDic> getAll();
}