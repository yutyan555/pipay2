package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import bean.Product;
import dao.DatabaseConnection;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        // データベース接続の初期化
        try {
            Connection connection = DatabaseConnection.getConnection();
            productDAO = new ProductDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Product product = null;
        try {
            // DAOを使用して商品詳細を取得
            product = productDAO.getProductById(Integer.parseInt(id));
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving product details", e);
        }

        // 商品情報をリクエストに設定し、JSPにフォワード
        request.setAttribute("product", product);
        request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
    }
}
