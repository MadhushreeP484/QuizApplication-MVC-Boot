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
<form action="/student/change-password/${email}" method="post">
<table>
        <tr>
            <td><label for="otp">Enter OTP</label></td>
            <td><input type="number" id="otp" name="otp"></td>
        </tr>
        <tr>
            <td><label for="password">Enter new Password</label></td>
            <td><input type="password" id="password" name="password"></td>
        </tr>
        <tr>
            <td><button type="submit">Change Password</button></td>
            <td><button type="reset">Cancel</button></td>
        </tr>
    </table>
</form>
</body>
</html>