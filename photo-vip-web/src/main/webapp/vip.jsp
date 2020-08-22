<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	换衣服<br/>
	<form action="/vip/cloth" method="post" enctype="multipart/form-data">
		<div>上传人图片：<input type="file" name="file1" multiple="multiple"></div>
		<div>上传衣服图片：<input type="file" name="file2" multiple="multiple"></div>
		<input type="submit" value="添加">
	</form>
	<br/>
	换背景<br/>
	<form action="/vip/bground" method="post" enctype="multipart/form-data">
		<div>上传背景图片：<input type="file" name="file1" multiple="multiple"></div>
		<div>上传人图片：<input type="file" name="file2" multiple="multiple"></div>
		<input type="submit" value="添加">
	</form>
	<br/>
</body>
</html>