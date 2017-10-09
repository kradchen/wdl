package com.weixin.binding.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class GetBindingPhoneController {
	@RequestMapping(value="/GetBindingPhone", method=RequestMethod.GET)
	public ModelAndView GetBindingPhone(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mav;
		mav =new ModelAndView("boundphone");
		return mav;
	}
}
