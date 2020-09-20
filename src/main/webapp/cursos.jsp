<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="Inicio" />

</jsp:include>

<h2>TAREAS</h2>
<ol>
	<li>Maquetar pagina web, css e includes para reutilizar  <strong>HECHO</strong></li>
	<li>miercoles: Si se logea un PROFESOR que muestre sus curso, desde la misma JSP podria crear un nuevo curso y eliminarlo <strong>HECHO</strong></li>
	<li>jueves: Si se logea un ALUMNO que muestre sus curso a los que esta inscrito, desde la misma JSP apuntarse a un nuevo curso <strong>HECHO</strong></li>	
</ol>

<h2>EXTRAS</h2>
<ol>
	<li>Trazas de LOG <strong>TODO</strong></li>
	<li>Singleton <strong>HECHO</strong></li>
	<li>Gestion de errores TRY y CACTH sobre todo en los controladore <strong>HECHO</strong></li>
	<li>Validacion de datos de los formulario con javax.validation <strong>TODO</strong></li>
	<li>Gestion de Seguridad, que los profesores y alumnos solo puedan modificar sus datos, No poder saltarse el Login <strong>TODO</strong></li>
	<li>Un ALUMNO puede escribir una reseña de un curso/profesor, calificando del 1 al 5 y escribiendo un comentario <strong>TODO</strong></li>	
</ol>



<a href="login.jsp">Iniciar Sesión</a>


<h1> Listado Cursos </h1>

<!-- ${cursos} -->

<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">CURSO</th>
			<th scope="col">IDENTIFICADOR</th>
			<th scope="col">HORAS</th>
			<th scope="col">PROFESOR</th>
			<th scope="col">ROL</th>
			
		</tr>
	</thead>
	<tbody>

<c:forEach items="${cursos}" var="c">
			<tr class="text-center">
				<td>${c.id}</td>
				<td>${c.nombre}</td>
				<td>${c.identificador}</td>
				<td>${c.horas}</td>
				<td>${c.profesor.nombre} ${c.profesor.apellidos} </td>
				<td>${c.profesor.rol}</td>
				
				
			</tr>
</c:forEach>

	</tbody>

</table>

<jsp:include page="includes/pie.jsp"/>