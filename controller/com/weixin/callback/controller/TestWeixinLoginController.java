package com.weixin.callback.controller;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import utility.HttpRequestUtility;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;



@Controller
@RequestMapping("/weixin")
public class TestWeixinLoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView action(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav;
		//实例化 统一业务API入口
	    IService iService = new WxService();
		try {
            String oauthUrl = iService.oauth2buildAuthorizationUrl("http://wdl666.com/nbUserCenter/weixin/callback",WxConsts.OAUTH2_SCOPE_BASE, "");

            
            System.out.print(oauthUrl+ "\r\n");
            
            System.out.print("-----------------------------------------------------------"+ "\r\n");
            URL getUrl=new URL(oauthUrl);
            HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
            http.setRequestMethod("GET"); 
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            
            
            http.connect();
            InputStream is = http.getInputStream(); 
            int size = is.available(); 
            byte[] b = new byte[size];
            is.read(b);
            
            
            String message = new String(b, "UTF-8");
            
            System.out.print(message+ "\r\n");
            
            
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		mav =new ModelAndView("login");
		
		return mav;
	}
	
	
	@RequestMapping(value="/callback", method=RequestMethod.GET)
	public ModelAndView callback(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav;
		
		System.out.println("回调");
		
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
            
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
		mav =new ModelAndView("callback");
		mav.addObject("Nickname",user.getNickname());
		mav.addObject("Openid",user.getOpenid());
		mav.addObject("Headimgurl",user.getHeadimgurl());
		mav.addObject("City",user.getCity());
		mav.addObject("Country",user.getCountry());
		mav.addObject("Province",user.getProvince());
		return mav;
	}
	
	
	
	
	
	
	
	
	
}
