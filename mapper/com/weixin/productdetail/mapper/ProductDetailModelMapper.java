package com.weixin.productdetail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weixin.productdetail.model.ProductDetailModel;

public interface ProductDetailModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductDetailModel record);

    int insertSelective(ProductDetailModel record);

    ProductDetailModel selectByPrimaryKey(Integer id);
    
    List<ProductDetailModel> selectByUid(String userUid);
    
    List<ProductDetailModel> selectByUidPrdID(@Param("userUid")String userUid,@Param("prdUid")String prdUid);

    int updateByPrimaryKeySelective(ProductDetailModel record);

    int updateByPrimaryKey(ProductDetailModel record);
}