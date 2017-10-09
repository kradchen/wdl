package utility;

import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 加密类型公共算法函数
 * 
 * @author dly
 * @since 2016年8月10日 下午4:09:10
 * @version 1.0
 */
public class EncryptUtility {

	public static String MD5To32(String plain) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			re_md5 = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;

	}
	


	
	private final static String DES = "DES";
	 
    public static void main(String[] args) throws Exception {
        String data = "测试中嗯 1234 abce";
        String key = genKey();//"wang!@#$%";
        System.err.println(genKey());
        System.err.println(encrypt(data, key));
        System.err.println(decrypt(encrypt(data, key), key));
 
    }
    
    public  static String genKey()
    {
    	try{
	    	KeyGenerator kg = KeyGenerator.getInstance(DES);
	    	Key key = kg.generateKey();
	    	byte[] keyBytes = key.getEncoded();
	    	return keyBytes.toString();
    	} catch (Exception e) {  
    		//log.error("解密错误，错误信息：", e);
    		//return "";
            throw new RuntimeException("解密错误，错误信息：", e);  
      }  
    }
    
    /**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key)  {
    	try{
	        byte[] bt = encrypt(data.getBytes(), key.getBytes());
	        String strs = new BASE64Encoder().encode(bt);
	        return strs;
    	} catch (Exception e) {  
//          log.error("解密错误，错误信息：", e);
    		//return "";
            throw new RuntimeException("解密错误，错误信息：", e);  
      }  
    }
 
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) {
    	try{
	        if (data == null)
	            return null;
	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] buf = decoder.decodeBuffer(data);
	        byte[] bt = decrypt(buf,key.getBytes());
	        return new String(bt);
    	} catch (Exception e) {  
//          log.error("解密错误，错误信息：", e);
    		//return "";
            throw new RuntimeException("解密错误，错误信息：", e);  
      }  
    }
 
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        
        byte[] a = cipher.doFinal(data);
 
        return a;
    }
     
     
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }

}
