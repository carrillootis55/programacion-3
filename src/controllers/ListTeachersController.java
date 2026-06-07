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

            loadTable();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al conectar con la base de datos"
            );
        }

        assignEvents();
    }

    //=================================================================================================================================================================

    private void assignEvents() {

    	view.getBtnAdd().addActionListener(e -> {

    	    try {

    	        // Validar que no existan más de 6 maestros
    	        if (repository.countTeachers() >= 6) {

    	            JOptionPane.showMessageDialog(
    	            		view,
    	            		"Ya existen los 6 maestros permitidos"
    	            );

    	            return;
    	        }

    	        // Crear formulario
    	        TeacherForm form = new TeacherForm();

    	        // Crear controller
    	        FormTeacherController controller =
    	        		new FormTeacherController(form);

    	        // Actualizar tabla al cerrar
    	        form.addWindowListener(new java.awt.event.WindowAdapter() {

    	            @Override
    	            public void windowClosed(
    	            		java.awt.event.WindowEvent e
    	            ) {

    	                loadTable();
    	            }
    	        });

    	        // Mostrar formulario
    	        form.setVisible(true);

    	    } catch (SQLException ex) {

    	        JOptionPane.showMessageDialog(
    	        		view,
    	        		"Error al validar cantidad de maestros"
    	        );
    	    }
    	});

        view.getBtnEdit().addActionListener(
                e -> editTeacher()
        );

        view.getBtnDelete().addActionListener(
                e -> deleteTeacher()
        );
    }

    //=================================================================================================================================================================

    private void loadTable() {

        try {

            TeacherTableModel model =
                    new TeacherTableModel(
                            repository.getOnlyTeachers()
                    );

            view.setTableModel(model);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al cargar maestros"
            );
        }
    }

    //=================================================================================================================================================================

    private void deleteTeacher() {

        int row = view.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un maestro"
            );

            return;
        }

        TeacherTableModel model =
                (TeacherTableModel)
                        view.getTable().getModel();

        int id = model.getTeacher(row).getId();

        int option = JOptionPane.showConfirmDialog(
                view,
                "¿Desea eliminar este maestro?"
        );

        if (option != JOptionPane.YES_OPTION) {

            return;
        }

        try {

            boolean deleted =
                    repository.delete(id);

            if (deleted) {

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

            loadTable();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al eliminar maestro"
            );
        }
    }

    //=================================================================================================================================================================

    private void editTeacher() {

        int row = view.getSelectedRow();

        // Validar selección
        if (row == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un maestro"
            );

            return;
        }

        // Obtener modelo
        TeacherTableModel model =
                (TeacherTableModel)
                        view.getTable().getModel();

        // Obtener maestro seleccionado
        Teacher teacher =
                model.getTeacher(row);

        // Crear formulario
        TeacherForm form = new TeacherForm();

        // Cargar datos
        form.loadData(teacher);

        // Crear controller
        FormTeacherController controller =
                new FormTeacherController(form);

        // Activar modo edición
        controller.setEditMode();

        // Mostrar ventana
        form.setVisible(true);

        // Actualizar tabla cuando cierre
        form.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosed(
                    java.awt.event.WindowEvent e
            ) {

                loadTable();
            }
        });
    }
}