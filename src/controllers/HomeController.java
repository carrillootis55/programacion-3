package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Maestro;
import models.Materia;
import models.Alumno;
import repository.AlumnoRepository;
import services.PDFExporter;
import tablemodels.AlumnoTableModels;
import tablemodels.CalificacionTableModel;
import repository.CalificacionRepository;
import repository.MateriaRepository;
import views.Formulario;
import views.LoginWindow;
import views.MainView;
import config.Config;

public class HomeController {

    private MainView view;
    private Maestro maestroActual;

    public HomeController(MainView view, Maestro maestro) {
        this.view = view;
        this.maestroActual = maestro;
        new AlumnoController(view.alumnosPanel, maestro);
        
        new ListaMaestrosController( view.listaMaestrosPanel);
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
        
        view.listaMaestrosPanel.getBtnPdf().addActionListener(
                e -> exportMaestrosPDF()
        );
        view.mItemAdminPerfil.addActionListener( e -> mostrarAdmin());
        
        view.mItemListaMaestros.addActionListener( e -> mostrarListaMaestros() );
        
        view.calificacionesPanel.getBtnPdf().addActionListener(e -> exportarCalificacionesPdf());
         
        
    }
    
    private void exportarCalificacionesPdf() {

        File file =view.calificacionesPanel.selectPdfFile();

        if (file == null)
            return;

        try {

            AlumnoRepository repo = new AlumnoRepository();

            List<Alumno> alumnos = repo.getAlumnosPorGrupo( maestroActual.getAnio(), maestroActual.getGrupo());

            MateriaRepository materiaRepo = new MateriaRepository();

            List<Materia> listaMaterias = materiaRepo.getMateriasPorAnio( maestroActual.getAnio()  );

            List<String> materias = new ArrayList<>();

            for (Materia materia : listaMaterias) {

                materias.add( materia.getNombre());
            }

            PDFExporter exporter = new PDFExporter();

            exporter.exportCalificaciones(alumnos, materias, file );

            JOptionPane.showMessageDialog( view,"PDF exportado correctamente" );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(view, "Error al exportar PDF");
        }
    }
    
    //Exportar maestros
    private void exportMaestrosPDF() {

     // Seleccionar archivo
     File file = view.listaMaestrosPanel.selectPdfFile();

     if (file == null) {
         return;
     }

     try {

         // Obtener maestros
         repository.MaestroRepository repo =new repository.MaestroRepository();

         List<Maestro> maestros =repo.obtenerSoloMaestros();

         // Validar lista vacía
         if (maestros == null || maestros.isEmpty()) {

             JOptionPane.showMessageDialog(view,"No hay maestros registrados");

             return;
         }

         // Exportar PDF
         PDFExporter exporter = new PDFExporter();

         exporter.exportMaestros(maestros, file);

         JOptionPane.showMessageDialog(view,"PDF exportado correctamente");

     } catch (Exception e) {

         e.printStackTrace();

         JOptionPane.showMessageDialog( view, "Error al exportar PDF" );
     }
 }
    

    private void mostrarAlumnos() {

        AlumnoRepository repository = new AlumnoRepository();

        try {
            /*List<Alumno> alumnos = repository.getAlumnos();

            if (alumnos == null || alumnos.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "No hay alumnos registrados");
                return;
            }

            AlumnoTableModels modelAlumnos =
                    new AlumnoTableModels(alumnos);

            view.alumnosPanel.setTableModel(modelAlumnos);
            new AlumnoController(view.alumnosPanel);

            view.showView(MainView.ALUMNOS);*/
        	List<Alumno> alumnos =
                    repository.getAlumnosPorGrupo(
                            maestroActual.getAnio(),
                            maestroActual.getGrupo()
                    );

            AlumnoTableModels modelAlumnos = new AlumnoTableModels(alumnos);

            view.alumnosPanel.setTableModel(modelAlumnos);
            //new AlumnoController(view.alumnosPanel);

            view.showView(MainView.ALUMNOS);

            if (alumnos == null || alumnos.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "No hay alumnos registrados");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    view,
                    "Error al cargar alumnos: " + e.getMessage()
            );
        }
    }
    
    private void mostrarMaestro() {
    	view.maestroPanel.updateMaestro(
                maestroActual
        );
    	view.showView(MainView.MAESTRO);
    }
    
    private void mostrarCalificaciones() {
    	try {

            AlumnoRepository repo =
                    new AlumnoRepository();

            // Obtener alumnos del grupo
            List<Alumno> alumnos =
                    repo.getAlumnosPorGrupo(
                            maestroActual.getAnio(),
                            maestroActual.getGrupo()
                    );

            // Obtener materias 
            MateriaRepository materiaRepo =
                    new MateriaRepository();

            List<Materia> listaMaterias =
                    materiaRepo.getMateriasPorAnio(
                            maestroActual.getAnio()
                    );

            // Convertir Materia a String
            List<String> materias =
                    new ArrayList<>();

            for (Materia materia : listaMaterias) {

                materias.add(materia.getNombre());
            }

            if (alumnos == null || alumnos.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "No hay alumnos registrados"
                );

                return;
            }

            // Crear modelo
            CalificacionTableModel model =new CalificacionTableModel(alumnos,materias);

            // Asignar tabla
            view.calificacionesPanel.setTableModel(model);

            // Mostrar panel
            view.showView(MainView.CALIFICACIONES);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error al cargar calificaciones: "
                            + e.getMessage()
            );
        }
	}
 
    private void mostrarAdmin() {

        //Actualizar datos admin
        view.adminPanel.updateAdmin(
                maestroActual
        );

        //Mostrar panel admin
        view.showView( MainView.ADMIN_PERFIL);
    }
    
    private void mostrarListaMaestros() {

        view.showView( MainView.LISTA_MAESTROS);
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