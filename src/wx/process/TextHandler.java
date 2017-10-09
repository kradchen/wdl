package wx.process;

import java.util.Map;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import com.weixin.userinfo.mapper.UserinfoModelMapper;
import com.weixin.userinfo.model.UserinfoModel;

public class TextHandler implements WxMessageHandler{

	UserinfoModelMapper userinfoModelMapper;

	public TextHandler(UserinfoModelMapper pUserinfoModelMapper){userinfoModelMapper = pUserinfoModelMapper;}
	
	@Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map context, IService iService){
		
		UserinfoModel mAddUserinfoModel=null;
		
		if (wxMessage.getMsgType().toLowerCase().equals("text"))
		{
			WxXmlOutMessage xmlOutMsg = null;
			
			xmlOutMsg = WxXmlOutMessage.TEXT().content("处理密码消息").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
			
			return xmlOutMsg;
		}
		
		
		return null;
	}
}
