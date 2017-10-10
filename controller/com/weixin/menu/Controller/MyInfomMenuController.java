package com.weixin.menu.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;
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
public class MyInfomMenuController {

	@Autowired
	UserinfoModelMapper userinfoModelMapper;
	
	@RequestMapping(value="/MyInfoMenu", method=RequestMethod.GET)
	public ModelAndView MyInfoMenu(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException, WxErrorException{
		ModelAndView mav = null;
		
//		List<String> jsApiList = new ArrayList<>();
//		//需要用到哪些JS SDK API 就设置哪些
//		jsApiList.add("chooseImage");//拍照或从手机相册中选图接口
//		jsApiList.add("onMenuShareQZone");//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
//		//实例化 统一业务API入口
//	    IService iService = new WxService();
//	    
//		try {
//	        //把config返回到前端进行js调用即可。
//	        WxJsapiConfig config = iService.createJsapiConfig("调用jssdk的完整url", jsApiList);
//	        System.out.println(config.toString());
//	    } catch (WxErrorException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    }
		
		
		//判断是否存在session
		Map modelMap = model.asMap();
		if (modelMap.get("sessionUserinfo")!= null){
			utility.Log.logger.error("sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
			
			mav =new ModelAndView("myInfo");
    		mav.addObject("Headimgurl",((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_headimgurl());
    		mav.addObject("Nickname",((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_nickname());
    		
    		//根据OpenID获取到用户的信息
			UserinfoModel mUserModel = userinfoModelMapper.selectByUserUid(((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
    		if (mUserModel != null)
    		{
    			mav.addObject("userType" , mUserModel.getUserType());
    			mav.addObject("userPhone",mUserModel.getMobile());
    			//如果非客户，显示客户数量
    			if (!mUserModel.getUserType().toLowerCase().equals("customer"))
    			{
	    			UserinfoModel mUserCnt = userinfoModelMapper.selectCustomerCntByUid(((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
	    			//String mExport = "<p class=\"box-company\">"+mUserCnt.getId().toString()+"</p>";
	    			String mExport = "<span class=\"box-gz\">关注者"+mUserCnt.getId().toString()+"</span>";
	    			mav.addObject("ExportCnt" , mExport);
    			}
    			else
    			{
    				//显示我的客户MySaller
    				utility.Log.logger.error("我的上级代码："+mUserModel.getMergeKey());
    				UserinfoModel mMySaler = userinfoModelMapper.selectByUserUid(mUserModel.getMergeKey());
    				if (mMySaler != null)
    				{
    					//mav.addObject("MySaller" , "<div class=\"share\" >我的客户经理:" + mMySaler.getUserName() + "</div>" );
    					mav.addObject("MySaller" , mMySaler.getUserName() );
    					if (mMySaler.getMobile() != null)
    					{
    						//mav.addObject("MySallerPhone" , "<div class=\"share\" >手机:" + mMySaler.getMobile() + "</div>");
    						mav.addObject("MySallerPhone" , mMySaler.getMobile() );
        					}
    				}
    			}
    		}
		}
		else
		{
			utility.Log.logger.error("MyInfomMenuController:没有session");
		
			//定义微信用户类
			IService iService = new WxService();
		    WxUser user = null;
		    WxOAuth2AccessTokenResult result = null;
		    utility.Log.logger.error("MyInfomMenuController:AppID:"+WxConfig.getInstance().getAppId());
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
			
			//根据OpenID获取到用户的信息
			UserinfoModel mUserModel = userinfoModelMapper.selectByUserUid(mSessionUserinfo.getWeixin_openid());
    		model.addAttribute("sessionUserinfo", mSessionUserinfo);
    		mav =new ModelAndView("myInfo");
    		mav.addObject("Headimgurl",mSessionUserinfo.getWeixin_headimgurl());
    		mav.addObject("Nickname",mSessionUserinfo.getWeixin_nickname());
    		mav.addObject("userPhone",mUserModel.getMobile());
    		if (mUserModel != null)
    		{
    			mav.addObject("userType" , mUserModel.getUserType());
    			//如果非客户，显示客户数量
    			if (!mUserModel.getUserType().toLowerCase().equals("customer"))
    			{
	    			UserinfoModel mUserCnt = userinfoModelMapper.selectCustomerCntByUid(mSessionUserinfo.getWeixin_openid());
	    			//String mExport = "<p class=\"box-company\">"+mUserCnt.getId().toString()+"</p>";
	    			String mExport = "<span class=\"box-gz\">关注者"+mUserCnt.getId().toString()+"</span>";
	    			mav.addObject("ExportCnt" , mExport);
    			}
    			else
    			{
    				//显示我的客户MySaller
    				utility.Log.logger.error("我的上级代码："+mUserModel.getMergeKey());
    				//utility.Log.logger.error("OS:"+System.getProperty("os.name").toLowerCase().substring(0, 3));
    				UserinfoModel mMySaler = userinfoModelMapper.selectByUserUid(mUserModel.getMergeKey());
    				if (mMySaler != null)
    				{
    					//mav.addObject("MySaller" , "<div class=\"share\" >我的客户经理:" + mMySaler.getUserName() + "</div>" );
    					mav.addObject("MySaller" , mMySaler.getUserName()  );
    					
    					if (mMySaler.getMobile() != null)
    					{
    						//mav.addObject("MySallerPhone" , "<div class=\"share\" >手机:" + mMySaler.getMobile() + "</div>");
    						mav.addObject("MySallerPhone" , mMySaler.getMobile()  );
    					}
    				}
    			}
    		}
    		//岗位信息
		}
		//返回
		return mav;
		
	}
	
}
