package com.connsec.db.mybatis.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.connsec.domain.Pagination;


public class DerbyDialect extends Dialect {

	public DerbyDialect() {
		super();

	}

	@Override
	public boolean supportsLimit() {
		return true;
	}
	
	@Override
	public String getLimitString(String sql,  Pagination pagination) {
		StringBuilder sb = new StringBuilder(sql.length() + 50);

		sb.append( sql );
		
		if ( pagination.getStartRow() == 0 ) {
			sb.append( " fetch first " );
		}
		else {
			sb.append( " offset " ).append( pagination.getStartRow() ).append( " rows fetch next " );
		}

		sb.append( pagination.getPageResults() ).append( " rows only" );
		
		
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
		return "DerbyDialect [" + DerbyDialect.class + "]";
	}
}
