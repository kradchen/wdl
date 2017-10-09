package com.weixin.sys.controller;

import java.util.HashMap;
import java.util.List;
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

import com.weixin.weixinuserinfo.mapper.IWeixinUserinfoModelMapper;
import com.weixin.weixinuserinfo.model.WeixinUserinfoModel;

import BasicSession.sessionUserinfo;
import paramter.InputParam;
import paramter.OutPutParam;
import utility.SerializableUtility;

@RestController
@Transactional
@SessionAttributes(value={"sessionUserinfo"})

@RequestMapping(value = "/sys")
public class GetUserStatusRestController {
	
	@RequestMapping(value = "/getUserStatus", method = RequestMethod.POST)
	public OutPutParam getUserStatus(@RequestBody InputParam inputParam, HttpServletRequest request , Model model) {
		//回传参数
		OutPutParam mRet = new OutPutParam();

		//判断是否存在session
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			System.out.println("sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
		}
		else
		{
			HashMap<String,Object> mRetParams = new HashMap<String,Object>();
			mRetParams.put("check", 0);
			mRetParams.put("code", "0");
			System.out.println("没有session");
			mRet.setBody(mRetParams);
			mRet.setSuccess();;
			mRet.setMessage("Seesion out time");
			return mRet;
		}

		
		mRet.setSuccess();
		return mRet;
	}
}
