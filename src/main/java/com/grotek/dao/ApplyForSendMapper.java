package com.grotek.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.ExsRbsm;

public interface ApplyForSendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_for_send
     *
     * @mbggenerated Thu Jul 21 10:21:31 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_for_send
     *
     * @mbggenerated Thu Jul 21 10:21:31 CST 2016
     */
    int insert(ApplyForSend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_for_send
     *
     * @mbggenerated Thu Jul 21 10:21:31 CST 2016
     */
    int insertSelective(ApplyForSend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_for_send
     *
     * @mbggenerated Thu Jul 21 10:21:31 CST 2016
     */
    ApplyForSend selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_for_send
     *
     * @mbggenerated Thu Jul 21 10:21:31 CST 2016
     */
    int updateByPrimaryKeySelective(ApplyForSend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_for_send
     *
     * @mbggenerated Thu Jul 21 10:21:31 CST 2016
     */
    int updateByPrimaryKey(ApplyForSend record);
    
    List<ApplyForSend> getApplyForSendsByEid(@Param("eid")String eid,@Param("pageable")PageRequest pageable);
    
    int allCountByEid(String eid);
    
    List<ApplyForSend> findApplyForSendsByEid(@Param("eid")String eid,@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    int searchCountByEid(@Param("eid")String eid,@Param("key")String key);
    
    List<ApplyForSend> getApplyForSends(@Param("pageable")PageRequest pageable);
    
    int allCount();
    
    List<ApplyForSend> findApplyForSends(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    List<ApplyForSend> getByMonth(@Param("eid")String eid,@Param("start")Date start,@Param("end")Date end);
    
    int searchCount(@Param("key")String key);
    
    int addone(ApplyForSend record);
    
    List<ApplyForSend> getCheckpending(@Param("pageable")PageRequest pageable);
    
    int allCountCheckpending();
    
    List<ApplyForSend> findCheckpending(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    int searchCountCheckpending(@Param("key")String key);
    
    List<ApplyForSend> getDelivered(@Param("pageable")PageRequest pageable);
    
    int allCountDelivered();
    
    List<ApplyForSend> findDelivered(@Param("key")String key,@Param("pageable")PageRequest pageable);
    
    int searchCountDelivered(@Param("key")String key);
}