package com.connsec.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public final class StringUtils {
	
	/**
	 * avoid null, and return value trim.
	 * @param value string value.
	 * @return the trim of value.
	 */
	public static String avoidNull(String value) {
		return (value == null) ? "" : value.trim();
	}
	
	public static boolean isNull(String value) {
		return value == null;
	}

	/**
	 * @param value string
	 * value
	 * @return value
	 */
	public static boolean isNullOrBlank(String value) {
		return value == null || "".equals(value.trim());
	}

	public static boolean isNotEmpty(String value) {
		return !isNullOrBlank(value);
	}
	
	public static boolean isNotNullAndEquals(String value,String equalString) {
		return !isNullOrBlank(value)&&value.equals(equalString);
	}
	
	public static boolean isNotNullAndEqualsIgnoreCase(String value,String equalString) {
		return !isNullOrBlank(value)&&value.equalsIgnoreCase(equalString);
	}
	
	/*
	 * 获取指定UTF-8模式字节长度的字符串
	 */
	public static String limitLength(String strValue, int bytelen){
		
		//中文汉字占用三个字节
		int strlen = bytelen/3;
		int real_bytelen = strlen*3;
		
		//如果为NULL或�?空，则直接返�?
		if(null==strValue || "".equalsIgnoreCase(strValue)){
			return "";
		}
		
		try{
			byte[] utf8_bytes = strValue.getBytes("UTF-8");
			if(utf8_bytes.length<=bytelen) return strValue;
			
			byte[] cutoff_bytes = new byte[real_bytelen];
			System.arraycopy(utf8_bytes, 0, cutoff_bytes, 0, real_bytelen);
			
			String strResult = new String(cutoff_bytes, "UTF-8");
			
			return strResult;
			
		}catch(Exception e){
			if(strValue.length()<strlen) return strValue;
			return strValue.substring(0, strlen);
		}
		
	}
	
	/**
	 * 对url进行编码�?
	 * @param ori_url 要编码的url
	 * @return 返回url
	 */
	public static String urlEncode(String url){
		try {
			String tempstr = URLEncoder.encode(url,"UTF-8");
			return tempstr.replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return url;
		}
	}
	
	
	/**
	 * 编码url
	 * @param ori_url
	 * @return
	 */
	public static String urlDecode(String url){
		try {
			
			String tempstr = URLDecoder.decode(url.replaceAll("%20", "\\+"),"UTF-8");
			return tempstr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return url;
		}
	}
	
	
	/**
	 * �?��字符串是否包含特殊字�?
	 * @param str
	 * @return
	 */
	public static Boolean specialWord(String string)
	{
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#�?…�?&*（）—�?+|{}【�?‘；：�?“�?。，、？]";
		return Pattern.compile(regEx).matcher(string).find();
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static Boolean startWithUpper(String string) {
		return Pattern.compile("^[A-Z]").matcher(string).find();
	}
	

	
	/**
	 * @param str
	 * @return
	 */
	public static Boolean startWithLower(String string) {
		return Pattern.compile("^[a-z]").matcher(string).find();
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static Boolean startWithNumber(String string) {
		return Pattern.compile("^[0-9]").matcher(string).find();
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static Boolean containsLower(String string) {
		return Pattern.compile("[a-z]").matcher(string).find();
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static Boolean containsUpper(String string) {
		return Pattern.compile("[A-Z]").matcher(string).find();
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static Boolean containsChinese(String string)
	{
		String regEx = "[\u2E80-\u9FFF]+$";		
		return Pattern.compile(regEx).matcher(string).find();
	}
	/**
	 * 密码不包含全部或部分的用户账户名
	 * �?��str2中是否包含str中全部或部分的数�?
	 * @param str
	 * @param str2
	 * @return
	 */
	public static Boolean containsPartOrAll(String string,String string2) {
		if(isNotEmpty(string) && isNotEmpty(string2)) {
			 return Pattern.compile("["+string+"]").matcher(string2).find();
		}
		return false;
	}
	
	public static boolean containsSpace(String string) {
		return string.lastIndexOf(" ") != -1;
	}
	
	public static Boolean isNumber(String string) {
		return Pattern.compile("[0-9]").matcher(string).find();
	}
	
	/**
	 * 返回字符串中包含的大写字母的
	 * @param str
	 * @return
	 */
	public static int countUpper(String string) {
		int count = 0;
		for(int i = 0; i < string.toCharArray().length; i++) {
			if(containsUpper(String.valueOf(string.charAt(i)))) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static int countLower(String string) {
		int count = 0;
		for(int i = 0; i < string.toCharArray().length; i++) {
			if(containsLower(String.valueOf(string.charAt(i)))) {
				count++;
			}
		}
		return count;
	}
	
	public static int countNumber(String string) {
		int count = 0;
		for(int i = 0; i < string.toCharArray().length; i++) {
			if(isNumber(String.valueOf(string.charAt(i)))) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static int countSpecialWord(String string) {
		int count = 0;
		for(int i = 0; i < string.toCharArray().length; i++) {
			if(specialWord(String.valueOf(string.charAt(i)))) {
				count++;
			}
		}
		return count;
	}
	
	public static List<String> string2List(String string, String split) {
		String[] strs = {};
		if (string != null && !string.equals("")) {
			strs = string.split(split);
		}
		ArrayList<String> resultList = new ArrayList<String>(0);
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null&& !strs[i].equals("")) {
				resultList.add(strs[i]);
			}
		}
		resultList.trimToSize();
		return resultList;
	}

	public static String list2String(List<String> list, String split) {
		String string = "";
		if (list == null)
			return string;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null && !list.get(i).equals("")) {
				string += list.get(i) + split;
			}
		}
		if (string.length() > 0) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}
    
	
	public static int parse2Integer(String string) {
		Integer value = 0;
		try {
			value = Integer.parseInt(string);
		} catch(Exception e){
			throw new RuntimeException("parse "+string+" to  Integer error.");
		}
		return value;
	}
	
	
	/**
	 * 处理如id=name形式的字符串
	 * @param proValue
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String,List<String>> processStr(String proValue,String key,String value) {
		Map<String,List<String>> map = new HashMap<String, List<String>>();
		List<String> idList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		if(StringUtils.isNotEmpty(proValue)) {
			List<String> list = StringUtils.string2List(proValue, ",");
			for(String str : list) {
				idList.add(str.split("\\,")[0]);
				nameList.add(str.split("\\,")[1]);
			}
		}
		map.put(key, idList);
		map.put(value, nameList);
		return map;
	}
	

	
	public static String convertWeekStr(String str,String split) {
		String retVal = "";
		try {
			if(StringUtils.isNotEmpty(str)) {
				String []array = str.split("\\"+split);
				for(int i = 0; i < array.length; i++) {
					if(StringUtils.isNotEmpty(retVal)) {
						retVal += split;
					}
					retVal += (Integer.parseInt(array[i]) + 1);
				}
			}
		} catch(NumberFormatException e) {
			e.printStackTrace();
		} 
		return retVal;
	}
	
	
	public static List<String> convertToSingleList(List<String> list){
		return convertToSingleList(list,",");
	}
	
	public static List<String> convertToSingleList(List<String> list,String reg){
		List<String> result = new ArrayList<String>();
		if(list!=null && list.size()>0){
			for(String str:list){
				String[] arrStr = str.split(reg);
				for(int i=0;i<arrStr.length; i++){
					result.add(arrStr[i]);
				}
			}
		}
		return result;
	}
	
    /** 
    * 汉字转换位汉语拼音
    * @param hanYu Chinese
    * @param first true is Convert first,else all
    * @return 拼音 
    */  
    public static String hanYu2Pinyin(String  hanYu,boolean first){         
        String pinyin = "";  
        char[] nameChar = hanYu.toCharArray();  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        for (int i = 0; i < nameChar.length; i++) {  
            if (nameChar[i] > 128) {  
                try {  
                	if(first){
                		pinyin += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);  
                	}else{
                		pinyin += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];  
                	}                	
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                    e.printStackTrace();  
                }  
            }else{  
            	pinyin += nameChar[i];  
            }  
        }  
        return pinyin;  
    }  

	
}
