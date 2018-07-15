package com.rbc.system.service;

import com.rbc.system.domain.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);

	Set<String> listRoles(Long userId);
}
