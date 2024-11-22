package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.PiPaymentStore;

public class PiPaymentStoreDAO {
    private Connection connection;

    public PiPaymentStoreDAO(Connection connection) {
        this.connection = connection;
    }

    // 店舗を追加するメソッド
    public void addStore(PiPaymentStore store) throws SQLException {
        String sql = "INSERT INTO pi_payment_stores (store_name, location, details, store_features, business_hours, contact_info, " +
                "payment_rate, payment_methods, google_map_url, image1, image2, image3) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, store.getStoreName());
            pstmt.setString(2, store.getLocation());
            pstmt.setString(3, store.getDetails());
            pstmt.setString(4, store.getStoreFeatures());
            pstmt.setString(5, store.getBusinessHours());
            pstmt.setString(6, store.getContactInfo());
            pstmt.setDouble(7, store.getPaymentRate());
            pstmt.setString(8, store.getPaymentMethods());
            pstmt.setString(9, store.getGoogleMapUrl());
            pstmt.setString(10, store.getImage1());
            pstmt.setString(11, store.getImage2());
            pstmt.setString(12, store.getImage3());
            
            pstmt.executeUpdate();
        }
    }

    // 店舗IDを指定して店舗情報を取得するメソッド
    public PiPaymentStore getStoreById(int storeId) throws SQLException {
        String sql = "SELECT * FROM pi_payment_stores WHERE store_id = ?";
        PiPaymentStore store = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                store = new PiPaymentStore();
                store.setStoreId(rs.getInt("store_id"));
                store.setStoreName(rs.getString("store_name"));
                store.setLocation(rs.getString("location"));
                store.setDetails(rs.getString("details"));
                store.setStoreFeatures(rs.getString("store_features"));
                store.setBusinessHours(rs.getString("business_hours"));
                store.setContactInfo(rs.getString("contact_info"));
                store.setPaymentRate(rs.getDouble("payment_rate"));
                store.setPaymentMethods(rs.getString("payment_methods"));
                store.setGoogleMapUrl(rs.getString("google_map_url"));
                store.setImage1(rs.getString("image1"));
                store.setImage2(rs.getString("image2"));
                store.setImage3(rs.getString("image3"));
                store.setCreatedAt(rs.getTimestamp("created_at"));
            }
        }
        return store;
    }

    // 全ての店舗を取得するメソッド
    public List<PiPaymentStore> getAllStores() throws SQLException {
        List<PiPaymentStore> stores = new ArrayList<>();
        String sql = "SELECT * FROM pi_payment_stores";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                PiPaymentStore store = new PiPaymentStore();
                store.setStoreId(rs.getInt("store_id"));
                store.setStoreName(rs.getString("store_name"));
                store.setLocation(rs.getString("location"));
                store.setDetails(rs.getString("details"));
                store.setStoreFeatures(rs.getString("store_features"));
                store.setBusinessHours(rs.getString("business_hours"));
                store.setContactInfo(rs.getString("contact_info"));
                store.setPaymentRate(rs.getDouble("payment_rate"));
                store.setPaymentMethods(rs.getString("payment_methods"));
                store.setGoogleMapUrl(rs.getString("google_map_url"));
                store.setImage1(rs.getString("image1"));
                store.setImage2(rs.getString("image2"));
                store.setImage3(rs.getString("image3"));
                store.setCreatedAt(rs.getTimestamp("created_at"));
                stores.add(store);
            }
        }
        return stores;
    }

    // 店舗情報を更新するメソッド
    public void updateStore(PiPaymentStore store) throws SQLException {
        String sql = "UPDATE pi_payment_stores SET store_name = ?, location = ?, details = ?, store_features = ?, business_hours = ?, " +
                "contact_info = ?, payment_rate = ?, payment_methods = ?, google_map_url = ?, image1 = ?, image2 = ?, image3 = ? " +
                "WHERE store_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, store.getStoreName());
            pstmt.setString(2, store.getLocation());
            pstmt.setString(3, store.getDetails());
            pstmt.setString(4, store.getStoreFeatures());
            pstmt.setString(5, store.getBusinessHours());
            pstmt.setString(6, store.getContactInfo());
            pstmt.setDouble(7, store.getPaymentRate());
            pstmt.setString(8, store.getPaymentMethods());
            pstmt.setString(9, store.getGoogleMapUrl());
            pstmt.setString(10, store.getImage1());
            pstmt.setString(11, store.getImage2());
            pstmt.setString(12, store.getImage3());
            pstmt.setInt(13, store.getStoreId());
            
            pstmt.executeUpdate();
        }
    }

    // 店舗情報を削除するメソッド
    public void deleteStore(int storeId) throws SQLException {
        String sql = "DELETE FROM pi_payment_stores WHERE store_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            pstmt.executeUpdate();
        }
    }
}
