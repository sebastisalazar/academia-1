package academia.modelo.dao;

import java.util.ArrayList;


import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

public interface CursoDAO {
	
	ArrayList<Curso> listar();
	
	ArrayList<Curso> listarPorProfesor(int id);
	
	Curso CrearCurso(Curso c) throws Exception;
	
	void BorrarCurso(Curso c)throws Exception;

}
