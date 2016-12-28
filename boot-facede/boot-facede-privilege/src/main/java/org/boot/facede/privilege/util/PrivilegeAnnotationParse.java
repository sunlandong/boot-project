package org.boot.facede.privilege.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 权限注解解析器 这个解析器的主要功能，是解析目标方法上如果有PrivilegeInfo注解，那么解析出这个注解中的value值（权限的值）
 * 
 * @author sunld
 * 
 */
public class PrivilegeAnnotationParse {

	/**
	 * 解析注解
	 * 
	 * @param targetClass
	 *            　目标类的class形式
	 * @param methodName
	 *            　在客户端调用哪个方法,methodName就代表哪个方法　
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public static PrivilegeInfo parse(Class targetClass, String methodName)
			throws Exception {
		/*
		 * 获取注解的值
		 */
		Method[] methods = targetClass.getMethods();
		for(Method method : methods) {
			if(StringUtils.equals(methodName, method.getName())) {
				PrivilegeInfo findAnnotation = AnnotationUtils.findAnnotation(method, PrivilegeInfo.class);
				if(findAnnotation!=null) {
					return findAnnotation;
				}
			}
		}
		return null;
	}
}
