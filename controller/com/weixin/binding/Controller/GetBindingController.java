package com.weixin.binding.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
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
import com.soecode.wxtools.bean.WxQrcode;
import com.soecode.wxtools.bean.WxQrcode.WxQrActionInfo;
import com.soecode.wxtools.bean.WxQrcode.WxQrActionInfo.WxScene;
import com.soecode.wxtools.bean.result.QrCodeResult;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;

@RestController
@Transactional
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class GetBindingController {
	@RequestMapping(value="/GetBinding", method=RequestMethod.GET)
	public ModelAndView GetBinding(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mav;
		System.out.println("appid值："+WxConfig.getInstance().getAppId()+"\r\n");
		System.out.println("appid值："+WxConfig.getInstance().getAppSecret()+"\r\n");
		System.out.println("code值："+request.getParameter("code")+"\r\n");
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
            mav =new ModelAndView("login");
            return mav;
        }
	    
	    
		mav =new ModelAndView("login");
		
		return mav;
		
	}
	
}
