package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Student;

public class StudentsTableModels extends AbstractTableModel {
	
	private List<Student> students;

	private final String[] columnas = {
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
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Student a = students.get(rowIndex);

        switch (columnIndex) {
        case 0: return a.getMatricula();
        case 1: return a.getNombre();
        case 2: return a.getApellidoPaterno();
        case 3: return a.getApellidoMaterno();
        case 4: return a.getFechaNacimiento(); 
        case 5: return a.getSexo();            
        case 6: return a.getAnio();
        case 7: return a.getGrupo();
        case 8: return a.getContactoEmergencia();
        case 9: return a.getNumeroEmergencia();
        case 10: return a.getParentesco();
        case 11: return a.getDomicilio();
        }

        return null;
    }
    public Student getAlumnoAt(int row) {
    	return students.get(row);
    }

}
