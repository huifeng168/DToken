package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.LogsMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.Logs;

@Service
public class LogsService  extends BaseService<Logs>{

	public LogsService() {
		super(LogsMapper.class);
		
	}
	
	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public LogsMapper getMapper() {
		// TODO Auto-generated method stub
		return (LogsMapper)super.getMapper();
	}
}
