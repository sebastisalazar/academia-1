
<h2>Para Hoy y entregar: LOGIN</h2>
<ol>
	<li>Enlace para formulario de login, o ponerlo en esta misma pagina</li>
	<li>formulario, que llame a un conrolador con ACTION y GET, parametros [usuario,password] </li>
	<li>Controlador que escuche en la url de "/login"</li>
	<li>Comprobar si existe usuario en la bbdd, declarar nuevo metodo en DAO e implementarlo</li>
	<ol>
		<li>Si no existe, volver al login y mensaje al usurio</li>
		<li>si es PROFESOR, que vaya a una jsp "privado/profesor.jsp"</li>
		<li>si es ALUMNO, que vaya a una jsp "privado/alumno.jsp"</li>
	</ol>
	
</ol>

<h2>TAREAS</h2>
<ol>
	<li>Maquetar pagina web, css e includes para reutilizar</li>
</ol>

<h1> Listado Cursos </h1>

${cursos}