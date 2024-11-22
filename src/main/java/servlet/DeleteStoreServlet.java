package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.PiPaymentStoreDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStoreServlet")
public class DeleteStoreServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int storeId = Integer.parseInt(request.getParameter("storeId"));

        try {
            storeDAO.deleteStore(storeId); // 店舗の削除処理
            response.sendRedirect("StoreMaintenanceServlet"); // 店舗一覧ページへリダイレクト
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // エラーページにリダイレクト
        }
    }
}
