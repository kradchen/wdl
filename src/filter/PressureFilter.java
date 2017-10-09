package filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import paramter.OutPutParam;
import redis.RedisVerificationUtil;
import utility.SerializableUtility;



/**
 * 鍘嬪姏杩囨护鍣�
 * @author xxp
 * @since 2016骞�11鏈�8鏃� 涓嬪崍4:39:26
 * @version
 */
public class PressureFilter implements javax.servlet.Filter{
	@Autowired
	private RedisVerificationUtil redisVerificationUtil;
	
	/**
	 * @return the redisVerificationUtil
	 */
	public RedisVerificationUtil getRedisVerificationUtil() {
		return redisVerificationUtil;
	}

	/**
	 * @param redisVerificationUtil the redisVerificationUtil to set
	 */
	public void setRedisVerificationUtil(RedisVerificationUtil redisVerificationUtil) {
		this.redisVerificationUtil = redisVerificationUtil;
	}
	/**
	 * 寮�鍚棩蹇�
	 */
	private boolean openFilter;
	
	
	/**
	 * 鍗曚綅鏃堕棿鍐呮渶澶ц姹傛暟
	 */
	private int maxLinkNum;
	/**
	 * 鍗曚綅鏃堕棿锛堢锛�
	 */
	private int limitTime;
	
	/**
	 * @return the openFilter
	 */
	public boolean isOpenFilter() {
		return openFilter;
	}

	/**
	 * @param openFilter the openFilter to set
	 */
	public void setOpenFilter(boolean openFilter) {
		this.openFilter = openFilter;
	}
	/**
	 * @return the maxLinkNum
	 */
	public int getMaxLinkNum() {
		return maxLinkNum;
	}

	/**
	 * @param maxLinkNum the maxLinkNum to set
	 */
	public void setMaxLinkNum(int maxLinkNum) {
		this.maxLinkNum = maxLinkNum;
	}

	/**
	 * @return the limitTime
	 */
	public int getLimitTime() {
		return limitTime;
	}

	/**
	 * @param limitTime the limitTime to set
	 */
	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if(openFilter)
		{	
			String hostIp= request.getRemoteHost();
			String key = SerializableUtility.ConvertIptoString(hostIp);
		    Object value=redisVerificationUtil.getObject(key,1);
			if(value==null)
			{
				System.out.println("doFilter: "+hostIp+"  "+limitTime+"   "+maxLinkNum+"  not exist");
				redisVerificationUtil.putObjectWithLife(key, 1,1,limitTime);
			}
			else
			{
				int linkNm =(int)value;
				++linkNm;
				System.out.println("doFilter: "+hostIp+"  "+limitTime+"   "+maxLinkNum+" "+linkNm);
				if(linkNm>maxLinkNum)
				{
					redisVerificationUtil.putObjectWithLife(key, linkNm,1,limitTime);
					response.setCharacterEncoding("GBK");
					OutPutParam mParam = new OutPutParam();
					mParam.setFailure();
					mParam.setBody(null);
					mParam.setMessage("绋嶅悗缁х画");
					response.getWriter().write(SerializableUtility.Serialize(mParam));
					return;
				}
				else
				{
					redisVerificationUtil.putObjectWithLife(key, linkNm,1,limitTime);
				}
			}
		}		
		filterChain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
