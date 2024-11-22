<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>店舗一覧</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .hover-shadow:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }
        .card-img-top {
            height: 300px;
            object-fit: cover;
        }

        /* 画像をフッターの上に配置 */
        .center-bottom-img {
            display: block;
            margin: 40px auto 20px; /* 上部に40px、下部に20pxの余白 */
            width: 100%; /* 幅を100%にし、カードサイズに合わせる */
            max-width: 400px; /* 最大幅を400pxに制限 */
            height: auto; /* 高さは自動で調整 */
            transition: transform 0.3s ease, box-shadow 0.3s ease; /* スムーズな遷移 */
        }

        /* 画像がマウスオーバー時に膨らむ */
        .center-bottom-img:hover {
            transform: scale(1.1); /* 1.1倍に拡大 */
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3); /* 影を付ける */
        }
    </style>
    <style>
        .hover-shadow:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }
        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">店舗一覧</h1>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                ${errorMessage}
            </div>
        </c:if>

        <c:if test="${not empty stores}">
            <div class="row g-4">
                <c:forEach var="store" items="${stores}">
                    <div class="col-md-4">
                        <a href="StoreDetailServlet?storeId=${store.storeId}" class="text-decoration-none text-dark">
                            <div class="card h-100 shadow-sm border-0 hover-shadow">
                                <!-- 画像を表示 -->
                                <c:if test="${not empty store.image1}">
                                    <img src="${pageContext.request.contextPath}/uploads/${store.image1}" 
                                         class="card-img-top img-fluid" 
                                         alt="${store.storeName}">
                                </c:if>
                                <div class="card-body">
                                    <h5 class="card-title">${store.storeName}</h5>
                                    <p class="card-text">所在地: ${store.location}</p>
                                    <p class="card-text">決済率: ${store.paymentRate}%</p>
                                    <p class="card-text">営業時間: ${store.businessHours}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty stores}">
            <p class="text-center">現在、登録された店舗はありません。</p>
        </c:if>
    </div>
    
    <!-- 画像をフッターの上に表示 -->
    <img src="<%= request.getContextPath() %>/img/yutyan.png" class="center-bottom-img" alt="yutyan">

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<jsp:include page="footer.jsp" />
