<%@ page import = "org.isen.hello.HelloServlet" %>
<% String title = "Hello JSP" %>
<%@ include file="header.jsp" %>


<%
  String name = HelloServlet.getNameFromRequest(request);
%>


<h1>Hello <%= name %></h1>

<form method="get">
<input name="name"/>
<input type="submit"/>
</form>


<%@ include file="footer.jsp" %>