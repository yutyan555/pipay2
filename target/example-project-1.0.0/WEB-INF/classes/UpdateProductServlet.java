package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

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

@MultipartConfig
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// セッションからユーザー情報を取得
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");  // リダイレクトしたら処理を終了
            return;  // 必ずリターンして以降の処理を行わない
        }
        try (Connection connection = DatabaseConnection.getConnection()) {
            ProductDAO productDAO = new ProductDAO(connection);

            // リクエストパラメータからproductIdを取得
            int productId = Integer.parseInt(request.getParameter("productId"));

            // 商品情報を取得
            Product product = productDAO.getProductById(productId);
            if (product != null) {
                // 商品情報をリクエストにセット
                request.setAttribute("product", product);
                // 商品編集ページにフォワード
                request.getRequestDispatcher("/EditProduct.jsp").forward(request, response);
            } else {
                // 商品が見つからなかった場合のエラーハンドリング
                request.getSession().setAttribute("errorMessage", "指定された商品が見つかりません。");
                response.sendRedirect("ProductMaintenanceServlet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "商品情報の取得中にエラーが発生しました。");
            response.sendRedirect("ProductMaintenanceServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信された商品IDを取得
        String idParam = request.getParameter("id");

        // 商品IDが空でないかチェック
        if (idParam == null || idParam.isEmpty()) {
            request.getSession().setAttribute("errorMessage", "商品IDが無効です。");
            response.sendRedirect("ProductMaintenanceServlet"); // 商品一覧にリダイレクト
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            ProductDAO productDAO = new ProductDAO(connection);

            // 商品IDが有効なら、他のフォームデータを取得
            int id = Integer.parseInt(idParam);  // 商品IDを整数に変換
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int stock = Integer.parseInt(request.getParameter("stock"));

            // 画像ファイルの処理
            String uploadDirectory = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();  // ディレクトリがなければ作成
            }

            String photo1 = null;
            String photo2 = null;
            String photo3 = null;

            // アップロードされたファイルを処理
            Part filePart1 = request.getPart("photo1");
            if (filePart1 != null && filePart1.getSize() > 0) {
                photo1 = saveFile(filePart1, uploadDirectory);
            }

            Part filePart2 = request.getPart("photo2");
            if (filePart2 != null && filePart2.getSize() > 0) {
                photo2 = saveFile(filePart2, uploadDirectory);
            }

            Part filePart3 = request.getPart("photo3");
            if (filePart3 != null && filePart3.getSize() > 0) {
                photo3 = saveFile(filePart3, uploadDirectory);
            }

            // 更新する商品情報を作成
            Product product = new Product(id, name, description, price, categoryId, stock, null, photo1, photo2, photo3);

            // 商品を更新
            productDAO.updateProduct(product);

            // 商品更新が成功した場合、商品一覧にリダイレクト
            request.getSession().setAttribute("successMessage", "商品が正常に更新されました。");
            response.sendRedirect("ProductMaintenanceServlet"); // 商品一覧ページにリダイレクト
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "商品更新中にエラーが発生しました。");
            response.sendRedirect("ProductEditPage.jsp"); // 商品編集ページにリダイレクト
        }
    }

    private String saveFile(Part filePart, String uploadDirectory) throws IOException {
        // 元のファイル名を取得
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // UUIDを使ってユニークなファイル名を生成
        String newFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        
        // 保存先のファイルパスを生成
        String filePath = uploadDirectory + File.separator + newFileName;
        
        // ファイルを保存
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, Paths.get(filePath));
        }
        
        return newFileName; // 新しいファイル名を返す
    }
}

