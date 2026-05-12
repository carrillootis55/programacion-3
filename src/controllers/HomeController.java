package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Maestro;
import models.Alumno;
import repository.AlumnoRepository;
import services.PDFExporter;
import tablemodels.AlumnoTableModels;
import tablemodels.CalificacionTableModel;
import views.Formulario;
import views.LoginWindow;
import views.MainView;
import config.Config;

public class HomeController {

    private MainView view;

    public HomeController(MainView view) {
        this.view = view;
        registerListeners();
    }

    private void registerListeners() {

        //Salir
        view.salir.addActionListener(e -> handleClose());
       
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClose();
            }
        });

        //Mostrar Alumnos
        view.mItemListaAlumnos.addActionListener(e -> mostrarAlumnos());
        view.mItemPerfil.addActionListener(e -> mostrarMaestro());

        //Inicio
        view.inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                view.showView(MainView.HOME);
            }
        });
        view.mItemCalificaciones.addActionListener(e -> mostrarCalificaciones());
        
        view.calificacionesPanel.getBtnPdf().addActionListener(e -> exportarCalificacionesPdf());
    	}
    
    private void exportarCalificacionesPdf() {

        File file =
                view.calificacionesPanel.selectPdfFile();

        if (file == null)
            return;

        try {
            AlumnoRepository repo =
                    new AlumnoRepository();

            PDFExporter exporter =
                    new PDFExporter();

            exporter.exportCalificaciones(
                    repo.getAlumnos(),
                    file
            );

            JOptionPane.showMessageDialog(
                    view,
                    "PDF exportado correctamente"
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    view,
                    "Error al exportar PDF"
            );
        }
    }
    private void mostrarAlumnos() {

        AlumnoRepository repository = new AlumnoRepository();

        try {
            List<Alumno> alumnos = repository.getAlumnos();

            if (alumnos == null || alumnos.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "No hay alumnos registrados");
                return;
            }

            AlumnoTableModels modelAlumnos =
                    new AlumnoTableModels(alumnos);

            view.alumnosPanel.setTableModel(modelAlumnos);
            new alumnoController(view.alumnosPanel);

            view.showView(MainView.ALUMNOS);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    view,
                    "Error al cargar alumnos: " + e.getMessage()
            );
        }
    }
    
    private void mostrarMaestro() {
    	
    	List<String> materias = new ArrayList<>();
    	materias.add("Geografia");
    	materias.add("Artes");
    	materias.add("Informatica");
    	
    	Maestro maestro = new Maestro("Mta. Eloise Villas", 32, "Maestria en educacion", 'M', materias);
    	view.maestroPanel.updateMaestro(maestro);
    	view.showView(MainView.MAESTRO);
    }
    
    private void mostrarCalificaciones() {
    	try {
            AlumnoRepository repo = new AlumnoRepository();
            List<Alumno> alumnos = repo.getAlumnos();

            CalificacionTableModel model = new CalificacionTableModel(alumnos);
            view.calificacionesPanel.setTableModel(model);

            view.showView(MainView.CALIFICACIONES);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al cargar calificaciones");
        }
    	
    }

    private void handleClose() {

        int option = view.confirmExit();

        if (option == JOptionPane.YES_OPTION) {
            new LoginWindow();
            //view.dispose();
            
            /*LoginWindow ventana = new LoginWindow();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);*/

            /*LoginWindow login = new LoginWindow();
            login.setLocationRelativeTo(null);
            login.setVisible(true);*/


            view.dispose();
        }
    }
    
    
    private void volverLogin() {

        int option = JOptionPane.showConfirmDialog(
                view,
                "¿Deseas cerrar sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {

            LoginWindow login = new LoginWindow();
            login.setLocationRelativeTo(null);
            login.setVisible(true);

            view.dispose();
        }
    }
}