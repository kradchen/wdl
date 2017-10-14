package controller;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import authinfo.UserInfo;
import mybatis.framework.UsingDataSourceSet;
import paramter.InputParam;
import paramter.OutPutParam;
import redis.RedisVerificationUtil;
import utility.EncryptUtility;
import utility.SerializableUtility;

/**
 * Controller基类
 * Controller公共方法，变量
 * @author xxp
 * @since 2016年11月02日 下午1:53:57
 * @version
 */
@RestController
public class BaseController {
	/**
	 * 数据库数据源集合
	 */
	//@Autowired
	//@Qualifier("usingDataSourceHashSet")
	protected UsingDataSourceSet DsHashSet;
	/**
	 * @return the dsHashSet
	 */
	public UsingDataSourceSet getDsHashSet() {
		return DsHashSet;
	}

	/**
	 * @param dsHashSet the dsHashSet to set
	 */
	public void setDsHashSet(UsingDataSourceSet dsHashSet) {
		DsHashSet = dsHashSet;
	}

	/**
	 * 验证redis工具类
	 */
	@Autowired
	protected RedisVerificationUtil redisVerificationUtil;

	/**
	 * 通用redis工具类
	 */
	//@Autowired
	//protected RedisCommonUtil redisCommonUtil;

	/**
	 * 聚合请求头
	 */
	private String Juhe_HttpUrl;
	
	

	/**
	 * 花啦生活接口HTTP请求头地址
	 */
	private String Huala_HttpUrl;
	/**
	 * 花啦生活接口资源头地址
	 */
	private String Huala_ResourceUrl;
	/**
	 * 日志连接地址
	 */
	private String Log_Url;

	/**
	 * 单点登录控制
	 */
	private boolean SinglePoint;
	
	
	/**
	 * @return the singlePoint
	 */
	public boolean isSinglePoint() {
		return SinglePoint;
	}

	/**
	 * @param singlePoint the singlePoint to set
	 */
	@Value("#{propertyConfigurerBean['app.singlePoint']}")
	public void setSinglePoint(boolean singlePoint) {
		SinglePoint = singlePoint;
	}
	private boolean Log_On;
	/**
	 * @return the log_On
	 */
	public boolean isLog_On() {
		return Log_On;
	}

	
	/**
	 * @return the log_Url
	 */
	public String getLog_Url() {
		return Log_Url;
	}

	@Value("#{propertyConfigurerBean['log.open']}")
	public void setLog_On(boolean log_On) {
		Log_On = log_On;
	}


	public String getJuhe_HttpUrl() {
		return Juhe_HttpUrl;
	}

	/**
	 * @param juhe_HttpUrl the juhe_HttpUrl to set
	 */
	@Value("#{propertyConfigurerBean['Juhe.HttpUrl']}")
	public void setJuhe_HttpUrl(String juhe_HttpUrl) {
		Juhe_HttpUrl = juhe_HttpUrl;
	}
	
	/**
	 * @param log_Url the log_Url to set
	 */
	@Value("#{propertyConfigurerBean['log.url']}")
	public void setLog_Url(String log_Url) {
		Log_Url = log_Url;
	}
	/**
	 * 日志等级变量宿主，请使用  Log_Level
	 */
	private int llprivate;
	

	
	/**
	 * @param llprivate the llprivate to set
	 */
	@Value("#{propertyConfigurerBean['log.level']}")
	public void setLlprivate(int llprivate) {
		this.llprivate = llprivate;
		Log_Level=llprivate;
	}

	/**
	 * 日志等级
	 */
	private static int Log_Level;
	
	/**
	 * @return the log_Level
	 */
	public static int getLog_Level() {
		return Log_Level;
	}

	/**
	 * @param log_Level the log_Level to set
	 */
	public static void setLog_Level(int log_Level) {
		Log_Level = log_Level;
	}
	
	/**
	 * 系统app编号
	 */
	private String App_Id;
	/**
	 * @return the app_Id
	 */
	public String getApp_Id() {
		return App_Id;
	}

	/**
	 * @param app_Id the app_Id to set
	 */
	@Value("#{propertyConfigurerBean['app.id']}")
	public void setApp_Id(String app_Id) {
		App_Id = app_Id;
	}
	
