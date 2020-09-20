package academia.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean llamaAeliminar = ("/eliminar-curso").equalsIgnoreCase(request.getServletPath());
		boolean llamaAalta = ("/alta-curso").equalsIgnoreCase(request.getServletPath());
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		//TODO singleton
		CursoDAO dao = CursoDAOImpl.getInstance();
		CursoDAOImpl cursoDao= CursoDAOImpl.getInstance();
		
		
		if (llamaAeliminar) {
			int idCurso=Integer.parseInt(request.getParameter("id"));
			 Curso c= new Curso();
			 c.setId(idCurso);
			 try {
				cursoDao.BorrarCurso(c);
				request.setAttribute("mensajeborrarcorrecto","El curso con id "+c.getId()+" ha sido eliminado correctamente");
			} catch (Exception e) {
				request.setAttribute("mensajeborrar", e.getMessage());
			}finally {
				request.getRequestDispatcher("login").forward(request, response);
			}
			 
			 
			 
		}else if (llamaAalta) {
			
			int id_curso=Integer.parseInt(request.getParameter("idcurso"));
			Usuario usuario= (Usuario) request.getSession().getAttribute("usuario_sesion");
			int id_usuario= usuario.getId();
			
			 try {
				cursoDao.AltaAlumnoCurso(id_usuario,id_curso);
				request.setAttribute("mensajealtacorrecto","Te has matriculado en el curso con id "+id_curso);
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
			
			
			Curso c= new Curso();
			
			c.setNombre(nombre);
			c.setHoras(horas);
			c.setIdentificador(identificador);
			c.setProfesor(profesor);
			
			
			
			try {
				cursoDao.CrearCurso(c);
			} catch (Exception e) {
				request.setAttribute("mensajecurso", e.getMessage());
			}finally {
				request.getRequestDispatcher("login").forward(request, response);
			}
		
		
		
		
	}

}
