package com.weixin.reg.controller;

//这个接口在微信主菜单直接调用这个接口，根据微信提供的信息，直接在session中填写OpenID、如果已经绑定手机号码等信息。


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.weixin.weixinuserinfo.mapper.IWeixinUserinfoModelMapper;
import com.weixin.weixinuserinfo.model.WeixinUserinfoModel;

import BasicSession.sessionUserinfo;

@RestController
@Transactional
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class BindingWeixinUserController {

	@Autowired
	IWeixinUserinfoModelMapper weixinUserinfoModelMapper;
	
	//绑定菜单调用的防范，菜单值为0
	
	@RequestMapping(value="/BindingWeixinUser", method=RequestMethod.GET)
	public ModelAndView BindingWeixinUser(Model model,HttpServletRequest request,HttpServletResponse response){
		
		//定义返回页面类
		ModelAndView mav;
		//显示日志信息
		System.out.println("MainCallback-菜单回调");
		
		//判断是否存在session
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			System.out.println("sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
		}
		else
		{
			System.out.println("没有session");
		}
		
		//实例化 统一业务API入口
	    IService iService = new WxService();
	    //设置返回参数
	    WxOAuth2AccessTokenResult result = null;
	    
	    try {
            result = iService.oauth2ToGetAccessToken(request.getParameter("code"));
            
            //获取到微信服务器端回传的相关信息
            System.out.println(result.getAccess_token());
            System.out.println(result.getOpenid());
        } catch (WxErrorException e) {
            // 如果出错直接转入到出错页面
            e.printStackTrace();
            mav =new ModelAndView("error");
            return mav;
        }
	    
	    //定义微信用户类
	    WxUser user = null;
	    
	    try {
	    	
	    	//获取微信用户信息
            user = iService.oauth2ToGetUserInfo(result.getAccess_token(), new WxUserGet("openid", WxConsts.LANG_CHINA));
            //存放微信用户信息
            sessionUserinfo mSessionUserinfo = new sessionUserinfo();
            //OpenID
    		mSessionUserinfo.setWeixin_openid(user.getOpenid());
    		//NickName
    		mSessionUserinfo.setWeixin_nickname(user.getNickname());
    		//HeadUrl
    		mSessionUserinfo.setWeixin_headimgurl(user.getHeadimgurl());
    		//City
    		mSessionUserinfo.setWeixin_city(user.getCity());
    		//Country
    		mSessionUserinfo.setWeixin_country(user.getCountry());
    		//Province
    		mSessionUserinfo.setWeixin_province(user.getProvince());
    		
    		//日志显示 , 可以传入自定义参数
            System.out.println(user.getNickname());
            System.out.println(user.getOpenid());
            System.out.println(user.getHeadimgurl());
            System.out.println(user.getCity());
            System.out.println(user.getCountry());
            System.out.println(user.getProvince());
            //System.out.println(request.getParameter("xxx"));
            
            //针对绑定功能进行判断，如果没有绑定过的人员，直接出现绑定页面，如果已经绑定过了，直接显示人员信息和解绑页面
            WeixinUserinfoModel mWeixinUserinfoModelMapper = weixinUserinfoModelMapper.selectByOpenID(user.getOpenid());
            if (mWeixinUserinfoModelMapper == null)
            {
            	mSessionUserinfo.setUser_phone("");
            	//没有绑定过，显示绑定页面
            	
            	//如果已经进行过绑定，在session中写入用户的信息
	    		//mav.addObject("Nickname",user.getNickname());
	    		//mav.addObject("Openid",user.getOpenid());
	    		//mav.addObject("Headimgurl",user.getHeadimgurl());
	    		//mav.addObject("City",user.getCity());
	    		//mav.addObject("Country",user.getCountry());
	    		//mav.addObject("Province",user.getProvince());
                //设置菜单点击0
                mSessionUserinfo.setUser_weixin_clickmenuitem("0");
        		//绑定页面
        		model.addAttribute("sessionUserinfo", mSessionUserinfo);
        		mav =new ModelAndView("index");
            }
            else
            {
            	mSessionUserinfo.setUser_phone(mWeixinUserinfoModelMapper.getWeixinPhone());
            	//已经绑定过，显示我的信息
                mSessionUserinfo.setUser_weixin_clickmenuitem("0");
        		//绑定页面
        		model.addAttribute("sessionUserinfo", mSessionUserinfo);
        		mav =new ModelAndView("myInfo");
            }
    		return mav;

        } catch (WxErrorException e) {
            e.printStackTrace();
            mav =new ModelAndView("error");
            return mav;
        }
	}
}
