package com.weixin.test.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value = "/Weixin")
public class WeixinBindController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav =new ModelAndView("index");

		return mav;
	}
	
	@RequestMapping(value = "/setUserInfo", method = RequestMethod.POST)
	public ModelAndView setUserInfo(HttpServletRequest request) {
		   return null;
	}

}
