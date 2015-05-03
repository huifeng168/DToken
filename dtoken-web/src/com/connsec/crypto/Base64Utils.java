package com.connsec.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;


/**
 * Base64 Utils
 * 
 * @author Crystal.Sea
 *
 */
public final class Base64Utils {


	
	public static String encodeBase64(byte[] simple){
		String cipher=BytesUtils.bytes2String(Base64.encodeBase64(simple));
		return cipher;
	}
	
	public static byte[] decoderBase64(String cipher){
		byte[] simple=Base64.decodeBase64(cipher);
		return simple;
	}
	
	public static String  encode(String simple){
		return  encodeBase64(simple.getBytes());
	}
	
	public static String  encoder(byte[] simple){
		return  encodeBase64(simple);
	}
	
	public static String  decode(String cipher){
		return BytesUtils.bytes2String(decoderBase64(cipher));
	}
	
	public static byte[]  decoder(String cipher){
		return decoderBase64(cipher);
	}
	
	
	 /**
	  *encode file to  base64 Code String
	  * @param fileName file path
	  * @return  * 
	  * @throws Exception
	  */

	 public static String fileToBase64(String fileName) throws Exception {
	  File file = new File(fileName);;
	  FileInputStream inputFile = new FileInputStream(file);
	  byte[] buffer = new byte[(int) file.length()];
	  inputFile.read(buffer);
	  inputFile.close();
	  return encodeBase64(buffer);

	 }

	 /**
	  * base64 Code decode String save to targetPath
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void decodeBase64ToFile(String base64Code, String targetPath)
	   throws Exception {
	  byte[] buffer = decoderBase64(base64Code);
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();

	 }

	 /**
	  * base64 code save to file
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void base64ToFile(String base64Code, String targetPath)
	   throws Exception {
	  byte[] buffer = base64Code.getBytes();
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();
	 }

}
