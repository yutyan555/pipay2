package servlet;

import java.io.IOException;
import java.sql.SQLException;

import bean.Product;
import bean.Purchase;
import dao.DatabaseConnection;
import dao.ProductDAO;
import dao.PurchaseDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ここで必要な情報をリクエストにセット（例: 商品情報）
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        
        // コンソールにメッセージを表示
        System.out.println("ここからCheckoutServlet");
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);

        // データをリクエスト属性として設定
        request.setAttribute("productId", productId);
        request.setAttribute("productName", productName);
        request.setAttribute("price", price);
        request.setAttribute("quantity", quantity);

        // purchaseForm.jsp にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/purchaseForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // パラメータ取得
        int productId = Integer.parseInt(request.getParameter("productId"));
        String customerName = request.getParameter("customerName");
        String postalCode = request.getParameter("postalCode");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Purchaseオブジェクトを作成
        Purchase purchase = new Purchase();
        purchase.setProductId(productId);
        purchase.setCustomerName(customerName);
        purchase.setPostalCode(postalCode);
        purchase.setAddress(address);
        purchase.setEmail(email);
        purchase.setQuantity(quantity);

        try {
            // 商品情報を取得し、在庫数を確認
            ProductDAO productDAO = new ProductDAO(DatabaseConnection.getConnection());
            Product product = productDAO.getProductById(productId);

            // 在庫数が購入数以上であることを確認
            if (product.getStock() >= quantity) {
                // 購入データを挿入
                PurchaseDAO purchaseDAO = new PurchaseDAO(DatabaseConnection.getConnection());
                if (purchaseDAO.addPurchase(purchase)) {
                    // 在庫数を更新
                    int newStock = product.getStock() - quantity;
                    productDAO.updateProductStock(productId, newStock);

                    response.sendRedirect("purchaseSuccess.jsp"); // 成功ページ
                } else {
                    response.sendRedirect("error.jsp"); // エラーページ
                }
            } else {
                // 在庫が足りない場合
                response.sendRedirect("insufficientStock.jsp"); // 在庫不足ページ
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }


}
