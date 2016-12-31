package com.grotek.pojo;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TravelRbsm {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.id
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.did
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private Dealer dealer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.stid
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private JobtypeDic type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.stime
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.etime
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date etime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.splace
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private String splace;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.eplace
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private String eplace;

    private Double sum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.eid
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private Employee employee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.status
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.checked
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private Integer checked;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_rbsm.paystatus
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    private Integer paystatus;
    
    private Date date;
    
    private List<TravelRbsmItem> items;
    
    private Integer count;
    
    private List<UsedCar> usedCars;
    
    private Integer ucount;
    
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.id
     *
     * @return the value of travel_rbsm.id
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.id
     *
     * @param id the value for travel_rbsm.id
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.stime
     *
     * @return the value of travel_rbsm.stime
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public Date getStime() {
        return stime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.stime
     *
     * @param stime the value for travel_rbsm.stime
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setStime(Date stime) {
        this.stime = stime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.etime
     *
     * @return the value of travel_rbsm.etime
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public Date getEtime() {
        return etime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.etime
     *
     * @param etime the value for travel_rbsm.etime
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setEtime(Date etime) {
        this.etime = etime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.splace
     *
     * @return the value of travel_rbsm.splace
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public String getSplace() {
        return splace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.splace
     *
     * @param splace the value for travel_rbsm.splace
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setSplace(String splace) {
        this.splace = splace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.eplace
     *
     * @return the value of travel_rbsm.eplace
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public String getEplace() {
        return eplace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.eplace
     *
     * @param eplace the value for travel_rbsm.eplace
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setEplace(String eplace) {
        this.eplace = eplace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.sum
     *
     * @return the value of travel_rbsm.sum
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public Double getSum() {
        return sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.sum
     *
     * @param sum the value for travel_rbsm.sum
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.status
     *
     * @return the value of travel_rbsm.status
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.status
     *
     * @param status the value for travel_rbsm.status
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.checked
     *
     * @return the value of travel_rbsm.checked
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public Integer getChecked() {
        return checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.checked
     *
     * @param checked the value for travel_rbsm.checked
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_rbsm.paystatus
     *
     * @return the value of travel_rbsm.paystatus
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public Integer getPaystatus() {
        return paystatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_rbsm.paystatus
     *
     * @param paystatus the value for travel_rbsm.paystatus
     *
     * @mbggenerated Wed Jul 20 10:09:36 CST 2016
     */
    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<TravelRbsmItem> getItems() {
		return items;
	}

	public void setItems(List<TravelRbsmItem> items) {
		this.items = items;
	}

	public List<UsedCar> getUsedCars() {
		return usedCars;
	}

	public void setUsedCars(List<UsedCar> usedCars) {
		this.usedCars = usedCars;
	}

	public Integer getCount() {
		return items==null?0:items.size();
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getUcount() {
		return usedCars==null?0:usedCars.size();
	}

	public void setUcount(Integer ucount) {
		this.ucount = ucount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}