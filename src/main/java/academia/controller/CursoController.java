package academia.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import academia.modelo.dao.CursoDAO;
import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class CursoController
 */
@WebServlet({"/cursos","/crear-curso", "/eliminar-curso","/alta-curso"})
public class CursoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//variables para saber a qué controlador llama
		boolean llamaAeliminar = ("/eliminar-curso").equalsIgnoreCase(request.getServletPath());
		boolean llamaAalta = ("/alta-curso").equalsIgnoreCase(request.getServletPath());
		
		//arraylist que guardara todos los cursos ya sea de Profesor o Alumno
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		//llamada al DAO usando SINGLETON
		CursoDAO dao = CursoDAOImpl.getInstance();
		CursoDAOImpl cursoDao= CursoDAOImpl.getInstance();
		
		//si llama a eliminar
		if (llamaAeliminar) {
			
			//se obtiene el id pasado por URL
			int idCurso=Integer.parseInt(request.getParameter("id"));
			
			//Se instancia objteto Curso
			 Curso c= new Curso();
			 
			 //se asgina el id obtenido por URL al objeto curso
			 c.setId(idCurso);
			 
			 //ejecuccion de borrado
			 try {
				cursoDao.BorrarCurso(c);
				
				//si el curso se borra correctamente enviará este mensaje
				request.setAttribute("mensajeborrarcorrecto","El curso con id "+c.getId()+" ha sido eliminado correctamente");
				
			} catch (Exception e) {
				//si el curso no se borra mandará el mensaje recibido por el DAO
				request.setAttribute("mensajeborrar", e.getMessage());
				
			}finally {
				//se redirecciona a login
				request.getRequestDispatcher("login").forward(request, response);
			}
			 
		//si no llama a alta/crear curso	 
		}else if (llamaAalta) {
			
			
			
			int id_curso=Integer.parseInt(request.getParameter("idcurso"));
			Usuario usuario= (Usuario) request.getSession().getAttribute("usuario_sesion");
			int id_usuario= usuario.getId();
			
			 try {
				cursoDao.AltaAlumnoCurso(id_usuario,id_curso);
				request.setAttribute("mensajealtacorrecto","Te has matriculado correctamente en el curso con id "+id_curso);
			} catch (Exception e) {
				request.setAttribute("mensajealta", e.getMessage());
				
			}finally {
				request.getRequestDispatcher("login").forward(request, response);
			}
			
			 
		}
		
		try {
			cursos = dao.listar();
			request.setAttribute("cursos", cursos);
		} catch (Exception e) {
			request.setAttribute("cursos", e);
			e.printStackTrace();
		}finally {
			
			request.getRequestDispatcher("cursos.jsp").forward(request, response);
		}
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		CursoDAOImpl cursoDao= CursoDAOImpl.getInstance();
			
			String nombre = request.getParameter("nombre");
			String identificador= request.getParameter("identificador");
			int horas = Integer.parseInt(request.getParameter("horas"));
			Usuario profesor=(Usuario) request.getSession().getAttribute("usuario_sesion");
			
			ArrayList<String> requeridos= new ArrayList<String>();
			
			
			Curso c= new Curso();
			
			c.setNombre(nombre);
			c.setHoras(horas);
			c.setIdentificador(identificador);
			c.setProfesor(profesor);
			
			Set<ConstraintViolation<Curso>> violationsCurso= validator.validate(c);

			// si existen violaciones para la ciudad se añaden a requeridos
			if (!violationsCurso.isEmpty()) {
				// se recogen los mensajes y se añaden al listado de requeridos
				for (ConstraintViolation<Curso> v : violationsCurso) {
					requeridos.add("<p> <span class=\"font-weight-bold mx-1\">"+v.getPropertyPath()+"</span>" + v.getMessage() +"</p>");
				}
			}
			
		
			try {
				if (requeridos.size()==0) {
					cursoDao.CrearCurso(c);
				}else {
					request.setAttribute("camposerroneos", requeridos);
				}
			} catch (Exception e) {
				request.setAttribute("mensajecurso", e.getMessage());
			}finally {
				request.getRequestDispatcher("login").forward(request, response);
			}
			
		
		
		
		
	}

}
