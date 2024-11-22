<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${store.storeName}-店舗詳細</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-img-top {
            height: 300px;
            object-fit: cover;
        }
        #map-container {
            margin: 0 auto; /* 中央揃え */
            max-width: 100%;
            overflow: hidden; /* 不要なスクロールバー防止 */
        }
        #google-map {
            width: 100%;
            height: 500px; /* 高さを調整 */
            border-radius: 10px; /* 見た目を整える */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">${store.storeName}</h1>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card mb-4">
                    <!-- メイン画像 -->
                    <c:if test="${not empty store.image1}">
                        <img src="${pageContext.request.contextPath}/uploads/${store.image1}" class="card-img-top img-fluid" alt="${store.storeName}">
                    </c:if>
                    <div class="card-body">
                        <h5 class="card-title text-center">${store.storeName}</h5>
                        <p class="card-text"><strong>所在地:</strong> ${store.location}</p>
                        <p class="card-text"><strong>決済率:</strong> ${store.paymentRate}%</p>
                        <p class="card-text"><strong>営業時間:</strong> ${store.businessHours}</p>
                        <p class="card-text"><strong>連絡先:</strong> ${store.contactInfo}</p>
                        <p class="card-text"><strong>特徴:</strong> ${store.storeFeatures}</p>
                        <p class="card-text"><strong>詳細情報:</strong> ${store.details}</p>

                        <!-- Google Map 埋め込み -->
                        <c:if test="${not empty store.googleMapUrl}">
                            <div id="map-container" class="text-center">
                                <iframe id="google-map" src="${store.googleMapUrl}" allowfullscreen="" loading="lazy"></iframe>
                            </div>
                        </c:if>

                        <!-- 画像 2 と 3 -->
                        <c:if test="${not empty store.image2}">
                            <img src="${pageContext.request.contextPath}/uploads/${store.image2}" class="card-img-top img-fluid mt-3" alt="${store.storeName}">
                        </c:if>
                        <c:if test="${not empty store.image3}">
                            <img src="${pageContext.request.contextPath}/uploads/${store.image3}" class="card-img-top img-fluid mt-3" alt="${store.storeName}">
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <div class="text-center">
            <a href="StoreListServlet" class="btn btn-primary">店舗一覧に戻る</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const iframe = document.getElementById("google-map");
            const mapContainer = document.getElementById("map-container");

            if (iframe) {
                // src が空または無効な場合、非表示に設定
                const src = iframe.src || "";
                if (src.trim() === "" || src === "about:blank") {
                    mapContainer.style.display = "none";
                }

                // iframe 読み込みエラー時の処理
                iframe.addEventListener("error", function () {
                    mapContainer.style.display = "none";
                });
            }
        });
    </script>
</body>
</html>
<jsp:include page="footer.jsp" />
