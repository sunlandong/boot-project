package org.boot.common.core.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;

public class JSONUtils {

	
	private static  ValueFilter filter = new ValueFilter() {
	    @Override
	    public Object process(Object obj, String s, Object v) {
	    if(v==null)
	        return "";
	    return v;
	    }
	};
	
	public static <T> JSONObject objectToJson(T t)throws Exception{
		String jsonString = JSON.toJSONString(t, filter);
		return JSONObject.parseObject(jsonString);
	}
	
	public static <T> JSONArray listToJsonArray(List<T> array)throws Exception{
		String jsonString = JSON.toJSONString(array,filter);
		JSONArray result = JSONArray.parseArray(jsonString);
		return result;
	}
}
