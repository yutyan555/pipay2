<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>マスタメンテナンス</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>管理項目</h1>
        <ul>
            <li><a href="StoreMaintenanceServlet">店舗マスタメンテナンス</a></li>
            <li><a href="ProductMaintenanceServlet">商品マスタメンテナンス</a></li>
            <li><a href="PurchaseHistoryServlet">ご購入チケット</a></li>
            <li><a href="#inquiryMaintenance">お問い合わせ</a></li>
        </ul>
    </div>
</body>
</html>
