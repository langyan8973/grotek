package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProductRaw;
import com.grotek.pojo.ProductRaw_Price;

public interface ProductRaw_PriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_price
     *
     * @mbggenerated Tue Jun 21 09:35:11 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_price
     *
     * @mbggenerated Tue Jun 21 09:35:11 CST 2016
     */
    int insert(ProductRaw_Price record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_price
     *
     * @mbggenerated Tue Jun 21 09:35:11 CST 2016
     */
    int insertSelective(ProductRaw_Price record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_price
     *
     * @mbggenerated Tue Jun 21 09:35:11 CST 2016
     */
    ProductRaw_Price selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_price
     *
     * @mbggenerated Tue Jun 21 09:35:11 CST 2016
     */
    int updateByPrimaryKeySelective(ProductRaw_Price record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_price
     *
     * @mbggenerated Tue Jun 21 09:35:11 CST 2016
     */
    int updateByPrimaryKey(ProductRaw_Price record);
    
    List<ProductRaw_Price> findProductRawPrices(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<ProductRaw_Price> getProductRawPrices(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    ProductRaw_Price getByPid(int pid);
}