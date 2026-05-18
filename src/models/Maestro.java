package models;
import java.util.List;

public class Maestro {
	
	private int id;
	private String password;
	private String email;
	private String nombre;
	private int edad;
	private String maestria;
	private char genero;
	//private List<String> materias;
	private String anio;
	private String grupo;
	private List<String> materias;
	private String role;
	
	public Maestro() {}

	public Maestro(String nombre, int edad, String maestria, char genero) {
	    this.nombre = nombre;
	    this.edad = edad;
	    this.maestria = maestria;
	    this.genero = genero;
	}
	
	public Maestro(int id, String nombre, String email,String password, String role) {
		 this.id = id;
		 this.nombre = nombre;
		 this.email = email;
		 this.password = password;
		 this.role = role;
		}

	public Maestro(String nombre, String email, String password,int edad, String maestria, char genero, String anio, String grupo) {
		 this.nombre = nombre;
		 this.email = email;
		 this.password = password;
		 this.edad = edad;
		 this.maestria = maestria;
		 this.genero = genero;
		 this.anio = anio;
		 this.grupo = grupo;
		}
	public Maestro(int id, String nombre, String email, String password) {
		 this.id = id;
		 this.nombre = nombre;
		 this.email = email;
		 this.password = password;
		}
	
	public Maestro(String nombre, String email, String password, int edad, String maestria, char genero, List<String> materias, String role) {
		 this.nombre = nombre;
		 this.email = email;
		 this.password = password;
		 this.edad = edad;
		 this.maestria = maestria;
		 this.genero = genero;
		 this.materias = materias;
		 this.role = role;
		}
	//Getters y Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getMaestria() {
		return maestria;
	}

	public void setMaestria(String maestria) {
		this.maestria = maestria;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	
	public String getAnio() {
	    return anio;
	}

	public void setAnio(String anio) {
	    this.anio = anio;
	}

	public String getGrupo() {
	    return grupo;
	}

	public void setGrupo(String grupo) {
	    this.grupo = grupo;
	}
	
	public String getRole() {
	    return role;
	}

	public void setRole(String role) {
	    this.role = role;
	}

	@Override
	public String toString() {
		return "Descripcion del maestro:/n" + 
				"Nombre:" + nombre + 
				"/nEdad: " + edad + 
				"/nMaestria: " + maestria + 
				"/nGenero: " + genero +
				"\nAño: " + anio +
	            "\nGrupo: " + grupo;
	}
	
	
}
