package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Teacher;

public class TeacherTableModel extends AbstractTableModel {

    private List<Teacher> teachers;

    private final String[] columns = {
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

        return columns.length;
    }

    @Override
    public String getColumnName(int column) {

        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Teacher teacher = teachers.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return teacher.getId();

            case 1:
                return teacher.getName();

            case 2:
                return teacher.getEmail();

            case 3:
                return teacher.getGender();

            case 4:
                return teacher.getAge();

            case 5:
                return teacher.getMasterDegree();

            case 6:
                return teacher.getYear();

            case 7:
                return teacher.getGroup();

            default:
                return null;
        }
    }

    public Teacher getTeacher(int row) {

        return teachers.get(row);
    }
}