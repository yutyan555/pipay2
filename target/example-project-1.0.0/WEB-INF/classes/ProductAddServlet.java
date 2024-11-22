package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;

import bean.Product;
import dao.DatabaseConnection;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/ProductAddServlet")
@MultipartConfig
public class ProductAddServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        // データベース接続の初期化
        try {
            Connection connection = DatabaseConnection.getConnection();
            productDAO = new ProductDAO(connection);  // connectionをProductDAOに渡す
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// セッションからユーザー情報を取得
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");  // リダイレクトしたら処理を終了
            return;  // 必ずリターンして以降の処理を行わない
        }
        response.sendRedirect("addProduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームデータを取得
        String name = request.getParameter("name").trim();
        String description = request.getParameter("description").trim();
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        
        // 画像ファイルを取得
        Part photo1Part = request.getPart("photo1");
        Part photo2Part = request.getPart("photo2");
        Part photo3Part = request.getPart("photo3");

        // 画像を保存するディレクトリのパス
        String uploadDir = getServletContext().getRealPath("/uploads");

        // 画像ファイル名を生成
        String photo1FileName = saveFile(photo1Part, uploadDir);
        String photo2FileName = saveFile(photo2Part, uploadDir);
        String photo3FileName = saveFile(photo3Part, uploadDir);

        // 商品オブジェクトの作成
        Product product = new Product(stock, name, description, price, categoryId, stock, null, photo1FileName, photo2FileName, photo3FileName);

        // データベースに商品を追加
        ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO(DatabaseConnection.getConnection());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        try {
			productDAO.addProductWithImages(product);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

        // リダイレクトして一覧ページに遷移
        response.sendRedirect("ProductServlet");
    }

    // ファイルを保存するメソッド
    private String saveFile(Part part, String uploadDir) throws IOException {
        if (part != null && part.getSize() > 0) {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // ファイル名
            Path filePath = Paths.get(uploadDir, fileName);

            // フォルダが存在しない場合は作成
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // ファイルを保存
            try (InputStream inputStream = part.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return fileName; // 保存したファイルの名前を返す
        }
        return null; // 画像がない場合はnullを返す
    }
}
