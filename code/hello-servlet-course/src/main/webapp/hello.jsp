<html>
<head>
  <title>Hello JSP</title>
</head>
<body>
<%
  String paramName = request.getParameter("name");
  String name = paramName == null ? "World" : paramName;
%>


<h1>Hello <%= name %></h1>

<form method="get">
<input name="name"/><input type="submit"/>
</form>
</body>
</html>