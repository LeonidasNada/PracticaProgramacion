package principal;

import java.util.Scanner;
import com.db4o.*;
import clases.Usuario;
import clases.Incidencia;

public class Programa {

	public static void main(String[] args) {

		try {
			// Creamos la BDOO
			ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "incidencias.db4o");

			 // Creamos 2 Usuarios
			Usuario u1 = new Usuario("Berto Romero", "45185170Z");
			u1.setTipo("basic"); 
			Usuario u2 = new Usuario("Pedro Sanchez", "45185170L");
			u2.setTipo("tech"); 
			bd.store(u1); 
			bd.store(u2);
			System.out.println("El usuario " + u1.getNombre() +	" se ha almacenado correctamente."); System.out.println("El usuario " +
			u2.getNombre() + " se ha almacenado correctamente.");
			 

			verMenuPrincipal(bd);
		} catch (Exception e) {

		}
	}

	// Método para comprobar si el dni existe
	public static void consultarTipoConDni(ObjectContainer bd, Usuario u) {

		
		if (u.getTipo().equals("basic")) {
			
			verMenuBasic(bd, u);
			
		} else if(u.getTipo().equals("tech")){
			
			verMenuTech(bd, u);

		}
		verMenuPrincipal(bd);
	}

	// Método para devolver la cantidad de incidencias
	public static void mostrarResultado(ObjectSet res) {
		System.out.println(" " + res.size() + " Incidencias:");
		System.out.println("");
		while (res.hasNext()) {
			System.out.println(res.next());
		}

		System.out.println("");
	}
	
	//Método para ver el menu principal 

	private static void verMenuPrincipal(ObjectContainer bd) {

		Scanner sca = new Scanner(System.in);

		System.out.println("=============================================");
		System.out.println("=========== Gestor de incidencias ===========");
		System.out.println("=============================================");
		System.out.println("");
		System.out.print("Introduzca su DNI para continuar: ");
		String dni = sca.nextLine();
		System.out.println("");
		System.out.println("=============================================");

		try {

			// Creamos la consulta para buscar la incidencia por su título
			// Nos retornará el objeto (Object) con ese título
			ObjectSet result = bd.queryByExample(new Usuario(null, dni));

			// Guardamos el resultado como tipo Incidencia
			Usuario u = (Usuario) result.next();

			consultarTipoConDni(bd, u);

		} catch (Exception e) {
			// Mostramos mensaje informativo
			System.out.println("¡No se ha encontrado ningun usuario con ese DNI!");
			System.out.println("");

			// Volvemos al menú principal
			verMenuPrincipal(bd);
		}
		
		
		
		
	}

	// Mostramos el menú Usuario básico
	private static void verMenuBasic(ObjectContainer bd, Usuario u) {

		// Menú básico

		Scanner sca = new Scanner(System.in);

		System.out.println("======= Bienvenido "+ u.getNombre() +"=======");
		System.out.println("=============================================");
		System.out.println("¿Qué desea hacer?");
		System.out.println("");
		System.out.println("1. Enviar incidencia");
		System.out.println("2. Salir del programa");
		System.out.println("");
		System.out.print("Introduzca Opción?: ");
		int opcion = sca.nextInt();
		System.out.println("");
		System.out.println("=============================================");

		// Evitar error de saltar líneas con Scanner
		sca.nextLine();

		// Switches para las opciones

		switch (opcion) {
		case 1:
			// Enviar Incidencia
			enviarIncidencia(bd, u);
			break;
		case 2:
			// Salir del programa
			System.out.println("¡Hasta pronto!");
			bd.close();
			System.exit(0);
			break;
		default:
			System.out.println("¡Debe seleccionar una opción válida!");
			System.out.println("");
			verMenuBasic(bd, u);
			break;
		}

	}
	
	// Menú tech
	private static void verMenuTech(ObjectContainer bd, Usuario u) {
		

		Scanner scan = new Scanner(System.in);
		
		System.out.println("=============================================");
		System.out.println("== Bienvenido "+ u.getNombre() +" (Técnico) ======");
		System.out.println("=============================================");
		System.out.println("¿Qué desea hacer?");
		System.out.println("");
		System.out.println("1. Ver incidencia");
		System.out.println("2. Eliminar incidencia");
		System.out.println("3. Salir del programa");
		System.out.println("");
		System.out.print("Introduzca Opción?: ");
		int opcion = scan.nextInt();
		System.out.println("");
		System.out.println("==============================================");

		// Evitar error de saltar líneas con Scanner
		scan.nextLine();

		// Switches para las opciones
		switch (opcion) {
		case 1:
			// Enviar Incidencia
			verIncidencias(bd, u);
			break;
		case 2:
			// Enviar Incidencia
			eliminarIncidencia(bd, u);
			break;
		case 3:
			// Salir del programa
			System.out.println("¡Hasta pronto!");
			bd.close();
			System.exit(0);
			break;
		default:
			System.out.println("¡Debe seleccionar una opción válida!");
			System.out.println("");
			verMenuTech(bd, u);
			break;
		}
	}

	// Método enviar incidencia
	private static void enviarIncidencia(ObjectContainer bd, Usuario u) {
		Scanner sca = new Scanner(System.in);

		// Variables
		String titulo;
		String descripcion;
		String correo;

		System.out.println("============= Nueva Incidencia ==============");
		System.out.println("=============================================");
		System.out.println("");

		System.out.print("Escribir un título para la incidencia: ");
		titulo = sca.nextLine();
		System.out.println("");

		System.out.print("Escribir la descripción para la incidencia: ");
		descripcion = sca.nextLine();
		System.out.println("");

		System.out.print("Escribir el correo para la incidencia: ");
		correo = sca.nextLine();
		System.out.println("");

		// Se crea
		Incidencia i = new Incidencia(titulo, descripcion, correo);
		// Se guarda
		bd.store(i);
		// Mostramos mensaje informativo
		System.out.println("La Incidencia \"" + i.getTitulo() + "\" se ha añadido correctamente!");
		System.out.println("");
		
		verIncidencias(bd, u);
	}

	// Método ver Incidencia
	private static void verIncidencias(ObjectContainer bd, Usuario u) {

		// Creamos Incidencia
		Incidencia i = new Incidencia(null, null, null);
		// Creamos el objeto OjectSet creando una consulta a la BDOO
		ObjectSet res = bd.queryByExample(i);
		// Lo mostramos
		mostrarResultado(res);
		// Mostramos el menú principal
		consultarTipoConDni(bd, u);
	}

	// Método eliminar incidencia
	private static void eliminarIncidencia(ObjectContainer bd, Usuario u) {

		// Creamos Incidencia
		Incidencia i = new Incidencia(null, null, null);
		// Creamos el objeto OjectSet creando una consulta a la BDOO
		ObjectSet res = bd.queryByExample(i);
		// Mostramos los objetos conseguidos
		mostrarResultado(res);
		Scanner sca = new Scanner(System.in);

		// Pedimos el título de la incidencia al usuario
		System.out.println("=============================================");
		System.out.println("=========== Eliminar Incidencia =============");
		System.out.println("=============================================");
		System.out.println("");

		// Pedimos al usuario tech la Incidencia a eliminar
		System.out.print("Introduzca el título de la Incidencia a eliminar: ");
		String titulo = sca.nextLine();
		System.out.println("");

		// Usamos un try-catch para evitar excepciones si no existe ninguna Incidencia
		// con
		// ese título
		try {

			// Creamos la consulta para buscar la Incidencia por su título
			// Nos retornará el objeto (Object) con ese título
			ObjectSet result = bd.queryByExample(new Incidencia(titulo, null, null));

			// Guardamos el resultado como tipo Incidencia
			Incidencia rec = (Incidencia) result.next();

			// Borramos la Incidencia de la BDOO
			bd.delete(rec);

			// Mostramos mensaje informativo
			System.out.println("La Incidencia \"" + rec.getTitulo() + "\" se ha eliminado correctamente!");
			System.out.println("");

			// Mostramos las Incidencia y vuelve al menú principal
			verIncidencias(bd, u);

		} catch (Exception e) {

			// Mostramos mensaje informativo
			System.out.println("¡No se ha encontrado ninguna Incidencia con ese título!");
			System.out.println("");

			// Volvemos al menú principal
			verMenuTech(bd, u);
		}

		//

	}
}
