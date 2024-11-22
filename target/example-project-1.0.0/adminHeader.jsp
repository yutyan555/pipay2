<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>メンテナンス用画面</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* カスタムスタイル */
        .header {
            background-color: #28a745; /* 緑色 */
            padding: 10px 0;
        }
        .header a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }
        .navbar-nav a:hover {
            color: #d4edda; /* ホバー時に明るい緑色 */
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
                    <h1>メンテナンス用</h1> <!-- ヘッダー名を変更 -->
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
                                    <a class="nav-link" href="masterMaintenance.jsp">管理項目</a>
                                </li>
                                <!-- ログアウトボタン -->
                                <li class="nav-item">
                                    <a class="nav-link" href="LogoutServlet">ログアウト</a>
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
