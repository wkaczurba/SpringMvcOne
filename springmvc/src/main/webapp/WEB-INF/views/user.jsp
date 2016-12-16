<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>User...</h1>
TODO: Get data from model + display everything here.

<ul>
  <li>FirstName: ${user.firstName}</li>
  <li>LastName: ${user.lastName}</li>
  <li>UserName: ${user.userName}</li>
  <li>Email: ${user.email}</li>
</ul>

</body>
</html>