
package com.connsec.db.mybatis.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.connsec.domain.Pagination;


public class SQLServerDialect extends Dialect {

	public SQLServerDialect() {
		super();

	}

	@Override
	public boolean supportsLimit() {
		return true;
	}
	
	@Override
	public String getLimitString(String sql,  Pagination pagination) {
		StringBuilder sb = new StringBuilder( "" );
		if(pagination.getPageResults()>0){
			
			sb.append("SELECT TOP "+pagination.getPageResults()+" * FROM ( ");
			sb.append("SELECT ROW_NUMBER() OVER() AS ROWNUMBER,MYBATIS_QUERY_TEMP_TABLE.* FROM ( ");
			sb.append("MYBATIS_QUERY_TEMP_TABLE ) MYBATIS_QUERY_TEMP_PAGE ");
			if(pagination.getStartRow()>0){
				sb.append("WHERE  ROWNUMBER > "+pagination.getStartRow());
			}
		}else{
			return sql;
		}
		return sb.toString();
	}
	
	@Override
	public String getPreparedStatementLimitString(String sql,  Pagination pagination) {
		//LIMIT #{pageResults}  OFFSET #{startRow}
		if(pagination.getPageResults()>0&&pagination.getStartRow()>0){
			return sql +  " LIMIT ? , ?";
		}else if(pagination.getPageResults()>0){
			return sql +  " LIMIT  ? ";
		}else{
			return sql +  " LIMIT ?";
		}
	}
	
	
	public void setLimitParamters(PreparedStatement preparedStatement,int parameterSize,Pagination pagination) {
		
		try {
			if(pagination.getPageResults()>0&&pagination.getStartRow()>0){
				preparedStatement.setInt(++parameterSize, pagination.getPageResults());
				preparedStatement.setInt(++parameterSize, pagination.getPageResults());
			}else if(pagination.getPageResults()>0){
				preparedStatement.setInt(++parameterSize, pagination.getPageResults());
			}else{
				preparedStatement.setInt(++parameterSize, 1000);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SQLServerDialect [" + SQLServerDialect.class + "]";
	}
}
