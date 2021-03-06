package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.grotek.pojo.DepartmentDic;
import com.grotek.pojo.PackType;

public interface PackTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pack_type
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pack_type
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    int insert(PackType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pack_type
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    int insertSelective(PackType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pack_type
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    PackType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pack_type
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    int updateByPrimaryKeySelective(PackType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pack_type
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    int updateByPrimaryKey(PackType record);
    
    List<PackType> getAllPackTypes();
    
    int checkName(@Param("name")String name);
}