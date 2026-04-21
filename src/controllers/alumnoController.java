package controllers;

import views.AlumnosView;
import views.Formulario;

public class alumnoController {
	
	private AlumnosView view;
		
	public alumnoController(AlumnosView view) {
		this.view = view;
		
		view.getBtnAgregar().addActionListener(e ->{
			Formulario form = new Formulario();
			new FormularioController(form);
			form.setVisible(true);
		});
		
		view.getBtnEditar().addActionListener(e ->{
		System.out.println("Editar");
		});
		
		view.getBtnEliminar().addActionListener(e ->{
			System.out.println("Eliminar");
		});
	}

}
