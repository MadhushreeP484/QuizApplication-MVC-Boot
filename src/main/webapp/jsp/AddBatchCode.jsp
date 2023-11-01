<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${pass}
${fail}
<form action="/trainer/create-batchcode" method="post">
<table>
<tr><td>Add Batch Code</td>
<td><input type="text" name="batchCode" placeholder="Enter New Batch Code"></td>
<tr> <td></td> <td><button>Create Batch Code</button></td>
</table>

</form>
</body>
</html>