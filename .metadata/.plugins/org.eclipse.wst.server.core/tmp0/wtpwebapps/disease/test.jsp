<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String login = (String)session.getAttribute("login");
		String name = (String)session.getAttribute("username");
		if(login==null){
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		else{
			out.println("身份："+login);
			out.println("欢迎您："+name);
			
		}
	%>
	<br/>
	---------------------User-----------------------<br/>
	增加用户无效回答次数
	<form action="user/update" method="post">
		<input type="submit" value="增加">
	</form>
	<br/>
	
	---------------------Doctor---------------------<br/>
	修改我的医院名
	<form action="doctor/update" method="post">
		医院名：<input type="text" name="hospital">
		<input type="submit" value="修改">
	</form>
	<br/>
	-----------------------Disease-------------------<br/>
	添加疾病
	<form action="disease/add" method="POST">
		疾病名：<input type="text" name="diseaseName">
		症状：<input type="text" name="symptom">
		潜伏期：<input type="text" name="latency">
		传播方式：<input type="text" name="spread">
		<input type="submit" value="添加">
	</form>
	<br/>
	
	修改疾病
	<form action="disease/update" method="POST">
		疾病名：<input type="text" name="diseaseName">
		症状：<input type="text" name="symptom">
		潜伏期：<input type="text" name="latency">
		传播方式：<input type="text" name="spread">
		<input type="submit" value="修改">
	</form>
	<br/>
	-----------------------Patient-------------------<br/>
	添加病人
	<form action="patient/add" method="POST">
		人名：<input type="text" name="name">
		身份证号：<input type="text" name="number">
		性别：<input type="text" name="gender">
		年龄：<input type="text" name="age">
		医院：<input type="text" name="hospital">
		省份：<input type="text" name="province">
		城市：<input type="text" name="city">
		疾病名：<input type="text" name="diseaseName">
		<input type="submit" value="添加">
	</form>
	
	为病人增加医生
	<form action="patient/updatePatientByAddDoctor" method="POST">
		人名：<input type="text" name="name">
		身份证号：<input type="text" name="number">
		医生名：<input type="text" name="username">
		<input type="submit" value="添加">
	</form>
	
	修改病人治愈情况
	<form action="patient/cure" method="POST">
		人名：<input type="text" name="name">
		身份证号：<input type="text" name="number">
		疾病名：<input type="text" name="diseaseName">
		治愈情况：<input type="text" name="cure">
		<input type="submit" value="添加">
	</form>

	根据省查某病病人
	<form action="patient/patient" method="GET">
		疾病：<input type="text" name="diseaseName">
		省份：<input type="text" name="province">
		<input type="submit" value="添加">
	</form>

	根据省查某病病人数量
	<form action="patient/patientnum" method="GET">
		疾病：<input type="text" name="diseaseName">
		省份：<input type="text" name="province">
		<input type="submit" value="添加">
	</form>
	
	查找某病所有病人数量
	<form action="patient/queryPatientNum" method="GET">
		疾病：<input type="text" name="diseaseName">
		<input type="submit" value="添加">
	</form>
	
	
	
	
	
	
	
	

</body>
</html>