package com.grotek.pojo;

public class ProductPage_Store {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productpage_store.id
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productpage_store.pid
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    private ProductPage page;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productpage_store.count
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    private Integer count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productpage_store.status
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productpage_store.id
     *
     * @return the value of productpage_store.id
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productpage_store.id
     *
     * @param id the value for productpage_store.id
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productpage_store.count
     *
     * @return the value of productpage_store.count
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productpage_store.count
     *
     * @param count the value for productpage_store.count
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productpage_store.status
     *
     * @return the value of productpage_store.status
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productpage_store.status
     *
     * @param status the value for productpage_store.status
     *
     * @mbggenerated Mon Jul 04 08:48:49 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public ProductPage getPage() {
		return page;
	}

	public void setPage(ProductPage page) {
		this.page = page;
	}
    
}