<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	添加订单 
	<form action="/order/add" method="post">
		地点：<input type="text" name="location">
		数量：<input type="text" name="num">
		总价：<input type="text" name="totalPrice">
		摄影师名字：<input type="text" name="pname">
		商品1：<input type="text" name="names[0]">
		商品2：<input type="text" name="names[1]">
		<input type="submit" value="添加">
	</form>
	<br/>
	
	完成订单
	<form action="/order/update" method="post">
		oid：<input type="text" name="oid">
		<input type="submit" value="完成">
	</form>
	<br/>
	查询订单
	<form action="/order/query" method="get">
		摄影师名字：<input type="text" name="pname">
		<input type="submit" value="查找">
	</form>
	<br/>
</body>
</html>