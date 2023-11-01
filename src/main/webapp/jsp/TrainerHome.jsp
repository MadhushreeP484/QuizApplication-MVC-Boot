<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
/* CSS for styling buttons and forms */
body {
	font-family: Arial, sans-serif;
}

button {
	padding: 10px 20px;
	background-color: gray;
	color: white;
	border: none;
	cursor: pointer;
	margin: 5px;
}

button:hover {
	background-color: silver;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.form {
	display: none;
	margin: 10px 0;
}
</style>
</head>
<body>
	${pass} ${fail}
	<div class="dropdown" id="addQuestionDropdown">
		<button>Add Question</button>
		<div class="dropdown-content">
			<button id="mcqButton">MCQ</button>
			<button id="descriptionButton">Description</button>
			<button id="trueFalseButton">True/False</button>
		</div>
	</div>

	<a href="/trainer/create-batchcode"><button id="createBatchButton">Create
			Batch</button></a>

	<div class="dropdown" id="createTest">
		<button>Create Test</button>
		<div class="dropdown-content">
			<c:choose>
				<c:when test="${batchCode.isEmpty()}">
					<h4>No Batch Codes Added</h4>
				</c:when>
				<c:otherwise>
					<c:forEach var="code" items="${batchCode}">
						<a href="/trainer/questions/${code.getBatchCode()}"><button>${code.getBatchCode()}</button></a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<button id="seePreviousResultButton">See Previous Result</button>
	<a><button id="logoutButton">Logout</button></a>

	<div class="form" id="mcqForm">
		<!-- MCQ Form -->
		<form action="/trainer/add-question-mcq" method="post">
			<table>
				<tr>
					<td><input type="text" name="question" placeholder="Question"></td>
				<tr>
					<td><input type="number" name="marks" placeholder="Marks"></td>
				<tr>
					<td><input type="text" name="option1" placeholder="Option 1"></td>
				<tr>
					<td><input type="text" name="option2" placeholder="Option 2"></td>
				<tr>
					<td><input type="text" name="option3" placeholder="Option 3"></td>
				<tr>
					<td><input type="text" name="option4" placeholder="Option 4"></td>
				<tr>
					<td><input type="text" name="answer"
						placeholder="Correct Answer"></td>
				<tr>
					<td><button>Add</button></td>
			</table>
		</form>
	</div>

	<div class="form" id="descriptionForm">
		<!-- Description Form -->
		<form action="/trainer/add-question-description" method="post">
			<table>
				<tr>
					<td><input type="text" name="question" placeholder="Question"></td>
				<tr>
					<td><input type="number" name="marks" placeholder="Marks"></td>
				<tr>
					<td><textarea name="answer" placeholder="Answer"></textarea></td>
				<tr>
					<td><button>Add</button></td>
			</table>
		</form>
	</div>

	<div class="form" id="trueFalseForm">
		<!-- True/False Form -->
		<form action="/trainer/add-question-truefalse" method="post">
			<table>
				<tr>
					<td><input name="question" type="text" placeholder="Question"></td>
				<tr>
					<td><input name="marks" type="number" placeholder="Marks"></td>
				<tr>
					<td><input type="radio" name="answer" value="True">True</td>
					<td><input type="radio" name="answer" value="False">False</td>
				<tr>
					<td><button>Add</button></td>
			</table>
		</form>
	</div>

	<script>
        const addQuestionDropdown = document.getElementById('addQuestionDropdown');
        const mcqButton = document.getElementById('mcqButton');
        const descriptionButton = document.getElementById('descriptionButton');
        const trueFalseButton = document.getElementById('trueFalseButton');
        const mcqForm = document.getElementById('mcqForm');
        const descriptionForm = document.getElementById('descriptionForm');
        const trueFalseForm = document.getElementById('trueFalseForm');
        const createTest = document.getElementById('createTest');
        

        function hideForms() {
            mcqForm.style.display = 'none';
            descriptionForm.style.display = 'none';
            trueFalseForm.style.display = 'none';
        }

        mcqButton.addEventListener('click', (e) => {
            e.stopPropagation();
            mcqForm.style.display = 'block';
            descriptionForm.style.display = 'none';
            trueFalseForm.style.display = 'none';
        });

        descriptionButton.addEventListener('click', (e) => {
            e.stopPropagation();
            mcqForm.style.display = 'none';
            descriptionForm.style.display = 'block';
            trueFalseForm.style.display = 'none';
        });

        trueFalseButton.addEventListener('click', (e) => {
            e.stopPropagation();
            mcqForm.style.display = 'none';
            descriptionForm.style.display = 'none';
            trueFalseForm.style.display = 'block';
        });

        // Hide forms when clicking anywhere outside the forms
       // document.addEventListener('click', hideForms);
    </script>
</body>
</html>

