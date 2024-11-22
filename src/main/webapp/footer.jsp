<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pi決済JAPAN</title>
    <style>
        footer {
            background-color: #343a40; /* フッター背景を黒に */
            padding: 20px;
            text-align: center;
            color: white; /* 文字色を白に */
        }

        .share-icons a {
            margin: 0 10px;
            text-decoration: none; /* 下線を消す */
        }

        .social-icon {
            width: 30px;
            height: 30px;
        }

        footer p {
            margin-top: 10px;
            font-size: 14px;
            color: #ccc; /* 文字色を薄いグレーに */
        }
    </style>
</head>
<body>

    <!-- フッター部分 -->
    <footer>
        <!-- SNSアイコンのHTML -->
        <div class="share-icons">
            <a href="https://twitter.com/intent/tweet?text=Pi決済JAPAN&url=https://pipay.jp" target="_blank">
                <img src="<%= request.getContextPath() %>/img/twitter-icon.png" alt="Twitter" class="social-icon">
            </a>
            <a href="https://www.facebook.com/sharer/sharer.php?u=https://pipay.jp" target="_blank">
                <img src="<%= request.getContextPath() %>/img/facebook-icon.png" alt="Facebook" class="social-icon">
            </a>
            <a href="https://line.me/R/msg/text/?Pi決済JAPAN%20https://pipay.jp" target="_blank">
                <img src="<%= request.getContextPath() %>/img/line-icon.png" alt="LINE" class="social-icon">
            </a>
        </div>

        <!-- Webカウンター -->

        <p>&copy; 2024 Pi決済JAPAN</p>
    </footer>

</body>
</html>
