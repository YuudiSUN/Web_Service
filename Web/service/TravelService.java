package travel.management.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class TravelService {

}

/**
 * @class PostgresqlJDBC
 * @brief This class allows developers or users to get and release the resource more easily.
 * 
 * This class contains methods to perform 2 operations : getting DB connection and releasing connection.
 */
class PostgresqlJDBC{
	 
	 static Connection con = null;
	 
	 public static Connection getConnection() {
	  
	        try {
	            // 加载PostgreSQL JDBC驱动
	            Class.forName("org.postgresql.Driver");
	            // 建立连接
	            con = DriverManager.getConnection(
	                    "jdbc:postgresql://postgresql-sun.alwaysdata.net:5432/sun_testbd", "sun_user1", "sunyudi");
	            System.out.println("连接已建立");
	            return con;
	     } catch (Exception e) {
	          e.printStackTrace();
	          System.err.println(e.getClass().getName()+": "+e.getMessage());
	          return null;
	     }
	 }
	 
	 public static void releaseConnection() {
	  try {
	   con.close();
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  con = null;
	 }
	 
	}