package com.grotek.pojo;

public class ShortEmployee {
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.id
     *
     * @mbggenerated Fri Jun 10 14:06:55 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.fullname
     *
     * @mbggenerated Fri Jun 10 14:06:55 CST 2016
     */
    private String fullname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.code
     *
     * @mbggenerated Fri Jun 10 14:06:55 CST 2016
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.sex
     *
     * @mbggenerated Fri Jun 10 14:06:55 CST 2016
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.address
     *
     * @mbggenerated Fri Jun 10 14:06:55 CST 2016
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.mobile
     *
     * @mbggenerated Fri Jun 10 14:06:55 CST 2016
     */
    private String mobile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public ShortEmployee(Employee employee){
		id = employee.getId();
		fullname = employee.getFullname();
		code = employee.getCode();
		sex  = employee.getSex();
		address = employee.getAddress();
		mobile = employee.getMobile();
	}
    
}
