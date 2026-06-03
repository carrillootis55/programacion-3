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
	
    private List<String> materias;


    private QualificationRepository repo;
    

    public ControllerRatings(StudentsRateView view, Student student,int index,List<String> materias) {
        this.view = view;
        this.student = student;
        this.index = index;
        this.materias = materias;
        this.repo = new QualificationRepository();

        start();
    }

    private void start() {
    	setUpValidation();
       
    	view.btnGuardar.addActionListener(
                e -> guardar()
        );
    }
    
    private void setUpValidation() {

        List<JTextField> campos =
                view.getCamposCalificaciones();

        List<JLabel> errores =
                view.getLabelsError();

        for (int i = 0; i < campos.size(); i++) {

            JTextField campo = campos.get(i);

            JLabel lblError = errores.get(i);

            campo.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {

                    String texto =campo.getText();

                    // vacío
                    if (texto.isEmpty()) {

                        lblError.setText(" ");

                        return;
                    }

                    try {

                        double numero = Double.parseDouble(texto);

                        if (numero < 0 || numero > 10) {

                            lblError.setText(
                                    "Solo numeros de 0-10"
                            );

                        } else {

                            lblError.setText(" ");
                        }

                    } catch (Exception ex) {

                        // letras
                        lblError.setText(
                                "Solo números"
                        );
                    }
                }

                @Override
                public void keyTyped(KeyEvent e) {

                    char c = e.getKeyChar();

                    if (!Character.isDigit(c)&& c != '.'&& c != KeyEvent.VK_BACK_SPACE) {

                        e.consume();
                    }
                }
            });
        }
    }
    
    private void guardar() {

        try {

            List<JTextField> fields = view.getCamposCalificaciones();

            for (JTextField field : fields) {

                String texto = field.getText().trim();
                if (texto.isEmpty()) {

                    JOptionPane.showMessageDialog( view,"Todas las calificaciones son obligatorias");

                    return;
                }

                double qualification = Double.parseDouble(texto);
               

                if (qualification < 0 ||qualification > 10) {

                    JOptionPane.showMessageDialog( view, "La calificación debe estar entre 0 y 10" );

                    return;
                }
            }

            for (int i = 0; i < materias.size(); i++) {

                String subjectName = materias.get(i);

                JTextField field = fields.get(i);

                double qualification =Double.parseDouble( field.getText());

                int idSubject = repo.getMateriaId(subjectName, student.getAnio());

                boolean exists = repo.existeCalificacion( student.getMatricula(),idSubject );

                if (exists) {
                    repo.actualizarCalificacion(student.getMatricula(),idSubject, qualification );

                } else {
                    repo.guardarCalificacion( student.getMatricula(), idSubject, qualification  );
                }
            }

            JOptionPane.showMessageDialog( view, "Calificaciones guardadas");

            view.dispose();

        }
        
        catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(view,"Solo se permiten números");
        }

        catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog( view, "Error al guardar");
        }
    }
	   private void cargarCalificaciones() {

	        try {

	            for (int i = 0; i < materias.size(); i++) {

	                String materia = materias.get(i);

	                Double calificacion =
	                        repo.obtenerCalificacion(
	                                student.getMatricula(),
	                                materia
	                        );

	                if (calificacion != null) {

	                    view.getCamposCalificaciones()
	                            .get(i)
	                            .setText(
	                                    String.valueOf(calificacion)
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