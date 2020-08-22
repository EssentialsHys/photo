<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	用户登录
	<form action="user/login" method="post">
		用户名：<input type="text" name="username">
		密码：<input type="password" name="password">
		<input type="submit" value="登录">
	</form>
	
	医生登录
	<form action="doctor/login" method="post">
		用户名：<input type="text" name="username">
		密码：<input type="password" name="password">
		<input type="submit" value="登录">
	</form>
</body>
</html>