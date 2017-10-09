package utility;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;


import org.bouncycastle.jce.provider.BouncyCastleProvider;


import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

public final class RSAUtils {

	//private static final Logger		LOGGER		= LoggerFactory.getLogger(RSAUtils.class);


	private static final Provider	PROVIDER	= new BouncyCastleProvider();

	private static final int		KEY_SIZE	= 1024;


	private RSAUtils() {
	}


	public static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PROVIDER);
			keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			//LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 鍔犲瘑
	 * 
	 * @param publicKey
	 *            鍏挜
	 * @param data
	 *            鏁版嵁
	 * @return 鍔犲瘑鍚庣殑鏁版嵁
	 */
	public static byte[] encrypt(PublicKey publicKey, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", PROVIDER);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			//LOGGER.error(e.getMessage(), e);
			return null;
		}
	}


	public static String encrypt(PublicKey publicKey, String text) {
		byte[] data = encrypt(publicKey, text.getBytes());
		return data != null ? com.sun.org.apache.xml.internal.security.utils.Base64.encode(data) : null;
	}


	public static byte[] decrypt(PrivateKey privateKey, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", PROVIDER);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			return null;
		}
	}


	public static String decrypt(PrivateKey privateKey, String text) throws Base64DecodingException {
		byte[] data = decrypt(privateKey, com.sun.org.apache.xml.internal.security.utils.Base64.decode(text));
		return data != null ? new String(data) : null;
	}

}
