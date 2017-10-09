package com.weixin.reg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import BasicSession.sessionUserinfo;
import paramter.InputParam;
import paramter.OutPutParam;
import publib.GenCode;
import utility.SerializableUtility;

@RestController
@Transactional
@SessionAttributes(value={"sessionUserinfo"})

@RequestMapping(value = "/reg")
public class SendSMSRestController {
	
	@RequestMapping(value = "/sendVerificationSMS", method = RequestMethod.POST)
	public OutPutParam sendSMS(@RequestBody InputParam inputParam, HttpServletRequest request , Model model) throws Exception {
	
		String mSMS = "";
		OutPutParam mRet = new OutPutParam();
		
		//判断是否存在session
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			System.out.println("sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
		}
		else
		{
			System.out.println("没有session");
			mRet.setFailure();
			mRet.setMessage("Seesion out time");
			return mRet;
		}
	
		//获取传入的手机号码
		HashMap<String, String> mInputParams = SerializableUtility.DeserializeParamWithGenericity(inputParam.getParam()); 
		String mPhone = mInputParams.get("phone").toString() ;
		
		//发送短信
		
		
		//判断传入手机号码 
		((sessionUserinfo)modelMap.get("sessionUserinfo")).setUser_phone(mPhone);
		System.out.print("手机号码："+((sessionUserinfo)modelMap.get("sessionUserinfo")).getUser_phone());
		//放入随机数
		String mValidatecode = GenCode.getFourRandom();
		((sessionUserinfo)modelMap.get("sessionUserinfo")).setWeixin_validatecode(mValidatecode);
		System.out.print("随机数字"+mValidatecode);
		
		//发送短信
		
		SMS.SendValidateSMS_Ali.SendValidateSMS(mPhone, mValidatecode);
		
		//
		HashMap<String, String> mParams = new HashMap<String, String>();
		mParams.put("ret", "1");
		mRet.setBody(mParams);
		mRet.setSuccess();
		return mRet;
	}
}
