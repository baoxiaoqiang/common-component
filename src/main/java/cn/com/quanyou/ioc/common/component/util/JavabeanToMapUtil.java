package cn.com.quanyou.ioc.common.component.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JavabeanToMapUtil {
	private static final Logger logger = LoggerFactory.getLogger(JavabeanToMapUtil.class);


    public static Date date = new Date();

	/** 
	 * 将一个 JavaBean 对象转化为一个  Map 
	 * @param bean 要转化的JavaBean 对象 
	 * @return 转化出来的  Map 对象 
	 * @throws IntrospectionException 如果分析类属性失败 
	 * @throws IllegalAccessException 如果实例化 JavaBean 失败 
	 * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
	 */  
	public static Map<String, Object> JavabeenToMap(Object bean) {  
	    Class<? extends Object> type = bean.getClass();  
	    Map<String, Object> returnMap = new HashMap<String, Object>();  
	    BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
			for (int i = 0; i< propertyDescriptors.length; i++) {  
				PropertyDescriptor descriptor = propertyDescriptors[i];  
				String propertyName = descriptor.getName();  
				if (!propertyName.equals("class")) {  
					Method readMethod = descriptor.getReadMethod();  
					Object result = readMethod.invoke(bean, new Object[0]);  
					if (result != null) {  
						returnMap.put(propertyName, result);  
					} else {  
						returnMap.put(propertyName, "");  
					}  
				}  
			}  
		} catch (IntrospectionException e) {
			e.printStackTrace();
			logger.error("JavabeenToMap分析类属性失败:" + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error("JavabeenToMap实例化 JavaBean 失败:" + e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.error("JavabeenToMap调用属性的 setter 方法失败:" + e.getMessage());
		}  
	    return returnMap;  
	}
	
	/** 
	 * 将一个 JavaBean 对象转化为一个  Map 
	 * @param bean 要转化的JavaBean 对象 
	 * @return 转化出来的  Map 对象 
	 * @throws IntrospectionException 如果分析类属性失败 
	 * @throws IllegalAccessException 如果实例化 JavaBean 失败 
	 * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
	 */  
	public static Map<String, String> JavabeenToMapString(Object bean) {  
	    Class<? extends Object> type = bean.getClass();  
	    Map<String, String> returnMap = new HashMap<String, String>();  
	    BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
			for (int i = 0; i< propertyDescriptors.length; i++) {  
				PropertyDescriptor descriptor = propertyDescriptors[i];  
				String propertyName = descriptor.getName();  
				if (!propertyName.equals("class")) {  
					Method readMethod = descriptor.getReadMethod();  
					String result = (String)readMethod.invoke(bean, new Object[0]);  
					if (result != null) {  
						returnMap.put(propertyName, result);  
					} else {  
						returnMap.put(propertyName, "");  
					}  
				}  
			}  
		} catch (IntrospectionException e) {
			e.printStackTrace();
			logger.error("JavabeenToMap分析类属性失败:" + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error("JavabeenToMap实例化 JavaBean 失败:" + e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.error("JavabeenToMap调用属性的 setter 方法失败:" + e.getMessage());
		}  
	    return returnMap;  
	}

	public static Map<String, String> transBean2Map(Object obj) {

		if(obj == null){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if(value != null){ //过滤为空的值
						map.put(key, String.valueOf(value));
					}

				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}
    //bean中有date类型的数据
    public static Map<String, String> transBean2Map(Object obj, String dateFormat) {

        if(obj == null){
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    if (property.getPropertyType().isInstance(date) && getter.invoke(obj) != null) {
                        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
                        map.put(key, sdf.format((Date)value));
                    }else {
                        if (value != null) {
                            map.put(key, String.valueOf(value));
                        }
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }



}
