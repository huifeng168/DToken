package com.connsec.crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;


public final class RSAUtils {
	public static final String KEY_ALGORTHM = "RSA";
	
	public static final String PUBLIC_KEY = "RSAPublicKey";

	public static final String PRIVATE_KEY = "RSAPrivateKey";

	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}

	public static String getPublicKey(Map<String, Object> keyMap)throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Utils.encoder(key.getEncoded());
	}

	public static String getPrivateKey(Map<String, Object> keyMap)throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Utils.encoder(key.getEncoded());
	}

	/**
	 * ��˽Կ����
	 * @param data  �������
	 * @param key  ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key)throws Exception {
		// ������Կ
		byte[] keyBytes = Base64Utils.decoder(key);
		// ȡ˽Կ
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

		// ����ݼ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * ��˽Կ����
	 * @param data  �������
	 * @param key  ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key)throws Exception {
		// ��˽Կ����
		byte[] keyBytes = Base64Utils.decoder(key);

		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		// ����ݽ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * �ù�Կ����
	 * @param data �������
	 * @param key ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key)throws Exception {
		// �Թ�Կ����
		byte[] keyBytes = Base64Utils.decoder(key);
		// ȡ��Կ
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

		// ����ݽ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * �ù�Կ����
	 * @param data �������
	 * @param key ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key)throws Exception {
		// ��˽Կ����
		byte[] keyBytes = Base64Utils.decoder(key);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

		// ����ݽ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

}
