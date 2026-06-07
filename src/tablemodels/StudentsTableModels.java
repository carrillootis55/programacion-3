package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Student;

public class StudentsTableModels extends AbstractTableModel {
	
	private List<Student> students;

	private final String[] columns = {
	    "Matrícula",
	    "Nombre",
	    "Apellido Paterno",
	    "Apellido Materno", 
	    "Fecha Nacimiento",
	    "Sexo",
	    "Año",
	    "Grupo",
	    "Contacto",
	    "Número",
	    "Parentesco",
	    "Domicilio"
	};

    public StudentsTableModels(List<Student> students) {
        this.students = students;
    }
    
    
    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Student student = students.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return student.getEnrollment();

            case 1:
                return student.getName();

            case 2:
                return student.getFatherLastName();

            case 3:
                return student.getMotherLastName();

            case 4:
                return student.getBirthDate();

            case 5:
                return student.getGender();

            case 6:
                return student.getYear();

            case 7:
                return student.getGroup();

            case 8:
                return student.getEmergencyContact();

            case 9:
                return student.getEmergencyNumber();

            case 10:
                return student.getRelationship();

            case 11:
                return student.getAddress();
        }

        return null;
    }

    public Student getStudentAt(int row) {
    	return students.get(row);
    }

}