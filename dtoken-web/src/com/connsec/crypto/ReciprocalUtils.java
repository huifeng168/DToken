/**
 * 
 */
package com.connsec.crypto;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.LogFactory;

import com.connsec.util.StringGenerator;


/**
 * Reciprocal cipher or Symmetric-key algorithm
 * 
 * algorithm Support DES,DESede,Blowfish and AES
 *  
 * default key value use ReciprocalUtils.defaultKey
 * 
 * generateKey  is generate random key for algorithm
 * 
 * @author Crystal.Sea
 *
 */
public final class ReciprocalUtils {
	
	 private static final String defaultKey= "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   //
	
	 public final class Algorithm {
			public static final String DES 		= 	"DES";
			public static final String DESede 	= 	"DESede";
			public static final String Blowfish = 	"Blowfish";
			public static final String AES 		= 	"AES";
		}
	 
	 public static String encode(String simple,String algorithm) {
		Key key=generatorKey(algorithm);
		byte[] byteFinal = null;
		byte[] byteSimple = simple.getBytes();
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFinal = cipher.doFinal(byteSimple);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		String cipherBASE64 = Base64Utils.encodeBase64(byteFinal);
		return cipherBASE64;
	}
	 
	 public static String decoder(String ciphers,String algorithm) {
			Key key=generatorKey(algorithm);
			Cipher cipher;
			byte[] byteSimple = Base64Utils.decoderBase64(ciphers);
			byte[] byteFinal = null;
			try {
				cipher = Cipher.getInstance(algorithm);
				cipher.init(Cipher.DECRYPT_MODE, key);
				byteFinal = cipher.doFinal(byteSimple);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cipher = null;
			}
			String simple = BytesUtils.bytes2String(byteFinal);
			return simple;

	}
	 
	private static Key generatorKey(String algorithm) {
		try {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			KeyGenerator _generator = KeyGenerator.getInstance(algorithm);
			_generator.init(new SecureRandom(defaultKey.getBytes()));
			Key key = _generator.generateKey();
			_generator = null;
			return key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	/**
	 * @param simple
	 * @param secretKey must length
	 * @return
	 * @throws Exception
	 */
	public static String encode(String simple,String secretKey,String algorithm){
		if(keyLengthCheck(secretKey,algorithm)){
			SecretKey key = new SecretKeySpec(secretKey.getBytes(), algorithm);
		    // Create the ciphers
		    Cipher ecipher;
		    byte[] byteFinal = null;
			try {
				ecipher = Cipher.getInstance(key.getAlgorithm());
			    // Encode the string into bytes using utf-8
			    ecipher.init(Cipher.ENCRYPT_MODE, key);
			    byte[] utf8 = simple.getBytes("UTF8");    
			    // Encrypt
			   byteFinal = ecipher.doFinal(utf8);   
			}catch (Exception e) {
				e.printStackTrace();
			}
		    
		    // Encode bytes to base64 to get a string
		    return Base64Utils.encodeBase64(byteFinal);
		}
	    return null;
	} 
	
	 public static String decoder(String ciphers,String secretKey,String algorithm) {
		 if(keyLengthCheck(secretKey,algorithm)){
		 	SecretKey key = new SecretKeySpec(secretKey.getBytes(), algorithm);
			Cipher cipher;
			byte[] byteSimple = Base64Utils.decoderBase64(ciphers);
			byte[] byteFinal = null;
			try {
				cipher = Cipher.getInstance(algorithm);
				cipher.init(Cipher.DECRYPT_MODE, key);
				byteFinal = cipher.doFinal(byteSimple);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cipher = null;
			}
			String simple = BytesUtils.bytes2String(byteFinal);
			return simple;
		 }
		 return null;
	}
	 
	private static boolean keyLengthCheck(String secretKey,String algorithm){
		boolean lengthCheck=false;
		if(algorithm.equals(Algorithm.DES)){
			if(secretKey.length()==8){
				lengthCheck=true;
			}else{
				LogFactory.getLog(ReciprocalUtils.class).debug("key length is "+secretKey.getBytes().length+" ,must lequal 8");
			}
		}else if(algorithm.equals(Algorithm.DESede)){
			if(secretKey.length()==24){
				lengthCheck=true;
			}else{
				LogFactory.getLog(ReciprocalUtils.class).debug("key length is "+secretKey.getBytes().length+" ,must equal 24");
			}
		}else if(algorithm.equals(Algorithm.AES)){
			if(secretKey.length()==16){
				lengthCheck=true;
			}else{
				LogFactory.getLog(ReciprocalUtils.class).debug("key length is "+secretKey.getBytes().length+" ,must equal 16");
			}
		}else if(algorithm.equals(Algorithm.Blowfish)){
			if(secretKey.length()<= 16){
				lengthCheck=true;
			}else{
				LogFactory.getLog(ReciprocalUtils.class).debug("key length is "+secretKey.getBytes().length+" ,must be less then 16");
			}
		}
		return lengthCheck;
	}
	 /**
	 * @param simple
	 * @param secretKey must length is 8
	 * @return
	 */
	public static String desEncode(String simple,String secretKey){
		 return encode( simple, secretKey,Algorithm.DES);
	 }
	 
	 public static String desDecoder(String ciphers,String secretKey){
		 return decoder( ciphers, secretKey,Algorithm.DES);
	 }
	 
	 /**
	 * @param simple
	 * @param secretKey must length is 24
	 * @return
	 */
	public static String desedeEncode(String simple,String secretKey){
		 return encode( simple, secretKey,Algorithm.DESede);
	 }
	 
	 public static String desedeDecoder(String ciphers,String secretKey){
		 return decoder( ciphers, secretKey,Algorithm.DESede);
	 }
	 
	 /**
	 * @param simple
	 * @param secretKey must length is 16
	 * @return
	 */
	public static String aesEncode(String simple,String secretKey){
		 return encode( simple, secretKey,Algorithm.AES);
	 }
	 
	 public static String aesDecoder(String ciphers,String secretKey){
		 return decoder( ciphers, secretKey,Algorithm.AES);
	 }
	 
	 
	 /**
	 * @param simple
	 * @param secretKey must less then 16
	 * @return
	 */
	public static String blowfishEncode(String simple,String secretKey){
		 return encode( simple, secretKey,Algorithm.Blowfish);
	 }
	 
	 public static String blowfishDecoder(String ciphers,String secretKey){
		 return decoder( ciphers, secretKey,Algorithm.Blowfish);
	 }	 
	 
	 public static String encode(String simple){
		 return encode( simple,Algorithm.AES);
	 }
	 
	 public static String decoder(String ciphers){
		 return decoder( ciphers,Algorithm.AES);
	 }
	 
	 
	 public static String  generateKey(String algorithm){
	 	if(algorithm.equals(Algorithm.DES)){
	 		return (new StringGenerator(8)).randomGenerate();
		}else if(algorithm.equals(Algorithm.AES)){
			return (new StringGenerator(16)).randomGenerate();
		}else if(algorithm.equals(Algorithm.Blowfish)){
			return (new StringGenerator(16)).randomGenerate();
		}else if(algorithm.equals(Algorithm.DESede)){
			return (new StringGenerator(24)).randomGenerate();
		}else{
			return (new StringGenerator()).uniqueGenerate();
		}
	 }
	 
}
