package controllers;

import java.util.List;

import javax.swing.JOptionPane;

import models.Alumno;
import repository.AlumnoRepository;
import tablemodels.AlumnoTableModels;
import views.AlumnosView;
import views.Formulario;

public class alumnoController {
	
	private AlumnosView view;
		
	public alumnoController(AlumnosView view) {
		this.view = view;
		//AGREGAR
		view.getBtnAgregar().addActionListener(e ->{
			Formulario form = new Formulario();
			FormularioController controller = new FormularioController(form);
			form.setLocationRelativeTo(null);
			form.setVisible(true);
		});
		
		
		//EDITAR
		view.getBtnEditar().addActionListener(e ->{
			int fila = view.getTabla().getSelectedRow();
			
			if(fila == -1) {
				JOptionPane.showMessageDialog(null, "Selecciona un alumno");
				return;
			}
			try {
			AlumnoTableModels model = (AlumnoTableModels) view.getTabla().getModel();
			Alumno alumno = model.getAlumnoAt(fila);
			
			Formulario form = new Formulario();
			FormularioController controller = new FormularioController(form);
			
			form.cargarDatos(alumno);
			controller.setModoEdicion(fila);
			form.setLocationRelativeTo(null);
			form.setVisible(true);
			} catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Error al abrir el formulario");
			}
		});
		
		//ELIMINAR
		
		view.getBtnEliminar().addActionListener(e ->{
			int fila = view.getTabla().getSelectedRow();
			
			if(fila == -1) {
				JOptionPane.showMessageDialog(null, "Selecciona un alumno");
				return;
			}
			
			int confirm = JOptionPane.showConfirmDialog(null, "Eliminar alumno?");
			
			if(confirm == JOptionPane.YES_OPTION) {
				try {
					AlumnoRepository repo = new AlumnoRepository();
					repo.delete(fila);
					
					List<Alumno> alumnos = repo.getAlumnos();
					view.setTableModel(new AlumnoTableModels(alumnos));
					
					JOptionPane.showMessageDialog(null, "Alumno eliminado");
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar");
				}
			}
		});
	}

}
