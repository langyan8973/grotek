package com.grotek.dao;

import java.util.List;

import com.grotek.pojo.ExsRbsmItem;

public interface ExsRbsmItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exs_rbsm_item
     *
     * @mbggenerated Mon Aug 22 10:10:07 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exs_rbsm_item
     *
     * @mbggenerated Mon Aug 22 10:10:07 CST 2016
     */
    int insert(ExsRbsmItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exs_rbsm_item
     *
     * @mbggenerated Mon Aug 22 10:10:07 CST 2016
     */
    int insertSelective(ExsRbsmItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exs_rbsm_item
     *
     * @mbggenerated Mon Aug 22 10:10:07 CST 2016
     */
    ExsRbsmItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exs_rbsm_item
     *
     * @mbggenerated Mon Aug 22 10:10:07 CST 2016
     */
    int updateByPrimaryKeySelective(ExsRbsmItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exs_rbsm_item
     *
     * @mbggenerated Mon Aug 22 10:10:07 CST 2016
     */
    int updateByPrimaryKey(ExsRbsmItem record);
    
    List<ExsRbsmItem> getByPid(int pid);
}