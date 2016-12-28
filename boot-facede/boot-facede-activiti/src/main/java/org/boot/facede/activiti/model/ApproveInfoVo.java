package org.boot.facede.activiti.model;

public class ApproveInfoVo {
	private ApproveInfo approveInfo;
	
	private String taskId;

	public ApproveInfo getApproveInfo() {
		return approveInfo;
	}

	public void setApproveInfo(ApproveInfo approveInfo) {
		this.approveInfo = approveInfo;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public String toString() {
		return "ApproveInfoVo [approveInfo=" + approveInfo + ", taskId="
				+ taskId + "]";
	}
}	
