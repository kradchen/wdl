package com.weixin.product.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.SenderContent.Media;
import com.soecode.wxtools.bean.SenderContent.Text;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.result.SenderResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.weixin.product.mapper.ProductModelMapper;
import com.weixin.product.model.ProductModel;
import com.weixin.productdetail.mapper.ProductDetailModelMapper;
import com.weixin.productdetail.model.ProductDetailModel;
import com.weixin.userinfo.mapper.UserinfoModelMapper;
import com.weixin.userinfo.model.UserinfoModel;

import BasicSession.sessionUserinfo;
import controller.BaseProperty;

@RestController
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class AddProdutRestController extends BaseProperty  {
	
	@Autowired
	ProductDetailModelMapper productDetailModelMapper;
	@Autowired
	ProductModelMapper productModelMapper;
	@Autowired
	UserinfoModelMapper userinfoModelMapper;
	
	//增加订单信息
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public ModelAndView AddProduct(Model model,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav;
		//获取是否存在用户信息，如果用户不存在，或者没有绑定手机号码的，直接到手机验证界面
		//判断是否存在session
		Map modelMap = model.asMap();
		String mUUID = request.getParameter("id");
		//从传递的参数，获取相关的UID
		ProductModel mProduct = productModelMapper.selectByPrdUid(mUUID);
		utility.Log.logger.error("AddProdutRestController:mUUID:"+mUUID);
		utility.Log.logger.error("AddProdutRestController:mProduct:"+mProduct);
		
		//判断是否存在这个一下订单
		//同一个线路，同一个人只能下一单
		List<ProductDetailModel> mMyPrdList = productDetailModelMapper.selectByUidPrdID( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid(), mProduct.getPrdUid());
		
		if (!mMyPrdList.isEmpty())
		{
			mav = new ModelAndView("myprdlist");
			return mav;
		
		}
		
		//String mVisitor_name = mProduct.getPageTitle();
		//String mVisitor_idno =  mProduct.getId();
		//String mVisitor_phone = ((sessionUserinfo)modelMap.get("sessionUserinfo")).getUser_phone();
		//String mVisitor_type = "成人";
		String mRoute_uid = mProduct.getPrdUid();
		
		String mOrderNumber = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());

		ProductDetailModel mProductDetailModel = new ProductDetailModel();
		//根据OpenID获取到用户的信息
		UserinfoModel mUserModel = userinfoModelMapper.selectByUserUid( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
				
		String mUUIDr=UUID.randomUUID().toString();
		utility.Log.logger.error("AddProdutRestController:mUUIDr:"+mUUIDr);
		//UID
		mProductDetailModel.setUid(mUUIDr);
		//订单号
		mProductDetailModel.setOrderNo(mOrderNumber);
		//路线ID
		mProductDetailModel.setProductUid(mRoute_uid);
		//使用用户的OpenID
		mProductDetailModel.setUserUid(mUserModel.getUserUid());
		//客户名字
		mProductDetailModel.setVisitorName(mUserModel.getWxNickname());
		//证件号码
		mProductDetailModel.setVisitorId("-");
		//客户身份证
		mProductDetailModel.setVisitorName("-");
		//客户手机号码
		mProductDetailModel.setVisitorPhone(mUserModel.getMobile());
		//客户类型
		mProductDetailModel.setVisitorType("成人");
		//价格
		mProductDetailModel.setVisitorPrice(mProduct.getPrdPrice());
		//时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mProductDetailModel.setCreateDttm(df.format(new Date()));
		//保存 旅游线路
		productDetailModelMapper.insert(mProductDetailModel);
		
	    TemplateSender senderTemplate = new TemplateSender();
	    
	    IService iService = new WxService();
	    
	    WxOAuth2AccessTokenResult result = null;
	    
	    try {
            result = iService.oauth2ToGetAccessToken(request.getParameter("code"));
            utility.Log.logger.error("AddProdutRestController:TOKEN:"+result.getAccess_token());
            utility.Log.logger.error("AddProdutRestController:OPENID:"+result.getOpenid());
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
//	    try {
//	        SenderResult Sendresult = iService.sendAllByOpenid(sender);
//	        utility.Log.logger.error(Sendresult.toString());
//	    } catch (WxErrorException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    }
	    
	    Map<String,Object>data = new LinkedHashMap<String, Object>();
	    
	    Map<String,String> first = new LinkedHashMap<String,String>();
	    first.put("value","您有一个新订单");
	    first.put("color","#0000FF");
	    data.put("first",first);
	      
	    Map<String,String> OrderId = new LinkedHashMap<String,String>();
	    OrderId.put("value",mOrderNumber);
	    OrderId.put("color","#FF0000");
	    data.put("OrderId",OrderId);
	    
	    Map<String,String> ProductId = new LinkedHashMap<String,String>();
	    ProductId.put("value",mProduct.getPageName());
	    ProductId.put("color","#000000");
	    data.put("ProductId",ProductId);
	    //ProductName
	    Map<String,String> ProductName = new LinkedHashMap<String,String>();
	    ProductName.put("value",mProduct.getPageTitle());
	    ProductName.put("color","#000000");
	    data.put("ProductName",ProductName);
	    //remark
	    Map<String,String> remark = new LinkedHashMap<String,String>();
	    remark.put("value","用户电话："+mUserModel.getMobile() + "\r\n"+"用户："+mUserModel.getWxNickname());
	    remark.put("color","#000000");
	    data.put("remark",remark);
	    
	    //向客户发一条消息
	    //senderTemplate.setTouser(mUserModel.getUserUid());
	    //senderTemplate.setTemplate_id(Template_NewOrder);
	    //senderTemplate.setData(data);
	    
	    if (mUserModel.getMergeKey().equals("0"))
	    {
	    	
	    }
	    else
	    {
	    	//向客户经理发送一条信息
	    	senderTemplate.setTouser(mUserModel.getMergeKey());
		    senderTemplate.setTemplate_id(Template_NewOrder);
		    senderTemplate.setData(data);
	    }
	    
	    try {
	        TemplateSenderResult resulttemplate = iService.templateSend(senderTemplate);
	        utility.Log.logger.error("AddProdutRestController：result："+result.toString());
	    } catch (WxErrorException e) {
	        e.printStackTrace();
	    }
	    
		mav = new ModelAndView("myprdlist");
		return mav;
	}
	
	
}
