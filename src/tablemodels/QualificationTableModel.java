package tablemodels;

import javax.swing.table.AbstractTableModel;

import models.Student;
import repository.QualificationRepository;

import java.util.List;

public class QualificationTableModel extends AbstractTableModel {
	
	private List<Student> students;
    private List<String> subjects;
    
    private QualificationRepository repository;

    public QualificationTableModel(List<Student> students, List<String> subjects) {
        this.students = students;
        this.subjects = subjects;
        this.repository = new QualificationRepository();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {

        return 3 + subjects.size();
    }

    @Override
    public String getColumnName(int column) {

        if (column == 0) {
            return "ID";
        }

        if (column == 1) {
            return "Nombre";
        }

        if (column == subjects.size() + 2) {
            return "Promedio";
        }

        return subjects.get(column - 2);
    }

    @Override
    public Object getValueAt(int row, int col) {

        Student student = students.get(row);

        if (col == 0) {
            return row + 1;
        }

        if (col == 1) {
            return student.getName();
        }

        if (col == subjects.size() + 2) {

            double total = 0;

            for (String subject : subjects) {

                try {

                    Double qualification =
                            repository.getQualification(
                                    student.getEnrollment(),
                                    subject
                            );

                    if (qualification != null) {
                        total += qualification;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            double average = total / subjects.size();

            //Redondear a 1 decimal
            return Math.round(average * 10.0) / 10.0;
        }

        String subject = subjects.get(col - 2);

        try {

            Double qualification =
                    repository.getQualification(
                            student.getEnrollment(),
                            subject
                    );

            if (qualification != null) {
                return qualification;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col >= 2 && col < subjects.size() + 2;
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