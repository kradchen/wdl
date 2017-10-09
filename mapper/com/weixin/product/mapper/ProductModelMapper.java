package com.weixin.product.mapper;

import java.util.List;

import com.weixin.product.model.ProductModel;

public interface ProductModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductModel record);

    int insertSelective(ProductModel record);

    ProductModel selectByPrimaryKey(Integer id);
    
    ProductModel selectByPrdUid(String prdUid);
    
    List<ProductModel> selectAll();

    int updateByPrimaryKeySelective(ProductModel record);

    int updateByPrimaryKey(ProductModel record);
}