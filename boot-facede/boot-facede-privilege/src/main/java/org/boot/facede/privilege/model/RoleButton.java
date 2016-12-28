package org.boot.facede.privilege.model;

import java.io.Serializable;

public class RoleButton implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_SYS_ROLE_BUTTON.RBID
     *
     * @mbggenerated
     */
    private String rbid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_SYS_ROLE_BUTTON.ROLEID
     *
     * @mbggenerated
     */
    private String roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_SYS_ROLE_BUTTON.BUTTONID
     *
     * @mbggenerated
     */
    private String buttonid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_SYS_ROLE_BUTTON
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_SYS_ROLE_BUTTON.RBID
     *
     * @return the value of T_SYS_ROLE_BUTTON.RBID
     *
     * @mbggenerated
     */
    public String getRbid() {
        return rbid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_SYS_ROLE_BUTTON.RBID
     *
     * @param rbid the value for T_SYS_ROLE_BUTTON.RBID
     *
     * @mbggenerated
     */
    public void setRbid(String rbid) {
        this.rbid = rbid == null ? null : rbid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_SYS_ROLE_BUTTON.ROLEID
     *
     * @return the value of T_SYS_ROLE_BUTTON.ROLEID
     *
     * @mbggenerated
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_SYS_ROLE_BUTTON.ROLEID
     *
     * @param roleid the value for T_SYS_ROLE_BUTTON.ROLEID
     *
     * @mbggenerated
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_SYS_ROLE_BUTTON.BUTTONID
     *
     * @return the value of T_SYS_ROLE_BUTTON.BUTTONID
     *
     * @mbggenerated
     */
    public String getButtonid() {
        return buttonid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_SYS_ROLE_BUTTON.BUTTONID
     *
     * @param buttonid the value for T_SYS_ROLE_BUTTON.BUTTONID
     *
     * @mbggenerated
     */
    public void setButtonid(String buttonid) {
        this.buttonid = buttonid == null ? null : buttonid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE_BUTTON
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rbid=").append(rbid);
        sb.append(", roleid=").append(roleid);
        sb.append(", buttonid=").append(buttonid);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE_BUTTON
     *
     * @mbggenerated
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
        RoleButton other = (RoleButton) that;
        return (this.getRbid() == null ? other.getRbid() == null : this.getRbid().equals(other.getRbid()))
            && (this.getRoleid() == null ? other.getRoleid() == null : this.getRoleid().equals(other.getRoleid()))
            && (this.getButtonid() == null ? other.getButtonid() == null : this.getButtonid().equals(other.getButtonid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE_BUTTON
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRbid() == null) ? 0 : getRbid().hashCode());
        result = prime * result + ((getRoleid() == null) ? 0 : getRoleid().hashCode());
        result = prime * result + ((getButtonid() == null) ? 0 : getButtonid().hashCode());
        return result;
    }
}