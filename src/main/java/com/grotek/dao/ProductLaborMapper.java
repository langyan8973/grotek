package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductLabor;

public interface ProductLaborMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlaborinfo
     *
     * @mbggenerated Thu Jul 14 16:28:49 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlaborinfo
     *
     * @mbggenerated Thu Jul 14 16:28:49 CST 2016
     */
    int insert(ProductLabor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlaborinfo
     *
     * @mbggenerated Thu Jul 14 16:28:49 CST 2016
     */
    int insertSelective(ProductLabor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlaborinfo
     *
     * @mbggenerated Thu Jul 14 16:28:49 CST 2016
     */
    ProductLabor selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlaborinfo
     *
     * @mbggenerated Thu Jul 14 16:28:49 CST 2016
     */
    int updateByPrimaryKeySelective(ProductLabor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlaborinfo
     *
     * @mbggenerated Thu Jul 14 16:28:49 CST 2016
     */
    int updateByPrimaryKey(ProductLabor record);
    
    List<ProductLabor> findProductLabors(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<ProductLabor> getProductLabors(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    int checkCode(String code);
}