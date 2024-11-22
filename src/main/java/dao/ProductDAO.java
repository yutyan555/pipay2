package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO {
    private Connection connection;

    // コンストラクタ
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

 // 商品を追加するメソッド（最大5枚の画像を含む）
    public void addProductWithImages(Product product, byte[] photo1Data, byte[] photo2Data, byte[] photo3Data) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, category_id, stock, photo1, photo2, photo3) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, product.getStock());

            // 画像データをセット
            stmt.setBytes(6, photo1Data);  // photo1
            stmt.setBytes(7, photo2Data);  // photo2
            stmt.setBytes(8, photo3Data);  // photo3

            stmt.executeUpdate();
        }
    }

 // 全ての商品を取得する
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("category_id"),
                        rs.getInt("stock"),
                        rs.getTimestamp("created_at"),
                        rs.getString("photo1"),  // photo1
                        rs.getString("photo2"),  // photo2画像データ
                        rs.getString("photo3")   // photo3画像データ
                ));
            }
        }
        return products;
    }

 // 商品をIDで取得する
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("category_id"),
                            rs.getInt("stock"),
                            rs.getTimestamp("created_at"),
                            rs.getString("photo1"),  // photo1画像データ
                            rs.getString("photo2"),  // photo2画像データ
                            rs.getString("photo3")   // photo3画像データ
                    );
                }
            }
        }
        return null;
    }
    
 // 商品の画像パスを取得するメソッド
    public void addProductWithImages(Product product) throws SQLException {
        String query = "INSERT INTO products (name, description, price, category_id, stock, photo1, photo2, photo3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, product.getStock());
            
            // 画像のファイル名を設定
            stmt.setString(6, product.getPhoto1());
            stmt.setString(7, product.getPhoto2());
            stmt.setString(8, product.getPhoto3());
            
            stmt.executeUpdate();
        }
    }
 // 商品削除メソッド
    public void deleteProductById(int id) throws SQLException {
    	// まず関連する取引データを削除
        String deleteTransactionsSQL = "DELETE FROM transactions WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteTransactionsSQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    	
    	// まず関連する購入データを削除
        String deletePurchasesSQL = "DELETE FROM purchases WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deletePurchasesSQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
 // 商品を編集するメソッド
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products " +
                     "SET name = ?, description = ?, price = ?, category_id = ?, stock = ?, photo1 = ?, photo2 = ?, photo3 = ? " +
                     "WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, product.getStock());
            
            // 更新する画像のファイル名をセット
            stmt.setString(6, product.getPhoto1());
            stmt.setString(7, product.getPhoto2());
            stmt.setString(8, product.getPhoto3());
            
            // 更新対象の商品IDをセット
            stmt.setInt(9, product.getId());
            
            stmt.executeUpdate();
        }
    }
 // 商品の在庫数を更新
    public void updateProductStock(int productId, int newStock) throws SQLException {
        String sql = "UPDATE products SET stock = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }
}
