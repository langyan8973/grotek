package com.grotek.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.DealerCreditDic;
import com.grotek.pojo.DealerValue;

public interface DealerCreditDicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_credit_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_credit_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int insert(DealerCreditDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_credit_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int insertSelective(DealerCreditDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_credit_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    DealerCreditDic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_credit_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int updateByPrimaryKeySelective(DealerCreditDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_credit_dic
     *
     * @mbggenerated Fri Jun 10 13:50:57 CST 2016
     */
    int updateByPrimaryKey(DealerCreditDic record);
    
    List<DealerCreditDic> findDealerCreditDics(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<DealerCreditDic> getDealerCreditDics(PageRequest pageable);
    
    int searchCount(String key);
    
    int allCount();
    
    DealerCreditDic getBySid(int sid);
}