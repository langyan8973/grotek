package com.grotek.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UsedCar {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.id
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.eid
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private Employee employee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.did
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private Dealer dealer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.jid
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private JobtypeDic type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.content
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.date
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.splace
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private String splace;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.eplace
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private String eplace;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.kmcount
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private Double kmcount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.gasolineunit
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private Double gasolineunit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.gasoline
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private Double gasoline;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column used_cars.status
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    private Integer status;
    
    private Integer tiid;
    
    private Integer checked;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.id
     *
     * @return the value of used_cars.id
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.id
     *
     * @param id the value for used_cars.id
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.content
     *
     * @return the value of used_cars.content
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.content
     *
     * @param content the value for used_cars.content
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.date
     *
     * @return the value of used_cars.date
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.date
     *
     * @param date the value for used_cars.date
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.splace
     *
     * @return the value of used_cars.splace
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public String getSplace() {
        return splace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.splace
     *
     * @param splace the value for used_cars.splace
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setSplace(String splace) {
        this.splace = splace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.eplace
     *
     * @return the value of used_cars.eplace
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public String getEplace() {
        return eplace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.eplace
     *
     * @param eplace the value for used_cars.eplace
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setEplace(String eplace) {
        this.eplace = eplace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.kmcount
     *
     * @return the value of used_cars.kmcount
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public Double getKmcount() {
        return kmcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.kmcount
     *
     * @param kmcount the value for used_cars.kmcount
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setKmcount(Double kmcount) {
        this.kmcount = kmcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.gasolineunit
     *
     * @return the value of used_cars.gasolineunit
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public Double getGasolineunit() {
        return gasolineunit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.gasolineunit
     *
     * @param gasolineunit the value for used_cars.gasolineunit
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setGasolineunit(Double gasolineunit) {
        this.gasolineunit = gasolineunit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.gasoline
     *
     * @return the value of used_cars.gasoline
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public Double getGasoline() {
        return gasoline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.gasoline
     *
     * @param gasoline the value for used_cars.gasoline
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setGasoline(Double gasoline) {
        this.gasoline = gasoline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column used_cars.status
     *
     * @return the value of used_cars.status
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column used_cars.status
     *
     * @param status the value for used_cars.status
     *
     * @mbggenerated Sun Aug 21 11:39:13 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public JobtypeDic getType() {
		return type;
	}

	public void setType(JobtypeDic type) {
		this.type = type;
	}

	public Integer getTiid() {
		return tiid;
	}

	public void setTiid(Integer tiid) {
		this.tiid = tiid;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
    
    
}