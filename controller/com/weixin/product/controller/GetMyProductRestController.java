package com.weixin.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.weixin.productdetail.mapper.ProductDetailModelMapper;
import com.weixin.productdetail.model.ProductDetailModel;

import BasicSession.sessionUserinfo;
import paramter.InputParam;
import paramter.OutPutParam;

@RestController
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class GetMyProductRestController {

	@Autowired
	ProductDetailModelMapper productDetailModelMapper;
	
	//获取我的相关信息
		@RequestMapping(value="/getMyProduct", method=RequestMethod.POST)
		public OutPutParam GetMyProduct(Model model,HttpServletRequest request,HttpServletResponse response,@RequestBody InputParam inputParam) {
			OutPutParam mRet = new OutPutParam();
			Map modelMap = model.asMap();
//			if (modelMap.get("sessionUserinfo")!= null){
//				//utility.Log.logger.error("GetUserDataRestController：sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
//			}
//			else
//			{
//				//utility.Log.logger.error("GetUserDataRestController：没有session");
//				mRet.setFailure();
//				mRet.setMessage("Seesion out time");
//				return mRet;
//			}
			List<ProductDetailModel> mProductDetailModel = productDetailModelMapper.selectByUid( ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid() );
			
			mRet.setBody(mProductDetailModel);
			mRet.setSuccess();
			return mRet;
		}
}
