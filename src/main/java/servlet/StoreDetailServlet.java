package servlet;

import java.io.IOException;
import java.sql.SQLException;

import bean.PiPaymentStore;
import dao.PiPaymentStoreDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StoreDetailServlet")
public class StoreDetailServlet extends HttpServlet {

    private PiPaymentStoreDAO storeDAO;

    @Override
    public void init() throws ServletException {
        try {
            storeDAO = new PiPaymentStoreDAO(dao.DatabaseConnection.getConnection());
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        
        try {
            // 店舗情報を取得
            PiPaymentStore store = storeDAO.getStoreById(storeId);

            // 店舗情報が見つからない場合のエラーハンドリング
            if (store == null) {
                request.setAttribute("errorMessage", "指定された店舗は存在しません。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // 店舗情報をリクエストにセット
            request.setAttribute("store", store);

            // 店舗詳細ページにフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("storeDetail.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "店舗情報の取得に失敗しました。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
