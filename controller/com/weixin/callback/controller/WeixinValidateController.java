package com.weixin.callback.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;


@RestController
@Transactional
@RequestMapping(value = "/Validate")
public class WeixinValidateController {
	@RequestMapping(value = "/doValidate", method = RequestMethod.GET)
	public void doValidateGet(HttpServletRequest request,HttpServletResponse response) {
		utility.Log.logger.error("WDL");
		
	    //实例化 统一业务API入口
	    IService iService = new WxService();
	        // 验证服务器的有效性
	        PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String signature = request.getParameter("signature");
	        String timestamp = request.getParameter("timestamp");
	        String nonce = request.getParameter("nonce");
	        String echostr = request.getParameter("echostr");
	        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
	            out.print(echostr);
	        }
	}
	
	
	
	
	
}
