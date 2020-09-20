<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty usuario_sesion}">
	<c:redirect url="/login"/>
</c:if>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Iniciar sesion" />

	<jsp:param name="title" value="Iniciar sesion" />

</jsp:include>
	
	
	<div class="d-flex flex-row justify-content-center border-0  ">
	
	
	<form class="px-5 py-3 border pb-5" action="login" method="post">
		<h1 class="text-primary text-center mb-3 mt-5">Iniciar sesi�n</h1>

		<p style="color:red">${mensaje}</p>
	
		<div class="form-group pt-2">
			<label for="nombre">Nombre</label> <input
				type="text" class="form-control px-5" id="email" name="nombre" placeholder="Escribe Tu Usuario" required autofocus>
		</div>
		<div class="form-group ">
			<label for="password">Password</label> <input
				type="password" class="form-control px-5" id="password" name="password" placeholder="Contrase�a" required >
		</div>
		
		<div class="d-flex justify-content-center">
			   <button type="submit" class="btn btn-primary px-5">Entrar</button>
		</div>
	</form>

</div>

<jsp:include page="includes/pie.jsp"/>	