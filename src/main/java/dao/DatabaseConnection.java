package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            // PostgreSQL JDBCドライバを手動でロード
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://cbdhrtd93854d5.cluster-czrs8kj4isg7.us-east-1.rds.amazonaws.com:5432/d47v4oav5comdj";  // Herokuの接続先データベースURL
            String user = "u556t9ld4hrciu";  // Herokuのユーザー名
            String password = "p4a60f14104aa5497e4c4459262e86d563aa645e1b7bc95b1af940babdfce4ca0";  // Herokuのパスワード


            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
    }
}
