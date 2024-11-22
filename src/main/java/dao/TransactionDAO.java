package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Transaction;

public class TransactionDAO {
    private Connection connection;

    // コンストラクタ
    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    // 取引を追加する
    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transactions (user_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getUserId());
            stmt.setInt(2, transaction.getProductId());
            stmt.setInt(3, transaction.getQuantity());
            stmt.setDouble(4, transaction.getTotalPrice());
            stmt.executeUpdate();
        }
    }

    // 全ての取引を取得する
    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("created_at")
                ));
            }
        }
        return transactions;
    }
}
