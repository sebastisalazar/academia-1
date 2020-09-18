package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

public class CursoDAOImpl implements CursoDAO {
	
	
	private final static String SQL_LISTAR = "SELECT \n" + 
												"	c.id as 'curso_id', " + 
												"	c.identificador, " + 
												"	c.nombre as 'curso_nombre', " + 
												"	c.horas, " + 
												"	f.id as 'profesor_id', " + 
												"	f.nombre as 'profesor_nombre', " + 
												"	f.apellidos as 'profesor_apellidos', " + 
												"	rol " +												
												" FROM cursos c, usuarios f " + 
												" WHERE " + 
												"	c.id_profesor = f.id;";
	
	private final static String SQL_LISTAR_POR_PROFESOR = "SELECT \n" + 
			"	c.id as 'curso_id', " + 
			"	c.identificador, " + 
			"	c.nombre as 'curso_nombre', " + 
			"	c.horas, " + 
			"	f.id as 'profesor_id', " + 
			"	f.nombre as 'profesor_nombre', " + 
			"	f.apellidos as 'profesor_apellidos', f.password as 'profesor_password'," + 
			"	rol " +												
			" FROM cursos c, usuarios f " + 
			" WHERE " + 
			"	c.id_profesor = f.id AND c.id_profesor=? ;";
	
	private final static String SQL_LISTAR_POR_ALUMNO= "SELECT c.nombre,c.identificador,c.horas, u.nombre " + 
			"FROM cursos c,alumnos_curso a,usuarios u " + 
			"WHERE c.id = a.id_curso " + 
			"AND a.id_alumno= u.id " + 
			"AND a.id_alumno = ?";

	private final static String SQL_INSERT_CURSO = "INSERT INTO academia.cursos (nombre, identificador, horas, id_profesor) VALUES(?, ?, ?, ?);";
	
	
	private String SQL_DELETE_CURSO="DELETE FROM academia.cursos WHERE id=?;";

	@Override
	public ArrayList<Curso> listar() {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
			 ResultSet rs = pst.executeQuery()	
			){
			
			while ( rs.next() ) {				
				
				Curso c = new Curso();
				c.setId( rs.getInt("curso_id"));
				c.setNombre( rs.getString("curso_nombre"));
				c.setIdentificador(rs.getString("identificador"));
				c.setHoras(rs.getInt("horas"));
				
				Usuario p = new Usuario();
				p.setId(rs.getInt("profesor_id"));
				p.setNombre(rs.getString("profesor_nombre"));
				p.setApellidos( rs.getString("profesor_apellidos"));
				p.setRol( rs.getInt("rol"));
								
				c.setProfesor(p);
				
				cursos.add(c);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursos;
	}
	
	
public ArrayList<Curso> listarPorProfesor(int id) {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR_POR_PROFESOR);
			 	
			){
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while ( rs.next() ) {				
				
				
				Curso c = new Curso();
				c.setId( rs.getInt("curso_id"));
				c.setNombre( rs.getString("curso_nombre"));
				c.setIdentificador(rs.getString("identificador"));
				c.setHoras(rs.getInt("horas"));
				
				Usuario p = new Usuario();
				p.setId(rs.getInt("profesor_id"));
				p.setNombre(rs.getString("profesor_nombre"));
				p.setApellidos( rs.getString("profesor_apellidos"));
				p.setPassword(rs.getString("profesor_password"));
				p.setRol( rs.getInt("rol"));
								
				c.setProfesor(p);
				
				cursos.add(c);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursos;
	}

public ArrayList<Curso> listarPorAlumno(int id) {
	
	ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	try( Connection con = ConnectionManager.getConnection() ;
		 PreparedStatement pst = con.prepareStatement(SQL_LISTAR_POR_ALUMNO);
		 	
		){
		
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		
		while ( rs.next() ) {				
			
			
			Curso c = new Curso();
			c.setId( rs.getInt("curso_id"));
			c.setNombre( rs.getString("curso_nombre"));
			c.setIdentificador(rs.getString("identificador"));
			c.setHoras(rs.getInt("horas"));
			
			Usuario p = new Usuario();
			p.setId(rs.getInt("profesor_id"));
			p.setNombre(rs.getString("profesor_nombre"));
			p.setApellidos( rs.getString("profesor_apellidos"));
			p.setPassword(rs.getString("profesor_password"));
			p.setRol( rs.getInt("rol"));
							
			c.setProfesor(p);
			
			cursos.add(c);
			
		}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return cursos;
}


@Override
public Curso CrearCurso(Curso c) throws Exception {
	
	
	try (Connection con = ConnectionManager.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_INSERT_CURSO);

	) {
		// se modifica la insert diciendo que el interrogante lo sustituya con el nombre
		// del objeto
		pst.setString(1, c.getNombre());
		pst.setString(2, c.getIdentificador());
		pst.setInt(3, c.getHoras());
		pst.setInt(4, c.getProfesor().getId());

		pst.executeUpdate();

		

	} catch (Exception e) {
		
		// este lanzariía el mensaje del catch interno (Erro, ya existe...)
		throw new Exception(e.getMessage());
	}

	return c;
	
}


@Override
public void BorrarCurso(Curso c) throws Exception {
	
	
	try(
		Connection con = ConnectionManager.getConnection();
		PreparedStatement pst=con.prepareStatement(SQL_DELETE_CURSO);
	){
		pst.setInt(1, c.getId());
		pst.executeUpdate();
		
	}catch (Exception e) {

		throw new Exception(e.getMessage());
	}
	
	
}

}
