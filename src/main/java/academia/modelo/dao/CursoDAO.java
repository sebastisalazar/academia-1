package academia.modelo.dao;

import java.util.ArrayList;

import academia.modelo.pojo.Curso;


public interface CursoDAO {
	
	ArrayList<Curso> listar();
	
	ArrayList<Curso> listarPorProfesor(int id);
	
	Curso CrearCurso(Curso c) throws Exception;
	
	void BorrarCurso(Curso c)throws Exception;
	
	void AltaAlumnoCurso(int id_curso, int id_alumno) throws Exception;

}
