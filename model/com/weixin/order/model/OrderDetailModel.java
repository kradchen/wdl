package com.weixin.order.model;

import java.math.BigDecimal;

public class OrderDetailModel {
    private Integer id;

    private String orderNo;

    private String routeUid;

    private String teamUid;

    private String userUid;

    private String visitorName;

    private String visitorIdno;

    private String visitorPhone;

    private String visitorType;

    private BigDecimal visitorPrice;

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

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName == null ? null : visitorName.trim();
    }

    public String getVisitorIdno() {
        return visitorIdno;
    }

    public void setVisitorIdno(String visitorIdno) {
        this.visitorIdno = visitorIdno == null ? null : visitorIdno.trim();
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone == null ? null : visitorPhone.trim();
    }

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType == null ? null : visitorType.trim();
    }

    public BigDecimal getVisitorPrice() {
        return visitorPrice;
    }

    public void setVisitorPrice(BigDecimal visitorPrice) {
        this.visitorPrice = visitorPrice;
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