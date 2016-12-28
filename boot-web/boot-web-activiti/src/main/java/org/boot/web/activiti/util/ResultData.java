package org.boot.web.activiti.util;

import java.io.Serializable;

/**
 * 页面请求数据，不带分页
 * @author landong.satisfy
 *
 * @param <T>
 */
public class ResultData<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5015035426484921366L;
	public ResultData(){};
	public ResultData(long total,int count,T data){
		this.total = total;
		this.count = count;
		this.data = data;
	}
	private long total;
	private int count;
	
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	private T data;
	

	
	
	
}
