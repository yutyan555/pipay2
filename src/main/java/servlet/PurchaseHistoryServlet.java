package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Purchase;
import dao.PurchaseDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PurchaseHistoryServlet")
public class PurchaseHistoryServlet extends HttpServlet {
    private PurchaseDAO purchaseDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = dao.DatabaseConnection.getConnection();
            purchaseDAO = new PurchaseDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// セッションからユーザー情報を取得
        HttpSession session = request.getSession(false);
        
    	if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");  // リダイレクトしたら処理を終了
            return;  // 必ずリターンして以降の処理を行わない
        }
        try {
            // 購入履歴を全件取得
            List<Purchase> purchases = purchaseDAO.getAllPurchases();
            
            // 購入履歴をリクエスト属性として設定
            request.setAttribute("purchases", purchases);
            
            // 購入履歴確認ページ（JSP）にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/purchaseHistory.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // エラーページにリダイレクト
        }
    }
}
