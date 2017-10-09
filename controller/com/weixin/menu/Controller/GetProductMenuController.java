package com.weixin.menu.Controller;

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
public class GetProductMenuController {

	@Autowired
	UserinfoModelMapper userinfoModelMapper;
	
	@RequestMapping(value="/GetProductMenu", method=RequestMethod.GET)
	public ModelAndView GetProductMenu(Model model,HttpServletRequest request,HttpServletResponse response) throws WxErrorException {
		ModelAndView mav = null;
		//获取是否存在用户信息，如果用户不存在，或者没有绑定手机号码的，直接到手机验证界面
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			utility.Log.logger.error("GetUserDataRestController：sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
			//获取列表页面
			mav= new ModelAndView("prdlist");
			return mav;
		}
		//定义微信用户类
		IService iService = new WxService();
	    WxUser user = null;
	    WxOAuth2AccessTokenResult result = null;
	    
	    result = iService.oauth2ToGetAccessToken(request.getParameter("code"));
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
		utility.Log.logger.error(user.getNickname());
		utility.Log.logger.error(user.getOpenid());
		utility.Log.logger.error(user.getHeadimgurl());
		utility.Log.logger.error(user.getCity());
		utility.Log.logger.error(user.getCountry());
		utility.Log.logger.error(user.getProvince());
		//生成code在session中，在5分钟内可以用
		mSessionUserinfo.setWeixin_code(request.getParameter("code"));
		
		utility.Log.logger.error("code值："+request.getParameter("code"));
		
		model.addAttribute("sessionUserinfo", mSessionUserinfo);
		
		//根据OpenID获取到用户的信息
		UserinfoModel mUserModel = userinfoModelMapper.selectByUserUid(mSessionUserinfo.getWeixin_openid());
		
		if (mUserModel == null)
		{
			utility.Log.logger.error("GetProductMenu: 没有获取到相关用户信息"+request.getParameter("code"));
			mav= new ModelAndView("");
			return mav;
		}
		
		if (mUserModel.getMobile() == null || mUserModel.getMobile().isEmpty())
		{
			utility.Log.logger.error("GetProductMenu: 用户没有绑定手机，重新绑定");
    		//绑定页面
    		mav =new ModelAndView("bound");
    		mav.addObject("Headimgurl" , user.getHeadimgurl());
    		mav.addObject("Nickname" , user.getNickname());
    		return mav;
		}
		
		//获取列表页面
		mav= new ModelAndView("prdlist");
		return mav;
	}
}
