package bean;

import java.sql.Timestamp;

public class Purchase {
    private int id;                // 購入ID
    private int productId;         // 商品ID
    private String customerName;   // 購入者氏名
    private String postalCode;     // 郵便番号
    private String address;        // 住所
    private String email;          // メールアドレス
    private int quantity;          // 購入口数
    private Timestamp purchaseDate; // 購入日
    private String productName;  // 商品名を追加


 // コンストラクタを修正
    public Purchase(int id, int productId, String customerName, String postalCode,
                    String address, String email, int quantity, Timestamp purchaseDate, String productName) {
        this.id = id;
        this.productId = productId;
        this.customerName = customerName;
        this.postalCode = postalCode;
        this.address = address;
        this.email = email;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.productName = productName;  // 商品名を設定
    }

    // ゲッター・セッターを追加
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


	public Purchase() {
		// TODO 自動生成されたコンストラクター・スタブ
	}


	// ゲッターとセッター
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
