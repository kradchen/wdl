package com.weixin.userinfo.mapper;

import com.weixin.userinfo.model.UserinfoModel;

public interface UserinfoModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserinfoModel record);

    int insertSelective(UserinfoModel record);

    UserinfoModel selectByPrimaryKey(Integer id);
    
    UserinfoModel selectByUserSecretKey(String userSecret);
    
    UserinfoModel selectByUserUid(String userUid);
    
    UserinfoModel selectCustomerCntByUid(String userUid);
    
    int updateByPrimaryKeySelective(UserinfoModel record);

    int updateByPrimaryKey(UserinfoModel record);
    
    int updateByUserUid(UserinfoModel record);
    
    int updateAgentByUserUid(UserinfoModel record);
    
    int updatePhoneByUserUid(UserinfoModel record);
}