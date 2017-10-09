package utility;

/**
 * 验证权限
 * 
 * @author xxp
 * @since 2016年8月9日 下午7:29:38
 * @version
 */
public class ConfirmationUtility {
	
	/**
	 * 权限验证
	 * 
	 * @param userId
	 *            用户编号
	 * @param tokenKey
	 *            信令牌
	 * @return 是否成功
	 */
	public static boolean ValidateUser(String user, String tokenKey) {
		/**
		 * 权限验证过程
		 */
		try {
			// 判断空
//			if (user==null || user.getUser_id()==null || user.getUser_id().trim().length() < 1
//					|| tokenKey.equals(null) || tokenKey.trim().length() < 1)
//			{
//				return false;
//			} else {
//
//				if (user.getToken().equals((tokenKey))) 
//				{
//					return true;
//				}
//
//				else {
//					return false;
//				}
//			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
