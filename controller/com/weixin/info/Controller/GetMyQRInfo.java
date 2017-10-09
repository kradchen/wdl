package com.weixin.info.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxQrcode;
import com.soecode.wxtools.bean.WxQrcode.WxQrActionInfo;
import com.soecode.wxtools.bean.WxQrcode.WxQrActionInfo.WxScene;
import com.soecode.wxtools.bean.result.QrCodeResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.weixin.userinfo.mapper.UserinfoModelMapper;
import com.weixin.userinfo.model.UserinfoModel;

import BasicSession.sessionUserinfo;

//用于获取二维码图片
@RestController
@Transactional
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class GetMyQRInfo {
	
	@Autowired
	UserinfoModelMapper userinfoModelMapper;
	
	@RequestMapping(value="/GetMyQRInfo", method=RequestMethod.GET)
	public void GetMyQRInfo(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		FileInputStream fis = null;
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		Map modelMap = model.asMap();
		utility.Log.logger.error("GetMyQRInfo:菜单回调");
		
		//实例化 统一业务API入口
	    IService iService = new WxService();
	    //设置返回参数
	    //WxOAuth2AccessTokenResult result = null;
	    
	    utility.Log.logger.error("GetMyQRInfo:APPID："+WxConfig.getInstance().getAppId()+"\r\n");
	    utility.Log.logger.error("GetMyQRInfo:AppSecret:"+WxConfig.getInstance().getAppSecret()+"\r\n");
	    utility.Log.logger.error("GetMyQRInfo:CODE:"+request.getParameter("code")+"\r\n");
		//result = iService.oauth2ToGetAccessToken(request.getParameter("code"));
		
		//获取到微信服务器端回传的相关信息
		//System.out.println("getAccess_token:"+result.getAccess_token());
		//System.out.println("getOpenid:"+result.getOpenid());
		//获取到当前用户的ID，使用这个ID为参数，生成二维码
		WxQrcode code = new WxQrcode();
		code.setAction_name(WxConsts.QR_CODE_LIMIT_STR_SCENE);
		UserinfoModel mMyKey = userinfoModelMapper.selectByUserUid(((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
		
		code.setAction_info(new WxQrActionInfo(new WxScene(mMyKey.getUserSecret())));
		
		code.setExpire_seconds(720);
		
		try {
		    QrCodeResult qrresult = iService.createQrCode(code);
		    utility.Log.logger.error("GetMyQRInfo:图片URL"+qrresult.getUrl());
		    String mPathPic= "";
		    if (System.getProperty("os.name").toLowerCase().substring(0, 3).equals("win"))
		    {
		    	mPathPic = "c://soft";
		    }
		    else
		    {
		    	mPathPic = "/usr/temp";
		    }
		    utility.Log.logger.error("GetMyQRInfo:图片本地路径"+mPathPic);
		    File file = iService.downloadQrCode(new File(mPathPic), qrresult.getTicket());
		    //
		    fis = new FileInputStream(file);
		    byte[] b = new byte[fis.available()];
		    fis.read(b);
		    out.write(b);
		    out.flush();
		} catch (WxErrorException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
}
