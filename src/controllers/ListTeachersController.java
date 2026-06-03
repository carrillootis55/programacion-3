package controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import models.Teacher;
import repository.TeacherRepository;
import tablemodels.TeacherTableModel;
import views.TeacherForm;
import views.TeacherListView;

public class ListTeachersController {

    private TeacherListView view;
    private TeacherRepository repository;

    public ListTeachersController(TeacherListView view) {

        this.view = view;

        try {

            repository = new TeacherRepository();

            cargarTabla();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, "Error al conectar con la base de datos");
        }

        asignarEventos();
    }
  //=================================================================================================================================================================
    private void asignarEventos() {
    	view.getBtnAgregar().addActionListener(e -> {

    	    try {
    	        // Validar que no existan más de 6 maestros
    	        if (repository.contarMaestros() >= 6) {

    	            JOptionPane.showMessageDialog( view,"Ya existen los 6 maestros permitidos" );

    	            return;
    	        }

    	        // Crear formulario
    	        TeacherForm form = new TeacherForm();

    	        // Crear controller
    	        FormTeacherController controller = new FormTeacherController(form);

    	        // Actualizar tabla al cerrar
    	        form.addWindowListener(new java.awt.event.WindowAdapter() {

    	            @Override
    	            public void windowClosed(java.awt.event.WindowEvent e) {
    	                cargarTabla();
    	            }
    	        });

    	        // Mostrar formulario
    	        form.setVisible(true);

    	    } catch (SQLException ex) {

    	        JOptionPane.showMessageDialog( view,"Error al validar cantidad de maestros"
    	        );
    	    }
    	});


        view.getBtnEditar().addActionListener(e -> editarMaestro() );

        
        view.getBtnEliminar().addActionListener(
                e -> eliminarMaestro()
        );
        
    }
  //=================================================================================================================================================================
    private void cargarTabla() {

        try {

            TeacherTableModel model =new TeacherTableModel(repository.obtenerSoloMaestros() );

            view.setTableModel(model);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, "Error al cargar maestros");
        }
    }

    
    private void eliminarMaestro() {

        int fila = view.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un maestro"
            );

            return;
        }

        TeacherTableModel model =
                (TeacherTableModel)
                        view.getTabla().getModel();

        int id = model.getMaestro(fila).getId();

        int opcion = JOptionPane.showConfirmDialog(
                view,
                "¿Desea eliminar este maestro?"
        );

        if (opcion != JOptionPane.YES_OPTION) {

            return;
        }

        try {

            boolean eliminado =
                    repository.eliminar(id);

            if (eliminado) {

                JOptionPane.showMessageDialog(
                        view,
                        "Maestro eliminado correctamente"
                );

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "No se puede eliminar.\n" +
                        "Debe haber mínimo 6 maestros."
                );
            }

            cargarTabla();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al eliminar maestro"
            );
        }
    }
   
  //=================================================================================================================================================================
    private void editarMaestro() {

        int fila = view.getSelectedRow();

        // Validar selección
        if (fila == -1) {

            JOptionPane.showMessageDialog( view,"Seleccione un maestro" );

            return;
        }

        // Obtener modelo
        TeacherTableModel model =(TeacherTableModel)view.getTabla().getModel();

        // Obtener maestro seleccionado
        Teacher teacher =model.getMaestro(fila);

        // Crear formulario
        TeacherForm formulario =new TeacherForm();

        // Cargar datos
        formulario.cargarDatos(teacher);

        // Crear controller
        FormTeacherController controller =new FormTeacherController(formulario);

        // Activar modo edición
        controller.setModoEdicion();

        // Mostrar ventana
        formulario.setVisible(true);

        // Actualizar tabla cuando cierre
        formulario.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosed( java.awt.event.WindowEvent e) {
                cargarTabla();
            }
        });
    }
    
    
    
}