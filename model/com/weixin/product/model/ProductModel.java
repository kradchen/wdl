package com.weixin.product.model;

import java.math.BigDecimal;

public class ProductModel {
    private Integer id;

    private String prdUid;

    private String prdDesc;

    private String pageName;

    private String pageTitle;

    private String createDttm;

    private String updateDttm;

    private BigDecimal prdPrice;
    
    public BigDecimal getPrdPrice() {
		return prdPrice;
	}

	public void setPrdPrice(BigDecimal prdPrice) {
		this.prdPrice = prdPrice;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrdUid() {
        return prdUid;
    }

    public void setPrdUid(String prdUid) {
        this.prdUid = prdUid == null ? null : prdUid.trim();
    }

    public String getPrdDesc() {
        return prdDesc;
    }

    public void setPrdDesc(String prdDesc) {
        this.prdDesc = prdDesc == null ? null : prdDesc.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle == null ? null : pageTitle.trim();
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