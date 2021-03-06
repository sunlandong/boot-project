package org.boot.facede.activiti.model;

import java.io.Serializable;
import java.util.Date;

public class ApproveInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_AS_APPROVE_INFO.LO_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    private Long loId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_AS_APPROVE_INFO.DT_CTIME_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    private Date dtCtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_AS_APPROVE_INFO.VC_APPLICATION_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    private String vcApplicationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_AS_APPROVE_INFO.VC_RESULT_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    private String vcResult;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_AS_APPROVE_INFO.VC_USER_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    private String vcUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_AS_APPROVE_INFO.VC_COMMENT_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    private String vcComment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ACT_AS_APPROVE_INFO
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_AS_APPROVE_INFO.LO_ID_
     *
     * @return the value of ACT_AS_APPROVE_INFO.LO_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public Long getLoId() {
        return loId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_AS_APPROVE_INFO.LO_ID_
     *
     * @param loId the value for ACT_AS_APPROVE_INFO.LO_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public void setLoId(Long loId) {
        this.loId = loId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_AS_APPROVE_INFO.DT_CTIME_
     *
     * @return the value of ACT_AS_APPROVE_INFO.DT_CTIME_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public Date getDtCtime() {
        return dtCtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_AS_APPROVE_INFO.DT_CTIME_
     *
     * @param dtCtime the value for ACT_AS_APPROVE_INFO.DT_CTIME_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public void setDtCtime(Date dtCtime) {
        this.dtCtime = dtCtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_AS_APPROVE_INFO.VC_APPLICATION_ID_
     *
     * @return the value of ACT_AS_APPROVE_INFO.VC_APPLICATION_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public String getVcApplicationId() {
        return vcApplicationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_AS_APPROVE_INFO.VC_APPLICATION_ID_
     *
     * @param vcApplicationId the value for ACT_AS_APPROVE_INFO.VC_APPLICATION_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public void setVcApplicationId(String vcApplicationId) {
        this.vcApplicationId = vcApplicationId == null ? null : vcApplicationId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_AS_APPROVE_INFO.VC_RESULT_
     *
     * @return the value of ACT_AS_APPROVE_INFO.VC_RESULT_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public String getVcResult() {
        return vcResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_AS_APPROVE_INFO.VC_RESULT_
     *
     * @param vcResult the value for ACT_AS_APPROVE_INFO.VC_RESULT_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public void setVcResult(String vcResult) {
        this.vcResult = vcResult == null ? null : vcResult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_AS_APPROVE_INFO.VC_USER_ID_
     *
     * @return the value of ACT_AS_APPROVE_INFO.VC_USER_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public String getVcUserId() {
        return vcUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_AS_APPROVE_INFO.VC_USER_ID_
     *
     * @param vcUserId the value for ACT_AS_APPROVE_INFO.VC_USER_ID_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public void setVcUserId(String vcUserId) {
        this.vcUserId = vcUserId == null ? null : vcUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_AS_APPROVE_INFO.VC_COMMENT_
     *
     * @return the value of ACT_AS_APPROVE_INFO.VC_COMMENT_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public String getVcComment() {
        return vcComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_AS_APPROVE_INFO.VC_COMMENT_
     *
     * @param vcComment the value for ACT_AS_APPROVE_INFO.VC_COMMENT_
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    public void setVcComment(String vcComment) {
        this.vcComment = vcComment == null ? null : vcComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_AS_APPROVE_INFO
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", loId=").append(loId);
        sb.append(", dtCtime=").append(dtCtime);
        sb.append(", vcApplicationId=").append(vcApplicationId);
        sb.append(", vcResult=").append(vcResult);
        sb.append(", vcUserId=").append(vcUserId);
        sb.append(", vcComment=").append(vcComment);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_AS_APPROVE_INFO
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ApproveInfo other = (ApproveInfo) that;
        return (this.getLoId() == null ? other.getLoId() == null : this.getLoId().equals(other.getLoId()))
            && (this.getDtCtime() == null ? other.getDtCtime() == null : this.getDtCtime().equals(other.getDtCtime()))
            && (this.getVcApplicationId() == null ? other.getVcApplicationId() == null : this.getVcApplicationId().equals(other.getVcApplicationId()))
            && (this.getVcResult() == null ? other.getVcResult() == null : this.getVcResult().equals(other.getVcResult()))
            && (this.getVcUserId() == null ? other.getVcUserId() == null : this.getVcUserId().equals(other.getVcUserId()))
            && (this.getVcComment() == null ? other.getVcComment() == null : this.getVcComment().equals(other.getVcComment()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_AS_APPROVE_INFO
     *
     * @mbg.generated Mon Dec 26 14:01:03 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoId() == null) ? 0 : getLoId().hashCode());
        result = prime * result + ((getDtCtime() == null) ? 0 : getDtCtime().hashCode());
        result = prime * result + ((getVcApplicationId() == null) ? 0 : getVcApplicationId().hashCode());
        result = prime * result + ((getVcResult() == null) ? 0 : getVcResult().hashCode());
        result = prime * result + ((getVcUserId() == null) ? 0 : getVcUserId().hashCode());
        result = prime * result + ((getVcComment() == null) ? 0 : getVcComment().hashCode());
        return result;
    }
}