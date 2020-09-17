<jsp:include page="includes/head.jsp"/>

<h2>TAREAS</h2>
<ol>
	<li>Maquetar pagina web, css e includes para reutilizar</li>
	<li>miercoles: Si se logea un PROFESOR que muestre sus curso, desde la misma JSP podria crear un nuevo curso y eliminarlo</li>
	<li>jueves: Si se logea un ALUMNO que muestre sus curso a los que esta inscrito, desde la misma JSP apuntarse a un nuevo curso</li>	
</ol>

<h2>EXTRAS</h2>
<ol>
	<li>Trazas de LOG</li>
	<li>Singleton</li>
	<li>Gestion de errores TRY y CACTH sobre todo en los controladore</li>
	<li>Validacion de datos de los formulario con javax.validation</li>
	<li>Gestion de Seguridad, que los profesores y alumnos solo puedan modificar sus datos, No poder saltarse el Login</li>
	<li>Un ALUMNO puede escribir una reseña de un curso/profesor, calificando del 1 al 5 y escribiendo un comentario</li>	
</ol>



<a href="login.jsp">Iniciar Sesión</a>


<h1> Listado Cursos </h1>

${cursos}


<jsp:include page="includes/footer.jsp"/>