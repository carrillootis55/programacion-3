package controllers;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;

import models.Alumno;
import models.Maestro;
import repository.AlumnoRepository;
import views.Formulario;

public class FormularioController {
	
	private Formulario view;
	private AlumnoRepository repository;
	
	private Maestro maestroActual;
	
	private boolean editando = false;
	private int indexEditar = -1;
	
	public FormularioController(Formulario view,  Maestro maestro) {
		this.view = view;
		this.maestroActual = maestro;
		this.repository = new AlumnoRepository();
		configurarGrupoYAnio();
		assignListeners();
	}
	
	public void setModoEdicion(int index) {
		this.editando = true;
		this.indexEditar = index;
	}
	
	private void configurarGrupoYAnio() {

	    //Año
	    if (maestroActual.getAnio().equals("1")) {
	        view.getRb1().setSelected(true);
	    } else if (maestroActual.getAnio().equals("2")) {
	        view.getRb2().setSelected(true);
	    } else {
	        view.getRb3().setSelected(true);
	    }

	    //Grupo
	    if (maestroActual.getGrupo().equals("A")) {
	        view.getRbA().setSelected(true);
	    } else {
	        view.getRbB().setSelected(true);
	    }

	    //Bloquear las opciones
	    //Año
	    view.getRb1().setEnabled(false);
	    view.getRb2().setEnabled(false);
	    view.getRb3().setEnabled(false);

	    //Grupo
	    view.getRbA().setEnabled(false);
	    view.getRbB().setEnabled(false);
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
		
		view.getRb1().addActionListener(e -> validateAnio());
		view.getRb2().addActionListener(e -> validateAnio());
		view.getRb3().addActionListener(e -> validateAnio());

		view.getRbA().addActionListener(e -> validateGrupo());
		view.getRbB().addActionListener(e -> validateGrupo());
		
		
		
		view.getBtnRegistrar().addActionListener(e -> validarFormulario());
		
		/*view.getTxtMatricula().addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        // Consumir si NO es letra ni número isLetterOrDigit si es numero se usa isDigit
		        if (!Character.isDigit(c)) {
		            e.consume();
		        }
		    }
		});*/
		view.getTxtMatricula().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        //Solo permite numeros
		        if (!Character.isDigit(c)) {
		            e.consume();
		        }

		        //Maximo 10 digitos
		        if (view.getTxtMatricula().getText().length() >= 10) {
		            e.consume();
		        }
		    }
		});
		
		view.getTxtNumeroEmergencia().addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c)) {
		            e.consume();
		        }
		    }
		});
		
		view.getTxtApellidoPaterno().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c =e.getKeyChar();
				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if(!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();

					view.setErrorApellidoPaterno("Solo se permiten letras");
				}
			}
		});

		view.getTxtApellidoMaterno().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c =e.getKeyChar();
				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if(!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE &&c != KeyEvent.VK_DELETE) {
					e.consume();

					view.setErrorApellidoMaterno("Solo se permiten letras");
				}
			}
		});
		
		view.getTxtNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c =e.getKeyChar();
				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if(!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();

					view.setErrorNombre("Solo se permiten letras");
				}
			}
		});
		
		
		view.getTxtContactoEmergencia().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c =e.getKeyChar();
				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if(!Character.isLetter(c) &&c != ' ' &&c != KeyEvent.VK_BACK_SPACE &&c != KeyEvent.VK_DELETE) {
					
					e.consume();

					view.setErrorContacto("Solo se permiten letras");
				}
			}
		});
	}
	
	private boolean validateMatricula() {

	    String matricula = view.getTxtMatricula().getText().trim();

	    //Verifica si esta vacia
	    if (matricula.isEmpty()) {

	        view.setErrorMatricula("La matrícula es obligatoria");
	        return false;
	    }
	    if (matricula.length() != 10) {

	        view.setErrorMatricula("La matrícula debe tener 10 dígitos");
	        return false;
	    }
	    
	    if (!editando && repository.existeMatricula(matricula)) {

	        view.setErrorMatricula("La matrícula ya está registrada");
	        return false;
	    }
	    
	    view.setErrorMatricula("");
	    return true;
	}
	
	private boolean validateApellidoMaterno() {

	    if (view.getTxtApellidoMaterno().getText().trim().isEmpty()) {
	        view.setErrorApellidoMaterno("El apellido paterno es obligatorio");
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
	
	private boolean validateAnio() {

	    if (!view.getRb1().isSelected() &&
	        !view.getRb2().isSelected() &&
	        !view.getRb3().isSelected()) {

	        view.setErrorAnio("Debe seleccionar un año");
	        return false;
	    }

	    view.setErrorAnio("");
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
	    
	    if(numero.length() < 10) {
	    	view.setErrorNumero("El número es muy corto");
	    	return false;
	    }
	    
	    view.setErrorNumero("");
	    return true;
		
	}
	
	 private void validarFormulario() {

	        boolean validacion = true;

	        //Se ejecutan las validaciones
	        if (!validateMatricula()) validacion = false;
	        if (!validateApellidoPaterno()) validacion = false;
	        if (!validateApellidoMaterno()) validacion = false;
	        if (!validateSexo()) validacion = false;
	        if (!validateAnio()) validacion = false;
	        if (!validateGrupo()) validacion = false;
	        if (!validateNumeroEmergencia()) validacion = false;
	        if (!validateParentesco()) validacion = false;
	        if (!validateDomicilio()) validacion = false;
	        if (!validateNombre()) validacion = false;
	        if (!validateContactoEmergencia()) validacion = false;

	        if (!validacion) return;

	            //Si es valido se crea la lista
	            Alumno alumno = new Alumno(
	                view.getTxtMatricula().getText(),
	                view.getTxtNombre().getText(),
	                view.getTxtApellidoPaterno().getText(),
	                view.getTxtApellidoMaterno().getText(),
	                view.getRbHombre().isSelected() ? 'H' : 'M',
            		view.getRb1().isSelected() ? "1" :
        			view.getRb2().isSelected() ? "2" : "3",
	                view.getRbA().isSelected() ? "A" : "B",
	                view.getTxtContactoEmergencia().getText(),
	                view.getTxtNumeroEmergencia().getText(),
	                view.getParentescoAlumno().getSelectedItem().toString(),
	                view.getDomicilio().getSelectedItem().toString()
	            );

	            //Se crea el repositorio para guardar
	            AlumnoRepository repository = new AlumnoRepository();

	            try {
	                //Guarda el alumno en el archivo
	            	if(editando) {
	            		//para el metodo de update se creo dentro del alumnoRepositorio
	            		repository.updateAlumno(alumno);
	            	}else {
	            		
	            		repository.save(alumno);
	            	}
	                //Imprime todos los alumnos en consola
	                System.out.println("=== LISTA DE ALUMNOS ===");
	                for (Alumno a : repository.getAlumnos()) {
	                    System.out.println(a);
	                    System.out.println("----------------------");
	                }


	                JOptionPane.showMessageDialog(view, "Alumno registrado correctamente");

	            } catch (Exception e) {
	                //Manejo de errores
	                JOptionPane.showMessageDialog(view, "Error al guardar: " + e.getMessage());
	            }
	            
	            int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas registrar otro alumno?");
	            if (opcion == JOptionPane.YES_OPTION) {
	            	view.limpiarFormulario();
	            	configurarGrupoYAnio();
	            }else {
	            	view.dispose();
	            }
	            
	            
	     }

	 
}
	
		
