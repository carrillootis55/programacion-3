package models;

public class Student{
	
	//Atributos
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento; 
    private char sexo;
    private String grupo;
    private String anio;
    private String contactoEmergencia;
    private String numeroEmergencia;
    private String parentesco;
    private String domicilio; 
    
    //Constructores
    public Student(){
    	
    }

    public Student(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento,char sexo, String anio,String grupo,String contactoEmergencia, 
    		String numeroEmergencia, String parentesco, String domicilio){

        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.anio = anio;
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
	public String getFechaNacimiento() {
	    return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
	    this.fechaNacimiento = fechaNacimiento;
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
	
	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
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
	
	//=================================================================================================================================================================

	@Override
    public String toString() {
        return "Matrícula: " + matricula +
               "\nNombre: " + nombre +
               "\nApellido Paterno: " + apellidoPaterno +
               "\nApellido Materno: " + apellidoMaterno +
               "\nFecha Nacimiento: " + fechaNacimiento +
               "\nSexo: " + sexo +
               "\nAño: " + anio +
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
               fechaNacimiento + "," +
               sexo + "," +
               anio + "," +
               grupo + "," +
               contactoEmergencia + "," +
               numeroEmergencia + "," +
               parentesco + "," +
               domicilio;
    }
    
    public static Student fromCsv(String linea) {
        String[] data = linea.split(",");

        return new Student(
            data[0],
            data[1],
            data[2],
            data[3],
            data[5],
            data[4].charAt(0),
            data[6], 
            data[7], 
            data[8],
            data[9],
            data[10],
            data[11]
        );
    }
    
    
}