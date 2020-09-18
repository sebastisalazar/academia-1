package academia.modelo.pojo;

public class Usuario {
	
	public static final int ROL_ALUMNO = 1;
	public static final int ROL_PROFESOR = 2;
	
	private int id;
	private String nombre;
	private String apellidos;
	private int rol;
	private String password;
	
	public Usuario() {
		super();
		this.id = 0;
		this.nombre = "";
		this.apellidos = "";
		this.rol = 0;
		this.password = "";
	}

	public Usuario(int id) {
		this();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", rol=" + rol + ", password="
				+ password + "]";
	}

	
}
