package org.boot.facede.activiti.model;

import java.io.Serializable;

import org.activiti.engine.task.Task;

/**
 * 包装任务信息和申请信息
 *
 */
public class TaskView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1224274773739494709L;
	private Task task;
	private Application application;
	
	public TaskView() {}
	
	public TaskView(Task task, Application application) {
		this.task = task;
		this.application = application;
	}

	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
}
