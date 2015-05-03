package com.connsec.db.mybatis;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.RowBounds;

import com.connsec.db.mybatis.dialect.Dialect;


public abstract class AbstractStatementHandlerInterceptor  implements Interceptor {
	protected Log log = LogFactory.getLog(getClass());
	
	protected Dialect dialect;
	
	protected String dialectString;

	/**
	 * @param dialect the dialect to set
	 */
	public void setDialect(Dialect dialect) {
		log.debug("dialect from bean : "+dialect);
		this.dialect = dialect;
	}

	
	/**
	 * @param dialectString the dialectString to set
	 */
	public void setDialectString(String dialectString) {
		this.dialectString = dialectString;
		try {
			
			this.dialect =(Dialect)Class.forName(dialectString).newInstance();
			log.debug("dialect from String : "+dialectString);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected StatementHandler getStatementHandler(Invocation invocation) {
		StatementHandler statement = (StatementHandler) invocation.getTarget();
		if (statement instanceof RoutingStatementHandler) {
			MetaObject metaObject=SystemMetaObject.forObject(statement);
			StatementHandler statementHandler=(StatementHandler)metaObject.getValue("delegate");
			return statementHandler;
		
		}
		return statement;
	}
	
	protected RowBounds getRowBounds(StatementHandler statement) {
			MetaObject metaObject=SystemMetaObject.forObject(statement);
			RowBounds rowBounds=(RowBounds)metaObject.getValue("rowBounds");
			return rowBounds;
	}
	
	protected boolean hasBounds(RowBounds rowBounds) {
		return (rowBounds != null 
				&& rowBounds.getLimit() > 0 
				&& rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT);
	}
	
	  protected String removeBreakingWhitespace(String original) {
		    StringTokenizer whitespaceStripper = new StringTokenizer(original);
		    StringBuilder builder = new StringBuilder();
		    while (whitespaceStripper.hasMoreTokens()) {
		      builder.append(whitespaceStripper.nextToken());
		      builder.append(" ");
		    }
		    return builder.toString();
		  }
}