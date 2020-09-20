<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../includes/cabecera.jsp">

	<jsp:param name="pagina" value="Vista Profesor" />

	<jsp:param name="title" value="Vista Profesor" />

</jsp:include>

	<h1>Bienvenido Profesor</h1>
	<!-- ${usuario_sesion} -->
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
	
	<c:choose>
	
	
		<c:when test="${not empty mensajeborrarcorrecto}">
				<div class="alert alert-success" role="alert">
			  		${mensajeborrarcorrecto}
				</div>
				
				<% request.removeAttribute("mensajeborrarcorrecto"); %>
		</c:when>
		
		<c:when test="${not empty mensajeborrar}">
				<div class="alert alert-danger" role="alert">
			  		${mensajeborrar}
				</div>
				
				<% request.removeAttribute("mensajeborrar"); %>
		</c:when>
		
	
	</c:choose>
	
	
	<c:choose>
	
		<c:when test="${not empty mensaje}">
			<div class="alert alert-warning" role="alert">
		  		${mensaje}
			</div>
		</c:when>
		
		<c:when test="${empty mensaje}">
			<% request.removeAttribute("mensaje"); %>
			<table class="table table-striped table-hover tabla ">
				<thead class="thead-dark text-center">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">CURSO</th>
						<th scope="col">IDENTIFICADOR</th>
						<th scope="col">HORAS</th>
						
						<th scope="col">BORRAR</th>
						
					</tr>
				</thead>
				<tbody>
			
			<c:forEach items="${cursos}" var="c">
						<tr class="text-center">
							<td>${c.id}</td>
							<td>${c.nombre}</td>
							<td>${c.identificador}</td>
							<td>${c.horas}</td>
							<td>
								<a onclick="confirmar('${c.nombre}')" href="eliminar-curso?id=${c.id}"> 
									<i class="fas fa-trash-alt"></i>
								</a>
							</td>
						</tr>
			</c:forEach>
			
				</tbody>
			
			</table>
		</c:when>
	
	
	</c:choose>
	

<br><br><br>

<h2>Crear nuevo curso</h2>
	<c:if test="${not empty mensajecurso}">
				<div class="alert alert-danger" role="alert">
			  		${mensajecurso}
				</div>
				
				<% request.removeAttribute("mensajecurso"); %>
	</c:if>


	<form action="crear-curso" method="POST">
	  <div class="form-group">
	    <label for="nombre">Nombre del curso</label>
	    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduce el nombre del curso">
	    
	  </div>
	  <div class="form-group">
	    <label for="horas">Horas</label>
	    <input type="number" class="form-control" id="horas" name="horas" placeholder="Introduce las horas">
	  </div>
	  
	  <div class="form-group">
	    <label for="horas">Identificador</label>
	    <input type="text" class="form-control" id="identificador" name="identificador" placeholder="Introduce el identificador">
	  </div>
	
	  <button type="submit" class="btn btn-primary">Crear</button>
	</form>

<jsp:include page="../includes/pie.jsp"/>