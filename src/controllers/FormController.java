package controllers;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;

import models.Student;
import models.Teacher;
import repository.StudentsRepository;
import views.Form;

public class FormController {
	
	private Form view;
	private StudentsRepository repository;

	//Maestro actual
	private Teacher currentTeacher;
	
	private boolean editing = false;
	private int editIndex = -1;
	
	public FormController(Form view, Teacher teacher) {

		this.view = view;
		this.currentTeacher = teacher;
		this.repository = new StudentsRepository();

		configureGroupAndYear();
		assignListeners();
	}
	
	public void setEditMode(int index) {

		this.editing = true;
		this.editIndex = index;
	}
	
	private void configureGroupAndYear() {

	    //Año
	    if (currentTeacher.getYear().equals("1")) {

	        view.getRbFirstYear().setSelected(true);

	    } else if (currentTeacher.getYear().equals("2")) {

	        view.getRbSecondYear().setSelected(true);

	    } else {

	        view.getRbThirdYear().setSelected(true);
	    }

	    //Grupo
	    if (currentTeacher.getGroup().equals("A")) {

	        view.getRbGroupA().setSelected(true);

	    } else {

	        view.getRbGroupB().setSelected(true);
	    }

	    //Bloquear las opciones

	    //Año
	    view.getRbFirstYear().setEnabled(false);
	    view.getRbSecondYear().setEnabled(false);
	    view.getRbThirdYear().setEnabled(false);

	    //Grupo
	    view.getRbGroupA().setEnabled(false);
	    view.getRbGroupB().setEnabled(false);
	}
	
	private void assignListeners() {

		view.getTxtEnrollment().getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateEnrollment();
			}

			public void removeUpdate(DocumentEvent e) {
				validateEnrollment();
			}

