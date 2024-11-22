<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pi決済JAPAN</title>
    <!-- Google Fontsから習字風フォントを読み込む -->
    <link href="https://fonts.googleapis.com/css2?family=Sawarabi+Mincho&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* カスタムスタイル */
        .header {
            background-color: #D4AF37;
            padding: 10px 0;
        }
        .header a {
            color: #000;
            text-decoration: none;
            font-weight: bold;
        }
        .navbar-nav a:hover {
            color: #0056b3;
        }
        /* 習字風フォントを適用 */
        .header h1 {
            font-family: 'Sawarabi Mincho', serif;
            font-size: 2rem; /* 少し小さくした */
            font-weight: normal;
            letter-spacing: 0.1rem;
        }
        /* ハンバーガーメニューを右寄せ */
        .navbar-toggler {
            margin-left: auto; /* メニューアイコンを右寄せ */
        }
    </style>
</head>
<body>

    <!-- ヘッダー部分 -->
    <div class="header">
        <div class="container">
            <div class="row align-items-center">
                <!-- 左側のロゴ部分 -->
                <div class="col-md-4">
                    <h1>Pi決済JAPAN</h1>
                </div>
                <!-- メニュー部分 -->
                <div class="col-md-8">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ml-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="StoreListServlet">店舗一覧</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="ProductServlet">商品一覧</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JSとjQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>
</html>
