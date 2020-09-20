package academia.modelo.pojo;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class Curso {
	
	private int id;
	//VALIDACIONES
	@NotBlank ( message = ": Escribe el nombre del curso")
	@Size( min = 3, max = 100, message = ": La longtitud de ser entre 3 y 100 caracteres")
	private String nombre;
	
	@NotBlank ( message = ": Escribe el identificador")
	@Size( min = 3, max = 100, message = ": La longtitud de ser entre 3 y 100 caracteres")
	private String identificador;
		
	
	@Min(value = 50, message = ": El minimo de horas para un curso es de 50 ")
    @Max(value = 650, message = ": El maximo de horas para un curso es de 650")
	private int horas;
		
	private Usuario profesor;
	// TODO
    // private ArrayList<Alumno> alumno

	
	
	public Curso() {
		super();
		this.id = 0;
		this.nombre = "";
		this.identificador = "";
		this.horas = 0;
		this.profesor = new Usuario();
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



	public String getIdentificador() {
		return identificador;
	}



	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}



	public int getHoras() {
		return horas;
	}



	public void setHoras(int horas) {
		this.horas = horas;
	}



	public Usuario getProfesor() {
		return profesor;
	}



	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}



	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", identificador=" + identificador + ", horas=" + horas
				+ ", profesor=" + profesor + "]";
	}
	
	
	
	
	
	
	
}
