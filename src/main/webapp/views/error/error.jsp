<%@ page contentType="text/html; charset=utf-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<strong>[ERROR] An error has occurred.</strong>
	<br />
	<br />
	<% if (exception != null) { %>
	- Error Type: <%= exception.getClass().getName() %>
	<br />
	- Error Message: <%= exception.getMessage() %>
	<br />
	<br />
	<% } %>
	<a href="javascript:history.back();">▶ Go back to the previous page</a>
</body>
</html>