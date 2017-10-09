package com.weixin.menu.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.weixin.userinfo.mapper.UserinfoModelMapper;
import com.weixin.userinfo.model.UserinfoModel;

import BasicSession.sessionUserinfo;

@RestController
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class MyOrderMenuController {

	@Autowired
	UserinfoModelMapper userinfoModelMapper;
	
	@RequestMapping(value="/MyOrderMenu", method=RequestMethod.GET)
	public ModelAndView MyOrderMenu(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException, WxErrorException{
		ModelAndView mav = null;
	
		//定义返回页面类
		//显示日志信息
		utility.Log.logger.error("MyOrderMenu:主菜单");
		
		//判断是否存在session
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			utility.Log.logger.error("MyOrderMenu:sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
		}
		else
		{
			utility.Log.logger.error("MyOrderMenu:没有session");
		}
		
		//实例化 统一业务API入口
	    IService iService = new WxService();
	    //设置返回参数
	    WxOAuth2AccessTokenResult result = null;
	    
	    try {
            result = iService.oauth2ToGetAccessToken(request.getParameter("code"));
            
            //获取到微信服务器端回传的相关信息
            utility.Log.logger.error("MyOrderMenu:TOKEN:"+result.getAccess_token());
            utility.Log.logger.error("MyOrderMenu:OPENID:"+result.getOpenid());
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
    		utility.Log.logger.error("MyOrderMenu"+user.getNickname());
    		utility.Log.logger.error("MyOrderMenu"+user.getOpenid());
    		utility.Log.logger.error("MyOrderMenu"+user.getHeadimgurl());
    		utility.Log.logger.error("MyOrderMenu"+user.getCity());
    		utility.Log.logger.error("MyOrderMenu"+user.getCountry());
    		utility.Log.logger.error("MyOrderMenu"+user.getProvince());
            
            //针对绑定功能进行判断，如果没有绑定过的人员，直接出现绑定页面，如果已经绑定过了，直接显示人员信息和解绑页面
            UserinfoModel mUserinfoModelMapper = userinfoModelMapper.selectByUserUid(user.getOpenid());
            if (mUserinfoModelMapper == null)
            {
            	utility.Log.logger.error("MyOrderMenu:没有绑定过信息:"+user.getOpenid());
            	mSessionUserinfo.setUser_phone("");
            	//没有绑定过，显示绑定页面
                //设置菜单点击0
                mSessionUserinfo.setUser_weixin_clickmenuitem("0");
        		//绑定页面
        		model.addAttribute("sessionUserinfo", mSessionUserinfo);
        		mav =new ModelAndView("binding");
            }
            else
            {
            	utility.Log.logger.error("MyOrderMenu:已经绑定过:"+user.getOpenid());
            	mSessionUserinfo.setUser_phone(mUserinfoModelMapper.getWxPhone());
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
