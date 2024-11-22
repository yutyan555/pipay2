<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="bean.Product" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品一覧</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .hover-shadow:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }
        .card-img-top {
            height: 300px;
            object-fit: cover;
        }

        /* 画像をフッターの上に配置 */
        .center-bottom-img {
            display: block;
            margin: 40px auto; /* 上部に40px、下部に20pxの余白 */
            width: 100%; /* 幅を100%にし、カードサイズに合わせる */
            max-width: 400px; /* 最大幅を400pxに制限 */
            height: auto; /* 高さは自動で調整 */
            transition: transform 0.3s ease, box-shadow 0.3s ease; /* スムーズな遷移 */
        }

        /* 画像がマウスオーバー時に膨らむ */
        .center-bottom-img:hover {
            transform: scale(1.1); /* 1.1倍に拡大 */
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3); /* 影を付ける */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">商品一覧</h1>
        <div class="row g-4">
            <%
                List<Product> products = (List<Product>) request.getAttribute("products");
                
                // 商品IDで昇順にソート
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return Integer.compare(p1.getId(), p2.getId());
                    }
                });

                for (Product product : products) {
            %>
            <div class="col-md-4">
                <a href="ProductDetailServlet?id=<%= product.getId() %>" class="text-decoration-none text-dark">
                    <div class="card h-100 shadow-sm border-0 hover-shadow">
                        <img src="<%= request.getContextPath() + "/uploads/" + product.getPhoto1() %>" 
                             class="card-img-top img-fluid" 
                             alt="<%= product.getName() %>">
                        <div class="card-body">
                            <h5 class="card-title"><%= product.getName() %></h5>
                            <p class="card-text text-danger fw-bold">π<%= product.getPrice() %>Pi</p>
                            <p class="card-text">在庫: <%= product.getStock() %></p>
                        </div>
                    </div>
                </a>
            </div>
            <% } %>
        </div>
    </div>

    <!-- 画像をフッターの上に表示 -->
    <img src="<%= request.getContextPath() %>/img/yutyan.png" class="center-bottom-img" alt="yutyan">

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<jsp:include page="footer.jsp" />
