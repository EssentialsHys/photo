<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/pics/upload" method="post" enctype="multipart/form-data">
		<div>上传文件：<input type="file" name="uploadFiles" multiple="multiple"></div>
		<input type="submit" value="上传">
	</form>
	<br/>
	
	查找摄影师的作品
	<form action="/pics/inf" method="get">
		用户名：<input type="text" name="pname">
		<input type="submit" value="查找">
	</form>
	<br/>
	
</body>
</html>