package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ProductMaintenanceServlet")
public class ProductMaintenanceServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
    	 try {
             Connection connection = dao.DatabaseConnection.getConnection();
             productDAO = new ProductDAO(connection);
         } catch (SQLException e) {
             throw new ServletException("DB connection error", e);
         }
    }

    // 商品一覧を表示する
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// セッションからユーザー情報を取得
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");  // リダイレクトしたら処理を終了
            return;  // 必ずリターンして以降の処理を行わない
        }
        try {
            // 商品一覧を取得
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);

            // 商品一覧を表示するJSPページに転送
            RequestDispatcher dispatcher = request.getRequestDispatcher("/product-maintenance.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースエラーが発生しました。");
        }
    }

    // 商品削除処理
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            // 商品を削除
            productDAO.deleteProductById(productId);

            // 商品一覧ページにリダイレクト
            response.sendRedirect("ProductMaintenanceServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースエラーが発生しました。");
        }
    }
}
