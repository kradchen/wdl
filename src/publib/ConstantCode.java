package publib;

/**
 * 编码常量 按照controller进行区分
 * 
 * @author xxp
 * @since 2016年8月4日 上午11:56:37
 * @version
 */
public class ConstantCode {
	
	//增加错误类型时候，先看一下是否有相同或者是类似的，不允许随意增加

	//系统处理成功为 1
	public static final String SYSTEMSUCCESSCODE = "true";// 处理成功
	
	//系统级别出错信息， -1 到 -9 较为严重的错误
	public static final String SYSTEMERRORCODE = "false";// 处理失败，服务器系统异常
	public static final String SYSTEMERRORCODE_USERCONFIRMATION_ILLEAGE = "-2";// 权限不合法 或Tocken验证失败
	public static final String SYSTEMERRORCODE_EXTERNALINTERFACE_EXCEPTION= "-3";// 外部接口错误
	
	//参数类型或常规错误 -10 到 -19
	public static final String SYSTEMERRORCODE_PARAMTER_ILLEAGE = "-11";// 参数不合法
	
	//权限类错误信息 -20 到 -29
	
	
	//单据类错误信息  -100 到 -199
	public static final String ORDERERRORCODE_DJBH_ILLEAGE = "-101";// 单据编码不存在
	
	//商品类错误信息 -200 到 -299
	
	
	
	/*************************多数据操作代码，代码 850-899***************************/
	
	public static final String EXCEPTION_CODE_DB_NOT_EXIST="850";
	
	
	
	
	
	
	
	/*************************认证部分，代码 900-999***************************/
	
	
	/**
	 * 用户认证入参不规范
	 */
	public static final String VERIFY_PARAM_INVALID = "900";
	
	/**
	 * 用户认证时间戳无效
	 */
	public static final String VERIFY_TIMETAG_INVALID = "901";
	/**
	 * 时间戳过期
	 */
	public static final String VERIFY_TIMETAG_EXPIRED = "902";
	
	/**
	 * 无此用户
	 */
	public static final String VERIFY_USER_INVALID = "903";
	
	/**
	 * 验签失败
	 */
	public static final String VERIFY_SIGN_INVALID = "904";

	/**
	 * 用户未登录，调用认证接口中需要先登录的接口时验证
	 */
	public static final String VERIFY_NO_LOGON =  "905";
	
	
	
	
}
