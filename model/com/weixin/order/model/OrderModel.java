package com.weixin.order.model;

import java.math.BigDecimal;

public class OrderModel {
    private Integer id;

    private String orderNo;

    private String routeUid;

    private String teamUid;

    private String userUid;

    private String routeDesc;

    private String routeType;

    private String dateSlot;

    private Integer dateCount;

    private String linker;

    private String linkerPhone;

    private Integer visitorCount;

    private BigDecimal totalPrice;

    private String orderState;

    private String orderPay;

    private String comment;

    private String createDttm;

    private String updateDttm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getRouteUid() {
        return routeUid;
    }

    public void setRouteUid(String routeUid) {
        this.routeUid = routeUid == null ? null : routeUid.trim();
    }

    public String getTeamUid() {
        return teamUid;
    }

    public void setTeamUid(String teamUid) {
        this.teamUid = teamUid == null ? null : teamUid.trim();
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc == null ? null : routeDesc.trim();
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType == null ? null : routeType.trim();
    }

    public String getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(String dateSlot) {
        this.dateSlot = dateSlot == null ? null : dateSlot.trim();
    }

    public Integer getDateCount() {
        return dateCount;
    }

    public void setDateCount(Integer dateCount) {
        this.dateCount = dateCount;
    }

    public String getLinker() {
        return linker;
    }

    public void setLinker(String linker) {
        this.linker = linker == null ? null : linker.trim();
    }

    public String getLinkerPhone() {
        return linkerPhone;
    }

    public void setLinkerPhone(String linkerPhone) {
        this.linkerPhone = linkerPhone == null ? null : linkerPhone.trim();
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public String getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(String orderPay) {
        this.orderPay = orderPay == null ? null : orderPay.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(String createDttm) {
        this.createDttm = createDttm == null ? null : createDttm.trim();
    }

    public String getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(String updateDttm) {
        this.updateDttm = updateDttm == null ? null : updateDttm.trim();
    }
}