package com.grotek.pojo;

public class PackType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pack_type.id
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pack_type.name
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pack_type.status
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    private Integer status;
    
    private Integer trans;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pack_type.id
     *
     * @return the value of pack_type.id
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pack_type.id
     *
     * @param id the value for pack_type.id
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pack_type.name
     *
     * @return the value of pack_type.name
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pack_type.name
     *
     * @param name the value for pack_type.name
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pack_type.status
     *
     * @return the value of pack_type.status
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pack_type.status
     *
     * @param status the value for pack_type.status
     *
     * @mbggenerated Sat Jun 25 11:16:04 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getTrans() {
		return trans;
	}

	public void setTrans(Integer trans) {
		this.trans = trans;
	}
    
}