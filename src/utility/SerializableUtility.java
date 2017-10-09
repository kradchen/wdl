package utility;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 序列化和反序列化工具类
 * 
 * @author xxp
 * @since 2016年8月2日 下午2:23:26
 * @version 1
 */
public class SerializableUtility {
	/**
	 * 解析输入的参数（HashMap）
	 * 
	 * @param pJsonString
	 * @return
	 */
	public static HashMap<String, String> DeserializeParamWithHashMap(String pJsonString) {
		HashMap<String, String> mRetValue = null;
		try {
			if (pJsonString.trim().length() > 0) {
				JsonFactory factory = new JsonFactory();
				ObjectMapper mapper = new ObjectMapper(factory);
				TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
				};
				mRetValue = mapper.readValue(pJsonString, typeRef);
			}
		} catch (IOException e) {

		} finally {
			return mRetValue;
		}
	}
	
	/**
	 * 解析输入的参数（泛型）
	 * 
	 * @param pJsonString
	 * @return
	 */
	public static <T> T DeserializeParamWithGenericity(String pJsonString) {
		T mRetValue = null;
		try {
			if (pJsonString.trim().length() > 0) {
				JsonFactory factory = new JsonFactory();
				ObjectMapper mapper = new ObjectMapper(factory);
				TypeReference<T> typeRef = new TypeReference<T>() {
				};
				mRetValue = mapper.readValue(pJsonString, typeRef);
			}
		} catch (IOException e) {
			
		} finally {
			return mRetValue;
		}
	}

	public static String Serialize(Object ob) {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(ob);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return "{}";
		}
	}

	public static <T>  T Deserialize(String jsonString,Class<T>  property) {
		ObjectMapper mapper = new ObjectMapper();
		T entity = null;
		try {
			entity = (T) mapper.readValue(jsonString,property);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
	
	public static byte[] ByteSerialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object ByteDeserialize(byte[] bytes) {
		if (bytes == null)
			return null;
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转换ip地址
	 * @param pIP
	 * @return
	 */
	public static String ConvertIptoString(String pIP)
	{
		String address="empty_ip_address";
		if(pIP!=null&&pIP.trim().length()>0)
		{
			address =pIP.replace('.', '_');
			address=address.replace(':', '_');
		}
		return address;
	}
}
