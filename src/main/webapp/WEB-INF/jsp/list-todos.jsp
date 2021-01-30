
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<div class="container">

<h1>${name}, here are the list of your todos!</h1>

<table class="table table-striped">
	<caption>Your todos are:</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done?</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<!-- JSTL For Loop -->
			<c:forEach items="${todos}" var="todo">

			<tr>
				<td>${todo.desc}</td>
				<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
				<td>${todo.done}</td>
				<td><a type="button" class="btn btn-primary" href="/update-todo?id=${todo.id}">Update</a></td>
				<td><a type="button" class="btn btn-danger" href="/delete-todo?id=${todo.id}">Delete</a></td>
			</tr>
			</c:forEach>
		</tbody>
</table>

<div><a href="/add-todo">Add a todo</a></div>

</div>

<%@ include file="common/footer.jspf" %>