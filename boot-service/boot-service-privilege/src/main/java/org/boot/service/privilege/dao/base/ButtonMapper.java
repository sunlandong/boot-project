package org.boot.service.privilege.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.boot.facede.privilege.model.Button;
import org.boot.facede.privilege.model.ButtonCriteria;
import org.boot.service.privilege.dao.extension.ButtonExtensionMapper;
@Mapper
public interface ButtonMapper extends ButtonExtensionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int countByExample(ButtonCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int deleteByExample(ButtonCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String buttonid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int insert(Button record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int insertSelective(Button record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    List<Button> selectByExampleWithRowbounds(ButtonCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    List<Button> selectByExample(ButtonCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    Button selectByPrimaryKey(String buttonid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Button record, @Param("example") ButtonCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Button record, @Param("example") ButtonCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Button record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_BUTTON
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Button record);
}