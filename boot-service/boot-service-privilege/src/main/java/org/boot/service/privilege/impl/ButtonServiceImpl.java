package org.boot.service.privilege.impl;

import java.util.List;
import java.util.Map;

import org.boot.facede.privilege.model.Button;
import org.boot.facede.privilege.model.ButtonCriteria;
import org.boot.facede.privilege.service.ButtonService;
import org.boot.facede.privilege.util.UUIDUtil;
import org.boot.service.privilege.dao.base.ButtonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ButtonServiceImpl implements ButtonService{
	
	@Autowired
	ButtonMapper buttonMapper;

	@Override
	@Transactional
	public void save(Button button) throws Exception {
		button.setButtonid(UUIDUtil.get32UUID());
		buttonMapper.insert(button);
	}

	@Override
	@Transactional
	public void delete(String buttonid) throws Exception {
		buttonMapper.deleteByPrimaryKey(buttonid);
		
	}

	@Override
	@Transactional
	public void edit(Button button) throws Exception {
		buttonMapper.updateByPrimaryKey(button);
	}

	@Override
	public List<Button> listWithSearch(Map<String, Object> params)
			throws Exception {
		return buttonMapper.listWithSearch(params);
	}
	
	@Override
	public List<Button> listAll() throws Exception {
		return buttonMapper.selectByExample(new ButtonCriteria());
	}

	@Override
	public Button findById(String buttonid) throws Exception {
		return buttonMapper.selectByPrimaryKey(buttonid);
	}

	@Override
	@Transactional
	public void deleteAll(List<String> buttonids) throws Exception {
		ButtonCriteria example = new ButtonCriteria();
		example.createCriteria().andButtonidIn(buttonids);
	}


}
