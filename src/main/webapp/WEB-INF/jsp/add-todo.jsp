<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!-- Spring form -->

<html>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<head>
<title>First Web Application</title>
</head>

<body>
<div class="container">
<h1>Add-to-do-page</h1>

<form:form method="post" modelAttribute="todo">
	<fieldset class="form-group">
		<form:label path="desc">Description:</form:label>
		<form:input path="desc" type="text" name="desc" class="form-control" required="required"/>
		<form:errors path="desc" cssClass="text-warning"/>
	</fieldset>
	<button type="submit" class="btn btn-success">Add</button>
</form:form>
</div>

		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>