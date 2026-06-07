package models;

public class Student{
	
	//Atributos
    private String enrollment;
    private String name;
    private String fatherLastName;
    private String motherLastName;
    private String birthDate; 
    private char gender;
    private String group;
    private String year;
    private String emergencyContact;
    private String emergencyNumber;
    private String relationship;
    private String address; 
    
    //Constructores
    public Student(){
    	
    }

    public Student(
    		String enrollment,
    		String name,
    		String fatherLastName,
    		String motherLastName,
    		String birthDate,
    		char gender,
    		String year,
    		String group,
    		String emergencyContact, 
    		String emergencyNumber,
    		String relationship,
    		String address
    ){

        this.enrollment = enrollment;
        this.name = name;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.year = year;
        this.group = group;
        this.emergencyContact = emergencyContact;
        this.emergencyNumber = emergencyNumber;
        this.relationship = relationship;
        this.address = address;
    }
    
    //Getters y Setters
    public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	
	public String getBirthDate() {
	    return birthDate;
	}

	public void setBirthDate(String birthDate) {
	    this.birthDate = birthDate;
	}
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyNumber() {
		return emergencyNumber;
	}

	public void setEmergencyNumber(String emergencyNumber) {
		this.emergencyNumber = emergencyNumber;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	//=================================================================================================================================================================

	@Override
    public String toString() {
        return "Matrícula: " + enrollment +
               "\nNombre: " + name +
               "\nApellido Paterno: " + fatherLastName +
               "\nApellido Materno: " + motherLastName +
               "\nFecha Nacimiento: " + birthDate +
               "\nSexo: " + gender +
               "\nAño: " + year +
               "\nGrupo: " + group +
               "\nContacto Emergencia: " + emergencyContact +
               "\nNúmero Emergencia: " + emergencyNumber +
               "\nParentesco: " + relationship +
               "\nDomicilio: " + address;
    }
    
	//Se convierte a formato CSV separado por comas
    public String toCsv() {
        return enrollment + "," +
               name + "," +
               fatherLastName + "," +
               motherLastName + "," +
               birthDate + "," +
               gender + "," +
               year + "," +
               group + "," +
               emergencyContact + "," +
               emergencyNumber + "," +
               relationship + "," +
               address;
    }
    
    public static Student fromCsv(String line) {
        String[] data = line.split(",");

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