package com.weixin.weixinuserinfo.mapper;

import com.weixin.weixinuserinfo.model.WeixinUserinfoModel;

public interface IWeixinUserinfoModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinUserinfoModel record);

    int insertSelective(WeixinUserinfoModel record);

    WeixinUserinfoModel selectByPrimaryKey(Integer id);
    
    WeixinUserinfoModel selectByPhone(String weixinPhone);
    
    WeixinUserinfoModel selectByOpenID(String weixinOpenid);

    int updateByPrimaryKeySelective(WeixinUserinfoModel record);

    int updateByPrimaryKey(WeixinUserinfoModel record);
}