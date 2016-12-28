package org.boot.web.activiti.util;


public class ResultUtils {
	
	public static final Integer OK = 0;
    public static final Integer ERROR = 100;
	
	public static <T> ErrorInfo<T> createMsg(int errcode,String errMsg,String url,T data) throws Exception{
		ErrorInfo<T> error = new ErrorInfo<T>();
		error.setCode(errcode);
		error.setMessage(errMsg);
		error.setUrl(url);
		error.setData(data);
		return error;
	}

	public static ErrorInfo<String> createSuccess(String errMsg) throws Exception{
		
		return createMsg(OK,errMsg,"","");
	}
	
	public static ErrorInfo<String> createError(String errMsg) throws Exception{
		return createMsg(ERROR,errMsg,"","");
	}
	
	
}
