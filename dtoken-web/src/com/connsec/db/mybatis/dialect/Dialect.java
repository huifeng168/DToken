package com.connsec.db.mybatis.dialect;


import java.sql.PreparedStatement;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connsec.domain.Pagination;


public abstract class Dialect {

	private static final Logger _log = LoggerFactory.getLogger(Dialect.class);

	public static final String DEFAULT_BATCH_SIZE = "15";
	public static final String NO_BATCH = "0";


	static HashMap<String,String> dialectMap;
	
	static {
		dialectMap=new HashMap<String,String>();
		dialectMap.put("db2", "com.connsec.db.mybatis.dialect.DB2Dialect");
		dialectMap.put("derby", "com.connsec.db.mybatis.dialect.DerbyDialect");
		dialectMap.put("mysql", "com.connsec.db.mybatis.dialect.MySQLDialect");
		dialectMap.put("oracle", "com.connsec.db.mybatis.dialect.OracleDialect");
		dialectMap.put("postgresql", "com.connsec.db.mybatis.dialect.PostgreSQLDialect");
		dialectMap.put("sqlserver", "com.connsec.db.mybatis.dialect.SQLServerDialect");
	}
	
	// constructors and factory methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	protected Dialect() {

	}






	@Override
    public String toString() {
		return getClass().getName();
	}

	/**
	 * Given a limit and an offset, apply the limit clause to the query.
	 *
	 * @param query The query to which to apply the limit.
	 * @param offset The offset of the limit
	 * @param limit The limit of the limit ;)
	 * @return The modified query statement with the limit applied.
	 */

	public String getLimitString(String query, Pagination pagination) {
		throw new UnsupportedOperationException( "Paged queries not supported by " + getClass().getName());
	}
	
	
	
	
	public String getPreparedStatementLimitString(String query, Pagination pagination) {
		throw new UnsupportedOperationException( "Paged queries not supported by " + getClass().getName());
	}
	
	
	public void setLimitParamters(PreparedStatement preparedStatement,int parameterSize,Pagination pagination) {
		throw new UnsupportedOperationException( "Paged queries not supported by " + getClass().getName());
	}
	
	
	public boolean supportsLimit() {
		return false;
	}

	/**
	 * @return the dialectMap
	 */
	public static HashMap<String, String> getDialectMap() {
		return dialectMap;
	}
	
	
}
