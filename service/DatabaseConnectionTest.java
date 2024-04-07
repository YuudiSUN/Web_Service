package travel.management.web.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // 获取数据库连接
            conn = PostgresqlJDBC.getConnection();
            if (conn != null) {
                // 获取并输出数据库元数据
                DatabaseMetaData metaData = conn.getMetaData();
                System.out.println("数据库已连接。");
                System.out.println("数据库产品名称: " + metaData.getDatabaseProductName());
                System.out.println("数据库产品版本: " + metaData.getDatabaseProductVersion());
                
                // 测试一个简单的查询
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT 1;");
                
                if (rs.next()) {
                    System.out.println("查询成功，返回值：" + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        } finally {
            // 释放资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                PostgresqlJDBC.releaseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
