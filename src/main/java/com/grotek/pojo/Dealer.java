package com.grotek.pojo;

import java.util.Date;

public class Dealer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.id
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.name
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.address
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.asid
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private District shengfen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.adid
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private District diqu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.aqid
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private District xian;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.axid
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private District zhen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.postcode
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String postcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.phone
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.owner
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.company
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String company;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.contact
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String contact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.mobile
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.turnover
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Double turnover;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.area
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Double area;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.population
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Integer population;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.fxd_count
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Integer fxdCount;

    /**
     * 竞争实力
     */
    private StrengthDic strength;

    /**
     * 商业信誉
     */
    private ReputationDic reputation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.business
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String business;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.territory
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String territory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.eid
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Employee agent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.stid
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private ShopTypeDic shoptype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.sfcjxsjh
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Integer sfcjxsjh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.jhdealer
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Dealer jhdea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.crid
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private CreditRatingDic creditrating;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.djdate
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Date djdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.description
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealerinfo.status
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    private Integer status;
    
    private Integer sfhz;
    
    private String pname;
    
    private String paddress;
    
    private String pcontact;
    
    private String pphone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.id
     *
     * @return the value of dealerinfo.id
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.id
     *
     * @param id the value for dealerinfo.id
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.name
     *
     * @return the value of dealerinfo.name
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.name
     *
     * @param name the value for dealerinfo.name
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.address
     *
     * @return the value of dealerinfo.address
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.address
     *
     * @param address the value for dealerinfo.address
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.postcode
     *
     * @return the value of dealerinfo.postcode
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.postcode
     *
     * @param postcode the value for dealerinfo.postcode
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.phone
     *
     * @return the value of dealerinfo.phone
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.phone
     *
     * @param phone the value for dealerinfo.phone
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.owner
     *
     * @return the value of dealerinfo.owner
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.owner
     *
     * @param owner the value for dealerinfo.owner
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.company
     *
     * @return the value of dealerinfo.company
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.company
     *
     * @param company the value for dealerinfo.company
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.contact
     *
     * @return the value of dealerinfo.contact
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getContact() {
        return contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.contact
     *
     * @param contact the value for dealerinfo.contact
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.mobile
     *
     * @return the value of dealerinfo.mobile
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.mobile
     *
     * @param mobile the value for dealerinfo.mobile
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.turnover
     *
     * @return the value of dealerinfo.turnover
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Double getTurnover() {
        return turnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.turnover
     *
     * @param turnover the value for dealerinfo.turnover
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.area
     *
     * @return the value of dealerinfo.area
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Double getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.area
     *
     * @param area the value for dealerinfo.area
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.population
     *
     * @return the value of dealerinfo.population
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.population
     *
     * @param population the value for dealerinfo.population
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.fxd_count
     *
     * @return the value of dealerinfo.fxd_count
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Integer getFxdCount() {
        return fxdCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.fxd_count
     *
     * @param fxdCount the value for dealerinfo.fxd_count
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setFxdCount(Integer fxdCount) {
        this.fxdCount = fxdCount;
    }

    public StrengthDic getStrength() {
		return strength;
	}

	public void setStrength(StrengthDic strength) {
		this.strength = strength;
	}

    public ReputationDic getReputation() {
		return reputation;
	}

	public void setReputation(ReputationDic reputation) {
		this.reputation = reputation;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.business
     *
     * @return the value of dealerinfo.business
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getBusiness() {
        return business;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.business
     *
     * @param business the value for dealerinfo.business
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setBusiness(String business) {
        this.business = business;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.territory
     *
     * @return the value of dealerinfo.territory
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.territory
     *
     * @param territory the value for dealerinfo.territory
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public ShopTypeDic getShoptype() {
		return shoptype;
	}

	public void setShoptype(ShopTypeDic shoptype) {
		this.shoptype = shoptype;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.sfcjxsjh
     *
     * @return the value of dealerinfo.sfcjxsjh
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Integer getSfcjxsjh() {
        return sfcjxsjh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.sfcjxsjh
     *
     * @param sfcjxsjh the value for dealerinfo.sfcjxsjh
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setSfcjxsjh(Integer sfcjxsjh) {
        this.sfcjxsjh = sfcjxsjh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.jhdealer
     *
     * @return the value of dealerinfo.jhdealer
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Dealer getJhdealer() {
        return jhdea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.jhdealer
     *
     * @param jhdealer the value for dealerinfo.jhdealer
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setJhdealer(Dealer jhdealer) {
        this.jhdea = jhdealer;
    }
    

    public CreditRatingDic getCreditrating() {
		return creditrating;
	}

	public void setCreditrating(CreditRatingDic creditrating) {
		this.creditrating = creditrating;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.djdate
     *
     * @return the value of dealerinfo.djdate
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Date getDjdate() {
        return djdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.djdate
     *
     * @param djdate the value for dealerinfo.djdate
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setDjdate(Date djdate) {
        this.djdate = djdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.description
     *
     * @return the value of dealerinfo.description
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.description
     *
     * @param description the value for dealerinfo.description
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealerinfo.status
     *
     * @return the value of dealerinfo.status
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealerinfo.status
     *
     * @param status the value for dealerinfo.status
     *
     * @mbggenerated Tue Jun 21 14:41:16 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public District getShengfen() {
		return shengfen;
	}

	public void setShengfen(District shengfen) {
		this.shengfen = shengfen;
	}

	public District getDiqu() {
		return diqu;
	}

	public void setDiqu(District diqu) {
		this.diqu = diqu;
	}

	public District getXian() {
		return xian;
	}

	public void setXian(District xian) {
		this.xian = xian;
	}

	public District getZhen() {
		return zhen;
	}

	public void setZhen(District zhen) {
		this.zhen = zhen;
	}

	public Employee getAgent() {
		return agent;
	}

	public void setAgent(Employee agent) {
		this.agent = agent;
	}

	public Dealer getJhdea() {
		return jhdea;
	}

	public void setJhdea(Dealer jhdea) {
		this.jhdea = jhdea;
	}

	public Integer getSfhz() {
		return sfhz;
	}

	public void setSfhz(Integer sfhz) {
		this.sfhz = sfhz;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public String getPcontact() {
		return pcontact;
	}

	public void setPcontact(String pcontact) {
		this.pcontact = pcontact;
	}

	public String getPphone() {
		return pphone;
	}

	public void setPphone(String pphone) {
		this.pphone = pphone;
	}
    
}