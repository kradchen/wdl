package BasicSession;

import java.sql.Date;

public class sessionUserinfo {
	private int id;
    private String user_code;
    private String user_name;
    private String user_key;
    private String user_role;
    private String user_phone;
    private int flag;
    private String comment;
    private Date create_dttm;
    
    private String weixin_nickname;
    private String weixin_sex;
    private String weixin_language;
    private String weixin_city;
    private String weixin_province;
    private String weixin_country;
    private String weixin_headimgurl;
    private String weixin_subscribe_time;
    private String weixin_appid;
    
    private String weixin_openid;
    
    private String weixin_code;
    
    private String weixin_validatecode;
    
    //用户点击微信公众号时候的菜单
    private String user_weixin_clickmenuitem;
    
    //用户当前的岗位
    private String user_post;

    //用户当前的所属部门
    private String user_src_department;

    //用户当前的权限部门
    private String user_tag_department;
    

    //用户绑定的电话
    public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String pUser_phone) {
		this.user_phone = pUser_phone;
	}
    
    //用户当前权限部门
    public String getUser_tag_department() {
		return user_tag_department;
	}

	public void setUser_tag_department(String pUser_tag_department) {
		this.user_tag_department = pUser_tag_department;
	}
	
    //所属部门
    public String getUser_src_department() {
		return user_src_department;
	}

	public void setUser_src_department(String pUser_src_department) {
		this.user_src_department = pUser_src_department;
	}

    
    //所属岗位
    public String getUser_post() {
		return user_post;
	}

	public void setUser_post(String pUser_post) {
		this.user_post = pUser_post;
	}
	

	//点击到的菜单项目
    public String getUser_weixin_clickmenuitem() {
		return user_weixin_clickmenuitem;
	}

	public void setUser_weixin_clickmenuitem(String pUser_weixin_clickmenuitem) {
		this.user_weixin_clickmenuitem = pUser_weixin_clickmenuitem;
	}

    
    
    public String getWeixin_validatecode() {
		return weixin_validatecode;
	}

	public void setWeixin_validatecode(String weixin_validatecode) {
		this.weixin_validatecode = weixin_validatecode;
	}
	
    
    public String getWeixin_appid() {
		return weixin_appid;
	}

	public void setWeixin_appid(String weixin_appid) {
		this.weixin_appid = weixin_appid;
	}
	
	public String getWeixin_code() {
		return weixin_code;
	}

	public void setWeixin_code(String weixin_code) {
		this.weixin_code = weixin_code;
	}
	
    public String getWeixin_openid() {
		return weixin_openid;
	}

	public void setWeixin_openid(String weixin_openid) {
		this.weixin_openid = weixin_openid;
	}

	public String getWeixin_nickname() {
		return weixin_nickname;
	}

	public void setWeixin_nickname(String weixin_nickname) {
		this.weixin_nickname = weixin_nickname;
	}

	public String getWeixin_sex() {
		return weixin_sex;
	}

	public void setWeixin_sex(String weixin_sex) {
		this.weixin_sex = weixin_sex;
	}

	public String getWeixin_language() {
		return weixin_language;
	}

	public void setWeixin_language(String weixin_language) {
		this.weixin_language = weixin_language;
	}

	public String getWeixin_city() {
		return weixin_city;
	}

	public void setWeixin_city(String weixin_city) {
		this.weixin_city = weixin_city;
	}

	public String getWeixin_province() {
		return weixin_province;
	}

	public void setWeixin_province(String weixin_province) {
		this.weixin_province = weixin_province;
	}

	public String getWeixin_country() {
		return weixin_country;
	}

	public void setWeixin_country(String weixin_country) {
		this.weixin_country = weixin_country;
	}

	public String getWeixin_headimgurl() {
		return weixin_headimgurl;
	}

	public void setWeixin_headimgurl(String weixin_headimgurl) {
		this.weixin_headimgurl = weixin_headimgurl;
	}

	public String getWeixin_subscribe_time() {
		return weixin_subscribe_time;
	}

	public void setWeixin_subscribe_time(String weixin_subscribe_time) {
		this.weixin_subscribe_time = weixin_subscribe_time;
	}
	
    public sessionUserinfo(){}
    
    public int getId() {
        return id;
    }
    public void setId(int pid) {
        this.id = pid;
    }
    
    
    public String getUsercode() {
        return user_code;
    }
    public void setUsercode(String puser_id) {
    	this.user_code = puser_id;
    }
    
    public void setUserName(String puser_name) {
        this.user_name = puser_name;
    }
    public String getusername() {
        return user_name;
    }
    
    public void setUserkey(String puser_key) {
        this.user_key = puser_key;
    }
    public String getUserkey() {
    	if (user_key == null){
    		return "";
    	}
        return user_key;
    }
    
    
    public String getUserrole() {
        return user_role;
    }
    public void setUserrole(String puserrole) {
        this.user_role = puserrole;
    }
    
    public int getFlag() {
        return flag;
    }
    public void setFlag(int pflag) {
        this.flag = pflag;
    }
    
    public String getComment() {
        return comment;
    }
    public void setComment(String pcomment) {
        this.comment = pcomment;
    }
    
    public Date getCreate_dttm() {
        return create_dttm;
    }
    public void setCreate_dttm(Date pcreate_dttm) {
        this.create_dttm = pcreate_dttm;
    }
}
