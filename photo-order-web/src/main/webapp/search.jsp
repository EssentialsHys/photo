<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	根据名字查找摄影师  
	<form action="/query/photographer/name" method="get">
		用户名：<input type="text" name="pname">
		<input type="submit" value="查找">
	</form>
	<br/>
	
	根据学校查找摄影师 
	<form action="/query/photographer/school" method="get">
		学校：<input type="text" name="school">
		<input type="submit" value="查找">
	</form>
	<br/>
	
	根据价格区间查找摄影师 
	<form action="/query/photographer/price" method="get">
		最小：<input type="text" name="low">
		最大：<input type="text" name="high">
		<input type="submit" value="查找">
	</form>
	<br/>
	
	根据风格查找摄影师
	<form action="/query/photographer/style" method="get">
		写真    <input type="radio" name="style" value="写真">　　
　　　         毕业照<input type="radio" name="style" value="毕业照">
		私房	 <input type="radio" name="style" value="私房">　　
　　　         情侣    <input type="radio" name="style" value="情侣">
　　　         肖像    <input type="radio" name="style" value="肖像">
		<input type="submit" value="查找">
	</form>
	<br/>
</body>
</html>