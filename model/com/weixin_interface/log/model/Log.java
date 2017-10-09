package com.weixin_interface.log.model;

import java.util.Date;

public class Log {
	
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;
	public Log(){
		
	}
	

	public Log(String pAppID , String pUserId , String pKeyWord ){
		
		app_id = pAppID;
		user_id = pUserId;
		token = "-";
		log_level = 3;
		log_keyword = pKeyWord;
		log_text = pKeyWord;
		log_dttm = new java.util.Date();
	}

	public Log(String pAppID , String pUserId , String pToken , String pKeyWord ){
		
		app_id = pAppID;
		user_id = pUserId;
		token = pToken == "" ? "-" : pToken;
		log_level = 3;
		log_keyword = pKeyWord == "" ? "-" : pKeyWord;
		log_text = pKeyWord;
		log_dttm = new java.util.Date();
	}
	
	public Log(String pAppID , String pUserId , String pToken , String pKeyWord , String pLogText){
	
		app_id = pAppID;
		user_id = pUserId;
		token = pToken == "" ? "-" : pToken;
		log_level = 3;
		log_keyword = pKeyWord;
		log_text = pLogText;
		log_dttm = new java.util.Date();
	}
		
	
	/**
	 * 编号
	 */
	private int id;
	/**
	 * 用户Id    notNull
	 * */
	private String user_id;
	/**
	 * 应用程序Id   notNull
	 * */
	private String app_id;
	/**
	 * 创建时间(前)
	 */
	private Date log_dttm;
	/**
	 * 登陆产生的token
	 */
	private String token;
	/**
	 * 日志层级0/1/2 error/error+warning/all  notNull
	 * */
	private int log_level;
	/**
	 * 日志关键词(操作类型) notNull
	 */
	private String log_keyword;
	/**
	 * 日志内容
	 */
	private String log_text; 
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public Date getLog_dttm() {
		return log_dttm;
	}
	public void setLog_dttm(Date log_dttm) {
		this.log_dttm = log_dttm;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getLog_level() {
		return log_level;
	}
	public void setLog_level(int log_level) {
		this.log_level = log_level;
	}
	public String getLog_keyword() {
		return log_keyword;
	}
	public void setLog_keyword(String log_keyword) {
		this.log_keyword = log_keyword;
	}
	public String getLog_text() {
		return log_text;
	}
	public void setLog_text(String log_text) {
		this.log_text = log_text;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
