package com.weixin.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.weixin.weixinuserinfo.mapper.IWeixinUserinfoModelMapper;
import com.weixin.weixinuserinfo.model.WeixinUserinfoModel;

import BasicSession.sessionUserinfo;
import paramter.InputParam;
import paramter.OutPutParam;


@Controller
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping("/")
public class Test1Controller {

	@Autowired
	IWeixinUserinfoModelMapper weixinUserinfoModelMapper;
	
	@RequestMapping(value = "/Test1", method = RequestMethod.POST)
	@ResponseBody
	public OutPutParam Test1(@RequestBody InputParam inputParam, HttpServletRequest request) {
		OutPutParam mRet = new OutPutParam();
		mRet.setSuccess();
		return mRet;
	}
	
	@RequestMapping(value = "/TestSMS", method = RequestMethod.GET)
	@ResponseBody
	public void TestSMS(HttpServletRequest request) throws Exception {
		
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
		//替换成你的AK
		final String accessKeyId = "LTAIvQwAddO6ZEjD";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "b0nWpfV7gJmKpAdjD6WdF2lKv2QgWW";//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		
		
		//组装请求对象
		 SendSmsRequest request1 = new SendSmsRequest();
		 //使用post提交
		 request1.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		 request1.setPhoneNumbers("13858128191");
		 //必填:短信签名-可在短信控制台中找到
		 request1.setSignName("玩儿到老");
		 //必填:短信模板-可在短信控制台中找到
		 request1.setTemplateCode("SMS_96520018");
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		 request1.setTemplateParam("{\"code\":\"123\"}");
		 //可选-上行短信扩展码(无特殊需求用户请忽略此字段)
		 //request.setSmsUpExtendCode("90997");
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 request1.setOutId("1");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request1);
		System.out.print(sendSmsResponse.getCode());
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
		//请求成功
			System.out.print("OK");
		}

	}
	
	@RequestMapping(value = "/getProductTest", method = RequestMethod.GET)
	public ModelAndView getProductTest(Model model , HttpServletRequest request) {
		ModelAndView mav =new ModelAndView("prdlist");

		return mav;
	}
	
	//@RequestMapping(value = "/TestIndex0/{phone}", method = RequestMethod.GET)//, @PathVariable String phone
	@RequestMapping(value = "/TestIndex0", method = RequestMethod.GET)
	public ModelAndView TestIndex(Model model , HttpServletRequest request) {
		
		//设置默认的session
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")== null){
			sessionUserinfo mSessionUserinfo = new sessionUserinfo();
	        //OpenID
			mSessionUserinfo.setWeixin_openid("openid0123456789");
			//NickName
			mSessionUserinfo.setWeixin_nickname("nickname_cc");
			//HeadUrl
			mSessionUserinfo.setWeixin_headimgurl("http://img.jsqq.net/uploads/allimg/150111/1_150111080328_19.jpg");
			//City
			mSessionUserinfo.setWeixin_city("杭州");
			//Country
			mSessionUserinfo.setWeixin_country("中国");
			//Province
			mSessionUserinfo.setWeixin_province("浙江");
			//点击菜单0
			mSessionUserinfo.setUser_weixin_clickmenuitem("0");
			//电话号码
	//		System.out.print("手机号码"+mSessionUserinfo.getUser_phone());
	//		if (mSessionUserinfo.getUser_phone() == null)
	//		{
				mSessionUserinfo.setUser_phone("13900000000");
	//		}
			//数据保存
			model.addAttribute("sessionUserinfo", mSessionUserinfo);
		}
		ModelAndView mav =new ModelAndView("login");
		
		
		
//		mav.addObject("Openid",mSessionUserinfo.getWeixin_openid());
//		mav.addObject("Headimgurl",mSessionUserinfo.getWeixin_headimgurl());
//		mav.addObject("City",mSessionUserinfo.getWeixin_city());
//		mav.addObject("Country",mSessionUserinfo.getWeixin_country());
//		mav.addObject("Province",mSessionUserinfo.getWeixin_province());
		
		
		return mav;
	}
}
