package com.grotek.pojo;

public class ProductRaw_Store {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productraw_store.id
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productraw_store.pid
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    private ProductRaw productRaw;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productraw_store.count
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    private Double count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productraw_store.status
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productraw_store.id
     *
     * @return the value of productraw_store.id
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productraw_store.id
     *
     * @param id the value for productraw_store.id
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    

    public ProductRaw getProductRaw() {
		return productRaw;
	}

	public void setProductRaw(ProductRaw productRaw) {
		this.productRaw = productRaw;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productraw_store.count
     *
     * @return the value of productraw_store.count
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    public Double getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productraw_store.count
     *
     * @param count the value for productraw_store.count
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    public void setCount(Double count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productraw_store.status
     *
     * @return the value of productraw_store.status
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productraw_store.status
     *
     * @param status the value for productraw_store.status
     *
     * @mbggenerated Tue Jun 21 13:21:33 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}