package org.boot.service.privilege.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.boot.facede.privilege.model.Role;
import org.boot.facede.privilege.model.RoleCriteria;
import org.boot.service.privilege.dao.extension.RoleExtensionMapper;

@Mapper
public interface RoleMapper extends RoleExtensionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int countByExample(RoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int deleteByExample(RoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String roleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    List<Role> selectByExampleWithRowbounds(RoleCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    List<Role> selectByExample(RoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    Role selectByPrimaryKey(String roleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_ROLE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Role record);
}