package authinfo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class UserInfo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String userId;

    private String userName;

    private String userPassword;

    private String userRole;

    private Integer active;

    private String phone;

    private String address;

    private String comment;

    private Date createDttm;

    private Date updateDttm;

    private Date lastLogin;
    
    private Integer logLevel;
    
    private String token;
    
    private String devId;
    
    private String dbID;
    
    private String appID;
    
    private String huala_ID;
    
    private String baiwandian_ID;
    
    private String huala_account;
    private String huala_sellerID;
    private String huala_token;
    
    private String juhe_areaid;
    
   

	private String juhe_shopname;
    
    private String juhe_uuid;
    
    private String juhe_licence_code;
    
    private String juhe_business_licence;
    
  

	private String juhe_area_full_name;
    
    private String juhe_phone;
    
    private String juhe_address;
    
    private String juhe_mobile;
    
    private String interface_url;
    
    private String corpId;
    

	/**
	 * @return the juhe_mobile
	 */
	public String getJuhe_mobile() {
		return juhe_mobile;
	}

	/**
	 * @param juhe_mobile the juhe_mobile to set
	 */
	public void setJuhe_mobile(String juhe_mobile) {
		this.juhe_mobile = juhe_mobile;
	}

	/**
  	 * @return the juhe_area_full_name
  	 */
  	public String getJuhe_area_full_name() {
  		return juhe_area_full_name;
  	}

  	/**
  	 * @param juhe_area_full_name the juhe_area_full_name to set
  	 */
  	public void setJuhe_area_full_name(String juhe_area_full_name) {
  		this.juhe_area_full_name = juhe_area_full_name;
  	}

  	/**
  	 * @return the juhe_phone
  	 */
  	public String getJuhe_phone() {
  		return juhe_phone;
  	}

  	/**
  	 * @param juhe_phone the juhe_phone to set
  	 */
  	public void setJuhe_phone(String juhe_phone) {
  		this.juhe_phone = juhe_phone;
  	}

  	/**
  	 * @return the juhe_address
  	 */
  	public String getJuhe_address() {
  		return juhe_address;
  	}

  	/**
  	 * @param juhe_address the juhe_address to set
  	 */
  	public void setJuhe_address(String juhe_address) {
  		this.juhe_address = juhe_address;
  	}
    /**
   	 * @return the juhe_shopname
   	 */
   	public String getJuhe_shopname() {
   		return juhe_shopname;
   	}

   	/**
   	 * @param juhe_shopname the juhe_shopname to set
   	 */
   	public void setJuhe_shopname(String juhe_shopname) {
   		this.juhe_shopname = juhe_shopname;
   	}

   	/**
   	 * @return the juhe_uuid
   	 */
   	public String getJuhe_uuid() {
   		return juhe_uuid;
   	}

   	/**
   	 * @param juhe_uuid the juhe_uuid to set
   	 */
   	public void setJuhe_uuid(String juhe_uuid) {
   		this.juhe_uuid = juhe_uuid;
   	}

   	/**
   	 * @return the juhe_licence_code
   	 */
   	public String getJuhe_licence_code() {
   		return juhe_licence_code;
   	}

   	/**
   	 * @param juhe_licence_code the juhe_licence_code to set
   	 */
   	public void setJuhe_licence_code(String juhe_licence_code) {
   		this.juhe_licence_code = juhe_licence_code;
   	}

   	/**
   	 * @return the juhe_business_licence
   	 */
   	public String getJuhe_business_licence() {
   		return juhe_business_licence;
   	}

   	/**
   	 * @param juhe_business_licence the juhe_business_licence to set
   	 */
   	public void setJuhe_business_licence(String juhe_business_licence) {
   		this.juhe_business_licence = juhe_business_licence;
   	}

    /**
	 * @return the juhe_areaid
	 */
	public String getJuhe_areaid() {
		return juhe_areaid;
	}

	/**
	 * @param juhe_areaid the juhe_areaid to set
	 */
	public void setJuhe_areaid(String juhe_areaid) {
		this.juhe_areaid = juhe_areaid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    
    public String getHuala_account() {
        return huala_account;
    }
    public void setHuala_account(String pHuala_account) {
        this.huala_account = pHuala_account == null ? "" : pHuala_account.trim();
    }

    
    public String getHuala_sellerID() {
        return huala_sellerID;
    }
    public void setHuala_sellerID(String pHuala_sellerID) {
        this.huala_sellerID = pHuala_sellerID == null ? "" : pHuala_sellerID.trim();
    }


    public String getHuala_token() {
        return huala_token;
    }
    public void setHuala_token(String pHuala_token) {
        this.huala_token = pHuala_token == null ? "" : pHuala_token.trim();
    }

    
    public String getHuala_ID() {
        return huala_ID;
    }
    
    public void setHuala_ID(String pHuala_ID) {
        this.huala_ID = pHuala_ID == null ? "" : pHuala_ID.trim();
    }

    public String getBaiwandian_ID() {
        return baiwandian_ID;
    }
    
    public void setBaiwandian_ID(String pBaiwandian_ID) {
        this.baiwandian_ID = pBaiwandian_ID == null ? "" : pBaiwandian_ID.trim();
    }

    
    public String getToken() {
        return token;
    }
    
    public void setToken(String ptoken) {
        this.token = ptoken == null ? "" : ptoken.trim();
    }

    public String getAppID() {
        return appID;
    }
    
    public void setAppID(String pAppID) {
        this.appID = pAppID == null ? "" : pAppID.trim();
    }

    

    public String getDevId() {
        return devId;
    }
    
    public void setDevId(String pDevId) {
        this.devId = pDevId == null ? null : pDevId.trim();
    }

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getlogLevel() {
        return logLevel;
    }

    public void setlogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(Date createDttm) {
        this.createDttm = createDttm;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }


    public String getdbID() {
        return dbID;
    }

    public void setdbID(String dbid) {
        this.dbID = dbid;
    }
    
    public String getinterfaceUrl() {
        return interface_url;
    }

    public void setinterfaceUrl(String pinterface_url) {
        this.interface_url = pinterface_url;
    }

    public String getcorpId() {
        return corpId;
    }

    public void setcorpId(String corpId) {
        this.corpId = corpId;
    }
}
