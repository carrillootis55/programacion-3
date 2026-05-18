package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Alumno;
import repository.AlumnoRepository;
import repository.CalificacionRepository;
import views.CalificarAlumnoView;

public class CalificacionesController {

	private CalificarAlumnoView view;
	private Alumno alumno;
	private int index;
	
    private List<String> materias;


    private CalificacionRepository repo;

    public CalificacionesController(CalificarAlumnoView view, Alumno alumno,int index,List<String> materias) {
        this.view = view;
        this.alumno = alumno;
        this.index = index;
        this.materias = materias;
        this.repo = new CalificacionRepository();

        iniciar();
    }

    private void iniciar() {
    	configurarValidaciones();
        view.btnGuardar.addActionListener(
                e -> guardar()
        );
    }
    
    private void configurarValidaciones() {

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

            List<JTextField> campos = view.getCamposCalificaciones();

            for (JTextField campo : campos) {

                String texto = campo.getText().trim();
                if (texto.isEmpty()) {

                    JOptionPane.showMessageDialog( view,"Todas las calificaciones son obligatorias");

                    return;
                }

                double calificacion = Double.parseDouble(texto);
               

                if (calificacion < 0 ||calificacion > 10) {

                    JOptionPane.showMessageDialog( view, "La calificación debe estar entre 0 y 10" );

                    return;
                }
            }

            for (int i = 0; i < materias.size(); i++) {

                String nombreMateria = materias.get(i);

                JTextField campo = campos.get(i);

                double calificacion =Double.parseDouble( campo.getText());

                int materiaId = repo.getMateriaId(nombreMateria, alumno.getAnio());

                boolean existe = repo.existeCalificacion( alumno.getMatricula(),materiaId );

                if (existe) {
                    repo.actualizarCalificacion(alumno.getMatricula(),materiaId, calificacion );

                } else {
                    repo.guardarCalificacion( alumno.getMatricula(), materiaId, calificacion  );
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

    
	
}
