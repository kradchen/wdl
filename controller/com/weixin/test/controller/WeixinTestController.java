package com.weixin.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.weixin.weixinuserinfo.mapper.IWeixinUserinfoModelMapper;
import com.weixin.weixinuserinfo.model.WeixinUserinfoModel;

@RestController
@Transactional
@RequestMapping(value = "/test")
public class WeixinTestController {

	@Autowired
	IWeixinUserinfoModelMapper weixinUserinfoModelMapper;
	
	
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public ModelAndView test1(HttpServletRequest request,HttpServletResponse response) {
		
		WeixinUserinfoModel mWeixinUserinfoModelMapper = weixinUserinfoModelMapper.selectByPrimaryKey(1);
        
		if (mWeixinUserinfoModelMapper == null)
		{
		System.out.print("null");
		}
		ModelAndView mav =new ModelAndView("dist/views/used/info");
		
		return mav;
		
	}
	
	
}
