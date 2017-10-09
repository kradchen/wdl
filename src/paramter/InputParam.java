package paramter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 入口参数模型
 * @author xxp
 * date:2016/07/26
 */
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true,allowSetters=true,allowGetters=true)
public class InputParam {
	//参数
	private String param;
	//用户id
	private String userid;
	//token值
	private String token;
	//设备号
	private String devid;
	//版本号
	private String version;
	//校验码
	private String verifycode;
	//请求时间
	private String date;
	//application id  应用编号
	private String appid;
	//公司编号
	private String corpid;
	//组别编号
	private String groupid;

	/**
	 * @return the groupId
	 */
	public String getGroupid() {
		return groupid;
	}

	/**
	 * @param corpId the groupId to set
	 */
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	
	/**
	 * @return the corpId
	 */
	public String getCorpid() {
		return corpid;
	}

	/**
	 * @param corpId the corpId to set
	 */
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid== null ? "" : appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date== null ? "" : this.date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the devid
	 */
	public String getDevid() {
		return devid== null ? "" : this.devid;
	}

	/**
	 * @param devid the devid to set
	 */
	public void setDevid(String devid) {
		this.devid = devid;
	}

	/**
	 * @return the verifycode
	 */
	public String getVerifycode() {
		return verifycode== null ? "" : this.verifycode;
	}

	/**
	 * @param verifycode the verifycode to set
	 */
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	/**
	 * @return the param
	 */
	public String getParam() {
		return param== null ? "" : this.param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid == null ? "" : userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token== null ? "" : this.token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version== null ? "" : this.version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
