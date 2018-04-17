<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
 out.println(getServletContext().getRealPath("/"));
String dirPath = getServletContext().getRealPath("/");
java.io.FileWriter fw = new java.io.FileWriter(dirPath + "/fromjsp.txt");
fw.write("Hello from JSP");
fw.flush();
fw.close();
 %>
</body>
</html>