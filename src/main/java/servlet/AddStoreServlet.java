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

import bean.PiPaymentStore;
import dao.PiPaymentStoreDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/AddStoreServlet")
public class AddStoreServlet extends HttpServlet {

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
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");  // リダイレクトしたら処理を終了
            return;  // 必ずリターンして以降の処理を行わない
        }
        // 店舗追加フォームを表示
        RequestDispatcher dispatcher = request.getRequestDispatcher("addStore.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームデータを取得
        String storeName = request.getParameter("storeName");
        String location = request.getParameter("location");
        String details = request.getParameter("details");
        String storeFeatures = request.getParameter("storeFeatures");
        String businessHours = request.getParameter("businessHours");
        String contactInfo = request.getParameter("contactInfo");
        double paymentRate = Double.parseDouble(request.getParameter("paymentRate"));
        String paymentMethods = request.getParameter("paymentMethods");
        String googleMapUrl = request.getParameter("googleMapUrl");
        
     // 画像を保存するディレクトリのパス
        String uploadDir = getServletContext().getRealPath("/uploads");
        
        // 画像ファイルを取得
        Part photo1Part = request.getPart("image1");
        Part photo2Part = request.getPart("image2");
        Part photo3Part = request.getPart("image3");

        // 画像ファイルの処理
        String image1 = saveFile(photo1Part,uploadDir);
        String image2 = saveFile(photo2Part,uploadDir);
        String image3 = saveFile(photo3Part,uploadDir);

        // PiPaymentStoreオブジェクトを作成
        PiPaymentStore store = new PiPaymentStore();
        store.setStoreName(storeName);
        store.setLocation(location);
        store.setDetails(details);
        store.setStoreFeatures(storeFeatures);
        store.setBusinessHours(businessHours);
        store.setContactInfo(contactInfo);
        store.setPaymentRate(paymentRate);
        store.setPaymentMethods(paymentMethods);
        store.setGoogleMapUrl(googleMapUrl);
        store.setImage1(image1);
        store.setImage2(image2);
        store.setImage3(image3);

        try {
            // 店舗をデータベースに追加
            storeDAO.addStore(store);
            response.sendRedirect("StoreMaintenanceServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "店舗の追加に失敗しました。");
            request.getRequestDispatcher("addStore.jsp").forward(request, response);
        }
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
