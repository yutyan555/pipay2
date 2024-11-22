<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>店舗情報編集</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>店舗情報編集</h1>
        <form action="StoreEditServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="storeId" value="${store.storeId}">

            <div class="form-group">
                <label for="storeName">ショップ名</label>
                <input type="text" id="storeName" name="storeName" class="form-control" value="${store.storeName}" required>
            </div>

            <div class="form-group">
                <label for="location">所在地</label>
                <input type="text" id="location" name="location" class="form-control" value="${store.location}" required>
            </div>

            <div class="form-group">
                <label for="details">詳細</label>
                <textarea id="details" name="details" class="form-control" required>${store.details}</textarea>
            </div>

            <div class="form-group">
                <label for="storeFeatures">店舗特徴</label>
                <input type="text" id="storeFeatures" name="storeFeatures" class="form-control" value="${store.storeFeatures}" required>
            </div>

            <div class="form-group">
                <label for="businessHours">営業時間</label>
                <input type="text" id="businessHours" name="businessHours" class="form-control" value="${store.businessHours}" required>
            </div>

            <div class="form-group">
                <label for="contactInfo">連絡先</label>
                <input type="text" id="contactInfo" name="contactInfo" class="form-control" value="${store.contactInfo}" required>
            </div>

            <div class="form-group">
                <label for="paymentRate">決済率</label>
                <input type="number" step="0.01" id="paymentRate" name="paymentRate" class="form-control" value="${store.paymentRate}" required>
            </div>

            <div class="form-group">
                <label for="paymentMethods">決済方法</label>
                <input type="text" id="paymentMethods" name="paymentMethods" class="form-control" value="${store.paymentMethods}" required>
            </div>

            <div class="form-group">
                <label for="googleMapUrl">GoogleマップURL</label>
                <input type="text" id="googleMapUrl" name="googleMapUrl" class="form-control" value="${store.googleMapUrl}" required>
            </div>

            <div class="form-group">
                <label for="image1">画像1</label>
                <input type="file" id="image1" name="image1" class="form-control">
                <c:if test="${not empty store.image1}">
                    <img src="uploads/${store.image1}" alt="image1" width="100">
                </c:if>
            </div>

            <div class="form-group">
                <label for="image2">画像2</label>
                <input type="file" id="image2" name="image2" class="form-control">
                <c:if test="${not empty store.image2}">
                    <img src="uploads/${store.image2}" alt="image2" width="100">
                </c:if>
            </div>

            <div class="form-group">
                <label for="image3">画像3</label>
                <input type="file" id="image3" name="image3" class="form-control">
                <c:if test="${not empty store.image3}">
                    <img src="uploads/${store.image3}" alt="image3" width="100">
                </c:if>
            </div>

            <button type="submit" class="btn btn-primary">更新</button>
        </form>
    </div>
</body>
</html>
