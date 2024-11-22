package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bean.PiPaymentStore;
import dao.PiPaymentStoreDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StoreListServlet")
public class StoreListServlet extends HttpServlet {

    private PiPaymentStoreDAO storeDAO;

    @Override
    public void init() throws ServletException {
        try {
            // データベース接続を初期化
            Connection connection = dao.DatabaseConnection.getConnection();
            storeDAO = new PiPaymentStoreDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // すべての店舗情報を取得
            List<PiPaymentStore> stores = storeDAO.getAllStores();

            // 店舗リストをstoreIdで昇順にソート
            Collections.sort(stores, new Comparator<PiPaymentStore>() {
                @Override
                public int compare(PiPaymentStore store1, PiPaymentStore store2) {
                    return Integer.compare(store1.getStoreId(), store2.getStoreId());
                }
            });

            // ソートした店舗リストをリクエストに設定
            request.setAttribute("stores", stores);

            // JSPにフォワード
            request.getRequestDispatcher("storeList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "店舗情報の取得に失敗しました。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
