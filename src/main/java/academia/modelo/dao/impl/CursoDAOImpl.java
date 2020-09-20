package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

public class CursoDAOImpl implements CursoDAO {
	
	
	//LOG
		private final static Logger LOG = Logger.getLogger(UsuarioDAOImpl.class);

	//Patrón singleton
		private static CursoDAOImpl INSTANCE=null;
		
		private CursoDAOImpl() {
			super();
		}
		
		
		public static synchronized CursoDAOImpl getInstance() {
			
			if (INSTANCE==null) {
				INSTANCE= new CursoDAOImpl();
			}
			
			return INSTANCE;
		}
		//Fin patron singleton
	
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
	
	private final static String SQL_LISTAR_POR_ALUMNO= "SELECT c.id as curso_id, c.nombre as curso,c.identificador as identificador,c.horas as horas, u.nombre as nombre, u.apellidos " + 
			"FROM cursos c,alumnos_curso a,usuarios u " + 
			"WHERE c.id = a.id_curso " + 
			"AND a.id_alumno= u.id  " + 
			"AND a.id_alumno = ? ";

	private final static String SQL_INSERT_CURSO = "INSERT INTO academia.cursos (nombre, identificador, horas, id_profesor) VALUES(?, ?, ?, ?);";
	
	private final static String SQL_ALTAALUMNOCURSO= "INSERT INTO academia.alumnos_curso (id_alumno, id_curso) VALUES(?, ?);";
	
	private String SQL_DELETE_CURSO="DELETE FROM academia.cursos WHERE id=?;";

	@Override
	public ArrayList<Curso> listar() throws Exception {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
			 ResultSet rs = pst.executeQuery()	
			){
			
			if (!rs.isBeforeFirst()) {
				
				throw new Exception ("No se encontraron cursos en la base de datos");
				
			}else {
				
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
			}
			
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return cursos;
	}
	
	
public ArrayList<Curso> listarPorProfesor(int id) throws Exception {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR_POR_PROFESOR);
			 	
			){
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				
				throw new Exception ("No se encontraron cursos impartidos por ti");
			}else {
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
				
			}
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return cursos;
	}

public ArrayList<Curso> listarPorAlumno(int id) throws Exception {
	
	ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	try( Connection con = ConnectionManager.getConnection() ;
		 PreparedStatement pst = con.prepareStatement(SQL_LISTAR_POR_ALUMNO);
		 	
		){
		
		
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		
		if (!rs.isBeforeFirst()) {
			
			throw new Exception ("No se encontraron cursos matriculados");
			
		}else {
			while ( rs.next() ) {				
				
				
				Curso c = new Curso();
				c.setId(rs.getInt("curso_id"));
				c.setNombre( rs.getString("curso"));
				c.setIdentificador(rs.getString("identificador"));
				c.setHoras(rs.getInt("horas"));
				
				
				//TODO
				Usuario p = new Usuario();
								
				c.setProfesor(p);
				
				cursos.add(c);
				
			}
		}
		
		
	}catch (Exception e) {
		throw new Exception(e.getMessage());
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
		
		

	}catch (Exception DBSQLException) {
		
		// este lanzariía el mensaje del catch interno (Erro, ya existe...)
		throw new Exception("Error, curso no insertado.\n El nombre o identificador del curso podrían ya existir.");
	}

	return c;
	
}

public void AltaAlumnoCurso(int id_alumno,int id_curso) throws Exception {
	
	try (Connection con = ConnectionManager.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_ALTAALUMNOCURSO);

	) {
		// se modifica la insert diciendo que el interrogante lo sustituya con el nombre
		// del objeto
		pst.setInt(1, id_alumno);
		pst.setInt(2, id_curso);

		pst.executeUpdate();

		

	} catch (Exception DBSQLException) {
		
		// este lanzariía el mensaje del catch interno (Erro, ya existe...)
		throw new Exception("Error.\n Ya estás matriculado en el curso seleccionado");
	}


	
}


@Override
public void BorrarCurso(Curso c) throws Exception {
	
	int deletedRow=0;
	try(
		Connection con = ConnectionManager.getConnection();
		PreparedStatement pst=con.prepareStatement(SQL_DELETE_CURSO);
	){
		pst.setInt(1, c.getId());
		
		try {
			deletedRow=pst.executeUpdate();
		} catch (Exception DBSQLException) {
			throw new Exception("Error, no se puede eliminar este curso ya que existen alumnos matriculados en el.");
		}
			if (deletedRow==2) {
				throw new Exception("Error, no se ha podido borrar el curso con "+c.getId()+"Puede deberse a que el curso ya ha sido eliminado o no existe");
			}
		
	
	}catch(Exception e) {
	
		throw new Exception(e.getMessage());
	
	}
	
	
}

}
