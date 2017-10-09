package com.weixin.callback.controller;

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

@RequestMapping(value = "/Callback")
public class WeixinMainCallbackController {
	
	@Autowired
	IWeixinUserinfoModelMapper weixinUserinfoModelMapper;
	
	@RequestMapping(value="/MainCallback", method=RequestMethod.GET)
	public ModelAndView MainCallback(Model model,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav;
		
		utility.Log.logger.error("WeixinMainCallbackController:MainCallback-菜单回调");
		
		//mav =new ModelAndView("index");
		
		//return mav;
		
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			utility.Log.logger.error("WeixinMainCallbackController:sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
		}
		else
		{
			utility.Log.logger.error("WeixinMainCallbackController:没有session");
		}
		
		
		//实例化 统一业务API入口
	    IService iService = new WxService();
	    
	    WxOAuth2AccessTokenResult result = null;
	    
	    try {
            result = iService.oauth2ToGetAccessToken(request.getParameter("code"));
            
            
            utility.Log.logger.error("WeixinMainCallbackController:TOKEN:"+result.getAccess_token());
            utility.Log.logger.error("WeixinMainCallbackController:OPENID:"+result.getOpenid());
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    WxUser user = null;
	    
	    try {
            user = iService.oauth2ToGetUserInfo(result.getAccess_token(), new WxUserGet("openid", WxConsts.LANG_CHINA));
            
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
    		//数据保存
    		model.addAttribute("sessionUserinfo", mSessionUserinfo);
    		
    		//日志显示
    		utility.Log.logger.error("WeixinMainCallbackController:"+user.getNickname());
    		utility.Log.logger.error("WeixinMainCallbackController:"+user.getOpenid());
    		utility.Log.logger.error("WeixinMainCallbackController:"+user.getHeadimgurl());
    		utility.Log.logger.error("WeixinMainCallbackController:"+user.getCity());
    		utility.Log.logger.error("WeixinMainCallbackController:"+user.getCountry());
    		utility.Log.logger.error("WeixinMainCallbackController:"+user.getProvince());
    		utility.Log.logger.error("WeixinMainCallbackController:"+request.getParameter("xxx"));
            
            //针对绑定功能进行判断，如果没有绑定过的人员，直接出现绑定页面，如果已经绑定过了，直接显示人员信息和解绑页面
            WeixinUserinfoModel mWeixinUserinfoModelMapper = weixinUserinfoModelMapper.selectByOpenID(user.getOpenid());
            
            if (mWeixinUserinfoModelMapper == null)
            {
	          //mav =new ModelAndView("dist/views/used/info");
	    	    mav =new ModelAndView("index");
	    		mav.addObject("Nickname",user.getNickname());
	    		mav.addObject("Openid",user.getOpenid());
	    		mav.addObject("Headimgurl",user.getHeadimgurl());
	    		mav.addObject("City",user.getCity());
	    		mav.addObject("Country",user.getCountry());
	    		mav.addObject("Province",user.getProvince());
	    		return mav;
            }
            else
            {
            	mav =new ModelAndView("index");
	    		mav.addObject("Nickname",user.getNickname());
	    		mav.addObject("Openid",user.getOpenid());
	    		mav.addObject("Headimgurl",user.getHeadimgurl());
	    		mav.addObject("City",user.getCity());
	    		mav.addObject("Country",user.getCountry());
	    		mav.addObject("Province",user.getProvince());
	    		return mav;
            	
            }
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            mav =new ModelAndView("error");
            return mav;
        }
	}
}
