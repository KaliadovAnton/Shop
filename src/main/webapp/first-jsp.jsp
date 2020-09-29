<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="anton.Good" %>

<% int pre = 0 ;%>
<%
    Cookie[] cookies = request.getCookies();
    String name = cookies[0].getValue();
    %>
<!doctype html>
<html>
<head>
<title>hello</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <h1>Hello, <%= name %>!</h1>
    <%  ArrayList<Good> goods = (ArrayList) request.getAttribute("goods");
        request.setAttribute("name", request.getAttribute("name"));
        %>
        <c:forEach var="preorder" items="${preorders}">
            <%= ++pre%>) ${preorder}
        </c:forEach>
    <form action="/my-app/temp-serv" method="POST" name="shop-form">
    <select multiple name="select">
        <c:forEach var="good" items="${goods}">
            <option value="${good.getName()} ${good.getPrice()}">${good.getName()}, ${good.getPrice()} byn
            </option>
        </c:forEach>
    </select>
    <p><input type="submit" name="item" value="Add Item"></p>
    </form>
    <form action="/my-app/check" method="POST">
        <p><input type="submit" name="select" value="Submit"></p>
    </form>
    <form action="/my-app/temp-serv" method="POST">
            <p><input type="submit" name="flush" value="Flush"></p>
        </form>
</body>
</html>
