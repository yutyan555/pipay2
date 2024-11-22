<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品編集</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">商品編集</h1>

        <!-- 成功メッセージの表示 -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>

        <!-- 編集フォーム -->
        <form action="UpdateProductServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${product.id}">
            <div class="mb-3">
                <label for="name" class="form-label">商品名</label>
                <input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">商品説明</label>
                <textarea class="form-control" id="description" name="description" rows="3" required>${product.description}</textarea>
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">価格</label>
                <input type="number" class="form-control" id="price" name="price" value="${product.price}" required>
            </div>
            <div class="mb-3">
                <label for="category" class="form-label">カテゴリID</label>
                <input type="number" class="form-control" id="category" name="categoryId" value="${product.categoryId}" required>
            </div>
            <div class="mb-3">
                <label for="stock" class="form-label">在庫</label>
                <input type="number" class="form-control" id="stock" name="stock" value="${product.stock}" required>
            </div>
            <div class="mb-3">
                <label for="photo1" class="form-label">画像1</label>
                <input type="file" class="form-control" id="photo1" name="photo1">
                <img src="${pageContext.request.contextPath}/uploads/${product.photo1}" alt="現在の画像1" class="img-fluid mt-2">
            </div>
            <div class="mb-3">
                <label for="photo2" class="form-label">画像2</label>
                <input type="file" class="form-control" id="photo2" name="photo2">
                <img src="${pageContext.request.contextPath}/uploads/${product.photo2}" alt="現在の画像2" class="img-fluid mt-2">
            </div>
            <div class="mb-3">
                <label for="photo3" class="form-label">画像3</label>
                <input type="file" class="form-control" id="photo3" name="photo3">
                <img src="${pageContext.request.contextPath}/uploads/${product.photo3}" alt="現在の画像3" class="img-fluid mt-2">
            </div>
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">更新</button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
