package clases;

public class Incidencia {

	//Atributos
	private String titulo;
	private String descripcion;
	private String correo;
	
	
	//Constructor vac�o
	public Incidencia() {
		super();
	}

	
	//Constructor
	public Incidencia(String titulo, String descripcion, String correo) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.correo = correo;
	}


	
	//Getters y Setters
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	//M�todo retornar incidencia
	
	@Override
	public String toString() {
		return "== Informaci�n del Usuario == \nT�tulo: " + titulo + "\nDescripci�n: " + descripcion + "\nCorreo: " + correo + "\n";
	}
	
}
