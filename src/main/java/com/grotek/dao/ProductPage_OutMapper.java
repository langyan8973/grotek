package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductRaw_Out;

public interface ProductPage_OutMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpage_out
     *
     * @mbggenerated Mon Jul 04 09:44:36 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpage_out
     *
     * @mbggenerated Mon Jul 04 09:44:36 CST 2016
     */
    int insert(ProductPage_Out record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpage_out
     *
     * @mbggenerated Mon Jul 04 09:44:36 CST 2016
     */
    int insertSelective(ProductPage_Out record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpage_out
     *
     * @mbggenerated Mon Jul 04 09:44:36 CST 2016
     */
    ProductPage_Out selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpage_out
     *
     * @mbggenerated Mon Jul 04 09:44:36 CST 2016
     */
    int updateByPrimaryKeySelective(ProductPage_Out record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpage_out
     *
     * @mbggenerated Mon Jul 04 09:44:36 CST 2016
     */
    int updateByPrimaryKey(ProductPage_Out record);
    
    List<ProductPage_Out> findProductPageOuts(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<ProductPage_Out> getProductPageOuts(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    List<ProductPage_Out> getByPid(int pid);
    
    List<ProductPage_Out> getByBoxinId(int boxinid);
}