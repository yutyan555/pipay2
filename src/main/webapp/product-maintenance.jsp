<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商品マスタメンテナンス</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="container">
		<h1>商品マスタメンテナンス</h1>

		<c:if test="${not empty products}">
			<table class="table">
				<thead>
					<tr>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>カテゴリ</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${products}">
						<tr>
							<td>${product.name}</td>
							<td>${product.price}円</td>
							<td>${product.stock}</td>
							<td>${product.categoryId}</td>
							<td>
								<!-- 編集ボタン --> <a
								href="UpdateProductServlet?productId=${product.id}"
								class="btn btn-warning">編集</a> <!-- 削除ボタン -->
								<form action="DeleteProductServlet" method="post"
									style="display: inline;">
									<input type="hidden" name="id" value="${product.id}">
									<button type="submit" class="btn btn-danger"
										onclick="return confirm('本当に削除しますか?');">削除</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<c:if test="${empty products}">
			<p>現在、登録された商品はありません。</p>
		</c:if>

		<a href="ProductAddServlet" class="btn btn-primary">商品を追加</a>
	</div>
</body>
</html>
