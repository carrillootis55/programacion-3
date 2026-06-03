package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Teacher;

public class TeacherTableModel extends AbstractTableModel {

    private List<Teacher> teachers;

    private final String[] columnas = {
        "ID",
        "Nombre",
        "Email",
        "Sexo",
        "Edad",
        "Maestría",
        "Año",
        "Grupo"
    };

    public TeacherTableModel(List<Teacher> teachers) {

        this.teachers = teachers;
    }

    @Override
    public int getRowCount() {

        return teachers.size();
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

        Teacher teacher = teachers.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return teacher.getId();

            case 1:
                return teacher.getNombre();

            case 2:
                return teacher.getEmail();

            case 3:
                return teacher.getSexo();

            case 4:
                return teacher.getEdad();

            case 5:
                return teacher.getMaestria();

            case 6:
                return teacher.getAnio();

            case 7:
                return teacher.getGrupo();

            default:
                return null;
        }
    }

    public Teacher getMaestro(int row) {

        return teachers.get(row);
    }
}