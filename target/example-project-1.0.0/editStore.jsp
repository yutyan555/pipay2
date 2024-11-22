<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bean.PiPaymentStore" %>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>店舗編集</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>店舗情報を編集</h1>

        <c:set var="storeId" value="${param.storeId}" />
        <c:if test="${not empty storeId}">
            <c:forEach var="store" items="${stores}">
                <c:if test="${store.storeId == storeId}">
                    <form action="UpdateStoreServlet" method="post">
                        <input type="hidden" name="storeId" value="${store.storeId}">
                        <div class="form-group">
                            <label for="storeName">ショップ名</label>
                            <input type="text" class="form-control" name="storeName" id="storeName" value="${store.storeName}" required>
                        </div>
                        <div class="form-group">
                            <label for="location">所在地</label>
                            <input type="text" class="form-control" name="location" id="location" value="${store.location}" required>
                        </div>
                        <div class="form-group">
                            <label for="paymentRate">決済率</label>
                            <input type="text" class="form-control" name="paymentRate" id="paymentRate" value="${store.paymentRate}" required>
                        </div>
                        <button type="submit" class="btn btn-success">更新</button>
                    </form>
                </c:if>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>
