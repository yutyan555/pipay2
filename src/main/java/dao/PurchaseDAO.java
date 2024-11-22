package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.Purchase;

public class PurchaseDAO {
    private Connection connection;

    // コンストラクタ
    public PurchaseDAO(Connection connection) {
        this.connection = connection;
    }

    // 購入データを追加する（Purchase Bean を引数に取る）
    public boolean addPurchase(Purchase purchase) throws SQLException {
        String sql = "INSERT INTO purchase (product_id, customer_name, postal_code, address, email, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, purchase.getProductId());        // 商品ID
            stmt.setString(2, purchase.getCustomerName());  // 購入者氏名
            stmt.setString(3, purchase.getPostalCode());    // 郵便番号
            stmt.setString(4, purchase.getAddress());       // 住所
            stmt.setString(5, purchase.getEmail());         // メールアドレス
            stmt.setInt(6, purchase.getQuantity());         // 購入口数

            // 購入データを挿入
            return stmt.executeUpdate() > 0;
        }
    }

 // 商品IDと商品名を一緒に取得するためのインナージョイン
    public List<Purchase> getAllPurchases() throws SQLException {
        String sql = "SELECT p.id, p.product_id, p.customer_name, p.postal_code, p.address, p.email, p.quantity, p.purchase_date, pr.name as product_name "
                   + "FROM purchase p "
                   + "INNER JOIN products pr ON p.product_id = pr.id";  // products テーブルと結合

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<Purchase> purchases = new ArrayList<>();
            while (rs.next()) {
                Purchase purchase = new Purchase(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("customer_name"),
                        rs.getString("postal_code"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("purchase_date"),
                        rs.getString("product_name")  // 商品名を追加
                );
                purchases.add(purchase);
            }
            return purchases;
        }
    }

 // 購入データをIDで取得する（商品名を含めて取得）
    public Purchase getPurchaseById(int purchaseId) throws SQLException {
        String sql = "SELECT p.id, p.product_id, p.customer_name, p.postal_code, p.address, p.email, p.quantity, p.purchase_date, pr.name as product_name "
                   + "FROM purchase p "
                   + "INNER JOIN products pr ON p.product_id = pr.id "  // products テーブルと結合
                   + "WHERE p.id = ?";  // 購入IDを条件に指定

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, purchaseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int productId = rs.getInt("product_id");
                    String customerName = rs.getString("customer_name");
                    String postalCode = rs.getString("postal_code");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    int quantity = rs.getInt("quantity");
                    Timestamp purchaseDate = rs.getTimestamp("purchase_date");
                    String productName = rs.getString("product_name");  // 商品名を取得

                    // 商品名を含めてPurchaseオブジェクトを作成して返す
                    return new Purchase(id, productId, customerName, postalCode, address, email, quantity, purchaseDate, productName);
                }
            }
        }
        return null;  // 該当する購入が見つからなかった場合
    }

}
