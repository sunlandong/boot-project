package org.boot.service.privilege.dao.extension;

import java.util.List;
import java.util.Map;

import org.boot.facede.privilege.model.Button;

public interface ButtonExtensionMapper {

	List<Button> listWithSearch(Map<String, Object> params) throws Exception;
}
