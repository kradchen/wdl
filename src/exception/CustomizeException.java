package exception;

public class CustomizeException extends Exception{

	private String Code;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return Code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		Code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}

	private String Message;
	
	
  public CustomizeException()
  {
	  super();
  }
  
  public CustomizeException(String Code)
  {
	  super(Code);
	  this.Code = Code;
	  this.Message =Message;
  }
  
  public CustomizeException(String Code,String Message)
  {
	  super(Code);
	  this.Message = Message;
  }
}
