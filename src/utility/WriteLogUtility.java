package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weixin_interface.log.model.Log;

import paramter.InputParam;

/**
 * 写日志类
 * @author szh
 * @since 2016年11月9日 下午12:59:33
 * @version
 */
public class WriteLogUtility {
	
	/**
	 * @param restURL(日志接口地址)
	 * @param log(日志model)
	 * @return
	 */
	public static String f_WriteLog(String restURL,Log log,boolean on){
		if(on)
		{
			return (new HttpRequestUtility()).doPost(restURL, log);
		}
		else
		{
			System.out.println(log.getLog_text());
			return "";
		}
	}

}


