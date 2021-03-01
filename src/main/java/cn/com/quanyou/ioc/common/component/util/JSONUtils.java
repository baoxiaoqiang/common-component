package cn.com.quanyou.ioc.common.component.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtils {
	
	
	public static JSON getGson() {
		return new JSONObject();
	}

	public static JSONObject getJSONObject(String json) {
		return JSON.parseObject(json);
	}
	
	public static String toJson(Object o) {
		return JSON.toJSONString(o);
	}
	
	public static <T> T get(String json, Class<T> clzz) {
		return JSON.parseObject(json, clzz);
	}
	
	public static Map<String, String> getMap(String json) {
		 return JSON.parseObject(json, new TypeReference<Map<String, String>>(){}.getType());
	}

	public static Map<String, Object> getObjectMap(String json) {
		return JSON.parseObject(json, new TypeReference<Map<String, Object>>(){}.getType());
	}
	
	public static List<Map<String, String>> getMapList(String json) {
		return JSON.parseObject(json, new TypeReference<List<Map<String,String>>>(){}.getType());
	}

	public static List<Map<String, Object>> getObjectMapList(String json) {
		return JSON.parseObject(json, new TypeReference<List<Map<String,Object>>>(){}.getType());
	}
	
	public static List<String> getStringList(String json) {
		return JSON.parseObject(json, new TypeReference<List<String>>(){}.getType());
	}

	public static <T> List<T> getList(String json, Class<T> clzz) {
		return JSON.parseArray(json, clzz);
	}

	/**
	 * @Description: 取字符串的前999位【不足时取本身长度】
	 * @param source 源字符串
	 * @Return: java.lang.String
	 * @Author: yangli
	 * @Date: 2019/9/4-16:23
	 **/
	public static String subThousandString(String source){
		int length = source.length();
		return source.substring(0, length > 1000 ? 999 : length);
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("a", 333);
		map.put("b", "2222");
		
		
		Map<String, Object> dMap1 = get(toJson(map), new HashMap<String, String>().getClass());
		for (String key : dMap1.keySet()) {
			Object val = dMap1.get(key);
			System.out.println(key + ", "+ val + ", " + (val instanceof String));
		}
		
		
		System.out.println("===============================");
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		list.add(map);
		
		List<Map<String, String>> dList = JSONUtils.getMapList(toJson(list));
		
		
		for (Map<String, String> dMap : dList) {
			
			for (String key : dMap.keySet()) {
				String val = dMap.get(key);
				System.out.println(key + ", "+ val + ", " + (val instanceof String));
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
