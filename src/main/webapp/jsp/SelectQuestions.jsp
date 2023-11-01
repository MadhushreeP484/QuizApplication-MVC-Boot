<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Test</title>
</head>
<body>
${pass}
${fail}
<form action="/trainer/create-test" method="post">
<input type="text" name="batchCode" value="${batchCode}" hidden="hidden">
<label>Enter Test Name</label><input type="text" name="testName">
<br>
<label>Start Time of Test</label><input type="datetime-local" name="startTime">
<br>
<label>Start Test Duration</label><input type="number" name="duration">
<br>
<c:if test="${!trueFalseQ.isEmpty()}">
<table border="1">
<tr><td>Id</td><td>Question</td><td>Answer</td><td>Marks</td><td>Subject</td>
<c:forEach var="tq" items="${trueFalseQ}">
	<tr><td>${tq.id}</td>
	<td>${tq.question}</td>
	<td>${tq.answer}</td>
	<td>${tq.marks}</td>
	<td>${tq.subject}</td>
	<td><input type="checkbox" name="trueFalseQuestions" value="${tq.id}"> Select Question</td>
</c:forEach>
</table>
</c:if>
<br>
<c:if test="${!mcqQ.isEmpty()}">
<table border="1">
<tr><td>Id</td><td>Question</td><td>Option1</td><td>Option2</td><td>Option3</td><td>Option4</td><td>Answer</td><td>Marks</td><td>Subject</td>
<c:forEach var="mq" items="${mcqQ}">
	<tr><td>${mq.id}</td>
	<td>${mq.question}</td>
	<td>${mq.answer}</td>
	<td>${mq.option1}</td>
	<td>${mq.option2}</td>
	<td>${mq.option3}</td>
	<td>${mq.option4}</td>
	<td>${mq.marks}</td>
	<td>${mq.subject}</td>
	<td><input type="checkbox" name="mcqQuestions" value="${mq.id}"> Select Question</td>
</c:forEach>
</table>
</c:if>
<br>
<c:if test="${!descQ.isEmpty()}">
<table border="1">
<tr><td>Id</td><td>Question</td><td>Answer</td><td>Marks</td><td>Subject</td>
<c:forEach var="dq" items="${descQ}">
	<tr><td>${dq.id}</td>
	<td>${dq.question}</td>
	<td>${dq.answer}</td>
	<td>${dq.marks}</td>
	<td>${dq.subject}</td>
	<td><input type="checkbox" name="descriptionQuestions" value="${dq.id}"> Select Question</td>
</c:forEach>
</table>
</c:if>
<button>Create Test</button>
</form>
</body>
</html>