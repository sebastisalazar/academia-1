package academia.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academia.modelo.pojo.Usuario;



/**
 * Servlet Filter implementation class PrivadoProfesor
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/privado/profesor.jsp" })
public class PrivadoProfesorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PrivadoProfesorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest) request; 
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session= req.getSession();
		
		//necesitamos la url de como comienza nuestra App, apra construir una ryta ABSOLUTA y que no sea relativa
		String urlInicioApp = req.getContextPath();
		
		Usuario u= (Usuario) session.getAttribute("usuario_sesion");
		
		
		if (u==null) {
			res.sendRedirect(urlInicioApp+"/login.jsp");
			
		}else if (u.getRol()==Usuario.ROL_ALUMNO){
				
			res.sendRedirect( urlInicioApp+"/login");
			
			
		}else {
			// si usuario administrador
			// dejamos pasar y continua la request
			chain.doFilter(request, response);	
		}

		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
