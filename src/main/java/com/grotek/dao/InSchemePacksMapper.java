package com.grotek.dao;

import java.util.List;

import com.grotek.pojo.InSchemePacks;

public interface InSchemePacksMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table in_scheme_packs
     *
     * @mbggenerated Tue Jul 05 09:52:44 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table in_scheme_packs
     *
     * @mbggenerated Tue Jul 05 09:52:44 CST 2016
     */
    int insert(InSchemePacks record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table in_scheme_packs
     *
     * @mbggenerated Tue Jul 05 09:52:44 CST 2016
     */
    int insertSelective(InSchemePacks record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table in_scheme_packs
     *
     * @mbggenerated Tue Jul 05 09:52:44 CST 2016
     */
    InSchemePacks selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table in_scheme_packs
     *
     * @mbggenerated Tue Jul 05 09:52:44 CST 2016
     */
    int updateByPrimaryKeySelective(InSchemePacks record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table in_scheme_packs
     *
     * @mbggenerated Tue Jul 05 09:52:44 CST 2016
     */
    int updateByPrimaryKey(InSchemePacks record);
    
    List<InSchemePacks> getBySid(int sid);
}