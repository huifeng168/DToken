package com.connsec.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class RQCodeUtils {

	
	public static void write2File(String path,String rqCodeText,String format,int width, int height ){
		try {  
			BitMatrix byteMatrix=genRQCode(rqCodeText,width,height);
			
	        File file = new File(path);  
	          
	        MatrixToImageWriter.writeToPath(byteMatrix, format, file); 
		} catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	public static BufferedImage write2BufferedImage(String rqCodeText,String format,int width, int height ){
		try {  
			BitMatrix byteMatrix=genRQCode(rqCodeText,width,height); 
	          
	        return MatrixToImageWriter.toBufferedImage(byteMatrix); 
		} catch (Exception e) {  
            e.printStackTrace();  
        }  
		return null;
	}
	
	public static void write2OutputStream(OutputStream stream,String rqCodeText,String format,int width, int height ){
		try {  
			BitMatrix byteMatrix=genRQCode(rqCodeText,width,height);
	          
	        MatrixToImageWriter.writeToStream(byteMatrix, format, stream); 
		} catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	
	public static BitMatrix genRQCode(String rqCodeText,int width, int height ){
		if(width==0){
			width=200;
		}
		if(height==0){
			height=200;
		}
		try {  
			return  new MultiFormatWriter().encode(
	        		rqCodeText, 
	        		BarcodeFormat.QR_CODE, 
	        		width, 
	        		height);  
		} catch (Exception e) {  
            e.printStackTrace();  
        }  
		return null;
	}
	
}
