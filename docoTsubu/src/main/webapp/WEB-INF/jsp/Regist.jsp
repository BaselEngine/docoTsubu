<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶ登録フォーム</h1>
<form action="/docoTsubu/Regist" method="post">
ユーザー名:<input type="text" name="name"><br>
パスワード:<input type="password" name="pass"><br>
パスワード確認:<input type="password" name="checkPass"><br>
<input type="submit" value="登録">
<br><br>
<a href="/docoTsubu/">TOPへ</a>
</form>
</body>
</html>