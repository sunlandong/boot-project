package org.boot.common.core.utils;


/**
 * 系统常量表
 * 
 * @author sunlandong
 *
 */
public class SystemCons{
	
	
	/**
	 * 用户状态
	 */
	public static final class USER_STATUS{
		//正常
		public static final String NORMAL = "1";
		//锁定
		public static final String LOCK = "2";
		//停用
		public static final String DISABLE = "3";
	}
	
	/**
	 * 对象删除规则
	 *
	 */
	public static final class OBJECT_DELETE_RULE {
		// 逻辑删除
		public static final String UPDATE = "update";
		// 物理删除
		public static final String DELETE = "delete";
	}

	/**
	 * 是否标识
	 */
	public static final class IS {
		public static final String YES = "1";
		public static final String NO = "0";
	}
	
	
}
