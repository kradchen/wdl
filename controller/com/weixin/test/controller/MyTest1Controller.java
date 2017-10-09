package com.weixin.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.weixin.userinfo.mapper.UserinfoModelMapper;
import com.weixin.userinfo.model.UserinfoModel;

@RestController
@RequestMapping(value = "/")

public class MyTest1Controller {
	@Autowired
	UserinfoModelMapper userinfoModelMapper;
	
	@RequestMapping(value = "/mytest1", method = RequestMethod.GET)
	public ModelAndView mytest1(HttpServletRequest request) {
		
	
		
		System.out.print("直接新增");
		//数据库中没有相关用户，直接新增
		UserinfoModel mAddUserinfoModel = new UserinfoModel();
		//用户所属ID
		mAddUserinfoModel.setAgentUid("0");
		//设置用户的上一级用户的UID
		mAddUserinfoModel.setMergeKey("0");
		//设置用户的OPENID
		mAddUserinfoModel.setUserUid("12345");
		//用户姓名
		mAddUserinfoModel.setUserName("-");
		//用户类型
		mAddUserinfoModel.setUserType("customer");
		
		mAddUserinfoModel.setMobile("");
		System.out.print(mAddUserinfoModel.toString());
		System.out.print("----");
		//新增
		userinfoModelMapper.insert(mAddUserinfoModel);
		
		
		//新增
		userinfoModelMapper.insert(mAddUserinfoModel);
		
       ModelAndView mav =new ModelAndView("index");
		
		return mav;
	}
	
	

}
