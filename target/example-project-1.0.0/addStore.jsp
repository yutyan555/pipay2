<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>店舗追加</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>店舗情報を追加</h1>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                ${errorMessage}
            </div>
        </c:if>

        <form action="AddStoreServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="storeName">ショップ名</label>
                <input type="text" class="form-control" name="storeName" id="storeName" required>
            </div>
            <div class="form-group">
                <label for="location">所在地</label>
                <input type="text" class="form-control" name="location" id="location" required>
            </div>
            <div class="form-group">
                <label for="details">詳細情報</label>
                <textarea class="form-control" name="details" id="details"></textarea>
            </div>
            <div class="form-group">
                <label for="storeFeatures">店舗の特徴</label>
                <textarea class="form-control" name="storeFeatures" id="storeFeatures"></textarea>
            </div>
            <div class="form-group">
                <label for="businessHours">営業時間</label>
                <input type="text" class="form-control" name="businessHours" id="businessHours">
            </div>
            <div class="form-group">
                <label for="contactInfo">連絡先</label>
                <input type="text" class="form-control" name="contactInfo" id="contactInfo">
            </div>
            <div class="form-group">
                <label for="paymentRate">決済率</label>
                <input type="number" step="0.01" class="form-control" name="paymentRate" id="paymentRate" required>
            </div>
            <div class="form-group">
                <label for="paymentMethods">お支払方法</label>
                <input type="text" class="form-control" name="paymentMethods" id="paymentMethods" required>
            </div>
            <div class="form-group">
                <label for="googleMapUrl">グーグルマップのURL</label>
                <input type="text" class="form-control" name="googleMapUrl" id="googleMapUrl">
            </div>
            <div class="form-group">
                <label for="image1">画像1</label>
                <input type="file" class="form-control" name="image1" id="image1">
            </div>
            <div class="form-group">
                <label for="image2">画像2</label>
                <input type="file" class="form-control" name="image2" id="image2">
            </div>
            <div class="form-group">
                <label for="image3">画像3</label>
                <input type="file" class="form-control" name="image3" id="image3">
            </div>
            <button type="submit" class="btn btn-primary">店舗を追加</button>
        </form>
    </div>
</body>
</html>
