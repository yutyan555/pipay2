package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションを取得
        HttpSession session = request.getSession(false); // セッションがあれば取得、なければnull

        if (session != null) {
            // セッション無効化（ログアウト）
            session.invalidate(); // セッションを無効化
        }

        // ログインページにリダイレクト
        response.sendRedirect("login.jsp"); // ログインページにリダイレクト
    }
}
