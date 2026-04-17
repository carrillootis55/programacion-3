package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Alumno;

public class AlumnoTableModels extends AbstractTableModel {
	
	private List<Alumno> alumnos;

    private final String[] columnas = {
        "Matrícula",
        "Nombre",
        "Apellido Paterno",
        "Apellido Materno",
        "Sexo",
        "Grupo",
        "Contacto",
        "Número",
        "Parentesco",
        "Domicilio"
    };

    public AlumnoTableModels(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public int getRowCount() {
        return alumnos.size();
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

        Alumno a = alumnos.get(rowIndex);

        switch (columnIndex) {
            case 0: 
            	return a.getMatricula();
            case 1: 
            	return a.getNombre();
            case 2: 
            	return a.getApellidoPaterno();
            case 3: 
            	return a.getApellidoMaterno();
            case 4: 
            	return a.getSexo();
            case 5: 
            	return a.getGrupo();
            case 6: 
            	return a.getContactoEmergencia();
            case 7: 
            	return a.getNumeroEmergencia();
            case 8: 
            	return a.getParentesco();
            case 9: 
            	return a.getDomicilio();
        }

        return null;
    }

}
