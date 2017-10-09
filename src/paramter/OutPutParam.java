package paramter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import publib.ConstantCode;

/**
 * 出口参数模型
 * @author xxp
 * @since 2016年11月9日 下午1:32:53
 * @version
 */
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true,allowSetters=true,allowGetters=true)
public class OutPutParam {
	public OutPutParam()
	{
		this.success=ConstantCode.SYSTEMSUCCESSCODE;
	}
	
	private Object body;
	
	/**
	 * @return the body
	 */
	public Object getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(Object body) {
		this.body = body;
	}

	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess() {
		this.success = ConstantCode.SYSTEMSUCCESSCODE;
	}

	public void setFailure() {
		this.success = ConstantCode.SYSTEMERRORCODE;
	}
	
	private String success;
	
	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	private String tag;

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
