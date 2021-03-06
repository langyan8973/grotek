package com.grotek.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.DealerCreditDic;
import com.grotek.pojo.DealerReturnDic;

public interface DealerReturnDicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_return_dic
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_return_dic
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    int insert(DealerReturnDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_return_dic
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    int insertSelective(DealerReturnDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_return_dic
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    DealerReturnDic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_return_dic
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    int updateByPrimaryKeySelective(DealerReturnDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_return_dic
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    int updateByPrimaryKey(DealerReturnDic record);
    
    List<DealerReturnDic> getDealerReturnDics(PageRequest pageable);
    
    int allCount();
    
    DealerReturnDic getByPurchases(Double p);
}