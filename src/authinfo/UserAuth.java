package authinfo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class UserAuth implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userAction;
	
	public String getuserAction() {
        return userAction;
    }

    public void setuserAction(String userAction) {
        this.userAction = userAction == null ? null : userAction.trim();
    }
    
    
    
    
    private String userFlag;
	
	public String getuserFlag() {
        return userFlag;
    }

    public void setuserFlag(String userFlag) {
        this.userFlag = userFlag == null ? null : userFlag.trim();
    }
    
}
