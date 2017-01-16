package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.Employee;
import com.grotek.pojo.ProductRaw;
import com.grotek.pojo.ProductRaw_Store;

public interface ProductRawMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productrawinfo
     *
     * @mbggenerated Mon Jun 20 11:08:50 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productrawinfo
     *
     * @mbggenerated Mon Jun 20 11:08:50 CST 2016
     */
    int insert(ProductRaw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productrawinfo
     *
     * @mbggenerated Mon Jun 20 11:08:50 CST 2016
     */
    int insertSelective(ProductRaw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productrawinfo
     *
     * @mbggenerated Mon Jun 20 11:08:50 CST 2016
     */
    ProductRaw selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productrawinfo
     *
     * @mbggenerated Mon Jun 20 11:08:50 CST 2016
     */
    int updateByPrimaryKeySelective(ProductRaw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productrawinfo
     *
     * @mbggenerated Mon Jun 20 11:08:50 CST 2016
     */
    int updateByPrimaryKey(ProductRaw record);
    
    List<ProductRaw> findProductRaws(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
   
    
    List<ProductRaw> getProductRaws(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    int checkNameAndCode(@Param("name")String name,@Param("code")String code);
}