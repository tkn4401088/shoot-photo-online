/*
 * Powered By [finger-framework]
 * Web Site: http://www.fingercrm.cn/
 * Since 2017 - 2017
 */


package com.finger.shoot.common;

import com.finger.portal.base.model.PageModel;
import com.finger.portal.base.util.JdbcUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * mybatis拦截器  配置分页  以selectPage开头的进拦截器
 *
 * @author zb
 * @version 1.0
 * @since 1.0
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class PagingInterceptor implements Interceptor {
	// 存储所有语句名称
	Map<String, String> map_statement = new HashMap<String, String>();
	// 用户提供分页计算条数后缀
	static final String COUNT_ID = "_count";

	/**
	 * 获取所有statement语句的名称
	 * <p>
	 *
	 * @param configuration
	 */
	protected synchronized void initStatementMap(Configuration configuration) {
		if (!map_statement.isEmpty()) {
			return;
		}
		Collection<String> statements = configuration.getMappedStatementNames();
		for (Iterator<String> iter = statements.iterator(); iter.hasNext();) {
			String element = iter.next();
			map_statement.put(element, element);
		}
	}

	/**
	 * 获取数据库连接
	 * <p>
	 *
	 * @param transaction
	 * @param statementLog
	 * @return
	 * @throws SQLException
	 */
	protected Connection getConnection(Transaction transaction, Log statementLog) throws SQLException {
		Connection connection = transaction.getConnection();
		if (statementLog.isDebugEnabled()) {
			return ConnectionLogger.newInstance(connection, statementLog, 0);
		} else {
			return connection;
		}
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object parameter = invocation.getArgs()[1];
		PageModel page = seekPage(parameter);
		if (page == null) {
			return invocation.proceed();
		} else {
			MappedStatement ms = (MappedStatement)invocation.getArgs()[0];
			if(ms!=null && ms.getId().indexOf("selectPage")>=0){
				return handlePaging(invocation, parameter, page);
			}
			return invocation.proceed();
		}

	}

	/**
	 * 处理分页的情况
	 */
	@SuppressWarnings("rawtypes")
	protected List handlePaging(Invocation invocation, Object parameter, PageModel page) throws Exception {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Configuration configuration = mappedStatement.getConfiguration();
		if (map_statement.isEmpty()) {
			initStatementMap(configuration);
		}
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		// 查询结果集
		StaticSqlSource sqlsource = new StaticSqlSource(configuration, getLimitString(boundSql.getSql(), page), boundSql.getParameterMappings());
		MappedStatement.Builder builder = new MappedStatement.Builder(configuration, "id_temp_result", sqlsource, SqlCommandType.SELECT);
		builder.resultMaps(mappedStatement.getResultMaps()).resultSetType(mappedStatement.getResultSetType()).statementType(mappedStatement.getStatementType());
		MappedStatement query_statement = builder.build();

		List data = (List) exeQuery(invocation, query_statement);
		int rowsCount = getTotalSize(invocation, configuration, mappedStatement, boundSql, parameter);
		int pageCount = rowsCount > 0 ? (rowsCount / page.getPageSize() + (rowsCount % page.getPageSize() > 0 ? 1 : 0)) : 0;
		if(data!=null && data.size()>0){
			for(Object obj : data){
				PageModel pg = (PageModel)obj;
				pg.setCurPage(page.getCurPage());
				pg.setPageSize(page.getPageSize());
				pg.setRowsCount(rowsCount);
				pg.setPageCount(pageCount);
			}
		}

		return data;
	}

	/**
	 * 根据提供的语句执行查询操作
	 */
	protected Object exeQuery(Invocation invocation, MappedStatement query_statement) throws Exception {
		Object[] args = invocation.getArgs();
		return invocation.getMethod().invoke(invocation.getTarget(), new Object[] { query_statement, args[1], args[2], args[3] });
	}

	/**
	 * 获取总记录数量
	 */
	@SuppressWarnings("rawtypes")
	protected int getTotalSize(Invocation invocation, Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object parameter) throws Exception {

		String count_id = mappedStatement.getId() + COUNT_ID;
		int totalSize = 0;
		if (map_statement.containsKey(count_id)) {
			// 优先查找能统计条数的sql
			List data = (List) exeQuery(invocation, mappedStatement.getConfiguration().getMappedStatement(count_id));
			if (data.size() > 0) {
				totalSize = Integer.parseInt(data.get(0).toString());
			}
		} else {
			Executor exe = (Executor) invocation.getTarget();
			Connection connection = getConnection(exe.getTransaction(), mappedStatement.getStatementLog());
			String countSql = getCountSql(boundSql.getSql());
			totalSize = getTotalSize(configuration, mappedStatement, boundSql, countSql, connection, parameter);
		}

		return totalSize;
	}

	/**
	 * 拼接查询sql,加入limit
	 */
	protected String getLimitString(String sql, PageModel page) {
		StringBuffer sb = new StringBuffer(sql.length() + 100);
		sb.append(sql);
		sb.append(" limit ").append(page.getStartNo() - 1).append(",").append(page.getPageSize());
		return sb.toString();
	}

	/**
	 * 拼接获取条数的sql语句
	 * <p>
	 *
	 * @param sqlPrimary
	 */
	protected String getCountSql(String sqlPrimary) {
		// 只保留最外层的sql
		if (JdbcUtils.replaceAllParenthesis(sqlPrimary).indexOf("order by") > 0) {
			sqlPrimary = sqlPrimary.replace(sqlPrimary.substring(sqlPrimary.lastIndexOf("order by"), sqlPrimary.length()), "");
		}
		return "select count(1) from (".concat(sqlPrimary).concat(") z");
	}

	/**
	 * 计算总条数
	 */
	protected int getTotalSize(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, String countSql, Connection connection, Object parameter) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalSize = 0;
		try {
			ParameterHandler handler = configuration.newParameterHandler(mappedStatement, parameter, boundSql);
			stmt = connection.prepareStatement(countSql);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalSize = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		}
		return totalSize;
	}

	/**
	 * 寻找page对象
	 */
	@SuppressWarnings("rawtypes")
	protected PageModel seekPage(Object parameter) {
		PageModel page = null;
		if (parameter == null) {
			return null;
		}
		if (parameter instanceof PageModel) {
			page = (PageModel) parameter;
		} else if (parameter instanceof Map) {
			Map map = (Map) parameter;
			for (Object arg : map.values()) {
				if (arg instanceof PageModel) {
					page = (PageModel) arg;
				}
			}
		}
		return page;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}