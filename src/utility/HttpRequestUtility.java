package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * http请求帮助类 测试用例中使用
 * @author xxp
 * @since 2016年8月11日 下午12:01:33
 * @version
 */
public class HttpRequestUtility {
	
	public String doPost(String restURL,Object data)
	{
		BufferedReader bufferReader=null;
		OutputStream outputStream=null;
		try {
			URL url = new URL(restURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			String input = mapper.writeValueAsString(data);
		    outputStream = httpConnection.getOutputStream();
		    outputStream.write(input.getBytes("UTF-8"));
			if(httpConnection.getResponseCode() != 200){	
				return "";
			}
			bufferReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(),Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String output= null;
			while((output=bufferReader.readLine())!=null)
			{
				sb.append(output);
			}
			return sb.toString();
		}
		catch(Exception e)
		{
			System.out.println(restURL);
			e.printStackTrace();
			return "";
		}finally{
            try{
                if(outputStream!=null){
                	outputStream.close();
                }
                if(bufferReader!=null){
                	bufferReader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
	}
	
	public String doGet(String restURL)
	{
		BufferedReader bufferReader=null;
		OutputStream outputStream=null;
		try {
			URL url = new URL(restURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			if(httpConnection.getResponseCode() != 200){	
				return "";
			}
			bufferReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(),Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String output= null;
			while((output=bufferReader.readLine())!=null)
			{
				sb.append(output);
			}
			return sb.toString();
		}
		catch(Exception e)
		{			
			System.out.println(restURL);
			e.printStackTrace();
			return "";
		}finally{
            try{
                if(outputStream!=null){
                	outputStream.close();
                }
                if(bufferReader!=null){
                	bufferReader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
	}
	
	
	public String doPost(String restURL,Object data,Map<String, String> headerProperty)
	{
		BufferedReader bufferReader=null;
		OutputStream outputStream=null;
		try {
			URL url = new URL(restURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			if(headerProperty!=null&&headerProperty.size()>0)
			{
				for (Entry<String, String> entry : headerProperty.entrySet()) {
					httpConnection.setRequestProperty(entry.getKey(),entry.getValue());
				}
			}
			ObjectMapper mapper = new ObjectMapper();
			String input = mapper.writeValueAsString(data);
		    outputStream = httpConnection.getOutputStream();
		    outputStream.write(input.getBytes("UTF-8"));
			if(httpConnection.getResponseCode() != 200){	
				return "";
			}
			bufferReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(),Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String output= null;
			while((output=bufferReader.readLine())!=null)
			{
				sb.append(output);
			}
			return sb.toString();
		}
		catch(Exception e)
		{
			System.out.println(restURL);
			e.printStackTrace();
			return "";
		}finally{
            try{
                if(outputStream!=null){
                	outputStream.close();
                }
                if(bufferReader!=null){
                	bufferReader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
	}
	public  String doGet(String restURL,Map<String, String> headerProperty)
	{
		BufferedReader bufferReader=null;
		OutputStream outputStream=null;
		try {
			URL url = new URL(restURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			
			if(headerProperty!=null&&headerProperty.size()>0)
			{
				for (Entry<String, String> entry : headerProperty.entrySet()) {
					httpConnection.setRequestProperty(entry.getKey(),entry.getValue());
				}
			}
			if(httpConnection.getResponseCode() != 200){	
				return "";
			}
			bufferReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(),Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String output= null;
			while((output=bufferReader.readLine())!=null)
			{
				sb.append(output);
			}
			return sb.toString();
		}
		catch(Exception e)
		{	
			System.out.println(restURL);
			e.printStackTrace();
			return e.getMessage();
		}finally{
            try{
                if(outputStream!=null){
                	outputStream.close();
                }
                if(bufferReader!=null){
                	bufferReader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
	}
	
	
	
	
	public  String DoGetByJson(String restURL,Map<String, String> headerProperty)
	{
		BufferedReader bufferReader=null;
		OutputStream outputStream=null;
		try {
			URL url = new URL(restURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			httpConnection.setRequestProperty("Accept", "application/json;charset=UTF-8");
			if(headerProperty!=null&&headerProperty.size()>0)
			{
				for (Entry<String, String> entry : headerProperty.entrySet()) {
					httpConnection.setRequestProperty(entry.getKey(),entry.getValue());
				}
			}
			if(httpConnection.getResponseCode() != 200){	
				return String.valueOf(httpConnection.getResponseCode());
			}
			bufferReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(),Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String output= null;
			while((output=bufferReader.readLine())!=null)
			{
				sb.append(output);
			}
			return sb.toString();
		}
		catch(Exception e)
		{		
			System.out.println(restURL);
			e.printStackTrace();
			return e.getMessage();
		}finally{
            try{
                if(outputStream!=null){
                	outputStream.close();
                }
                if(bufferReader!=null){
                	bufferReader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
	}
}
