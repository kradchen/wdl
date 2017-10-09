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

import com.weixin.product.mapper.ProductModelMapper;
import com.weixin.product.model.ProductModel;

import paramter.InputParam;
import paramter.OutPutParam;

@RestController
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")
public class GetProductRestController {

	@Autowired
	ProductModelMapper productModelMapper;
	
	@RequestMapping(value="/getProduct", method=RequestMethod.POST)
	public OutPutParam GetProduct(Model model,HttpServletRequest request,HttpServletResponse response,@RequestBody InputParam inputParam) {
		OutPutParam mRet = new OutPutParam();
//		Map modelMap = model.asMap();
//		if (modelMap.get("sessionUserinfo")!= null){
//			//utility.Log.logger.error("GetUserDataRestController：sessionUserinfo:" + " -- " + ((sessionUserinfo)modelMap.get("sessionUserinfo")).getWeixin_openid());
//		}
//		else
//		{
//			//utility.Log.logger.error("GetUserDataRestController：没有session");
//			mRet.setFailure();
//			mRet.setMessage("Seesion out time");
//			return mRet;
//		}
		
		//直接获取列表数据
		List<ProductModel> mProductModel = productModelMapper.selectAll();
		
		mRet.setBody(mProductModel);
		mRet.setSuccess();
		
		return mRet;
	}
	
}
