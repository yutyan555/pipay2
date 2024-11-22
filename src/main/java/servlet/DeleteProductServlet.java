package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.DatabaseConnection;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ProductDAO productDAO = new ProductDAO(connection);

            // 商品IDを取得して削除
            int id = Integer.parseInt(request.getParameter("id"));
            productDAO.deleteProductById(id);

            // 成功メッセージをセッションに保存してリダイレクト
            request.getSession().setAttribute("successMessage", "商品が正常に削除されました。");
            response.sendRedirect("ProductMaintenanceServlet");

        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "商品削除中にエラーが発生しました。");
            response.sendRedirect("ProductMaintenanceServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "商品IDが無効です。");
            response.sendRedirect("ProductMaintenanceServlet");
        }
    }
}
