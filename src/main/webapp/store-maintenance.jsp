<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>店舗マスタメンテナンス</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="container">
		<h1>店舗マスタメンテナンス</h1>

		<c:if test="${not empty stores}">
			<table class="table">
				<thead>
					<tr>
						<th>ショップ名</th>
						<th>所在地</th>
						<th>決済率</th>
						<th>営業時間</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="store" items="${stores}">
						<tr>
							<td>${store.storeName}</td>
							<td>${store.location}</td>
							<td>${store.paymentRate}%</td>
							<td>${store.businessHours}</td>
							<td>
								<!-- 編集ボタン --> <a
								href="StoreEditServlet?storeId=${store.storeId}"
								class="btn btn-warning">編集</a> <!-- 削除ボタン -->
								<form action="DeleteStoreServlet" method="post"
									style="display: inline;">
									<input type="hidden" name="storeId" value="${store.storeId}">
									<button type="submit" class="btn btn-danger"
										onclick="return confirm('本当に削除しますか?');">削除</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<c:if test="${empty stores}">
			<p>現在、登録された店舗はありません。</p>
		</c:if>

		<a href="addStore.jsp" class="btn btn-primary">店舗の追加</a>
	</div>
</body>
</html>
