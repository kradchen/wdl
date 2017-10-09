package authinfo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class UserKey  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userKey1;
	
	public String getuserKey1() {
        return userKey1;
    }

    public void setuserKey1(String userKey1) {
        this.userKey1 = userKey1 == null ? null : userKey1.trim();
    }
    
    
    
    
    private String userKey2;
	
	public String getuserKey2() {
        return userKey1;
    }

    public void setuserKey2(String userKey2) {
        this.userKey2 = userKey2 == null ? null : userKey2.trim();
    }
    
    
}
