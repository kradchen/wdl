package com.weixin.weixinuserinfo.model;

public class WeixinUserinfoModel {
    private Integer id;

    private String weixinAppid;

    private String weixinOpenid;

    private String weixinNickname;

    private String weixinLanguage;

    private String weixinCity;

    private String weixinProvince;

    private String weixinCountry;

    private String weixinHeadimgurl;

    private Integer weixinSubscribeTime;

    private String weixinPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeixinAppid() {
        return weixinAppid;
    }

    public void setWeixinAppid(String weixinAppid) {
        this.weixinAppid = weixinAppid == null ? null : weixinAppid.trim();
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid == null ? null : weixinOpenid.trim();
    }

    public String getWeixinNickname() {
        return weixinNickname;
    }

    public void setWeixinNickname(String weixinNickname) {
        this.weixinNickname = weixinNickname == null ? null : weixinNickname.trim();
    }

    public String getWeixinLanguage() {
        return weixinLanguage;
    }

    public void setWeixinLanguage(String weixinLanguage) {
        this.weixinLanguage = weixinLanguage == null ? null : weixinLanguage.trim();
    }

    public String getWeixinCity() {
        return weixinCity;
    }

    public void setWeixinCity(String weixinCity) {
        this.weixinCity = weixinCity == null ? null : weixinCity.trim();
    }

    public String getWeixinProvince() {
        return weixinProvince;
    }

    public void setWeixinProvince(String weixinProvince) {
        this.weixinProvince = weixinProvince == null ? null : weixinProvince.trim();
    }

    public String getWeixinCountry() {
        return weixinCountry;
    }

    public void setWeixinCountry(String weixinCountry) {
        this.weixinCountry = weixinCountry == null ? null : weixinCountry.trim();
    }

    public String getWeixinHeadimgurl() {
        return weixinHeadimgurl;
    }

    public void setWeixinHeadimgurl(String weixinHeadimgurl) {
        this.weixinHeadimgurl = weixinHeadimgurl == null ? null : weixinHeadimgurl.trim();
    }

    public Integer getWeixinSubscribeTime() {
        return weixinSubscribeTime;
    }

    public void setWeixinSubscribeTime(Integer weixinSubscribeTime) {
        this.weixinSubscribeTime = weixinSubscribeTime;
    }

    public String getWeixinPhone() {
        return weixinPhone;
    }

    public void setWeixinPhone(String weixinPhone) {
        this.weixinPhone = weixinPhone == null ? null : weixinPhone.trim();
    }
}