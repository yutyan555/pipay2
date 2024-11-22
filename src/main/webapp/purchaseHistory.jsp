<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>購入履歴</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">購入履歴</h1>
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th scope="col">購入ID</th>
                    <th scope="col">商品ID</th>
                    <th scope="col">商品名</th>  <!-- 商品名を表示 -->
                    <th scope="col">購入者名</th>
                    <th scope="col">郵便番号</th>
                    <th scope="col">住所</th>
                    <th scope="col">メールアドレス</th>
                    <th scope="col">購入口数</th>
                    <th scope="col">購入日</th>
                </tr>
            </thead>
            <tbody>
                <!-- 購入履歴をループして表示 -->
                <c:forEach var="purchase" items="${purchases}">
                    <tr>
                        <td>${purchase.id}</td>
                        <td>${purchase.productId}</td>
                        <td>${purchase.productName}</td>  <!-- 商品名を表示 -->
                        <td>${purchase.customerName}</td>
                        <td>${purchase.postalCode}</td>
                        <td>${purchase.address}</td>
                        <td>${purchase.email}</td>
                        <td>${purchase.quantity}</td>
                        <td>${purchase.purchaseDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS と jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
