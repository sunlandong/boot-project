package org.boot.web.core.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.boot.common.core.utils.ErrorInfo;
import org.boot.common.core.utils.ResultUtils;
import org.boot.facede.core.model.User;
import org.boot.facede.core.service.UserService;
import org.boot.web.core.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/findUsersList",method=RequestMethod.GET)
	@ResponseBody
	public List<User> findUsersList(@RequestParam(required=false,defaultValue="0")int start,
			@RequestParam(required=false,defaultValue="10")int limit,HttpServletRequest request) throws Exception{
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		return userService.findUsersList(start, limit, params);
	}

	@RequestMapping(value="/getUser/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("userid")String userid) throws Exception{
		return userService.getUserById(userid);
	}
	
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<String> saveUser(@RequestBody User user) throws Exception{
		//需要从redis从取当前操作人
		userService.saveUser(user);
		return ResultUtils.createSuccess("ok");
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.PUT)
	@ResponseBody
	public ErrorInfo<String> updateUser(@RequestBody User user) throws Exception{
		//需要从redis从取当前操作人
		userService.updateUser(user);
		return ResultUtils.createSuccess("ok");
	}
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.DELETE)
	@ResponseBody
	public ErrorInfo<String> deleteUser(@RequestBody String[] userids) throws Exception{
		//需要从redis从取当前操作人
		userService.deleteUser(userids);
		return ResultUtils.createSuccess("ok");
	}
	
}
