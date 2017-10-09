package wx.process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.weixin.userinfo.mapper.UserinfoModelMapper;
import com.weixin.userinfo.model.UserinfoModel;
import com.weixin.weixinuserinfo.model.WeixinUserinfoModel;

import BasicSession.sessionUserinfo;

//绑定公众号事件，绑定公众号以后，直接在表中增加一条记录，同时判断：代理商，牛头，客户
public class SubscribeHandler  implements WxMessageHandler  {

	UserinfoModelMapper userinfoModelMapper;

	public SubscribeHandler(UserinfoModelMapper pUserinfoModelMapper){userinfoModelMapper = pUserinfoModelMapper;}
	
	@Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map context, IService iService)
            throws WxErrorException {
		utility.Log.logger.error("SubscribeHandler:"+"处理");
		
		UserinfoModel mAddUserinfoModel=null;
		
		
        //必须以build()作为结尾，否则不生效。
		//传入没有参数的，直接保存为客户，但是没有客户经理
		//传入有参数的，先判断是否存在这个openid，如果没有这个openid，新增，如果已经存在，判断是否有客户经理，如果没有，就更新客户经理。
		if (wxMessage.getEvent() == null)
		{
			return null;
		}
		
		
		if (wxMessage.getEvent().toLowerCase().equals("subscribe"))
		{
			//关注
			utility.Log.logger.error("SubscribeHandler:处理绑定事件");
			Integer mIsUpdate = 0;
			WxXmlOutMessage xmlOutMsg = null;
			
			//判断是否已经存在这个openid数据了
			UserinfoModel mIsUpdateModel = userinfoModelMapper.selectByUserUid(wxMessage.getFromUserName());
			if (mIsUpdateModel == null)
			{
				utility.Log.logger.error("SubscribeHandler:新绑定");
				mIsUpdate = 0;
			}
			else
			{
				utility.Log.logger.error("SubscribeHandler:数据已经存在");
				mIsUpdate = 1;
			}
			
			//第一种情况是没有多余的参数，说明是直接扫描的标准二维码
			if (wxMessage.getEventKey() == "")
			{
				utility.Log.logger.error("SubscriveHandler:没有参数传入，直接扫描");
				//数据库中没有相关用户，直接新增
				mAddUserinfoModel = new UserinfoModel();
				//用户所属ID
				mAddUserinfoModel.setAgentUid("0");
				//设置用户的上一级用户的UID
				mAddUserinfoModel.setMergeKey("0");
				//设置用户的OPENID
				mAddUserinfoModel.setUserUid(wxMessage.getFromUserName());
				//用户姓名
				mAddUserinfoModel.setUserName("-");
				//用户类型
				mAddUserinfoModel.setUserType("customer");
				mAddUserinfoModel.setUserSpec("客户");
				mAddUserinfoModel.setFlag("1");
				xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎关注 玩到老 旅游公众号").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

			}
			
			//首先获取到传入的参数，这个参数就是userid，在系统中讯在这个userid，如果是代理商，新增的用户就是牛头，如果是牛头新增的用户就是客户
			UserinfoModel mUserinfoModel = userinfoModelMapper.selectByUserSecretKey(wxMessage.getEventKey().replace("qrscene_", ""));
			
			if (mUserinfoModel == null)
			{
				utility.Log.logger.error("SubscribeHandler:客户经理不存在，客户经理："+wxMessage.getEventKey());

				//无法根据传递过来的参数识别用户，直接新增关注用户，这个情况一般情况下不存在
				mAddUserinfoModel = new UserinfoModel();
				//用户所属ID
				mAddUserinfoModel.setAgentUid("0");
				//设置用户的上一级用户的UID
				mAddUserinfoModel.setMergeKey("0");
				//设置用户的OPENID
				mAddUserinfoModel.setUserUid(wxMessage.getFromUserName());
				//用户姓名
				mAddUserinfoModel.setUserName("-");
				//用户类型
				mAddUserinfoModel.setUserType("customer");
				mAddUserinfoModel.setUserSpec("客户");
				mAddUserinfoModel.setFlag("1");
				xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎关注 玩到老 旅游公众号。您当前还没有客户经理，可以再次扫描客户经理的二维码进行绑定客户经理。").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
			}
			else
			{
				utility.Log.logger.error("SubscribeHandler:用户类型："+mUserinfoModel.getUserType());
				if (mUserinfoModel.getUserType().equals( "agent" ) )
				{
					utility.Log.logger.error("SubscribeHandler:新增业务员");
					//如果扫描到的是代理商，直接把用户定义为业务员，上级用户为代理商
				    mAddUserinfoModel = new UserinfoModel();
					//用户所属ID
					mAddUserinfoModel.setAgentUid(mUserinfoModel.getAgentUid());
					//设置用户的上一级用户的UID
					mAddUserinfoModel.setMergeKey(mUserinfoModel.getUserUid());
					//设置用户的OPENID
					mAddUserinfoModel.setUserUid(wxMessage.getFromUserName());
					//用户姓名
					mAddUserinfoModel.setUserName("-");
					//用户类型
					mAddUserinfoModel.setUserType("seller");
					mAddUserinfoModel.setUserSpec("客户经理");
					mAddUserinfoModel.setFlag("1");
					xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎关注 玩到老 旅游公众号").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

				}
				
				if (mUserinfoModel.getUserType().equals( "seller" ))
				{
					utility.Log.logger.error("SubscribeHandler:新增客户");
					//如果扫描到的是牛头，直接把用户定义为客户，客户经理为被扫描者
					mAddUserinfoModel = new UserinfoModel();
					//用户所属ID
					mAddUserinfoModel.setAgentUid(mUserinfoModel.getAgentUid());
					//设置用户的上一级用户的UID
					mAddUserinfoModel.setMergeKey(mUserinfoModel.getUserUid());
					//设置用户的OPENID
					mAddUserinfoModel.setUserUid(wxMessage.getFromUserName());
					//用户姓名
					mAddUserinfoModel.setUserName("-");
					//用户类型
					mAddUserinfoModel.setUserType("customer");
					mAddUserinfoModel.setUserSpec("客户");
					mAddUserinfoModel.setFlag("1");
					xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎关注 玩到老 旅游公众号").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

				}
				
				if (mUserinfoModel.getUserType().equals("customer"))
				{
					utility.Log.logger.error("SubscribeHandler:新增并级客户，上级用户："+mUserinfoModel.getMergeKey());
					//如果扫描到的是客户，直接把新增用户定义为客户，用户客户经理为上一个客户的牛头
					mAddUserinfoModel = new UserinfoModel();
					//用户所属ID
					mAddUserinfoModel.setAgentUid(mUserinfoModel.getAgentUid());
					//设置用户的上一级用户的UID
					mAddUserinfoModel.setMergeKey(mUserinfoModel.getMergeKey());
					//设置用户的OPENID
					mAddUserinfoModel.setUserUid(wxMessage.getFromUserName());
					//用户姓名
					mAddUserinfoModel.setUserName("-");
					//用户类型
					mAddUserinfoModel.setUserType("customer");
					mAddUserinfoModel.setUserSpec("客户");
					mAddUserinfoModel.setFlag("1");
					xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎关注 玩到老 旅游公众号").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

				}
			}
			
			//获取到相关的openid，从系统中寻找是否存在这个openid，如果存在修改活动标志flag
			//处理保存数据
			//自己的唯一码
			UUID uuid=UUID.randomUUID();
	        String str = uuid.toString(); 
	        
	        System.out.println(mAddUserinfoModel);
	        
			mAddUserinfoModel.setUserSecret(str.replace("-", ""));
			
			//获取微信用户信息
			//WxUser user = iService.oauth2ToGetUserInfo(result.getAccess_token(), new WxUserGet("openid", WxConsts.LANG_CHINA));
			//性别
			mAddUserinfoModel.setSex("0");
			
			utility.Log.logger.error("SubscribeHandler:保存/更新标志："+mIsUpdate.toString());
			if (mIsUpdate == 0)
			{
				//
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mAddUserinfoModel.setCreateDttm(df.format(new Date()));
				mAddUserinfoModel.setUpdateDttm(df.format(new Date()));

				//新增
				userinfoModelMapper.insert(mAddUserinfoModel);
			}
			else
			{
				//在最后更新的时候，如果是coustomer，并且没有客户经理的情况下是可以进行更新的，其他的情况不允许
				if(mIsUpdateModel.getUserType().toLowerCase().equals("customer") && mIsUpdateModel.getMergeKey().equals("0"))
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					mAddUserinfoModel.setUpdateDttm(df.format(new Date()));
					//更新
					System.out.println("更新UID："+mAddUserinfoModel.getUserUid()+"更新MegerKey"+mAddUserinfoModel.getMergeKey());
					userinfoModelMapper.updateAgentByUserUid(mAddUserinfoModel);
				}
			}
		//xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎关注 玩到老 旅游公众号").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
        return xmlOutMsg;
		}
        return null;
    }
}
