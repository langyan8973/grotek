package com.grotek.dao;

import com.grotek.pojo.DealerGoal;

public interface DealerGoalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_goal
     *
     * @mbggenerated Wed Jul 13 09:14:55 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_goal
     *
     * @mbggenerated Wed Jul 13 09:14:55 CST 2016
     */
    int insert(DealerGoal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_goal
     *
     * @mbggenerated Wed Jul 13 09:14:55 CST 2016
     */
    int insertSelective(DealerGoal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_goal
     *
     * @mbggenerated Wed Jul 13 09:14:55 CST 2016
     */
    DealerGoal selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_goal
     *
     * @mbggenerated Wed Jul 13 09:14:55 CST 2016
     */
    int updateByPrimaryKeySelective(DealerGoal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dealer_goal
     *
     * @mbggenerated Wed Jul 13 09:14:55 CST 2016
     */
    int updateByPrimaryKey(DealerGoal record);
    
    DealerGoal getByDid(int did);
}