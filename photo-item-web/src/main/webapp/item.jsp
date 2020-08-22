<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	添加商品
	<form action="/item/add" method="post" enctype="multipart/form-data">
		名字：<input type="text" name="name">
		价格：<input type="text" name="price">
		描述：<input type="text" name="description">
		数量：<input type="text" name="num">
		<div>上传图片：<input type="file" name="uploadFile" multiple="multiple"></div>
		<input type="submit" value="添加">
	</form>
	<br/>
	
	查询所有商品
	<form action="/item/all" method="get">
		<input type="submit" value="完成">
	</form>
	<br/>
	
	
</body>
</html>