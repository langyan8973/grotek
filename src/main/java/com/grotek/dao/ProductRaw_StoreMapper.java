package com.grotek.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProductRaw_Price;
import com.grotek.pojo.ProductRaw_Store;

public interface ProductRaw_StoreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_store
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_store
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    int insert(ProductRaw_Store record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_store
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    int insertSelective(ProductRaw_Store record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_store
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    ProductRaw_Store selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_store
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    int updateByPrimaryKeySelective(ProductRaw_Store record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productraw_store
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    int updateByPrimaryKey(ProductRaw_Store record);
    
    List<ProductRaw_Store> getProductRawStores(PageRequest pageable);
    
    int allCount();
    
    ProductRaw_Store getByPid(int pid);
}