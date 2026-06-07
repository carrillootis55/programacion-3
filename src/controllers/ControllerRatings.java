package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Student;
import repository.QualificationRepository;
import views.StudentsRateView;
import java.awt.Desktop;
import java.io.File;
import services.PDFExporter;

public class ControllerRatings {

	private StudentsRateView view;
	private Student student;
	private int index;
	
    private List<String> subjects;

    private QualificationRepository repository;
    

    public ControllerRatings(
    		StudentsRateView view,
    		Student student,
    		int index,
    		List<String> subjects) {

        this.view = view;
        this.student = student;
        this.index = index;
        this.subjects = subjects;

        this.repository = new QualificationRepository();

        start();
    }

    private void start() {

    	setUpValidation();
       
    	view.getBtnSave().addActionListener(
                e -> save()
        );
    }
    
    private void setUpValidation() {

        List<JTextField> fields =
                view.getGradeFields();

        List<JLabel> errorLabels =
                view.getErrorLabels();

        for (int i = 0; i < fields.size(); i++) {

            JTextField field = fields.get(i);

            JLabel errorLabel = errorLabels.get(i);

            field.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {

                    String text = field.getText();

                    // vacío
                    if (text.isEmpty()) {

                        errorLabel.setText(" ");

                        return;
                    }

                    try {

                        double number =
                                Double.parseDouble(text);

                        if (number < 0 || number > 10) {

                            errorLabel.setText(
                                    "Solo numeros de 0-10"
                            );

                        } else {

                            errorLabel.setText(" ");
                        }

                    } catch (Exception ex) {

                        // letras
                        errorLabel.setText(
                                "Solo números"
                        );
                    }
                }

                @Override
                public void keyTyped(KeyEvent e) {

                    char character = e.getKeyChar();

                    if (!Character.isDigit(character)
                    		&& character != '.'
                    		&& character != KeyEvent.VK_BACK_SPACE) {

                        e.consume();
                    }
                }
            });
        }
    }
    
    private void save() {

        try {

            List<JTextField> fields =
            		view.getGradeFields();

            for (JTextField field : fields) {

                String text = field.getText().trim();

                if (text.isEmpty()) {

                    JOptionPane.showMessageDialog(
                    		view,
                    		"Todas las calificaciones son obligatorias"
                    );

                    return;
                }

                double qualification =
                		Double.parseDouble(text);

                if (qualification < 0
                		|| qualification > 10) {

                    JOptionPane.showMessageDialog(
                    		view,
                    		"La calificación debe estar entre 0 y 10"
                    );

                    return;
                }
            }

            for (int i = 0; i < subjects.size(); i++) {

                String subjectName = subjects.get(i);

                JTextField field = fields.get(i);

                double qualification =
                		Double.parseDouble(field.getText());

                int subjectId =
                		repository.getSubjectId(
                				subjectName,
                				student.getYear()
                		);

                boolean exists =
                		repository.qualificationExists(
                				student.getEnrollment(),
                				subjectId
                		);

                if (exists) {

                    repository.updateQualification(
                    		student.getEnrollment(),
                    		subjectId,
                    		qualification
                    );

                } else {

                    repository.saveQualification(
                    		student.getEnrollment(),
                    		subjectId,
                    		qualification
                    );
                }
            }

            JOptionPane.showMessageDialog(
            		view,
            		"Calificaciones guardadas"
            );

            view.dispose();
        }
        
        catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
            		view,
            		"Solo se permiten números"
            );
        }

        catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
            		view,
            		"Error al guardar"
            );
        }
    }

	private void loadQualifications() {

        try {

            for (int i = 0; i < subjects.size(); i++) {

                String subject = subjects.get(i);

                Double qualification =
                        repository.getQualification(
                                student.getEnrollment(),
                                subject
                        );

                if (qualification != null) {

                    view.getGradeFields()
                            .get(i)
                            .setText(
                                    String.valueOf(qualification)
                            );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al cargar calificaciones"
            );
        }
	}
}
