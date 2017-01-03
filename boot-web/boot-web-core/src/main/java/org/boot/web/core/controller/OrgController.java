package org.boot.web.core.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.boot.common.core.utils.ErrorInfo;
import org.boot.common.core.utils.ResultUtils;
import org.boot.facede.core.model.Org;
import org.boot.facede.core.service.OrgService;
import org.boot.web.core.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping(value="/org")
public class OrgController {

	@Autowired
	OrgService orgService;
	
	@RequestMapping(value="/getOrgTreeData",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getOrgTreeData()throws Exception{
		return orgService.getOrgTreeData("0");
	}
	
	@RequestMapping(value="/findOrgsList",method=RequestMethod.GET)
	@ResponseBody
	public List<Org> findOrgsList(@RequestParam(required=false,defaultValue="0")int start,
			@RequestParam(required=false,defaultValue="10")int limit,HttpServletRequest request) throws Exception{
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		return orgService.getOrgListWithPage(start, limit, params);
	}

	@RequestMapping(value="/getOrg/{orgid}",method=RequestMethod.GET)
	@ResponseBody
	public Org getOrg(@PathVariable("orgid")String orgid) throws Exception{
		return orgService.getOrg(orgid);
	}
	
	@RequestMapping(value="/saveOrg",method=RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<String> saveOrg(@RequestBody Org org) throws Exception{
		//需要从redis从取当前操作人
		orgService.saveOrg(org);
		return ResultUtils.createSuccess("ok");
	}
	
	@RequestMapping(value="/updateOrg",method=RequestMethod.PUT)
	@ResponseBody
	public ErrorInfo<String> updateOrg(@RequestBody Org org) throws Exception{
		//需要从redis从取当前操作人
		orgService.updateOrg(org);
		return ResultUtils.createSuccess("ok");
	}
	
	@RequestMapping(value="/deleteOrg/{orgid}",method=RequestMethod.DELETE)
	@ResponseBody
	public ErrorInfo<String> deleteOrg(@PathVariable("orgid") String orgid) throws Exception{
		//需要从redis从取当前操作人
		orgService.deleteOrg(orgid);
		return ResultUtils.createSuccess("ok");
	}
	
}
