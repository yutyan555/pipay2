<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="adminHeader.jsp" />
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品追加</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* カスタムスタイル */
        .form-container {
            margin-top: 20px;
        }
        .form-container h1 {
            margin-bottom: 20px;
        }
        .form-container .btn {
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <div class="container form-container">
        <h1>商品追加</h1>
        <!-- enctype="multipart/form-data" を追加してファイル送信をサポート -->
        <form action="ProductAddServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">商品名:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            
            <div class="form-group">
                <label for="description">商品説明:</label>
                <input type="text" class="form-control" id="description" name="description" required>
            </div>
            
            <div class="form-group">
                <label for="price">価格:</label>
                <input type="number" class="form-control" id="price" name="price" required>
            </div>
            
            <div class="form-group">
                <label for="category_id">カテゴリID:</label>
                <input type="number" class="form-control" id="category_id" name="category_id" required>
            </div>
            
            <div class="form-group">
                <label for="stock">在庫:</label>
                <input type="number" class="form-control" id="stock" name="stock" required>
            </div>

            <!-- 画像の追加 -->
            <div class="form-group">
                <label for="photo1">画像1:</label>
                <input type="file" class="form-control-file" id="photo1" name="photo1">
            </div>

            <div class="form-group">
                <label for="photo2">画像2:</label>
                <input type="file" class="form-control-file" id="photo2" name="photo2">
            </div>

            <div class="form-group">
                <label for="photo3">画像3:</label>
                <input type="file" class="form-control-file" id="photo3" name="photo3">
            </div>
            
            <button type="submit" class="btn btn-primary">追加</button>
        </form>
        <br>
        <a href="ProductMaintenanceServlet" class="btn btn-secondary">商品一覧へ戻る</a>
    </div>

    <!-- Bootstrap JSとjQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>
</html>
