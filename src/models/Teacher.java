package models;
import java.util.List;

public class Teacher {
	
	private int id;
	private String password;
	private String email;
	private String name;
	private String gender;
	private int age;
	private String masterDegree;
	private String year;
	private String group;
	private List<String> subjects;
	private String role;
	private String birthDate;
	
	public Teacher() {
		
	}

	public Teacher(String name, int age, String masterDegree) {
	    this.name = name;
	    this.age = age;
	    this.masterDegree = masterDegree;
	}
	
	public Teacher(int id, String name, String email,String password, String role) {
		 this.id = id;
		 this.name = name;
		 this.email = email;
		 this.password = password;
		 this.role = role;
	}

	public Teacher(String name, String birthDate, String email, String password, String gender, int age, String masterDegree, String year, String group) {
		 this.name = name;
		 this.birthDate = birthDate;
		 this.email = email;
		 this.password = password;
		 this.gender = gender;
		 this.age = age;
		 this.masterDegree = masterDegree;
		 this.year = year;
		 this.group = group;
	}	
	
	public Teacher(int id, String name, String email, String password) {
		 this.id = id;
		 this.name = name;
		 this.email = email;
		 this.password = password;
	}
	
	public Teacher(String name, String email, String password,String gender, int age, String masterDegree, List<String> subjects, String role) {
		 this.name = name;
		 this.email = email;
		 this.password = password;
		 this.gender = gender;
		 this.age = age;
		 this.masterDegree = masterDegree;
		 this.subjects = subjects;
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
	
	public String getName() {
		return name;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMasterDegree() {
		return masterDegree;
	}

	public void setMasterDegree(String masterDegree) {
		this.masterDegree = masterDegree;
	}
	
	public String getYear() {
	    return year;
	}

	public void setYear(String year) {
	    this.year = year;
	}

	public String getGroup() {
	    return group;
	}

	public void setGroup(String group) {
	    this.group = group;
	}
	
	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}
	
	public String getRole() {
	    return role;
	}

	public void setRole(String role) {
	    this.role = role;
	}

	@Override
	public String toString() {
		return "Descripcion del maestro:\n" + 
				"Nombre: " + name + "\n" +
				"Fecha de nacimiento: " + birthDate + "\n" +
				"Sexo: " + gender + "\n" +
				"Edad: " + age + "\n" +
				"Maestria: " + masterDegree + "\n" +
	            "Año: " + year + "\n" +
	            "Grupo: " + group;
	}
}