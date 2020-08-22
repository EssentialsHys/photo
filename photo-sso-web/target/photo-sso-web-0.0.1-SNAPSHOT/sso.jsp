<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	用户注册
	<form action="/sso/user/register" method="post">
		用户名：<input type="text" name="uname">
		密码：<input type="password" name="upwd">
		学校：<input type="text" name="school">
		<input type="submit" value="注册">
	</form>
	<br/>
		
	用户登录
	<form action="/sso/user/login" method="post">
		用户名：<input type="text" name="uname">
		密码：<input type="password" name="upwd">
		<input type="submit" value="登录">
	</form>
	<br/>
	
	摄影师注册
	<form action="/sso/photographer/register" method="post">
		用户名：<input type="text" name="pname">
		密码：<input type="password" name="ppwd">
		学校：<input type="text" name="school">
		单价：<input type="text" name="price">
		
		<input type="checkbox" name="portray" value="写真"> 写真 &nbsp;  
        <input type="checkbox" name="graduation" value="毕业照"> 毕业照 &nbsp; 
        <input type="checkbox" name="room" value="私房"> 私房 &nbsp;  
        <input type="checkbox" name="couple" value="情侣"> 情侣 &nbsp; 
        <input type="checkbox" name="face" value="肖像"> 肖像 &nbsp;  

		<input type="submit" value="注册">
	</form>
	<br/>
	
	摄影师登录
	<form action="/sso/photographer/login" method="post">
		用户名：<input type="text" name="pname">
		密码：<input type="password" name="ppwd">
		<input type="submit" value="登录">
	</form>
	
	商家注册
	<form action="/sso/merchant/register" method="post">
		用户名：<input type="text" name="mname">
		密码：<input type="password" name="mpwd">
		地址：<input type="text" name="maddress">
		<input type="submit" value="注册">
	</form>
	
	
	商家登录
	<form action="/sso/merchant/login" method="post">
		用户名：<input type="text" name="mname">
		密码：<input type="password" name="mpwd">
		<input type="submit" value="登录">
	</form>
	
	<br/>
</body>
</html>