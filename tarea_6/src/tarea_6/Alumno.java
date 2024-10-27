package tarea_6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Alberto Polo
 */

public class Alumno implements Serializable {

	private static final long serialVersionUID = 2180170612359928850L;

	// Scanner declarado como static para no tener que cerrarlo:
	private static Scanner sc = new Scanner(System.in);

	// Atributos privados de la clase Alumno
	private int nia = 0;
	private String nombre;
	private String apellidos;
	private char genero = 'S';
	private Date fechaNacimiento;
	private String ciclo;
	private String curso;
	private String grupo;
	// Añado a la clase el atributo ficheroElegido para almacenar la elección del
	// fichero:
	private File ficheroElegido = null;

	// Constructores de la clase Alumno
	public Alumno() {
	}

	public Alumno(int nia, String nombre, String apellidos, char genero, Date fechaNacimiento, String ciclo,
			String curso, String grupo) {

		this.nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
	}

	// Getters & Setters:
	public int getNia() {
		return nia;
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

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public File getFicheroElegido() {
		return ficheroElegido;
	}

	public void setFicheroElegido(File ficheroElegido) {
		this.ficheroElegido = ficheroElegido;
	}

	@Override
	public String toString() {
		return "NIA: " + nia + " --> " + nombre + ", " + apellidos + " - Género: " + genero + " - Fecha de Nacimiento: "
				+ fechaNacimiento + " - Ciclo: " + ciclo + " - Curso: " + curso + " Grupo: " + grupo;
	}

	/**
	 * @author Alberto Polo
	 * @param Recibe fechaDate como fecha en formato java.util.Date
	 * @return Devuelve la fecha introducida como parámetro transformada en String
	 */
	public String convierteDateEnString(Date fechaDate) {
		// Definimos el formato de fecha:
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		// Convertimos el objeto Date a String:
		String fechaString = formatoFecha.format(fechaDate);

		return fechaString;
	}

	/**
	 * @author Alberto Polo
	 * @param Recibe fechaString como fecha en formato String
	 * @return Devuelve la fecha introducida como parámetro transformada en
	 *         java.util.Date
	 */
	public Date convierteStringEnDate(String fechaString) {
		// Definir formato de fecha según el String:
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaDate = new Date();
		try {
			// Convertir el String en Date;
			fechaDate = formato.parse(fechaString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaDate;
	}

	/**
	 * @author Alberto Polo Este método genera el menú que accede a todas las
	 *         funcionalidades de la tarea 6
	 */
	public void generaMenu() {

		int opcion = -1;

		// do {
		while (opcion != 0) {
			System.out.print("""
					1.- Crea un fichero vacio.
					2.- Selecciona  un fichero.
					3.- Añadir alumno.
					4.- Mostrar todos los alumnos.
					0.- SALIR
					------------------------------
					Por  favor, elija una opción: 	""");
			opcion = sc.nextInt();
			switch (opcion) {
			// OPCIÓN 1.- Crea un fichero vacio.
			case 1 -> {
				System.out.print("""
						------------------------------------------------
						Ha elegido la opción: 1.- Crea un fichero vacio.
						------------------------------------------------
						""");
				generaFicheroVacio();
			}
			// OPCIÓN 2.- Selecciona un fichero.
			case 2 -> {

				System.out.print("""
						-------------------------------------------------
						Ha elegido la opción: 2.- Selecciona  un fichero.
						-------------------------------------------------
						""");
				System.out.println("Los ficheros existentes en el directorio son:");
				System.out.println("---------------------------------------------");
				File directorio = new File(".");
				if (directorio.isDirectory()) {
					// Mostramos un listado de los ficheros existentes en el directorio actual:
					String[] ficheros = directorio.list();
					for (String fichero : ficheros) {
						if (fichero.charAt(0) != '.' && fichero.contains(".")) {
							System.out.println(fichero);
						}
					}
					System.out.println("---------------------------------------------");
					if (ficheroElegido != null) {
						System.out.println("El fichero actualmente seleccionado es: " + ficheroElegido.getName());
					}
					System.out.println("---------------------------------------------------------");
					System.out.print("¿Qué fichero existente desea seleccionar ahora?: ");

					sc.nextLine();
					String eleccion = sc.nextLine();

					for (String fichero : ficheros) {
						if (fichero.equals(eleccion)) {
							File nuevoFicheroElegido = new File(eleccion);
							ficheroElegido = nuevoFicheroElegido;
						}
					}
					System.out.println("---------------------------------------------------------");
					System.out.println("El fichero actualmente seleccionado es: " + ficheroElegido.getName());
					System.out.println("---------------------------------------------------------");
				}
			}
			// OPCIÓN 3.- Añadir alumno.
			case 3 -> {
				System.out.print("""
						----------------------------------------
						Ha elegido la opción: 3.- Añadir alumno.
						----------------------------------------
						""");
				if (ficheroElegido != null) {
					aniadeAlumno();
				} else {
					System.out.println("----------------------------------------------------------");
					System.out.println("""
							No ha seleccionado ningún fichero.
							Por favor, seleccione un fichero con la opción 2 del menú.""");
					System.out.println("----------------------------------------------------------");
				}
			}
			// OPCIÓN 4.- Mostrar todos los alumnos.
			case 4 -> {
				System.out.println("""
						----------------------------------------------------
						Ha elegido la opción: 4.- Mostrar todos los alumnos.
						----------------------------------------------------
						""");

				if (ficheroElegido != null && ficheroElegido.exists()) {
					System.out.println("---------------------------------------------------------");
					System.out.println("El fichero actualmente seleccionado es: " + ficheroElegido.getName());
					System.out.println("---------------------------------------------------------");
					leeFichero();
				} else {
					System.out.println("""
							No ha seleccionado ningún fichero.
							Por favor, seleccione un fichero con la opción 2 del menú.""");
					System.out.println("----------------------------------------------------------");
				}
			}
			// OPCIÓN 0.- SALIR
			case 0 -> System.out.println("""
					-----------------------------------
					Ha elegido SALIR: Fin del programa.
					-----------------------------------
					""");

			default -> System.out.print("""
					--------------------------------------------------------------------------
					La opción elegida no es correcta, por favor, elija una opción entre 0 y 4.
					--------------------------------------------------------------------------
					""");
			}
			// } while (opcion != 0);
		}
	}

	/**
	 * @author Alberto Polo Este método se ocupa de generar un fichero de texto
	 *         vacío y, simultáneamente, dejarlo como fichero predeterminado si el
	 *         usuario no establece una nueva elección.
	 */
	public void generaFicheroVacio() {
		System.out.print("Introduzca el nombre y la extensión del fichero vacío a crear:");

		// Este sc.nextline() es necesario para limpiar el buffer !!!
		sc.nextLine();

		String nombreFicheroVacio = sc.nextLine();
		File fichero = new File(".\\" + nombreFicheroVacio);

		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
				setFicheroElegido(fichero);
			} else {
				System.out.print("""
						--------------------------------------------
						El fichero ya existe,
						pero queda seleccionado como predeterminado.
						--------------------------------------------
						""");
				ficheroElegido = fichero;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Alberto Polo Método que se ocupa de pedir los datos de un alumno
	 *         nuevo al usuario para, posteriormente, añadirlo al fichero de texto
	 *         previamente seleccionado.
	 */
	public void aniadeAlumno() {

		if (ficheroElegido.exists() && ficheroElegido != null) {

			BufferedWriter bw = null;

			try {

				// Abrimos archivo en modo de escritura (sobreescribe el archivo si existe)
				bw = new BufferedWriter(new FileWriter(ficheroElegido, true));

				sc.nextLine();

				// NIA - int
				// Vamos añadiendo el nia secuencialmente incrementándolo en 1 unidad:
				nia = nia + 1;
				bw.write(nia);
				bw.newLine();

				// NOMBRE - String
				System.out.print("Introduzca el NOMBRE del alumno: ");
				nombre = sc.nextLine().toUpperCase();
				bw.write(nombre);
				bw.newLine();

				// APELLIDOS - String
				System.out.print("Introduzca los APELLIDOS del alumno: ");
				apellidos = sc.nextLine().toUpperCase();
				bw.write(apellidos);
				bw.newLine();

				// GÉNERO - char

				do {
					System.out.print("Introduzca el GÉNERO del alumno(H/M): ");
					String entradaTeclado = sc.nextLine().toUpperCase();

					if (entradaTeclado.length() > 0) {
						genero = entradaTeclado.charAt(0);
						bw.write(genero);
						bw.newLine();
					} else {
						genero = ' ';
					}

				} while (genero != 'H' && genero != 'M');

				// FECHA DE NACIMIENTO - Date
				System.out.print("Introduzca la FECHA DE NACIMIENTO del alumno en formato (dd/MM/yyyy): ");
				String fechaNacimientoString = sc.nextLine();
				fechaNacimiento = convierteStringEnDate(fechaNacimientoString);
				bw.write(fechaNacimientoString);
				bw.newLine();

				// CICLO - String
				System.out.print("Introduzca el CICLO del alumno: ");
				ciclo = sc.nextLine().toUpperCase();
				bw.write(ciclo);
				bw.newLine();

				// CURSO - String
				System.out.print("Introduzca el CURSO del alumno: ");
				curso = sc.nextLine().toUpperCase();
				bw.write(curso);
				bw.newLine();

				// GRUPO - String
				System.out.print("Introduzca el GRUPO del alumno: ");
				grupo = sc.nextLine().toUpperCase();
				bw.write(grupo);
				bw.newLine();
				System.out.println("----------------------------------------------------------------------");

			} catch (IOException e) {
				e.printStackTrace();

			} finally {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("El fichero: " + ficheroElegido.getName() + " no existe");
		}
	}

	/**
	 * @author Alberto Polo Método que se ocupa de recorrer el fichero de texto
	 *         seleccionado previamente y mostrar todo su contenido por pantalla
	 *         sirviéndose de la clase BufferedReader
	 */
	public void leeFichero() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(ficheroElegido));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			System.out.println("---------------------------------------------------------");
			System.out.println("Fin del fichero actualmente seleccionado: " + ficheroElegido.getName());
			System.out.println("---------------------------------------------------------");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}