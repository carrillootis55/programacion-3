package models;

public class Alumno{
	
	//Atributos
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private char sexo;
    private String grupo;
    private String contactoEmergencia;
    private String numeroEmergencia;
    private String parentesco;
    private String domicilio; 
    
    //Constructores
    public Alumno(){
    	
    }

    public Alumno(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, char sexo, String grupo, String contactoEmergencia, 
    		String numeroEmergencia, String parentesco, String domicilio){

        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.grupo = grupo;
        this.contactoEmergencia = contactoEmergencia;
        this.numeroEmergencia = numeroEmergencia;
        this.parentesco = parentesco;
        this.domicilio = domicilio;
    }
    
    //Getters y Setters
    public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	public String getNumeroEmergencia() {
		return numeroEmergencia;
	}

	public void setNumeroEmergencia(String numeroEmergencia) {
		this.numeroEmergencia = numeroEmergencia;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	@Override
    public String toString() {
        return "Matrícula: " + matricula +
               "\nNombre: " + nombre +
               "\nApellido Paterno: " + apellidoPaterno +
               "\nApellido Materno: " + apellidoMaterno +
               "\nSexo: " + sexo +
               "\nGrupo: " + grupo +
               "\nContacto Emergencia: " + contactoEmergencia +
               "\nNúmero Emergencia: " + numeroEmergencia +
               "\nParentesco: " + parentesco +
               "\nDomicilio: " + domicilio;
    }
    
	//Se convierte a formato CSV separado por comas
    public String toCsv() {
        return matricula + "," +
               nombre + "," +
               apellidoPaterno + "," +
               apellidoMaterno + "," +
               sexo + "," +
               grupo + "," +
               contactoEmergencia + "," +
               numeroEmergencia + "," +
               parentesco + "," +
               domicilio;
    }
    
    public static Alumno fromCsv(String linea) {
        String[] data = linea.split(",");

        return new Alumno(
            data[0],
            data[1],
            data[2],
            data[3],
            data[4].charAt(0),
            data[5],
            data[6],
            data[7],
            data[8],
            data[9]
        );
    }
    
    
    
    
}