package controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import models.Maestro;
import repository.MaestroRepository;
import tablemodels.MaestroTableModel;
import views.FormularioMaestro;
import views.ListaMaestrosView;

public class ListaMaestrosController {

    private ListaMaestrosView view;
    private MaestroRepository repository;

    public ListaMaestrosController(ListaMaestrosView view) {

        this.view = view;

        try {

            repository = new MaestroRepository();

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
    	        FormularioMaestro formulario = new FormularioMaestro();

    	        // Crear controller
    	        FormularioMaestroController controller = new FormularioMaestroController(formulario);

    	        // Actualizar tabla al cerrar
    	        formulario.addWindowListener(new java.awt.event.WindowAdapter() {

    	            @Override
    	            public void windowClosed(java.awt.event.WindowEvent e) {
    	                cargarTabla();
    	            }
    	        });

    	        // Mostrar formulario
    	        formulario.setVisible(true);

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

            MaestroTableModel model =new MaestroTableModel(repository.obtenerSoloMaestros() );

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

        MaestroTableModel model =
                (MaestroTableModel)
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
        MaestroTableModel model =(MaestroTableModel)view.getTabla().getModel();

        // Obtener maestro seleccionado
        Maestro maestro =model.getMaestro(fila);

        // Crear formulario
        FormularioMaestro formulario =new FormularioMaestro();

        // Cargar datos
        formulario.cargarDatos(maestro);

        // Crear controller
        FormularioMaestroController controller =new FormularioMaestroController(formulario);

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