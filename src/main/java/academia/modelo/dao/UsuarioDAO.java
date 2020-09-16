package academia.modelo.dao;

import academia.modelo.pojo.Usuario;

public interface UsuarioDAO {
	
	
	/**
	 * Busca un usuario en la bbdd, por su nombre y password <br>
	 * 
	 *  SELECT * FROM usuarios WHERE nombre = 'ander' AND password = MD5('123456');
	 *	SELECT * FROM usuarios WHERE nombre = 'elier' AND password = MD5('123456');
	 *	SELECT * FROM usuarios WHERE nombre = 'no existe' AND password = MD5('123456');
	 * 
	 * @param nombre
	 * @param password texto plano y en la bbdd esta cifrado en MD5
	 * @return si encuentra retorna Usuario que puede tener el rol de Alumno o Profesor <br>
	 *         Si no lo encuentra retorna null 
	 */
	Usuario buscar( String nombre, String password);

}
