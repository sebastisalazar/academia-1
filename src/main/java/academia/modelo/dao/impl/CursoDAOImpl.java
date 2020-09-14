package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Profesor;

public class CursoDAOImpl implements CursoDAO {
	
	
	private final static String SQL_LISTAR = "SELECT \n" + 
												"	c.id as 'curso_id',\n" + 
												"	c.identificador,\n" + 
												"	c.nombre as 'curso_nombre',\n" + 
												"	c.horas,\n" + 
												"	f.id as 'profesor_id',\n" + 
												"	f.nombre as 'profesor_nombre',\n" + 
												"	f.apellidos as 'profesor_apellidos'\n" + 
												"\n" + 
												"FROM cursos c, profesores f\n" + 
												"WHERE\n" + 
												"	c.id_profesor = f.id;";
	

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
				
				Profesor p = new Profesor();
				p.setId(rs.getInt("profesor_id"));
				p.setNombre(rs.getString("profesor_nombre"));
				//TODO apellidos
				
				c.setProfesor(p);
				
				cursos.add(c);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursos;
	}

}
