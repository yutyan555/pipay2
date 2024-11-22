package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import dao.DatabaseConnection;
import dao.PurchaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    private PurchaseDAO purchaseDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            purchaseDAO = new PurchaseDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームからデータを取得
        String productIdStr = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");

        // 入力データを確認（デバッグ用）
        System.out.println("Product ID: " + productIdStr);
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + priceStr);
        System.out.println("Quantity: " + quantityStr);
        // データの必須チェック
        if (productIdStr == null || productName == null || priceStr == null || quantityStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "必要なパラメータが不足しています");
            return;
        }

     // 必要なデータをリクエスト属性に設定
        request.setAttribute("productId", productIdStr);
        request.setAttribute("productName", productName);
        request.setAttribute("price", priceStr);
        request.setAttribute("quantity", quantityStr);

        // パラメータをURLに追加してリダイレクト
        String redirectUrl = request.getContextPath() + "/CheckoutServlet?productId=" + productIdStr
                + "&productName=" + URLEncoder.encode(productName, "UTF-8")
                + "&price=" + priceStr
                + "&quantity=" + quantityStr;

        response.sendRedirect(redirectUrl);
    }

}
