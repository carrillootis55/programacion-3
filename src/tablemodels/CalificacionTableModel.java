package tablemodels;

import javax.swing.table.AbstractTableModel;

import models.Alumno;

import java.util.List;

public class CalificacionTableModel extends AbstractTableModel {

	private final String[] columnas = {
			"ID", "Nombre", "Artes", "Geografia", "Informatica"
	};
	
    private List<Alumno> alumnos;
	
	public CalificacionTableModel(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return alumnos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	
	@Override
    public Object getValueAt(int row, int col) {
        Alumno a = alumnos.get(row);
        switch (col) {
            case 0: return row + 1; //NUMERO DE FILA
            case 1: return a.getNombre();
            case 2: return a.getArtes();
            case 3: return a.getGeografia();
            case 4: return a.getInformatica();
            default: return null;
        }
    }

	
}
