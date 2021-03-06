package com.weixin.callback.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wx.test.DemoHandler;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;

@RestController
@Transactional
@RequestMapping(value = "/weixin")

public class TestWeixinController {

	@RequestMapping(value = "/gettest", method = RequestMethod.GET)
	public void gettest(HttpServletRequest request,HttpServletResponse response) {

		final long serialVersionUID = 1L;
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
	
	
	
	
	
	
	@RequestMapping(value = "/gettest", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
	       request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        // 返回消息给微信服务器
	        PrintWriter out = response.getWriter();
	        // 获取encrypt_type 消息加解密方式标识
	        String encrypt_type = request.getParameter("encrypt_type");
	        // 创建一个路由器
	        IService iService = null;
			WxMessageRouter router = new WxMessageRouter(iService);
	        try {
	            // 判断消息加解密方式，如果是加密模式。encrypt_type==aes
	            if (encrypt_type != null && "aes".equals(encrypt_type)) {
//	              String signature = request.getParameter("signature");
	                String timestamp = request.getParameter("timestamp");
	                String nonce = request.getParameter("nonce");
	                String msg_signature = request.getParameter("msg_signature");
	                // 微信服务器推送过来的加密消息是XML格式。使用WxXmlMessage中的decryptMsg()解密得到明文。
	                WxXmlMessage wx = WxXmlMessage.decryptMsg(request.getInputStream(), WxConfig.getInstance(), timestamp,
	                        nonce, msg_signature);
	                System.out.println("消息：\n " + wx.toString());
	                // 添加规则；这里的规则是指所有消息都交给交给DemoHandler处理
	                // 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
	                // end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
	                router.rule().handler(new DemoHandler()).end();
	                // 把消息传递给路由器进行处理，得到最后一个handler处理的结果
	                WxXmlOutMessage xmlOutMsg = router.route(wx);
	                if (xmlOutMsg != null) {
	                    // 将要返回的消息加密，返回
	                    out.print(WxXmlOutMessage.encryptMsg(WxConfig.getInstance(), xmlOutMsg.toXml(), timestamp, nonce));// 返回给用户。
	                }
	            //如果是明文模式，执行以下语句
	            } else {
	                // 微信服务器推送过来的是XML格式。
	                WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
	                System.out.println("消息：\n " + wx.toString());
	                // 添加规则；这里的规则是指所有消息都交给DemoHandler处理
	                // 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
	                // end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
	                //router.rule().matcher(matcher)
	                router.rule().handler(new DemoHandler()).end();
	                // 把消息传递给路由器进行处理
	                WxXmlOutMessage xmlOutMsg = router.route(wx);
	                if (xmlOutMsg != null)
	                    out.print(xmlOutMsg.toXml());// 因为是明文，所以不用加密，直接返回给用户。
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            out.close();
	        }
	
	
	}
	
	
	
	
	
	
}
