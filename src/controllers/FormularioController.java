package controllers;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;

import views.Formulario;

public class FormularioController {

	private Formulario view;
	
	public FormularioController(Formulario view) {
		this.view = view;
		assignListeners();
	}
	
	private void assignListeners() {

		view.getTxtMatricula().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {validateMatricula();}
			public void removeUpdate(DocumentEvent e) {validateMatricula();}
			public void changedUpdate(DocumentEvent e) {validateMatricula();}
		});
		
		view.getTxtNombre().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {validateNombre();}
			public void removeUpdate(DocumentEvent e) {validateNombre();}
			public void changedUpdate(DocumentEvent e) {validateNombre();}
			});
		
		view.getTxtApellidoPaterno().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {validateApellidoPaterno();}
			public void removeUpdate(DocumentEvent e) {validateApellidoPaterno();}
			public void changedUpdate(DocumentEvent e) {validateApellidoPaterno();
			}
		});
		view.getTxtApellidoMaterno().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {validateApellidoMaterno();}
			public void removeUpdate(DocumentEvent e) {validateApellidoMaterno();}
			public void changedUpdate(DocumentEvent e) {validateApellidoMaterno();}
		});
		
		view.getTxtContactoEmergencia().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {validateContactoEmergencia();}
			public void removeUpdate(DocumentEvent e) {validateContactoEmergencia();}
			public void changedUpdate(DocumentEvent e) {validateContactoEmergencia();}
		});

		view.getTxtNumeroEmergencia().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {validateNumeroEmergencia();}
			public void removeUpdate(DocumentEvent e) {validateNumeroEmergencia();}
			public void changedUpdate(DocumentEvent e) {validateNumeroEmergencia();}
		});
		
		view.getParentescoAlumno().addActionListener(e -> validateParentesco());

		view.getDomicilio().addActionListener(e -> validateDomicilio());

		view.getRbMujer().addActionListener(e -> validateSexo());
		view.getRbHombre().addActionListener(e -> validateSexo());

		view.getRbA().addActionListener(e -> validateGrupo());
		view.getRbB().addActionListener(e -> validateGrupo());
		
		view.getBtnRegistrar().addActionListener(e -> validarFormulario());

	}
	
	
	private boolean validateMatricula() {

	    if (view.getTxtMatricula().getText().trim().isEmpty()) {
		    view.setErrorMatricula("La matrícula es obligatoria");
	        return false;
	    }
	    view.setErrorMatricula("");
	    return true;
	}
	
	private boolean validateApellidoMaterno() {
	    if (view.getTxtApellidoMaterno().getText().trim().isEmpty()) {
	        view.setErrorApellidoMaterno("El apellido materno es obligatorio");
	        return false;
	    }
	    view.setErrorApellidoMaterno("");
	    return true;
	}
	
	private boolean validateApellidoPaterno() {
	    if (view.getTxtApellidoPaterno().getText().trim().isEmpty()) {
	        view.setErrorApellidoPaterno("El apellido paterno es obligatorio");
	        return false;
	    }
	    view.setErrorApellidoPaterno("");
	    return true;
	}
	
	private boolean validateNombre() {
	    if (view.getTxtNombre().getText().trim().isEmpty()) {
	        view.setErrorNombre("El nombre es obligatorio");
	        return false;
	    }
	    view.setErrorNombre("");
	    return true;
	}
	
	private boolean validateSexo() {
	    if (!view.getRbMujer().isSelected() && !view.getRbHombre().isSelected()) {
	        view.setErrorSexo("Debe seleccionar un sexo");
	        return false;
	    }
	    view.setErrorSexo("");
	    return true;
	}
	
	private boolean validateGrupo() {
	    if (!view.getRbA().isSelected() && !view.getRbB().isSelected()) {
	        view.setErrorGrupo("Debe seleccionar un grupo");
	        return false;
	    }
	    view.setErrorGrupo("");
	    return true;
	}
	
	private boolean validateParentesco() {
	    if (view.getParentescoAlumno().getSelectedIndex() == 0) {
	        view.setErrorParentesco("Debe seleccionar un parentesco");
	        return false;
	    }
	    view.setErrorParentesco("");
	    return true;
	}
	
	private boolean validateDomicilio() {
	    if (view.getDomicilio().getSelectedIndex() == 0) {
	        view.setErrorDomicilio("Debe seleccionar un domicilio");
	        return false;
	    }
	    view.setErrorDomicilio("");
	    return true;
	}
	
	private boolean validateContactoEmergencia() {
	    if (view.getTxtContactoEmergencia().getText().trim().isEmpty()) {
	        view.setErrorContacto("El contacto es obligatorio");
	        return false;
	    }
	    view.setErrorContacto("");
	    return true;
	}
	
	private boolean validateNumeroEmergencia() {
	    String numero = view.getTxtNumeroEmergencia().getText().trim();

	    if (numero.isEmpty()) {
	        view.setErrorNumero("El número es obligatorio");
	        return false;
	    }
	    view.setErrorNumero("");
	    return true;
	}
	
	private void validarFormulario() {

	    boolean validacion = true;

	    if (!validateMatricula()) {validacion = false;} 

	    if (!validateApellidoPaterno()) {validacion = false;} 
	    
	    if (!validateApellidoMaterno()) {validacion = false;}
	    
	    if (!validateSexo()) {validacion = false;}
	    
	    if (!validateGrupo()) {validacion = false;}
	    
	    if (!validateNumeroEmergencia()) {validacion = false;}
	    
	    if (!validateParentesco()) {validacion = false;}
	    
	    if (!validateDomicilio()) {validacion = false;}
	    
	    if (!validateNombre()) {validacion = false;}
	    
	    if (!validateContactoEmergencia()) {validacion = false;}
	    
	    if (!validateDomicilio()) {validacion = false;}
	    
	    if(!validateParentesco()) { validacion = false;}
	    
	    
	    if (validacion) {
	        JOptionPane.showMessageDialog(null, "Alumno registrado correctamente");
	        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas registrar otro alumno?");

	    	if(opcion == JOptionPane.YES_OPTION) {
				new Formulario();
				view.dispose();
			}		}
	    }
    	
	
}
