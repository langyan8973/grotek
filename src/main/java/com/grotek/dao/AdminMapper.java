package com.grotek.dao;

import java.util.List;

import com.grotek.pojo.Admin;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    int updateByPrimaryKey(Admin record);
    
    Admin getByName(String name);
    
    List<Admin> getAll();
}