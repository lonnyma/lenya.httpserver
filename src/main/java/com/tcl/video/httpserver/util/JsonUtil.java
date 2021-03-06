package com.tcl.video.httpserver.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.lenya.soft.core.bean.Base;

public class JsonUtil extends Base{
	
	
	
	/**
	 * 将json转换成map
	 * @param <T>
	 * @param json
	 * @return
	 */
	public static <T> Map<String,T> json2Map(String json){
		   Map<String,T> result = null;  
	       // 将json字符串转换成jsonObject  
	       JSONObject jsonObject = JSONObject.parseObject(json);  
	       result =  json2Map(jsonObject);		
		return result;
	}
	
		
	@SuppressWarnings("unchecked")
	private static <T> Map<String,T>  json2Map(JSONObject jsonObject){
		Map<String,T> result = new HashMap<String, T>();
		Set<String> keys = jsonObject.keySet();  	       
	       // 遍历jsonObject数据，添加到Map对象  
	       for(Iterator<String> it=keys.iterator();it.hasNext();)
	       {  
	    	   String key = String.valueOf(it.next());  
	           String value = jsonObject.getString(key);  
	           if(null!=value)	           
	        	   result.put(key, (T) value);  	       
	           }  	
	  return result;       
	}
	
	
	/**
	 * 功能描述：将JsonArry 转换成对象，并用list装载该对象
	 * @param objdataList
	 * @param clzz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonArry2List(JSONArray objdataList,Class<T> clzz){
		List<T> tList = new ArrayList<T>();		
		JsonUtil jsonUtil = new JsonUtil();
		for (int i = 0; i < objdataList.size(); i++) {
			JSONObject json = objdataList.getJSONObject(i);
			Map<String, Object> map = json2Map(json);
			tList.add((T) jsonUtil.initBean(map, clzz, null));
		}	
		jsonUtil= null;
		return tList;		
	}
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 功能描述：字符串转换成对象
	 * @param jsonString
	 * @param type
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T fromJson(String jsonString, Class<T> type)			 {
		try {
			return mapper.readValue(jsonString, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述：将对象转换成json字符串
	 * @param object
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, Object> parseToMap(String str) {
		if (str == null) {
			return null;
		}
		try {
			return JSON.parseObject(str, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			return null;
		}
	}
}
