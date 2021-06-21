<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% 
User loginUser = (User) session.getAttribute("loginUser");
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこ粒</title>
</head>
<body>
<h1>どこつぶろぐいん</h1>
<% if(loginUser != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getName() %>さん</p>
	<a href="/docoTsubu/Main">つぶやき投稿・閲覧へ</a>
<% } else { %>
	<p>ログイン失敗</p>
	<a href="/docoTsubu/">TOPへ</a>
<% } %>
</body>
</html>