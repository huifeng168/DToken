package com.connsec.db.mybatis;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;


public class MyBatisSessionFactoryBean extends SqlSessionFactoryBean {
	protected Log log = LogFactory.getLog(getClass());
	private List<Interceptor> interceptors = Collections.emptyList();
	
	private int timeout = 30 ;

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
   
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		SqlSessionFactory factory = super.buildSqlSessionFactory();
		
		
		Configuration config = factory.getConfiguration();
		log.debug("buildSqlSessionFactory : "+config.toString());
		for (Interceptor interceptor : interceptors) {
			config.addInterceptor(interceptor);
		}
		//config.s
		log.debug("DefaultStatementTimeout : "+ config.getDefaultStatementTimeout());
		config.setDefaultStatementTimeout(timeout);
		log.debug("after change ,DefaultStatementTimeout : "+ config.getDefaultStatementTimeout());
		log.debug(config.getMappedStatementNames());
		return factory;
	}
	
	public SqlSessionFactory build() throws IOException {
		return buildSqlSessionFactory();
	}
}