package org.boot.web.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class RequestUtil {

	public static Map<String, Object> getParameterMap(HttpServletRequest request)throws Exception{
		Map<String, Object> param = new  HashMap<String, Object>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		String mapvalue = "";
		if(null!=parameterMap) {
			for(Entry<String, String[]> entrySet: parameterMap.entrySet()){
				String key = entrySet.getKey();
				String[] value = entrySet.getValue();
				if(null==value){
					mapvalue = "";
				}else if(value instanceof String[]){
					
					for(int i=0;i<value.length;i++){ 
						mapvalue = value[i] + ",";
					}
					mapvalue = StringUtils.substring(mapvalue, 0, mapvalue.length()-1);
				} else {
					mapvalue = mapvalue.toString();
				}
				param.put(key, mapvalue);
			}
		}
		
		return param;
	}
	
	public static JSONObject getParameterJson(HttpServletRequest request)throws Exception{
		JSONObject param = new  JSONObject();
		Map<String, String[]> parameterMap = request.getParameterMap();
		String mapvalue = "";
		if(null!=parameterMap) {
			for(Entry<String, String[]> entrySet: parameterMap.entrySet()){
				String key = entrySet.getKey();
				String[] value = entrySet.getValue();
				if(null==value){
					mapvalue = "";
				}else if(value instanceof String[]){
					
					for(int i=0;i<value.length;i++){ 
						mapvalue = value[i] + ",";
					}
					mapvalue = StringUtils.substring(mapvalue, 0, mapvalue.length()-1);
				} else {
					mapvalue = mapvalue.toString();
				}
				param.put(key, mapvalue);
			}
		}
		
		return param;
	}
}
