package controllers;

import javax.swing.JOptionPane;

import models.Alumno;
import repository.AlumnoRepository;
import views.CalificarAlumnoView;

public class CalificacionesController {

	private CalificarAlumnoView view;
	private Alumno alumno;
	private int index;
	
	public CalificacionesController(CalificarAlumnoView view, Alumno alumno, int index) {
		this.view = view;
		this.alumno = alumno;
		this.index = index;
		iniciar();
	}
	private void iniciar() {
		view.btnGuardar.addActionListener(e -> guardar());
	}
	
	private void guardar() {
		try {
	           double artes = Double.parseDouble(view.txtArtes.getText());
	           double geo = Double.parseDouble(view.txtGeografia.getText());
	           double info = Double.parseDouble(view.txtInformatica.getText());

	           
	           alumno.setArtes(artes);
	           alumno.setGeografia(geo);
	           alumno.setInformatica(info);
	           
	           AlumnoRepository repo = new AlumnoRepository();
	           repo.update(index, alumno);
	           
	           JOptionPane.showMessageDialog(view, "Calificaciones guardadas");
	           view.dispose();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(view, "Datos invalidos");
		}
	}
	
	
}
