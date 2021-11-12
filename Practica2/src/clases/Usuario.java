package clases;



public class Usuario {

	//Atributos 
	private String nombre;
	private String dni;
	private String tipo;
	
	
	
	//Constructor
	public Usuario(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	
	}


	
	//Getters y Setters
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	//M�todo retornar Nombre + DNI
	
	@Override
	public String toString() {
		return "== Informaci�n del Usuario == \nNombre: " + nombre + "\nDNI: " + dni + "\n";
	}

	
	
	
}
