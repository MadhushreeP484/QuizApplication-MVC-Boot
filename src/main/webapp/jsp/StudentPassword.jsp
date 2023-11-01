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
<form action="/student/email-verify" method="post">
    <table>
        <tr>
            <td><label for="email">Enter Registered Email</label></td>
            <td><input type="text" id="email" name="email"></td>
        </tr>
        <tr>
            <td><button type="submit">Send Link</button></td>
            <td><button type="reset">Cancel</button></td>
        </tr>
    </table>
</form>
</body>
</html>