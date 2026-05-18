package tablemodels;

import javax.swing.table.AbstractTableModel;

import models.Alumno;
import repository.CalificacionRepository;

import java.util.List;

public class CalificacionTableModel extends AbstractTableModel {
	
	private List<Alumno> alumnos;
    private List<String> materias;
    
    private CalificacionRepository repo;

    public CalificacionTableModel( List<Alumno> alumnos,List<String> materias ) {
        this.alumnos = alumnos;
        this.materias = materias;
        this.repo = new CalificacionRepository();
    }

    @Override
    public int getRowCount() {
        return alumnos.size();
    }

    @Override
    public int getColumnCount() {

        return 3 + materias.size();
    }

    @Override
    public String getColumnName(int column) {

        if (column == 0) {
            return "ID";
        }

        if (column == 1) {
            return "Nombre";
        }

        if (column == materias.size() + 2) {
            return "Promedio";
        }

        return materias.get(column - 2);
    }

    @Override
    public Object getValueAt(int row, int col) {

        Alumno alumno = alumnos.get(row);

        if (col == 0) {
            return row + 1;
        }


        if (col == 1) {
            return alumno.getNombre();
        }

        if (col == materias.size() + 2) {

            double suma = 0;

            for (String materia : materias) {

                try {

                    Double calificacion =
                            repo.obtenerCalificacion(
                                    alumno.getMatricula(),
                                    materia
                            );

                    if (calificacion != null) {
                        suma += calificacion;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            double promedio = suma / materias.size();

            //Redondear a 1 decimal
            return Math.round(promedio * 10.0) / 10.0;
        }

        String materia = materias.get(col - 2);

        try {

            Double calificacion =
                    repo.obtenerCalificacion(
                            alumno.getMatricula(),
                            materia
                    );

            if (calificacion != null) {
                return calificacion;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col >= 2 && col < materias.size() + 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 0) {
            return Integer.class;
        }

        if (columnIndex == 1) {
            return String.class;
        }

        return Double.class;
    }
    
}