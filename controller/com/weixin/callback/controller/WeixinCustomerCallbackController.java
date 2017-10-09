package com.weixin.callback.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;

@RestController
@Transactional
@RequestMapping(value = "/Callback")
public class WeixinCustomerCallbackController {
	
	@RequestMapping(value="/CustomerCallback", method=RequestMethod.GET)
	public ModelAndView CustomerCallback(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav;
		
		System.out.println("CustomerCallback:菜单回调");
		
		//实例化 统一业务API入口
	    IService iService = new WxService();
	    
	    WxOAuth2AccessTokenResult result = null;
	    
	    try {
            result = iService.oauth2ToGetAccessToken(request.getParameter("code"));
            
            
            System.out.println(result.getAccess_token());
            System.out.println(result.getOpenid());
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    WxUser user = null;
	    
	    try {
            user = iService.oauth2ToGetUserInfo(result.getAccess_token(), new WxUserGet("openid", WxConsts.LANG_CHINA));
            
            System.out.println(user.getNickname()+"\r\n");
            System.out.println(user.getOpenid()+"\r\n");
            System.out.println(user.getHeadimgurl()+"\r\n");
            System.out.println(user.getCity()+"\r\n");
            System.out.println(user.getCountry()+"\r\n");
            System.out.println(user.getProvince()+"\r\n");
            System.out.println(request.getParameter("xxx")+"\r\n");
            
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
		mav =new ModelAndView("index");
		mav.addObject("Nickname",user.getNickname());
		mav.addObject("Openid",user.getOpenid());
		mav.addObject("Headimgurl",user.getHeadimgurl());
		mav.addObject("City",user.getCity());
		mav.addObject("Country",user.getCountry());
		mav.addObject("Province",user.getProvince());
		return mav;
	}
	
}
