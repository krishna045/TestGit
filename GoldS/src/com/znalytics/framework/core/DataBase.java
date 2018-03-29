// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.testng.Assert;

/**
 * The Class DataBase.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class DataBase {

	/** The con. */
	private Connection con = null;

	/** The server name. */
	private String serverName = "";

	/** The db name. */
	private String dbName = "";

	/**
	 * Gets the connection string.
	 *
	 * @return the connection string
	 */
	private String getConnectionString() {
		String dbuser = DataSource.globalConfig.get("sqluser");
		if (dbuser == "")
			return ";integratedSecurity=true;";
		String dbpassword = DataSource.globalConfig.get("sqlpassword");
		return String.format(";user=%1$s;password=%2$s", dbuser, dbpassword);
	}

	/**
	 * Instantiates a new data base.
	 *
	 * @param serverName
	 *            the server name
	 * @param dbName
	 *            the db name
	 */
	public DataBase(String serverName, String dbName) {
		this.serverName = serverName;
		this.dbName = dbName;
		try {
			this.serverName = this.serverName.replaceAll("\\s+", " ");
			String[] dbServer = this.serverName.split(",");
			if (dbServer[1] == "") {
				dbServer[1] = "1433";
			}
			String url = "jdbc:sqlserver://" + dbServer[0] + ":" + dbServer[1]
					+ ";database=" + this.dbName + getConnectionString();
			this.con = DriverManager.getConnection(url);
			if (con != null)
				Logs.LOGGER.info("Connection Successful!");
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Gets the data.
	 *
	 * @param query
	 *            the query
	 * @return the data
	 */
	public ResultSet getData(String query) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		String methodName = stacktrace[2].getMethodName();
		Logs.LOGGER.info("SQL -- (" + methodName + ") : " + query);
		try {
			Statement stmt = this.con.createStatement();
			return stmt.executeQuery(query);
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
			Assert.fail(e.getMessage());
		}
		return null;
	}

	/**
	 * Update data.
	 *
	 * @param query
	 *            the query
	 * @return the int
	 */
	public int updateData(String query) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		String methodName = stacktrace[2].getMethodName();
		Logs.LOGGER.info("SQL -- (" + methodName + ") : " + query);
		try {
			Statement stmt = this.con.createStatement();
			int rowCount = stmt.executeUpdate(query);
			Logs.LOGGER.info("# of rows updated: " + rowCount);
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
			Assert.fail(e.getMessage());
		}
		return 0;
	}

	/**
	 * Return data from complex statement.
	 *
	 * @param query
	 *            the query
	 * @return the result set
	 */
	public ResultSet returnDataFromComplexStatement(String query) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		String methodName = stacktrace[2].getMethodName();
		Logs.LOGGER.info("SQL -- (" + methodName + ") : " + query);
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			boolean results = stmt.execute(query);
			int count = 0;
			do {
				if (results) {
					rs = stmt.getResultSet();
					Logs.LOGGER.info("Result set data found.");
					return rs;
				} else {
					count = stmt.getUpdateCount();
					if (count >= 0) {
						Logs.LOGGER.info("DDL or update data.");
					} else {
						Logs.LOGGER.info("No more results to process.");
					}
				}
				results = stmt.getMoreResults();
			} while (results || count != -1);
			stmt.close();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		return rs;
	}

	/**
	 * Does table exist.
	 *
	 * @param tableName
	 *            the table name
	 * @return true, if successful
	 */
	public boolean doesTableExist(String tableName) {
		String sql = "SELECT COUNT(*) AS count FROM information_schema.tables "
				+ "WHERE table_name = '" + tableName + "'";
		try {
			ResultSet rs = getData(sql);
			if (rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		return false;
	}

	/**
	 * Commit.
	 */
	public void commit() {
		try {
			Logs.LOGGER.info("Commiting Database Transaction.");
			this.con.commit();
		} catch (SQLException e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * Rollback.
	 */
	public void rollback() {
		try {
			Logs.LOGGER.info("Rolling back Database Transaction.");
			this.con.rollback();
		} catch (SQLException e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		try {
			Logs.LOGGER
					.info("JVM Called for closing the unwanted DB Connection.");
			this.con.close();
		} catch (SQLException e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}
}
