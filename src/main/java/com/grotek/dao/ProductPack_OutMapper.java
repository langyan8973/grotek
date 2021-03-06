package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductRaw_Out;

public interface ProductPack_OutMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpack_out
     *
     * @mbggenerated Thu Jun 30 15:51:53 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpack_out
     *
     * @mbggenerated Thu Jun 30 15:51:53 CST 2016
     */
    int insert(ProductPack_Out record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpack_out
     *
     * @mbggenerated Thu Jun 30 15:51:53 CST 2016
     */
    int insertSelective(ProductPack_Out record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpack_out
     *
     * @mbggenerated Thu Jun 30 15:51:53 CST 2016
     */
    ProductPack_Out selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpack_out
     *
     * @mbggenerated Thu Jun 30 15:51:53 CST 2016
     */
    int updateByPrimaryKeySelective(ProductPack_Out record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productpack_out
     *
     * @mbggenerated Thu Jun 30 15:51:53 CST 2016
     */
    int updateByPrimaryKey(ProductPack_Out record);
    
    List<ProductPack_Out> findProductPackOuts(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<ProductPack_Out> getProductPackOuts(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    List<ProductPack_Out> getByPid(int pid);
    
    List<ProductPack_Out> getByBoxinId(int boxinid);
}