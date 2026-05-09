package models;
import java.util.List;

public class Maestro {
	private String nombre;
	private int edad;
	private String maestria;
	private char genero;
	private List<String> materias;
	
	public Maestro() {}

	public Maestro(String nombre, int edad, String maestria, char genero, List<String> materias) {
		this.nombre = nombre;
		this.edad = edad;
		this.maestria = maestria;
		this.genero = genero;
		this.materias = materias;
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

	public List<String> getMaterias() {
		return materias;
	}

	public void setMaterias(List<String> materias) {
		this.materias = materias;
	}

	@Override
	public String toString() {
		return "Descripcion del maestro:/n" + 
				"Nombre:" + nombre + 
				"/nEdad: " + edad + 
				"/nMaestria: " + maestria + 
				"/nGenero: " + genero
				+ "/nMaterias: " + materias;
	}
	
	
}