			public void changedUpdate(DocumentEvent e) {
				validateEnrollment();
			}
		});
		
		view.getTxtName().getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateName();
			}

			public void removeUpdate(DocumentEvent e) {
				validateName();
			}

			public void changedUpdate(DocumentEvent e) {
				validateName();
			}
		});

		view.getTxtBirthDate().getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {

		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	validateBirthDate();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		    	validateBirthDate();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    	validateBirthDate();
		    }
		});

		view.getTxtFatherLastName().getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateFatherLastName();
			}

			public void removeUpdate(DocumentEvent e) {
				validateFatherLastName();
			}

			public void changedUpdate(DocumentEvent e) {
				validateFatherLastName();
			}
		});

		view.getTxtMotherLastName().getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateMotherLastName();
			}

			public void removeUpdate(DocumentEvent e) {
				validateMotherLastName();
			}

			public void changedUpdate(DocumentEvent e) {
				validateMotherLastName();
			}
		});
		
		view.getTxtEmergencyContact().getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateEmergencyContact();
			}

			public void removeUpdate(DocumentEvent e) {
				validateEmergencyContact();
			}

			public void changedUpdate(DocumentEvent e) {
				validateEmergencyContact();
			}
		});

		view.getTxtEmergencyNumber().getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateEmergencyNumber();
			}

			public void removeUpdate(DocumentEvent e) {
				validateEmergencyNumber();
			}

			public void changedUpdate(DocumentEvent e) {
				validateEmergencyNumber();
			}
		});
		
		view.getCbRelationship().addActionListener(
				e -> validateRelationship()
		);

		view.getCbAddress().addActionListener(
				e -> validateAddress()
		);

		view.getRbFemale().addActionListener(
				e -> validateGender()
		);

		view.getRbMale().addActionListener(
				e -> validateGender()
		);
		
		view.getRbFirstYear().addActionListener(
				e -> validateYear()
		);

		view.getRbSecondYear().addActionListener(
				e -> validateYear()
		);

		view.getRbThirdYear().addActionListener(
				e -> validateYear()
		);

		view.getRbGroupA().addActionListener(
				e -> validateGroup()
		);

		view.getRbGroupB().addActionListener(
				e -> validateGroup()
		);
		
		view.getBtnRegister().addActionListener(
				e -> validateForm()
		);
		
		view.getTxtEnrollment().addKeyListener(
				new KeyAdapter() {

		    @Override
		    public void keyTyped(KeyEvent e) {

		        char character = e.getKeyChar();

		        //Solo permite numeros
		        if (!Character.isDigit(character)) {

		            e.consume();
		        }

		        //Maximo 10 digitos
		        if (view.getTxtEnrollment().getText().length() >= 10) {

		            e.consume();
		        }
		    }
		});
		
		view.getTxtEmergencyNumber().addKeyListener(
				new KeyAdapter() {

		    @Override
		    public void keyTyped(KeyEvent e) {

		        char character = e.getKeyChar();

		        if (!Character.isDigit(character)) {

		            e.consume();

		            return;
		        }
		        
		        //Deja teclear solo 10 digitos
		        if (view.getTxtEmergencyNumber().getText().length() >= 10) {

		            e.consume();
		        }
		    }
		});
		
		view.getTxtFatherLastName().addKeyListener(
				new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				char character = e.getKeyChar();

				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if (!Character.isLetter(character)
						&& character != ' '
						&& character != KeyEvent.VK_BACK_SPACE
						&& character != KeyEvent.VK_DELETE) {

					e.consume();

					view.setFatherLastNameError("Solo se permiten letras");
				}
			}
		});

		view.getTxtMotherLastName().addKeyListener(
				new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				char character = e.getKeyChar();

				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if (!Character.isLetter(character)
						&& character != ' '
						&& character != KeyEvent.VK_BACK_SPACE
						&& character != KeyEvent.VK_DELETE) {

					e.consume();

					view.setMotherLastNameError("Solo se permiten letras");
				}
			}
		});
		
		view.getTxtName().addKeyListener(
				new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				char character = e.getKeyChar();

				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if (!Character.isLetter(character)
						&& character != ' '
						&& character != KeyEvent.VK_BACK_SPACE
						&& character != KeyEvent.VK_DELETE) {

					e.consume();

					view.setNameError("Solo se permiten letras");
				}
			}
		});
		
		view.getTxtEmergencyContact().addKeyListener(
				new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				char character = e.getKeyChar();

				// SI NO ES UNA LETRA Y TAMPOCO ES RETROCESO O BORRAR SE CONSUME EL EVENTO
				if (!Character.isLetter(character)
						&& character != ' '
						&& character != KeyEvent.VK_BACK_SPACE
						&& character != KeyEvent.VK_DELETE) {
					
					e.consume();

					view.setEmergencyNumberError("Solo se permiten letras");
				}
			}
		});

		view.getTxtBirthDate().addKeyListener(
				new KeyAdapter() {

	        @Override
	        public void keyTyped(KeyEvent e) {

	            char character = e.getKeyChar();

	            if (!Character.isDigit(character)
	            		&& character != '/'
	            		&& character != KeyEvent.VK_BACK_SPACE) {

	            	e.consume();
	            }
	        }
	    });
	}
	
	private boolean validateEnrollment() {

	    String enrollment =
	    		view.getTxtEnrollment().getText().trim();

	    //Verifica si esta vacia
	    if (enrollment.isEmpty()) {

	        view.setEnrollmentError("La matrícula es obligatoria");

	        return false;
	    }
	    
	    if (enrollment.length() != 10) {

	        view.setEnrollmentError("La matrícula debe tener 10 dígitos");

	        return false;
	    }
	    
	    if (!editing
	    		&& repository.enrollmentExists(enrollment)) {

	        view.setEnrollmentError("La matrícula ya está registrada");

	        return false;
	    }
	    
	    view.setEnrollmentError("");

	    return true;
	}
	
	private boolean validateMotherLastName() {

	    if (view.getTxtMotherLastName().getText().trim().isEmpty()) {

	        view.setMotherLastNameError("El apellido materno es obligatorio");

	        return false;
	    }

	    view.setMotherLastNameError("");

	    return true;
	}
	
	private boolean validateFatherLastName() {
		
	    if (view.getTxtFatherLastName().getText().trim().isEmpty()) {

	        view.setFatherLastNameError("El apellido paterno es obligatorio");

	        return false;
	    }

	    view.setFatherLastNameError("");

	    return true;
	}
	
	private boolean validateName() {
		
		if (view.getTxtName().getText().trim().isEmpty()) {

	        view.setNameError("El nombre es obligatorio");

	        return false;
	    }

	    view.setNameError("");

	    return true;
	}
	
	 //LocalDate sirve para manejar fechas, dia, anio y mes sin hora  el parse(textp, formato) convierte el string en una fecha real)
	private boolean validateBirthDate() {

	    String birthDate =
	    		view.getTxtBirthDate().getText().trim();

	    if (birthDate.isEmpty()) {

	        view.setBirthDateError("Campo obligatorio");

	        return false;
	    }

	    // EXPRESIÓN REGULAR: VA A VALIDAR que el usuario SOLO escriba números y diagonales en orden
	    String typingProcess =
	    		"^\\d{0,2}(/?\\d{0,2})?(/?\\d{0,4})?$";
	    
	    if (!birthDate.matches(typingProcess)) {

	        view.setBirthDateError("Formato invalido: DD/MM/AAAA");

	        return false;
	    }

	    try {

	        DateTimeFormatter formatter =
	        		DateTimeFormatter.ofPattern("dd/MM/uuuu")
	        		.withResolverStyle(ResolverStyle.STRICT);

	        LocalDate.parse(birthDate, formatter);

	        view.setBirthDateError("");

	        return true;

	    } catch (Exception e) {

	        view.setBirthDateError("Fecha irreal o inválida (DD/MM/AAAA)");

	        return false;
	    }
	}
	
	private boolean validateGender() {

	    if (!view.getRbFemale().isSelected()
	    		&& !view.getRbMale().isSelected()) {

	        view.setGenderError("Debe seleccionar un sexo");

	        return false;
	    }

	    view.setGenderError("");

	    return true;
	}
	
	private boolean validateGroup() {

	    if (!view.getRbGroupA().isSelected()
	    		&& !view.getRbGroupB().isSelected()) {

	        view.setGroupError("Debe seleccionar un grupo");

	        return false;
	    }

	    view.setGroupError("");

	    return true;
	}
	
	private boolean validateYear() {

	    if (!view.getRbFirstYear().isSelected()
	            && !view.getRbSecondYear().isSelected()
	            && !view.getRbThirdYear().isSelected()) {

	        view.setYearError("Debe seleccionar un año");

	        return false;
	    }

	    view.setYearError("");

	    return true;
	}
	
	private boolean validateRelationship() {

	    if (view.getCbRelationship().getSelectedIndex() == 0) {

	        view.setRelationshipError("Debe seleccionar un parentesco");

	        return false;
	    }

	    view.setRelationshipError("");

	    return true;
	}
	
	private boolean validateAddress() {

	    if (view.getCbAddress().getSelectedIndex() == 0) {

	        view.setAddressError("Debe seleccionar un domicilio");

	        return false;
	    }

	    view.setAddressError("");

	    return true;
	}
	
	private boolean validateEmergencyContact() {
		
		if (view.getTxtEmergencyContact().getText().trim().isEmpty()) {

	        view.setEmergencyNumberError("El contacto es obligatorio");

	        return false;
	    }

	    view.setEmergencyNumberError("");

	    return true;
	}
	
	private boolean validateEmergencyNumber() {
	   
		String emergencyNumber =
				view.getTxtEmergencyNumber().getText().trim();

	    if (emergencyNumber.isEmpty()) {

		    view.setEmergencyContactError("El número es obligatorio");

	        return false;
	    }
	    
	    if (emergencyNumber.length() != 10) {

	        view.setEmergencyContactError("El número debe tener 10 dígitos");

	        return false;
	    }
	    
	    view.setEmergencyContactError("");

	    return true;
	}
	
	private void validateForm() {

        boolean validation = true;

        //Se ejecutan las validaciones
        if (!validateEnrollment()) validation = false;

        if (!validateFatherLastName()) validation = false;

        if (!validateMotherLastName()) validation = false;

        if (!validateGender()) validation = false;

        if (!validateBirthDate()) validation = false;

        if (!validateYear()) validation = false;

        if (!validateGroup()) validation = false;

        if (!validateEmergencyNumber()) validation = false;

        if (!validateRelationship()) validation = false;

        if (!validateAddress()) validation = false;

        if (!validateName()) validation = false;

        if (!validateEmergencyContact()) validation = false;

        if (!validation) return;

        String userDate =
        		view.getTxtBirthDate().getText().trim();

        String databaseDate = "";

        try {

            DateTimeFormatter inputFormat =
            		DateTimeFormatter.ofPattern("dd/MM/uuuu")
            		.withResolverStyle(ResolverStyle.STRICT);

            LocalDate localDate =
            		LocalDate.parse(userDate, inputFormat);

            // genera el String en formato YYYY-MM-DD para el mysql
            databaseDate = localDate.toString();

        } catch (Exception ex) {

            view.setBirthDateError("Formato inválido (DD/MM/AAAA)");

            return;
        }

        Student student = new Student(
        	    view.getTxtEnrollment().getText(),
        	    view.getTxtName().getText(),
        	    view.getTxtFatherLastName().getText(),
        	    view.getTxtMotherLastName().getText(),
        	    databaseDate,
        	    view.getRbMale().isSelected() ? 'H' : 'M',
        	    view.getRbFirstYear().isSelected() ? "1"
        	    : view.getRbSecondYear().isSelected() ? "2"
        	    : "3",
        	    view.getRbGroupA().isSelected() ? "A" : "B",
        	    view.getTxtEmergencyContact().getText(),
        	    view.getTxtEmergencyNumber().getText(),
        	    view.getCbRelationship().getSelectedItem().toString(),
        	    view.getCbAddress().getSelectedItem().toString()
        	);

        StudentsRepository repository =
        		new StudentsRepository();

        try {

        	if (editing) {

        		repository.updateStudent(student);

        	} else {
        		
        		repository.save(student);
        	}

            System.out.println("=== LISTA DE ALUMNOS ===");

            for (Student studentItem : repository.getStudents()) {

                System.out.println(studentItem);

                System.out.println("----------------------");
            }

            JOptionPane.showMessageDialog(
            		view,
            		"Alumno registrado correctamente"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
            		view,
            		"Error al guardar: " + e.getMessage()
            );
        }
	            
        int option =
        		JOptionPane.showConfirmDialog(
        				null,
        				"¿Deseas registrar otro alumno?"
        		);

        if (option == JOptionPane.YES_OPTION) {

        	view.clearForm();

        	configureGroupAndYear();

        } else {

        	view.dispose();
        }
	}
}