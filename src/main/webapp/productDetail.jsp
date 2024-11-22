<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品詳細</title>
    <link rel="stylesheet" href="styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJvY4v6b2BO5ZVNYf7/q3OXonwEYbbuJKR5w+h8wGpK0qGmXq0cA1XKHZiVX" crossorigin="anonymous">
    <style>
        /* フォントサイズを大きくする */
        body {
            font-size: 1.2rem;
        }

        h1, h4 {
            font-size: 2rem;
        }

        .card-body {
            font-size: 1.25rem;
        }

        .product-info p {
            font-size: 1.15rem;
        }

        /* 画像サイズを大きくする */
        .img-fluid {
            max-width: 100%;
            height: auto;
        }

        /* ボタンのサイズを大きくする */
        .btn {
            font-size: 1.25rem;
            padding: 12px;
        }

        /* コンテナの幅を広げる */
        .container {
            max-width: 1200px;
        }

        /* 商品詳細のレイアウトを広くする */
        .card {
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">商品詳細</h1>

        <c:if test="${not empty product}">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card shadow-lg">
                        <div class="card-body">
                            <!-- 商品名 -->
                            <h4 class="card-title text-center">${product.name}</h4>

                            <!-- 商品情報 -->
                            <div class="product-info mb-4">
                                <p><strong>ID:</strong> ${product.id}</p>
                                <p><strong>価格:</strong> π${product.price}</p>
                                <p><strong>カテゴリID:</strong> ${product.categoryId}</p>
                                <p><strong>在庫:</strong> ${product.stock}</p>
                                <p><strong>説明:</strong> ${product.description}</p>
                            </div>

                            <!-- 商品画像 -->
                            <div class="row mb-3">
                                <c:if test="${not empty product.photo1}">
                                    <div class="col-4">
                                        <img src="${pageContext.request.contextPath}/uploads/${product.photo1}" alt="Image 1" class="img-fluid rounded">
                                    </div>
                                </c:if>
                                <c:if test="${not empty product.photo2}">
                                    <div class="col-4">
                                        <img src="${pageContext.request.contextPath}/uploads/${product.photo2}" alt="Image 2" class="img-fluid rounded">
                                    </div>
                                </c:if>
                                <c:if test="${not empty product.photo3}">
                                    <div class="col-4">
                                        <img src="${pageContext.request.contextPath}/uploads/${product.photo3}" alt="Image 3" class="img-fluid rounded">
                                    </div>
                                </c:if>
                            </div>

                            <!-- 購入フォーム -->
                            <form action="PurchaseServlet" method="post">
                                <input type="hidden" name="productId" value="${product.id}">
                                <input type="hidden" name="productName" value="${product.name}">
                                <input type="hidden" name="price" value="${product.price}">
                                <div class="form-group mb-3">
                                    <label for="quantity">数量</label>
                                    <input type="number" class="form-control" name="quantity" id="quantity" min="1" max="${product.stock}" value="1" required>
                                </div>
                                <button type="submit" class="btn btn-success btn-block w-100">購入する</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        
        <c:if test="${empty product}">
            <p class="text-center">商品情報が見つかりませんでした。</p>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gyb2lA1td1Z3dhzqnnfEXfJxjjsf1p1iX1u+/b5V60y6vVfl5p1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyu2Q7c9p2M3u4dg1T/u4fp9JZcnqEwe2xjpbJXK6GmQxzR9poF1Je7" crossorigin="anonymous"></script>
</body>
</html>

<jsp:include page="footer.jsp" />
