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
import org.springframework.web.servlet.ModelAndView;

import com.weixin.product.mapper.ProductModelMapper;
import com.weixin.product.model.ProductModel;
import com.weixin.productdetail.mapper.ProductDetailModelMapper;
import com.weixin.productdetail.model.ProductDetailModel;

import BasicSession.sessionUserinfo;
import paramter.InputParam;
import paramter.OutPutParam;

@RestController
@SessionAttributes(value={"sessionUserinfo"})
@RequestMapping(value = "/")

public class GetProductDetailController {
	@Autowired
	ProductModelMapper productModelMapper;
	
	//获取我的相关信息
		@RequestMapping(value="/getProductDetail", method=RequestMethod.GET)
		public ModelAndView GetProductDetail(Model model,HttpServletRequest request,HttpServletResponse response) {
			ModelAndView mav = null;

			//根据传入的参数uid来获取线路的详细信息
			String mUid = request.getParameter("uuid");
			if (mUid == null || mUid.isEmpty())
			{
				mav =new ModelAndView("info");
				return mav;
				
			}
			ProductModel mProductDetailModel = productModelMapper.selectByPrdUid( mUid );
			if (mProductDetailModel == null)
			{
				mav =new ModelAndView("info");
				return mav;
			}
			
			mav =new ModelAndView("product/"+mProductDetailModel.getPageName()+"/"+mProductDetailModel.getPageName());
			//加入参数 //"'/wdl/addProduct?id="+mProductDetailModel.getPrdUid()+"'";
			String mParam = 
			"'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe1aae2706bb9023d&redirect_uri=http%3A%2F%2Fwdl666.com%2Fwdl%2FaddProduct?id="+mProductDetailModel.getPrdUid()+"&response_type=code&scope=snsapi_userinfo&state=#wechat_redirect'";
			
			
			mav.addObject("AddPrdUid",mParam);
			utility.Log.logger.error("GetProductDetailController:param:"+mParam);
			return mav;
		}
}
