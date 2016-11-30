<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>aspath.jsp - passing arguments using path.</h1>
<p>
command: ${command}<br>
number: ${number}<br>
number2: ${number2}<br>
</p>
<p>
  <a href="<%=request.getContextPath()%>">go back... to <%=request.getContextPath()%></a>
  
</p>
</body>
</html>