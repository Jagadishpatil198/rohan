package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility implements IPathConstants {
       static   Connection  con=null;
	static ResultSet result=null;
static	FileUtility flib=new FileUtility();

	public static void getDbConnection(String url, String username, String password) {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
		}
	}
/**
 * connect to database
 * @throws Throwable
 */
	public static void connectToDB() {
		Driver driverRef;
		try {
			 driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection(flib.getDataFromPropetyFile("DBUrl"), flib.getDataFromPropetyFile("DB_Username"), flib.getDataFromPropetyFile("DB_Password"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getDataFomDB method to retrieve data from database as key value pairs.
	 * @param query
	 * @return
	 */
	public static ResultSet executequery(String query)
	{
		try {
			// executing the query
			result=con.createStatement().executeQuery(query);
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		return result;
	}

	
	public static  int executeNonSelectQuary(String query) {
		int resultSet = 0;
		try {
			Statement stat = con.createStatement();
			resultSet = stat.executeUpdate(query);
		} catch (Exception e) {
		}
		return resultSet;
	}
	public boolean executeSelectQuaryVerifyAndGetData(String query, int columnIndex, String expectedData) {
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			Statement stat = con.createStatement();
			resultSet = stat.executeQuery(query);
			while (resultSet.next()) {
				if (resultSet.getString(columnIndex).equals(expectedData)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				System.out.println(expectedData + "===>data verified in data base table");
				return true;
			} else {
				{
					System.out.println(expectedData + "===>data is not verified in data base table");
					return false;
				}
			}
		} catch (Exception e) {
		}
		return flag;
	}
/**
 * close the DB connection
 * @throws SQLException
 */
	public static void closeDbConnection() throws SQLException {
		
			con.close();
		
	}
}
