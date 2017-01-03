package org.boot.service.core.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.boot.common.core.exception.BizException;
import org.boot.common.core.utils.MD5;
import org.boot.common.core.utils.StringUtil;
import org.boot.common.core.utils.SystemCons;
import org.boot.facede.core.model.User;
import org.boot.facede.core.model.UserCriteria;
import org.boot.facede.core.model.UserCriteria.Criteria;
import org.boot.facede.core.service.UserService;
import org.boot.service.core.dao.base.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public User getUserById(String id) throws Exception {
		UserCriteria example= new UserCriteria();
		example.createCriteria().andVcIsDelEqualTo(SystemCons.IS.NO).andVcIdEqualTo(id);
		List<User> selectByExample = userMapper.selectByExample(example);
		if(selectByExample.size()==0) {
			throw new BizException("没有结果");
		}
		return selectByExample.get(0);
	}

	@Override
	public List<User> findUsersList(int start, int limit,
			Map<String, Object> params) throws Exception {
		UserCriteria example = new UserCriteria();
		example.createCriteria().andVcIsDelEqualTo(SystemCons.IS.NO);
		return userMapper.selectByExample(example);
	}

	@Override
	@Transactional
	public void saveUser(User user) throws Exception {
		this.checkAccount_(user);
		
		user.setVcId(StringUtil.get36UUID());
		user.setDtCreateTime(new Date());
		user.setVcIsDel(SystemCons.IS.NO);
		user.setVcPassword(MD5.md5(user.getVcPassword()));
		userMapper.insertSelective(user);
	}

	private void checkAccount_(User user) {
		if(StringUtils.isNoneBlank(user.getVcAccount())) {
			UserCriteria example = new UserCriteria();
			Criteria accountCriteria = example.createCriteria();
			accountCriteria.andVcAccountEqualTo(user.getVcAccount()).andVcIsDelEqualTo(SystemCons.IS.NO);
			long accountCount = userMapper.countByExample(example);
			if(accountCount>0) {
				throw new BizException("该用户名已经存在");
			}
		} else {
			throw new BizException("用户名不能为空");
		}
		if(StringUtils.isNoneBlank(user.getVcAccount())) {
			UserCriteria example = new UserCriteria();
			Criteria nameCriteria = example.createCriteria();
			nameCriteria.andVcNameEqualTo(user.getVcName()).andVcIsDelEqualTo(SystemCons.IS.NO);
			long nameCount = userMapper.countByExample(example);
			if(nameCount>0) {
				throw new BizException("该名字已经存在");
			}
		} else {
			throw new BizException("名字不能为空");
		}
	}

	@Override
	@Transactional
	public void updateUser(User user) throws Exception {
		checkAccount_(user);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	@Transactional
	public void deleteUser(String ...userIds) throws Exception {
		for (String userid: userIds) {
			//要判断一下自己不能删除自己
			User user = new User();
			user.setVcId(userid);
			user.setVcIsDel(SystemCons.IS.YES);
			userMapper.updateByPrimaryKeySelective(user);
		}
	}

}
