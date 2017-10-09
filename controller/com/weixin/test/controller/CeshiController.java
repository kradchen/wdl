package com.weixin.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.weixin.weixinuserinfo.mapper.IWeixinUserinfoModelMapper;
import com.weixin.weixinuserinfo.model.WeixinUserinfoModel;

@RestController
@RequestMapping(value = "/ceshi")
public class CeshiController {
	@Autowired
	IWeixinUserinfoModelMapper weixinUserinfoModelMapper;
	
	@RequestMapping(value = "/ceshi", method = RequestMethod.GET)
	public ModelAndView ceshi(HttpServletRequest request) {
		WeixinUserinfoModel mWeixinUserinfoModel = weixinUserinfoModelMapper.selectByOpenID("1");
		
		ModelAndView mav =new ModelAndView("callback");
		
		if (mWeixinUserinfoModel != null)
		{
			mWeixinUserinfoModel.getWeixinNickname();
			mav.addObject("Nickname",mWeixinUserinfoModel.getWeixinNickname());
		}
		return mav;
	}
}
