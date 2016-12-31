package com.grotek.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.grotek.pojo.Dealer;
import com.grotek.pojo.Employee;
import com.grotek.pojo.JobtypeDic;
import com.grotek.pojo.TravelExsType;
import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.TravelRbsmItem;
import com.grotek.pojo.UsedCar;

public class TravelRbsmDto implements Serializable {

    private Integer id;

    private Dealer dealer;

    private JobtypeDic type;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date etime;

    private String splace;

    private String eplace;

    private Double sum;

    private Employee employee;

    private Integer status;

    private Integer checked;

    private Integer paystatus;
    
    private Date date;
    
    private List<TravelRbsmItem> items;
    
    private Integer count;
    
    private String remark;
    
    public TravelRbsmDto(TravelRbsm rbsm) {
		// TODO Auto-generated constructor stub
    	this.id=rbsm.getId();
    	this.dealer = rbsm.getDealer();
    	this.type = rbsm.getType();
    	this.stime = rbsm.getStime();
    	this.etime = rbsm.getEtime();
    	this.splace = rbsm.getSplace();
    	this.eplace = rbsm.getEplace();
    	this.sum = rbsm.getSum();
    	this.employee = rbsm.getEmployee();
    	this.status = rbsm.getStatus();
    	this.checked = rbsm.getChecked();
    	this.paystatus = rbsm.getPaystatus();
    	this.date = rbsm.getDate();
    	this.remark = rbsm.getRemark();
    	if(rbsm.getItems()!=null || rbsm.getUsedCars()!=null){
    		this.items = new ArrayList<TravelRbsmItem>();
    		if(rbsm.getItems()!=null){
    			this.items.addAll(rbsm.getItems());
    		}
    		if(rbsm.getUsedCars()!=null && rbsm.getUsedCars().size()>0){
    			for (Iterator iterator = rbsm.getUsedCars().iterator(); iterator.hasNext();) {
					UsedCar usedCar = (UsedCar) iterator.next();
					TravelRbsmItem item = new TravelRbsmItem();
					item.setAmount(usedCar.getGasoline());
					item.setGasolineid(usedCar.getId());
					TravelExsType type = new TravelExsType();
					type.setName("汽油费");
					item.setType(type);
					this.items.add(item);
				}
    		}
    	}
    	this.count = items.size();
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getSplace() {
        return splace;
    }

    public void setSplace(String splace) {
        this.splace = splace;
    }

    public String getEplace() {
        return eplace;
    }

    public void setEplace(String eplace) {
        this.eplace = eplace;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getPaystatus() {
        return paystatus;
    }

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

	public Integer getCount() {
		return items==null?0:items.size();
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
