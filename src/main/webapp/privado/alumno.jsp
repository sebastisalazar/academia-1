<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../includes/cabecera.jsp"/>

<h1>Bienvenido Alumno</h1>

<!-- ${usuario_sesion}-->

<!--  ${cursos}-->


<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">NOMBRE</th>
			<th scope="col">APELLIDOS</th>
			<th scope="col">ROL</th>
			<th scope="col">PASSWORD (CIFRADA)</th>
			
		</tr>
	</thead>
	<tbody>


			<tr class="text-center">
				<td>${usuario_sesion.id}</td>
				<td>${usuario_sesion.nombre}</td>
				<td>${usuario_sesion.apellidos} </td>
				<td>${usuario_sesion.rol} </td>
				<td>${usuario_sesion.password}</td>
				
			</tr>


	</tbody>

</table>
	

<br><br><br>
	
	
	<h2>Tus Cursos</h2>
	<!--  ${cursos}-->
	
	
	<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">CURSO</th>
			<th scope="col">IDENTIFICADOR</th>
			<th scope="col">HORAS</th>
			
		</tr>
	</thead>
	<tbody>

<c:forEach items="${cursos}" var="c">
			<tr class="text-center">
				<td>${c.id}</td>
				<td>${c.nombre}</td>
				<td>${c.identificador}</td>
				<td>${c.horas}</td>
				</td>
			</tr>
</c:forEach>

	</tbody>

</table>

<br><br><br>


<h2>Cursos disponibles</h2>

<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">CURSO</th>
			<th scope="col">IDENTIFICADOR</th>
			<th scope="col">HORAS</th>
			<th scope="col">PROFESOR</th>
			<th scope="col">ALTA</th>
			
		</tr>
	</thead>
	<tbody>

<c:forEach items="${cursosTodos}" var="c">
			<tr class="text-center">
				<td>${c.id}</td>
				<td>${c.nombre}</td>
				<td>${c.identificador}</td>
				<td>${c.horas}</td>
				<td>${c.profesor.nombre} ${c.profesor.apellidos}</td>
				<td><a href="alta-curso?idcurso=${c.id}" >apuntarse</a></td>
				
			</tr>
</c:forEach>

	</tbody>

</table>


<jsp:include page="../includes/pie.jsp"/>