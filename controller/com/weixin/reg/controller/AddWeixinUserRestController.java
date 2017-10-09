package com.weixin.reg.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.weixin.userinfo.mapper.UserinfoModelMapper;
import com.weixin.userinfo.model.UserinfoModel;

import BasicSession.sessionUserinfo;
import paramter.InputParam;
import paramter.OutPutParam;
import utility.SerializableUtility;

@RestController
@Transactional
@SessionAttributes(value={"sessionUserinfo"})

@RequestMapping(value = "/reg")
public class AddWeixinUserRestController {
	
	@Autowired
	UserinfoModelMapper userinfoModelMapper;
	
	@RequestMapping(value = "/addUserPhone", method = RequestMethod.POST)
	public OutPutParam addUserPhone(@RequestBody InputParam inputParam, HttpServletRequest request , Model model) {
		
		OutPutParam mRet = new OutPutParam();
		
		//判断用户信息是否已经在用户表里面
		//判断是否存在session
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			System.out.println("sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
		}
		else
		{
			System.out.println("没有session");
			mRet.setFailure();
			return mRet;
		}
		
		//传递过来的一个code和session中的数据比对。
		HashMap<String, String> mInputParams = SerializableUtility.DeserializeParamWithGenericity(inputParam.getParam()); 
		String mCode = mInputParams.get("code").toString() ;
		String mVCode = ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_validatecode();
		HashMap<String, String> mParams = new HashMap<String, String>();
		System.out.println(mCode + "---" + mVCode);
		if (mCode.equals(mVCode))
		{
			//查找记录
			UserinfoModel mUserinfo = userinfoModelMapper.selectByUserUid( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
			System.out.println("mUserinfo"+mUserinfo);
			mUserinfo.setMobile( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getUser_phone() );
			mUserinfo.setWxNickname( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_nickname() );
			mUserinfo.setWxOpenid( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
			mUserinfo.setWxPhone( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getUser_phone() );
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			mUserinfo.setWxSubTime(df.format(new Date()));
			
			userinfoModelMapper.updatePhoneByUserUid(mUserinfo);
			mParams.put("ret", "1");
			mRet.setBody(mParams);
			mRet.setMessage("新增成功");
			mRet.setSuccess();
			
		}
		else
		{
			mRet.setMessage("校验码比对失败");
			mParams.put("ret", "0");
			mRet.setBody(mParams);
			mRet.setFailure();
		}
		
		return mRet;
	}
}
