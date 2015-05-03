package com.connsec.db.mybatis.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.connsec.domain.Pagination;


public class OracleDialect extends Dialect {

	public OracleDialect() {
		super();

	}

	@Override
	public boolean supportsLimit() {
		return true;
	}
	
	@Override
	public String getLimitString(String sql,  Pagination pagination) {
		if ( pagination.getPageResults() == 0 ) {
			return sql + " fetch first " + pagination.getStartRow() + " rows only";
		}
		StringBuilder pagingSelect = new StringBuilder( sql.length() + 200 )
				.append(
						"select * from ( select inner2_.*, rownumber() over(order by order of inner2_) as rownumber_ from ( "
				)
				.append( sql )  //nest the main query in an outer select
				.append( " fetch first " )
				.append( pagination.getPageResults() )
				.append( " rows only ) as inner2_ ) as inner1_ where rownumber_ > " )
				.append( pagination.getStartRow() )
				.append( " order by rownumber_" );
		return pagingSelect.toString();
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
		return "OracleDialect [" + OracleDialect.class + "]";
	}
}