	//系统测试标志位
	private boolean App_TestFlg;
	
	

	/**
	 * @return the app_TestFlg
	 */
	public boolean isApp_TestFlg() {
		return App_TestFlg;
	}

	/**
	 * @param app_TestFlg the app_TestFlg to set
	 */
	@Value("#{propertyConfigurerBean['app.testFlg']}")
	public void setApp_TestFlg(boolean app_TestFlg) {
		App_TestFlg = app_TestFlg;
	}

	//临时测试用
	private String Huala_HttpUrl_tmp;
	public String getHuala_HttpUrl_tmp() {
		return Huala_HttpUrl_tmp;
	}

	@Value("#{propertyConfigurerBean['Huala.HttpUrl_tmp']}")
	public void setHuala_HttpUrl_tmp(String huala_HttpUrl_tmp) {
		Huala_HttpUrl_tmp = huala_HttpUrl_tmp;
	}

	/**
	 * @return the huala_HttpUrl
	 */
	public String getHuala_HttpUrl() {
		return Huala_HttpUrl;
	}

	/**
	 * @param huala_HttpUrl
	 *            the huala_HttpUrl to set
	 */
	@Value("#{propertyConfigurerBean['Huala.HttpUrl']}")
	public void setHuala_HttpUrl(String huala_HttpUrl) {
		Huala_HttpUrl = huala_HttpUrl;
	}


	/**
	 * @return the huala_ResourceUrl
	 */
	public String getHuala_ResourceUrl() {
		return Huala_ResourceUrl;
	}
	
	@Value("#{propertyConfigurerBean['Huala.ResourceUrl']}")
	public void setHuala_ResourceUrl(String huala_ResourceUrl) {
		Huala_ResourceUrl = huala_ResourceUrl;
	}
	
	
	protected boolean CheckToken(String pUserid,String pToken) 
	{
		return true;
	}
	/**
	 * 检测接口权限
	 * @param pToken
	 * @param pInterfaceName
	 * @return
	 */
	protected OutPutParam CheckAuth(InputParam pModel,Integer pUserRole)
	{
		String mll = "";
		OutPutParam pOptions = new OutPutParam();
		pOptions.setSuccess();
//		//判断是否可以有权利使用
//		HashMap<String, String> params = SerializableUtility.DeserializeParamWithGenericity(pModel.getParam()); 
//		if (!params.containsKey("interface"))
//		{
//			//判断Token中的参数
//			pOptions.setFailure();
//			pOptions.setMessage("该用户无权使用此接口");;
//			return pOptions;
//		}
//		String interName=params.get("interface");
//		Object ObAuth = redisVerificationUtil.getObject(pModel.getAppid()+"_"+interName);
//		AuthModel authModel = null;
//		//获取缓存的接口信息
//		if(ObAuth!=null)
//		{
//			authModel = (AuthModel)ObAuth;	
//		}
//		else
//		{
//			//缓存信息过期，重新获取接口信息
//			AuthModel modelCondition = new AuthModel();
//			modelCondition.setAppId(pModel.getAppid());
//			modelCondition.setInterfaceName(interName);
//			List<AuthModel> aLst= authModelMapper.selectByConditions(modelCondition);
//			if(aLst.size()>0)
//			{
//				authModel = aLst.get(0);
//			}
//			redisVerificationUtil.putObjectWithLife(pModel.getAppid()+"_"+interName,authModel,60*60);
//		}
//		//检测接口是否存在
//		if(authModel==null)
//		{
//			pOptions.setFailure();
//			pOptions.setMessage("此接口未定义："+interName);
//			return pOptions;
//		}
//		
//		if (!authModel.getFlag().equals("1"))
//		{
//			//判断Token中的参数
//			pOptions.setFailure();
//			pOptions.setMessage("此接口已停用");
//			return pOptions;
//			
//		}
//		if ( pUserRole > authModel.getUserRole() )
//		{
//			//判断Token中的参数
//			pOptions.setFailure();
//			pOptions.setMessage("该用户无权使用此接口;用户级别："+pUserRole+"需要级别："+ authModel.getUserRole());
//			return pOptions;
//		}
		return pOptions;
	}
	/**
	 * 检测Application应用
	 * @return
	 */
	protected OutPutParam CheckApplication(String pAppid)
	{
		OutPutParam  mOutParam = new OutPutParam();
//		Object ObApplication = redisVerificationUtil.getObject("ApplicationList");
//		List<ApplicationModel> appLst= null;
//		//获取缓存的接口信息
//		if(ObApplication!=null)
//		{
//			appLst =(List<ApplicationModel>) ObApplication;
//		}
//		else
//		{
//			appLst= applicationModelMapperBase.selectAll();
//			redisVerificationUtil.putObjectWithLife("ApplicationList",appLst,3*24*60*60);
//		}
//		//获取信息异常  就返回异常信息
//		if(appLst==null||appLst.size()==0)
//		{
//			mOutParam.setFailure();
//			mOutParam.setMessage("获取应用信息异常");
//			return mOutParam;
//		}
//		//匹配AppId
//		boolean isExist =false;
//		for (ApplicationModel applicationModel : appLst) {
//			if(isExist=applicationModel.getAppId().equals(pAppid))
//				break;
//		}
//		if(isExist)
//		{
//			mOutParam.setSuccess();
//		}
//		else
//		{
//			mOutParam.setFailure();
//			mOutParam.setMessage("应用不存在");
//		}
		
		mOutParam.setSuccess();
		return mOutParam;
	}
	
	
	/**
	 * 数据库选择转换
	 * @param TypeMapper
	 * @throws Exception
	 */
//	protected void setDataSourceMapper(String pTypeMapper)throws Exception
//	{
//		CustomizedContextHolder.setCustomerType(DsHashSet.getTypeMapper(),pTypeMapper);
//	}
	/**
	 * 重置数据库源，选择默认数据库源
	 * @throws Exception
	 */
//	protected void resetDataSourceMapper()throws Exception
//	{
//		CustomizedContextHolder.setCustomerType(DsHashSet.getTypeMapper(),DsHashSet.getDefaultDataBaseMapperName());
//	}
	
