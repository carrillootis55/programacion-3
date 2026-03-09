package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Formulario extends JFrame{
	
	private JTextField txtMatricula;
	private JTextField txtApellidoMaterno;
	private JTextField txtApellidoPaterno;
	private JTextField txtNombre;
	
	private JTextField txtContactoEmergencia;
	private JTextField txtNumeroEmergencia;
	
	private JRadioButton rbMujer;
	private JRadioButton rbHombre;
	private ButtonGroup bgSexo;
	
	private JRadioButton rbA;
	private JRadioButton rbB;
	private ButtonGroup bgGrupo;
	
	private JComboBox<String> parentescoAlumno;
	private JComboBox<String> domicilio;

	private JLabel lblErrorMatricula;
	private JLabel lblErrorApellidoMaterno;
	private JLabel lblErrorApellidoPaterno;
	private JLabel lblErrorNombre;
	
	private JLabel lblErrorContactoEmergencia;
	private JLabel lblErrorNumeroContacto;
	private JLabel lblErrorSexo;
	private JLabel lblErrorGrupo;
	
	private JLabel lblErrorParentesco;
	private JLabel lblErrorDomicilio;
	
	public Formulario() {
		setSize(400, 600); //lo reemplazo por pack
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(100,100);
		setResizable(false);
		setTitle("LISTA DE ALUMNOS");

		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		inicializarComponentes();
		crearBotones();
		
		assignListeners();

		setVisible(true);
	}
	
	private void assignListeners() {

		txtMatricula.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateMatricula();
			}

			public void removeUpdate(DocumentEvent e) {
				validateMatricula();
			}

			public void changedUpdate(DocumentEvent e) {
				validateMatricula();
			}
		});

		txtNombre.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateNombre();
			}

			public void removeUpdate(DocumentEvent e) {
				validateNombre();
			}

			public void changedUpdate(DocumentEvent e) {
				validateNombre();
			}
		});

		txtApellidoPaterno.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateApellidoPaterno();
			}

			public void removeUpdate(DocumentEvent e) {
				validateApellidoPaterno();
			}

			public void changedUpdate(DocumentEvent e) {
				validateApellidoPaterno();
			}
		});
		
		txtApellidoMaterno.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateApellidoMaterno();
			}

			public void removeUpdate(DocumentEvent e) {
				validateApellidoMaterno();
			}

			public void changedUpdate(DocumentEvent e) {
				validateApellidoMaterno();
			}
		});
		
		txtApellidoMaterno.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateApellidoMaterno();
			}

			public void removeUpdate(DocumentEvent e) {
				validateApellidoMaterno();
			}

			public void changedUpdate(DocumentEvent e) {
				validateApellidoMaterno();
			}
		});
		
		txtContactoEmergencia.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateContactoEmergencia();
			}

			public void removeUpdate(DocumentEvent e) {
				validateContactoEmergencia();
			}

			public void changedUpdate(DocumentEvent e) {
				validateContactoEmergencia();
			}
		});
		
		
		txtNumeroEmergencia.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validateNumeroEmergencia();
			}

			public void removeUpdate(DocumentEvent e) {
				validateNumeroEmergencia();
			}

			public void changedUpdate(DocumentEvent e) {
				validateNumeroEmergencia();
			}
		});
		
		parentescoAlumno.addActionListener(e -> validateParentesco());

		domicilio.addActionListener(e -> validateDomicilio());

		rbMujer.addActionListener(e -> validateSexo());
		rbHombre.addActionListener(e -> validateSexo());

		rbA.addActionListener(e -> validateGrupo());
		rbB.addActionListener(e -> validateGrupo());
				
	}
	
	private boolean validateMatricula() {

	    if (txtMatricula.getText().trim().isEmpty()) {
		    lblErrorMatricula.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorMatricula.setText("La matrícula es obligatoria");
	        return false;
	    }
	    lblErrorMatricula.setText("");
	    return true;
	}
	
	private boolean validateApellidoMaterno() {
	    if (txtApellidoMaterno.getText().trim().isEmpty()) {
		    lblErrorApellidoMaterno.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorApellidoMaterno.setText("El apellido materno es obligatorio");
	        return false;
	    }
	    lblErrorApellidoMaterno.setText("");
	    return true;
	}
	
	private boolean validateApellidoPaterno() {
	    if (txtApellidoPaterno.getText().trim().isEmpty()) {
		    lblErrorApellidoPaterno.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorApellidoPaterno.setText("El apellido paterno es obligatorio");
	        return false;
	    }
	    lblErrorApellidoPaterno.setText("");
	    return true;
	}
	
	private boolean validateNombre() {
	    if (txtNombre.getText().trim().isEmpty()) {
		    lblErrorNombre.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorNombre.setText("El nombre es obligatorio");
	        return false;
	    }
	    lblErrorNombre.setText("");
	    return true;
	}
	
	private boolean validateGrupo() {
	    if (!rbA.isSelected() && !rbB.isSelected()) {
		    lblErrorSexo.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorSexo.setText("Debe seleccionar un sexo");
	        return false;
	    }
	    lblErrorSexo.setText("");
	    return true;
	}
	
	private boolean validateSexo() {
	    if (!rbMujer.isSelected() && !rbHombre.isSelected()) {
		    lblErrorGrupo.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorGrupo.setText("Debe seleccionar un grupo");
	        return false;
	    }
	    
	    lblErrorGrupo.setText("");
	    return true;
	}
	
	private boolean validateParentesco() {
	    if (parentescoAlumno.getSelectedIndex()== 0) {
		    lblErrorParentesco.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorParentesco.setText("Debe seleccionar un parentesco");
	        return false;
	    }
	    lblErrorParentesco.setText("");
	    return true;
	}
	
	private boolean validateDomicilio() {
	    if (domicilio.getSelectedIndex() == 0) {
	    	lblErrorDomicilio.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorDomicilio.setText("Debe seleccionar un domicilio");
	        return false;
	    }
	    
		lblErrorDomicilio.setText("");

	    return true;
	}
	
	private boolean validateContactoEmergencia() {
	    if (txtContactoEmergencia.getText().trim().isEmpty()) {
		    lblErrorContactoEmergencia.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorContactoEmergencia.setText("El contacto es obligatorio");
	        return false;
	    }
	    lblErrorContactoEmergencia.setText("");
	    return true;
	}
	
	private boolean validateNumeroEmergencia() {
	    String numero = txtNumeroEmergencia.getText().trim();

	    if (numero.isEmpty()) {
		    lblErrorNumeroContacto.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	        lblErrorNumeroContacto.setText("El número es obligatorio");
	        return false;
	    }
	    lblErrorNumeroContacto.setText("");

	    return true;
	}
	
	public void inicializarComponentes() {
		
		
		JLabel lblTitulo = new JLabel("REGISTRO DEL ALUMNO");
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setFont(new Font("Times New Roman", Font.ITALIC, 15));


		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//JCheckBox chkAceptoCondiciones = new JCheckBox("", true);
		//(chkAceptoCondiciones);
		JLabel matricula = new JLabel("Matricula:");
		matricula.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(matricula);
	    matricula.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtMatricula = new JTextField(30);
		
		lblErrorMatricula = new JLabel();
		lblErrorMatricula.setForeground(Color.RED);
		lblErrorMatricula.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorMatricula);
		
		txtMatricula.setMaximumSize(txtMatricula.getPreferredSize()); 
		txtMatricula.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txtMatricula);
				
		JLabel apellidoPaterno= new JLabel("Apellido Paterno:");
		apellidoPaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(apellidoPaterno);
	    apellidoPaterno.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtApellidoPaterno = new JTextField(30);
		
		lblErrorApellidoPaterno = new JLabel();
		lblErrorApellidoPaterno.setForeground(Color.RED);
		lblErrorApellidoPaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorApellidoPaterno);
		
		txtApellidoPaterno.setMaximumSize(txtApellidoPaterno.getPreferredSize()); 
		txtApellidoPaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txtApellidoPaterno);
		
		JLabel apellidoMaterno= new JLabel("Apellido Materno:");
		apellidoMaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(apellidoMaterno);
	    apellidoMaterno.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtApellidoMaterno = new JTextField(30);
		
		lblErrorApellidoMaterno = new JLabel();
		lblErrorApellidoMaterno.setForeground(Color.RED);
		lblErrorApellidoMaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorApellidoMaterno);
		
		txtApellidoMaterno.setMaximumSize(txtApellidoMaterno.getPreferredSize()); 
		txtApellidoMaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txtApellidoMaterno);
		
	
		JLabel alumno= new JLabel("Nombre");
		alumno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(alumno);
	    alumno.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtNombre = new JTextField(30);
		
		lblErrorNombre = new JLabel();
		lblErrorNombre.setForeground(Color.RED);
		lblErrorNombre.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorNombre);
		
		txtNombre.setMaximumSize(txtNombre.getPreferredSize()); 
		txtNombre.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txtNombre);
		
		JPanel panelSexo = new JPanel();
		panelSexo.setLayout(new BoxLayout(panelSexo, BoxLayout.Y_AXIS));

		JPanel panelSexoGrupo = new JPanel();
		panelSexoGrupo.setLayout(new GridLayout(3, 2, 10, 5));

		JLabel sexo = new JLabel("Sexo:");
		JLabel grupo = new JLabel("Grupo:");
	    sexo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    grupo.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		rbMujer = new JRadioButton("Mujer");
		rbHombre = new JRadioButton("Hombre");
	    rbMujer.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    rbHombre.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		rbA = new JRadioButton("A");
		rbB = new JRadioButton("B");
	    rbA.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    rbB.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		bgSexo = new ButtonGroup();
		bgSexo.add(rbMujer);
		bgSexo.add(rbHombre);

		bgGrupo = new ButtonGroup();
		bgGrupo.add(rbA);
		bgGrupo.add(rbB);

		panelSexoGrupo.add(sexo);
		panelSexoGrupo.add(grupo);

		panelSexoGrupo.add(rbMujer);
		panelSexoGrupo.add(rbA);

		panelSexoGrupo.add(rbHombre);
		panelSexoGrupo.add(rbB);

		panelSexoGrupo.setAlignmentX(LEFT_ALIGNMENT);

		panelComponentes.add(panelSexoGrupo);
		lblErrorSexo = new JLabel();
		lblErrorSexo.setForeground(Color.RED);
		lblErrorSexo.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorSexo);

		lblErrorGrupo = new JLabel();
		lblErrorGrupo.setForeground(Color.RED);
		lblErrorGrupo.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorGrupo);
		
		panelSexoGrupo.setMaximumSize(panelSexoGrupo.getPreferredSize());
		panelComponentes.add(Box.createVerticalStrut(15));
		
		JLabel nombreContacto= new JLabel("Nombre del Contacto de Emergencia:");
		nombreContacto.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(nombreContacto);
	    nombreContacto.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		txtContactoEmergencia = new JTextField(30);
		
		lblErrorContactoEmergencia = new JLabel();
		lblErrorContactoEmergencia.setForeground(Color.RED);
		lblErrorContactoEmergencia.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorContactoEmergencia);
		
		txtContactoEmergencia.setMaximumSize(txtContactoEmergencia.getPreferredSize()); 
		txtContactoEmergencia.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txtContactoEmergencia);
		
		JLabel contacto= new JLabel("Numero del Contacto de Emergencia:");
		contacto.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(contacto);
	    contacto.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		txtNumeroEmergencia = new JTextField(30);
		
		lblErrorNumeroContacto = new JLabel();
		lblErrorNumeroContacto.setForeground(Color.RED);
		lblErrorNumeroContacto.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorNumeroContacto);
		
		txtNumeroEmergencia.setMaximumSize(txtNumeroEmergencia.getPreferredSize()); 
		txtNumeroEmergencia.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txtNumeroEmergencia);
		
		JLabel parentesco= new JLabel("Parentesco con el alumno:");
		parentesco.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(parentesco);
	    parentesco.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		String[] opcionesParentesco = {"Seleccione","Padre","Madre","Hermana/Hermano",
				"Tio/Tia", "Abuelo/Abuela"};
		
		parentescoAlumno = new JComboBox<String>(opcionesParentesco);

		parentescoAlumno.setSelectedIndex(0);
	    parentescoAlumno.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorParentesco = new JLabel();
	    lblErrorParentesco.setForeground(Color.RED);
	    lblErrorParentesco.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(lblErrorParentesco);

		parentescoAlumno.setMaximumSize(txtNumeroEmergencia.getPreferredSize()); 
		parentescoAlumno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(parentescoAlumno);
		
		JLabel direccion= new JLabel("Domicilio (calle y numero):");
		direccion.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(direccion);
	    direccion.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		String[] opcionesDomicilio = {"Seleccione","Calle Altamirano Allende 1398","Calle Gomez Farias 4367","Calle Rosa Maria 2345",
				"Calle Diana Laura 2365", "Calle Ficus 3489", "Calle Aquiles Serdan 1993", "Calle Francisco y Madero 3740", "Calle Colosio 2521",
				"Calle 3 de Mayo 9757"};
		
		domicilio = new JComboBox<String>(opcionesDomicilio);
	    domicilio.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	    lblErrorDomicilio = new JLabel();
	    lblErrorDomicilio.setForeground(Color.RED);
	    lblErrorDomicilio.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(	    lblErrorDomicilio);
	    
		domicilio.setSelectedIndex(0);
		domicilio.setMaximumSize(txtNumeroEmergencia.getPreferredSize()); 
		domicilio.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(domicilio);
		
		//private boolean validate
		
		
		//ESTAS OPCIONES SON PARA VER CUAL SE VE MEJOR
		/*JList lista = new JList(opcionesDomicilio);
		JScrollPane lista2 = new JScrollPane(lista);
		panelComponentes.add(lista2);
		
		
		/*JTextArea
		 * 
		JTextArea domicilio = new JTextArea(5, 30);	
		
		JScrollPane scrollPane = new JScrollPane(domicilio);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelComponentes.add(scrollPane);
		
		*/
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
		
	}
	
	
		private void validarFormulario() {

		    lblErrorMatricula.setText("");
		    lblErrorApellidoMaterno.setText("");
		    lblErrorApellidoPaterno.setText("");
		    lblErrorNombre.setText("");
		    lblErrorNumeroContacto.setText("");
		    lblErrorSexo.setText("");
		    lblErrorGrupo.setText("");
		    lblErrorContactoEmergencia.setText("");
		    
		    lblErrorParentesco.setText("");
		    lblErrorDomicilio.setText("");

		    boolean validacion = true;

		    if (!validateMatricula()) {
		    	validacion = false;
		    } 
		    
		    if (!validateApellidoPaterno()) {
		    	validacion = false;
		    } 
		    
		    if (!validateApellidoMaterno()) {
		    	validacion = false;
		    }
		    if (!validateSexo()) {
		    	validacion = false;
		    }
		    if (!validateGrupo()) {
		    	validacion = false;
		    }
		    if (!validateNumeroEmergencia()) {
		    	validacion = false;
		    }
		    if (!validateParentesco()) {
		    	validacion = false;
		    }
		    if (!validateDomicilio()) {
		    	validacion = false;
		    }
		    
		    if (!validateNombre()) {
		    	validacion = false;
		    }
		    if (!validateContactoEmergencia()) {
		    	validacion = false;
		    }
		    
		    if (!validateDomicilio()) {
		    	validacion = false;
		    }
		    
		    if(!validateParentesco()) {
		    	validacion = false;
		    }
		    
		    
		    if (validacion) {
		        JOptionPane.showMessageDialog(this, "Alumno registrado correctamente");
		        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas registrar otro alumno?");

		    	if(opcion == JOptionPane.YES_OPTION) {
					new Formulario();
					dispose();
				}		}
		    }
	    	
	
	private void crearBotones() {
	    JPanel panelBoton = new JPanel();
	    panelBoton.setBackground(Color.WHITE);
	    
	    JButton botonRegistro = new JButton("REGISTRAR ALUMNO");
	    botonRegistro.setBackground(Color.WHITE);
	    botonRegistro.setForeground(Color.BLACK);
	    
	   
	    panelBoton.add(botonRegistro);
	    botonRegistro.addActionListener(e -> validarFormulario());
	    add(panelBoton, BorderLayout.SOUTH);
	    
	    JButton btnRegresar = new JButton("REGRESAR");
	    btnRegresar.setBackground(Color.WHITE);
	    btnRegresar.setForeground(Color.BLACK);
	    btnRegresar.addActionListener(e -> {
			
		int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro que deseas regresar? Se perderán todos los datos");
		
		if(opcion == JOptionPane.YES_OPTION) {
			new LoginWindow();
			dispose();
		}
		
		});	
	    
		JButton botonCierre = new JButton("CERRAR");
		botonCierre.setBackground(Color.WHITE);
		botonCierre.setForeground(Color.BLACK);
		botonCierre.setToolTipText("Cierre de ventana");
		botonCierre.addActionListener(e ->{
		
		int opcionCierre = JOptionPane.showConfirmDialog(this, "¿Estas seguro que deseas cerrar?");
		
		if(opcionCierre == JOptionPane.YES_OPTION) {
			dispose();
		}

		});
	    

	    panelBoton.add(btnRegresar);
	    panelBoton.add(botonCierre);
	    
	    
	}

	
	
}
