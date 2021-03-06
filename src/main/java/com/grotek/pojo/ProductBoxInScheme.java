package com.grotek.pojo;

import java.util.Date;
import java.util.List;

public class ProductBoxInScheme {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column productbox_in_scheme.id
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column productbox_in_scheme.pid
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	private ProductBox box;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column productbox_in_scheme.date
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	private Date date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column productbox_in_scheme.status
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column productbox_in_scheme.count
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	private Integer count;
	
	private List<InSchemeRaws> schemeRaws;
	
	private List<InSchemePacks> schemePacks;
	
	private List<InSchemePages> schemePages;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column productbox_in_scheme.id
	 * @return  the value of productbox_in_scheme.id
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column productbox_in_scheme.id
	 * @param id  the value for productbox_in_scheme.id
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column productbox_in_scheme.date
	 * @return  the value of productbox_in_scheme.date
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column productbox_in_scheme.date
	 * @param date  the value for productbox_in_scheme.date
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column productbox_in_scheme.status
	 * @return  the value of productbox_in_scheme.status
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column productbox_in_scheme.status
	 * @param status  the value for productbox_in_scheme.status
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column productbox_in_scheme.count
	 * @return  the value of productbox_in_scheme.count
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column productbox_in_scheme.count
	 * @param count  the value for productbox_in_scheme.count
	 * @mbggenerated  Tue Jul 05 10:10:47 CST 2016
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	public ProductBox getBox() {
		return box;
	}

	public void setBox(ProductBox box) {
		this.box = box;
	}

	public List<InSchemeRaws> getSchemeRaws() {
		return schemeRaws;
	}

	public void setSchemeRaws(List<InSchemeRaws> schemeRaws) {
		this.schemeRaws = schemeRaws;
	}

	public List<InSchemePacks> getSchemePacks() {
		return schemePacks;
	}

	public void setSchemePacks(List<InSchemePacks> schemePacks) {
		this.schemePacks = schemePacks;
	}

	public List<InSchemePages> getSchemePages() {
		return schemePages;
	}

	public void setSchemePages(List<InSchemePages> schemePages) {
		this.schemePages = schemePages;
	}
	
	
	
}