	protected OutPutParam checkVerificationBase(InputParam pModel)
	{
		OutPutParam outparam = new OutPutParam();
//		if (pModel.getVerifycode().isEmpty()){
//			outparam.setFailure();
//			outparam.setMessage("验证码缺失");
//			return outparam;
//		}
//		
//		//判断MD5值
//		//验证模式为datatime＋userid＋devid＋token
//		String mInputParam = pModel.getDate()+pModel.getUserid()+pModel.getDevid()+pModel.getToken() + (pModel.getCorpid().equals(null) == true ? "" : pModel.getCorpid());
//		String mGenMD5 = EncryptUtility.MD5To32(mInputParam);
//		if (!mGenMD5.equals(pModel.getVerifycode()))
//		{
//			outparam.setFailure();
//			outparam.setMessage("验证码无效: 传入参数:"+mInputParam +"；传入验证码:"+pModel.getVerifycode()+"；生成验证码:"+mGenMD5);
//			return outparam;
//			
//		}
//		outparam= CheckApplication(pModel.getAppid());
//		if(!outparam.getSuccess().toLowerCase().equals("true"))
//		{
//			return outparam;
//		}
//		String token = pModel.getToken();
//		Object mObject= redisVerificationUtil.getObject(token+"_UserInfo");
//		UserInfo mUserInfo= null;
//		if (mObject == null){
//				outparam.setFailure();
//				outparam.setMessage("Token 无效");
//				return outparam;
//		}
//		
//		mUserInfo = (UserInfo)mObject;
//		if (!mUserInfo.getUserId().equals(pModel.getUserid())){
//			outparam.setFailure();
//			outparam.setMessage("申请用户错误");
//			return outparam;
//		}
//		if (!mUserInfo.getDevId().equals(pModel.getDevid())){
//			outparam.setFailure();
//			outparam.setMessage("申请设备号错误");
//			return outparam;
//		}
//		//检测auth信息
//		outparam=CheckAuth(pModel,Integer.parseInt(mUserInfo.getUserRole()));
//		if(!outparam.getSuccess().toLowerCase().equals("true"))
//		{
//			return outparam;
//		}
//		//判断APPID是否存在
		
		
		//判断Token中的参数
		outparam.setSuccess();
		outparam.setBody(new UserInfo());
		return outparam;
	}
}
