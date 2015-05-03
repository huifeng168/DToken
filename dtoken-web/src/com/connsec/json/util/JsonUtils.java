package com.connsec.json.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {

	/**
	 *  transform json string to java bean object
	 * @param json
	 * @param bean
	 * @return Object
	 */
	@SuppressWarnings("finally")
	public static Object json2Object(String json,Object bean){
		try {
			bean=(new ObjectMapper()).readValue(json, bean.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			return bean;
		}
	}
	
	/**
	 * transform  java bean object to json string
	 * @param bean
	 * @return string
	 */
	@SuppressWarnings("finally")
	public static String object2Json(Object bean){
		String json="";
		try {
			json= (new ObjectMapper()).writeValueAsString(bean);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			return json;
		}
	}
	
}
