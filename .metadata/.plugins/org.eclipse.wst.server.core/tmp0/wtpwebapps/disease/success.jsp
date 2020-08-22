<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%!
		String name;
	%>
	<%
		/*Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("username")){
				name = cookie.getValue();
				out.println("欢迎您cookie"+name);
			}
		}*/
		
		
		String login = (String)session.getAttribute("username");
		if(login==null){
			response.sendRedirect("login.jsp");
		}
		else{
			out.println("欢迎您"+login);
		}
	%>
	<br/>
	success!
	<a href="test.jsp">返回</a>
</body>
</html>