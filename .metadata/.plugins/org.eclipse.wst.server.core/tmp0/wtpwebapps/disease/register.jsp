<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<body>
	用户注册
	<form action="user/register" method="post">
		用户名：<input type="text" name="username">
		密码：<input type="password" name="password">
		无效次数：<input type="text" name="invalidNum" value="0" readonly="true">
		<input type="submit" value="注册">
	</form>
	
	医生注册
	<form action="doctor/register" method="post">
		用户名：<input type="text" name="username">
		密码：<input type="password" name="password">
		性别：<input type="text" name="gender">
		医院：<input type="text" name="hospital">
		<input type="submit" value="注册">
	</form>
</body>
</html>