<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="header.jsp" />
<jsp:include page="adminHeader.jsp" />
<html>
<head>
    <title>ユーザー登録</title>
</head>
<body>
    <h1>ユーザー登録</h1>
    <form action="RegisterServlet" method="post">
        <label for="username">ユーザー名: </label><br>
        <input type="text" id="username" name="username" required><br><br>

        <label for="email">メールアドレス: </label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">パスワード: </label><br>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">登録</button>
    </form>
</body>
</html>
