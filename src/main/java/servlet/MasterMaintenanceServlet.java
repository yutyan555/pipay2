package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MasterMaintenanceServlet")
public class MasterMaintenanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションからユーザー情報を取得
        HttpSession session = request.getSession(false);
        
        // セッションが無い、またはユーザー情報が無い場合はログインページにリダイレクト
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");  // ログインページにリダイレクト
        } else {
            // ログイン済みの場合、masterMaintenance.jspを表示
            request.getRequestDispatcher("masterMaintenance.jsp").forward(request, response);
        }
    }
}
