<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>購入フォーム</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container mt-5">
        <h1>購入フォーム</h1>
        <form action="CheckoutServlet" method="post">
            <!-- 商品情報（編集不可） -->
            <div class="form-group">
                <label for="productId">商品ID</label>
                <input type="text" class="form-control" id="productId" name="productId" value="${productId}" readonly>
            </div>
            <div class="form-group">
                <label for="productName">商品名</label>
                <input type="text" class="form-control" id="productName" name="productName" value="${productName}" readonly>
            </div>
            <div class="form-group">
                <label for="price">価格</label>
                <input type="text" class="form-control" id="price" name="price" value="${price}" readonly>
            </div>
            <div class="form-group">
                <label for="quantity">購入数</label>
                <input type="number" class="form-control" id="quantity" name="quantity" value="${quantity}" readonly>
            </div>

            <!-- 購入者情報 -->
            <div class="form-group">
                <label for="customerName">氏名</label>
                <input type="text" class="form-control" id="customerName" name="customerName" required>
            </div>
            <div class="form-group">
                <label for="postalCode">郵便番号</label>
                <input type="text" class="form-control" id="postalCode" name="postalCode" required>
            </div>
            <div class="form-group">
                <label for="address">住所</label>
                <input type="text" class="form-control" id="address" name="address" required>
            </div>
            <div class="form-group">
                <label for="email">メールアドレス</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <button type="submit" class="btn btn-primary">購入する</button>
        </form>
    </div>

    <!-- Bootstrap JSとjQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>
</html>
