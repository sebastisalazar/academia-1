package academia.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.dao.impl.UsuarioDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		ArrayList<Curso> cursos = new ArrayList<Curso>();

		ArrayList<Curso> cursosTodos = new ArrayList<Curso>();
		
		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		CursoDAOImpl daocurso = CursoDAOImpl.getInstance();
		
		//obtiene si hay una session ya iniciada
		Usuario usuario=(Usuario) request.getSession().getAttribute("usuario_sesion");
		
		
		ArrayList<String> requeridos= new ArrayList<String>();
		
		//si no existe sesión y se entra al controlador por el formulario login
		if (usuario==null) {
			
			//se verifica campos vacios
			if (("").equalsIgnoreCase(nombre)) {
				requeridos.add("<p> <span class=\"font-weight-bold mx-1\">Nombre: </span>"+"Escribe el usuario</p>");
			}
			
			if (("").equalsIgnoreCase(password)) {
				requeridos.add("<p> <span class=\"font-weight-bold mx-1\">Password: </span>"+"Escribe la contraseña</p>");
			}
			
			
			//si no hay campos vacios se procede a comprobar si existe
			if (requeridos.size()==0) {
				
				
				//se verifica su cuenta en BBDD
				usuario=dao.buscar(nombre, password);
				
				if ( usuario == null ) {
					
					//si no se encuentra en la BBDD se muestra mensaje
					request.setAttribute("mensaje", "Credenciales incorrectas, prueba de nuevo por favor");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				
				//si existe cuenta y la cuenta es de tipo PROFESOR se obtiene los datos relacionados a la cuenta
				}else if ( usuario.getRol() == Usuario.ROL_PROFESOR ) {
					
					int idProfersor = usuario.getId();	
					
					try {
						cursos = daocurso.listarPorProfesor(idProfersor);
					} catch (Exception e) {
						request.setAttribute("mensaje", e.getMessage());
						
					}
					
					request.setAttribute("cursos", cursos);
					request.getSession().setAttribute("usuario_sesion", usuario);
					
					
					//Se redirige a la vista para profesores
					request.getRequestDispatcher("privado/profesor.jsp").forward(request, response);
					
				//si es alumno se obtiene los datos relacionados a la cuenta
				}else {
					
					int idAlumno= usuario.getId();
					
					try {
						cursos=daocurso.listarPorAlumno(idAlumno);
					} catch (Exception e) {
						request.setAttribute("mensaje", e.getMessage());
					}
					
					try {
						cursosTodos=daocurso.listar();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					request.setAttribute("cursos", cursos);
					request.setAttribute("cursosTodos", cursosTodos);
					request.getSession().setAttribute("usuario_sesion", usuario);
					
					//Se redirige a la vista para alumnos
					request.getRequestDispatcher("privado/alumno.jsp").forward(request, response);
				}
				
			//si hay errores en el formulario se redirige
			}else {
				
				request.setAttribute("camposerroneos", requeridos);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
			
			
		//si yá existe sesión iniciada y el usuario escribe en url /login otra vez (habiendose ya logeado)
			
		}else {
			
			//si es profesor se redirige a la vista de profesor
			if (usuario.getRol()== Usuario.ROL_PROFESOR) {
				try {
					cursos = daocurso.listarPorProfesor(usuario.getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Crea el DAO de Cursos y obtento todos los cursos de ese profesor por su id
				
				request.setAttribute("cursos", cursos);
				request.getSession().setAttribute("usuario_sesion", usuario);
				request.getRequestDispatcher("privado/profesor.jsp").forward(request, response);
				
			//si es alumno se redirige a la vista de alumno
			}else {
				
				try {
					cursos=daocurso.listarPorAlumno(usuario.getId());
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage());
				}
				try {
					cursosTodos=daocurso.listar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("cursos", cursos);

				request.setAttribute("cursosTodos", cursosTodos);
				request.getSession().setAttribute("usuario_sesion", usuario);
				request.getRequestDispatcher("privado/alumno.jsp").forward(request, response);
			}
			
		}
		
		
		
		
		

		
			
			
		
		
	}

}
