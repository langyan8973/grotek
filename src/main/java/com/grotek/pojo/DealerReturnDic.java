package com.grotek.pojo;

import java.util.Date;

public class DealerReturnDic {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealer_return_dic.id
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealer_return_dic.purchases
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    private Double purchases;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealer_return_dic.rebate
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    private Double rebate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dealer_return_dic.status
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    private Integer status;
    
    private Date date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealer_return_dic.id
     *
     * @return the value of dealer_return_dic.id
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealer_return_dic.id
     *
     * @param id the value for dealer_return_dic.id
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealer_return_dic.purchases
     *
     * @return the value of dealer_return_dic.purchases
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public Double getPurchases() {
        return purchases;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealer_return_dic.purchases
     *
     * @param purchases the value for dealer_return_dic.purchases
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public void setPurchases(Double purchases) {
        this.purchases = purchases;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealer_return_dic.rebate
     *
     * @return the value of dealer_return_dic.rebate
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public Double getRebate() {
        return rebate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealer_return_dic.rebate
     *
     * @param rebate the value for dealer_return_dic.rebate
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public void setRebate(Double rebate) {
        this.rebate = rebate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dealer_return_dic.status
     *
     * @return the value of dealer_return_dic.status
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dealer_return_dic.status
     *
     * @param status the value for dealer_return_dic.status
     *
     * @mbggenerated Fri Jun 10 13:57:54 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
}