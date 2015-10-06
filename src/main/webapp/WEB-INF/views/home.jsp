<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<p>size: ${listSize} </p>
<p>content: ${content} </p>
<%-- <c:forEach var="val" items="${l}"> ${val} </c:forEach> --%>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
