package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProductBox_Price;

public interface ProductBox_PriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productbox_price
     *
     * @mbggenerated Mon Jul 04 14:53:39 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productbox_price
     *
     * @mbggenerated Mon Jul 04 14:53:39 CST 2016
     */
    int insert(ProductBox_Price record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productbox_price
     *
     * @mbggenerated Mon Jul 04 14:53:39 CST 2016
     */
    int insertSelective(ProductBox_Price record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productbox_price
     *
     * @mbggenerated Mon Jul 04 14:53:39 CST 2016
     */
    ProductBox_Price selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productbox_price
     *
     * @mbggenerated Mon Jul 04 14:53:39 CST 2016
     */
    int updateByPrimaryKeySelective(ProductBox_Price record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productbox_price
     *
     * @mbggenerated Mon Jul 04 14:53:39 CST 2016
     */
    int updateByPrimaryKey(ProductBox_Price record);
    
    List<ProductBox_Price> findProductBoxPrices(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<ProductBox_Price> getProductBoxPrices(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    ProductBox_Price getByPid(int pid);
}