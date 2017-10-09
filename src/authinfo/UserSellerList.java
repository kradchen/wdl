package authinfo;

import java.io.Serializable;

public class UserSellerList   implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userSellerID;
	
	public String getuserSellerID() {
        return userSellerID;
    }

    public void setuserSellerID(String userSellerID) {
        this.userSellerID = userSellerID == null ? null : userSellerID.trim();
    }
    
    
    
    
    private String userSellerName;
	
	public String getuserSellerName() {
        return userSellerName;
    }

    public void setuserSellerName(String userSellerName) {
        this.userSellerName = userSellerName == null ? null : userSellerName.trim();
    }
    
}
