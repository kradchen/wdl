package com.weixin.menu.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.soecode.wxtools.exception.WxErrorException;

@RestController
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class GetActListMenuController {

	@RequestMapping(value="/getActListMenu", method=RequestMethod.GET)
	public ModelAndView GetActListMenu(Model model,HttpServletRequest request,HttpServletResponse response) throws WxErrorException {
		ModelAndView mav = null;
		utility.Log.logger.error("GetActListMenuController:开始处理");
		
		mav = new ModelAndView("actlist");
		
		return mav;
	}
}
