package mybatis.framework;

import java.util.HashSet;

import exception.CustomizeException;
import publib.ConstantCode;


public abstract class CustomizedContextHolder {
//	public final static String DATA_SOURCE_MAIN = "mainDataSource";
//	public final static String DATA_SOURCE_TEST1 = "testDataSource1";
//	public final static String DATA_SOURCE_TEST2 = "testDataSource2";
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
	
	public static void setCustomerType(HashSet<String> typeMapper,String customerType) throws Exception {
		if(typeMapper!=null&&!typeMapper.contains(customerType))
		{
			throw new CustomizeException(ConstantCode.EXCEPTION_CODE_DB_NOT_EXIST,
					"不存在当前数据源"+customerType);
		}
	    contextHolder.set(customerType);  
	}  
	  
	public static String getCustomerType() {  
	    return contextHolder.get();  
	}  
	  
	public static void clearCustomerType() {  
	    contextHolder.remove();  
	}  
}
