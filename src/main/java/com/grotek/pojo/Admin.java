package com.grotek.pojo;

public class Admin {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.id
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.name
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.password
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.status
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    private Integer status;
    
    private Role role;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.id
     *
     * @return the value of admin.id
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.id
     *
     * @param id the value for admin.id
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.name
     *
     * @return the value of admin.name
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.name
     *
     * @param name the value for admin.name
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.password
     *
     * @return the value of admin.password
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.password
     *
     * @param password the value for admin.password
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.status
     *
     * @return the value of admin.status
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.status
     *
     * @param status the value for admin.status
     *
     * @mbggenerated Sun Jun 12 09:39:22 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
    
}