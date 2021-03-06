package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import com.grotek.pojo.UsedCar;

public interface UsedCarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table used_cars
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table used_cars
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    int insert(UsedCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table used_cars
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    int insertSelective(UsedCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table used_cars
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    UsedCar selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table used_cars
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    int updateByPrimaryKeySelective(UsedCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table used_cars
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    int updateByPrimaryKey(UsedCar record);
    
    List<UsedCar> findByName(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<UsedCar> getUsedCars(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    List<UsedCar> getByTiid(int tiid);
    
    int passByid(int id);
}