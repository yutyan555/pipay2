package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.PiPaymentStore;
import dao.PiPaymentStoreDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StoreMaintenanceServlet")
public class StoreMaintenanceServlet extends HttpServlet {
    private PiPaymentStoreDAO storeDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = dao.DatabaseConnection.getConnection();
            storeDAO = new PiPaymentStoreDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// セッションからユーザー情報を取得
        HttpSession session = request.getSession(false);
    	// セッションが無い、またはユーザー情報が無い場合はログインページにリダイレクト
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");  // リダイレクトしたら処理を終了
            return;  // 必ずリターンして以降の処理を行わない
        }
        try {
            List<PiPaymentStore> stores = storeDAO.getAllStores(); // 店舗一覧の取得
            request.setAttribute("stores", stores);
            RequestDispatcher dispatcher = request.getRequestDispatcher("store-maintenance.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // エラーページにリダイレクト
        }
    }
}
