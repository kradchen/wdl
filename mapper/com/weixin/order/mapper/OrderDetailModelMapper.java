package com.weixin.order.mapper;

import com.weixin.order.model.OrderDetailModel;

public interface OrderDetailModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetailModel record);

    int insertSelective(OrderDetailModel record);

    OrderDetailModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDetailModel record);

    int updateByPrimaryKey(OrderDetailModel record);
}