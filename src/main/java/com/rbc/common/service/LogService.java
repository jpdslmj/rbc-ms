package com.rbc.common.service;

import com.rbc.common.domain.LogDO;
import com.rbc.common.domain.PageDO;
import com.rbc.common.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
