package com.kuangkee.search.pojo;

import java.util.Date;

public class Order {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.version
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Long version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.p_id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Long pId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.phone
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.price
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Integer price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.address
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.agency_shop_name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String agencyShopName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.wechat
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String wechat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.agency_shop
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Boolean agencyShop;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.u_id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Long uId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.u_name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String uName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.u_phone
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String uPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.create_date
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.update_date
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.order_status
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String orderStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order.info
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    private String info;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.id
     *
     * @return the value of tb_order.id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.id
     *
     * @param id the value for tb_order.id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.version
     *
     * @return the value of tb_order.version
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.version
     *
     * @param version the value for tb_order.version
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.p_id
     *
     * @return the value of tb_order.p_id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Long getpId() {
        return pId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.p_id
     *
     * @param pId the value for tb_order.p_id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setpId(Long pId) {
        this.pId = pId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.phone
     *
     * @return the value of tb_order.phone
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.phone
     *
     * @param phone the value for tb_order.phone
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.price
     *
     * @return the value of tb_order.price
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.price
     *
     * @param price the value for tb_order.price
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.name
     *
     * @return the value of tb_order.name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.name
     *
     * @param name the value for tb_order.name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.address
     *
     * @return the value of tb_order.address
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.address
     *
     * @param address the value for tb_order.address
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.agency_shop_name
     *
     * @return the value of tb_order.agency_shop_name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getAgencyShopName() {
        return agencyShopName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.agency_shop_name
     *
     * @param agencyShopName the value for tb_order.agency_shop_name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setAgencyShopName(String agencyShopName) {
        this.agencyShopName = agencyShopName == null ? null : agencyShopName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.wechat
     *
     * @return the value of tb_order.wechat
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.wechat
     *
     * @param wechat the value for tb_order.wechat
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.agency_shop
     *
     * @return the value of tb_order.agency_shop
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Boolean getAgencyShop() {
        return agencyShop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.agency_shop
     *
     * @param agencyShop the value for tb_order.agency_shop
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setAgencyShop(Boolean agencyShop) {
        this.agencyShop = agencyShop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.u_id
     *
     * @return the value of tb_order.u_id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Long getuId() {
        return uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.u_id
     *
     * @param uId the value for tb_order.u_id
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setuId(Long uId) {
        this.uId = uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.u_name
     *
     * @return the value of tb_order.u_name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getuName() {
        return uName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.u_name
     *
     * @param uName the value for tb_order.u_name
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.u_phone
     *
     * @return the value of tb_order.u_phone
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getuPhone() {
        return uPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.u_phone
     *
     * @param uPhone the value for tb_order.u_phone
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.create_date
     *
     * @return the value of tb_order.create_date
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.create_date
     *
     * @param createDate the value for tb_order.create_date
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.update_date
     *
     * @return the value of tb_order.update_date
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.update_date
     *
     * @param updateDate the value for tb_order.update_date
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.order_status
     *
     * @return the value of tb_order.order_status
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.order_status
     *
     * @param orderStatus the value for tb_order.order_status
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_order.info
     *
     * @return the value of tb_order.info
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_order.info
     *
     * @param info the value for tb_order.info
     *
     * @mbggenerated Wed Mar 07 11:12:37 CST 2018
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

	@Override
	public String toString() {
		return "Order [id=" + id + ", version=" + version + ", pId=" + pId + ", phone=" + phone + ", price=" + price
				+ ", name=" + name + ", address=" + address + ", agencyShopName=" + agencyShopName + ", wechat="
				+ wechat + ", agencyShop=" + agencyShop + ", uId=" + uId + ", uName=" + uName + ", uPhone=" + uPhone
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", orderStatus=" + orderStatus
				+ ", info=" + info + "]";
	}
    
}