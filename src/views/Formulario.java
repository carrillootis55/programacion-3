package views;
/*KeyListener cuando el usuario presiona teclas
 * FocusListener Cuando un componente gana o pierde foco
 * WindowListener Eventos de la ventana
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

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

import controllers.LoginController;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	private JButton botonRegistro;
	
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
		
		setVisible(true);
	}
	
	
	public JTextField getTxtMatricula() {
		return txtMatricula;
	}

	public JTextField getTxtApellidoMaterno() {
		return txtApellidoMaterno;
	}

	public JTextField getTxtApellidoPaterno() {
		return txtApellidoPaterno;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtContactoEmergencia() {
		return txtContactoEmergencia;
	}

	public JTextField getTxtNumeroEmergencia() {
		return txtNumeroEmergencia;
	}

	public JRadioButton getRbMujer() {
		return rbMujer;
	}

	public JRadioButton getRbHombre() {
		return rbHombre;
	}

	public JRadioButton getRbA() {
		return rbA;
	}

	public JRadioButton getRbB() {
		return rbB;
	}	

	public JComboBox<String> getParentescoAlumno() {
		return parentescoAlumno;
	}

	public JComboBox<String> getDomicilio() {
		return domicilio;
	}
	public JButton getBtnRegistrar(){
	    return botonRegistro;
	}

	public void setErrorMatricula(String msg) {
	    lblErrorMatricula.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorMatricula.setText(msg);
	}

	public void setErrorNombre(String msg) {
	    lblErrorNombre.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorNombre.setText(msg);
	}

	public void setErrorApellidoPaterno(String msg) {
	    lblErrorApellidoPaterno.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorApellidoPaterno.setText(msg);
	}

	public void setErrorApellidoMaterno(String msg) {
	    lblErrorApellidoMaterno.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorApellidoMaterno.setText(msg);
	}

	public void setErrorSexo(String msg) {
	    lblErrorSexo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorSexo.setText(msg);
	}

	public void setErrorGrupo(String msg) {
	    lblErrorGrupo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorGrupo.setText(msg);
	}

	public void setErrorParentesco(String msg) {
	    lblErrorParentesco.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorParentesco.setText(msg);
	}

	public void setErrorDomicilio(String msg) {
		lblErrorDomicilio.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorDomicilio.setText(msg);
	}

	public void setErrorContacto(String msg) {
		lblErrorContactoEmergencia.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorContactoEmergencia.setText(msg);
	}

	public void setErrorNumero(String msg) {
		lblErrorNumeroContacto.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblErrorNumeroContacto.setText(msg);
	}
	
	
	public void resetErrors() {
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
		
		txtMatricula.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        txtMatricula.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        txtMatricula.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
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
		txtApellidoPaterno.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtApellidoPaterno.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtApellidoPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
		
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
		txtApellidoMaterno.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtApellidoMaterno.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtApellidoMaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
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
		txtNombre.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        txtNombre.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
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
		txtContactoEmergencia.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtContactoEmergencia.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtContactoEmergencia.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
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
		txtNumeroEmergencia.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtNumeroEmergencia.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtNumeroEmergencia.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
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
	

	
	private void crearBotones() {
	    JPanel panelBoton = new JPanel();
	    panelBoton.setBackground(Color.WHITE);
	    
	    botonRegistro = new JButton("REGISTRAR ALUMNO");
	    botonRegistro.setBackground(Color.WHITE);
	    botonRegistro.setForeground(Color.BLACK);
	    
	   
	    panelBoton.add(botonRegistro);
	    add(panelBoton, BorderLayout.SOUTH);
	    
	    JButton btnRegresar = new JButton("REGRESAR");
	    btnRegresar.setBackground(Color.WHITE);
	    btnRegresar.setForeground(Color.BLACK);
	    btnRegresar.addActionListener(e -> {
			
		int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro que deseas regresar? Se perderán todos los datos");
		
		if(opcion == JOptionPane.YES_OPTION) {
			//new LoginWindow();//con este desactivo o sin estar regresa al mainView
			//new LoginWindow(); //con este activo regresa a login
			//dispose();
			
			LoginWindow ventana = new LoginWindow();
	        LoginView vista = new LoginView(ventana);
	        new LoginController(vista);

	        ventana.setContentPane(vista);
	        ventana.pack();
	        ventana.setLocationRelativeTo(null);
	        ventana.setVisible(true);

	        dispose(); //Cerrar formulario actual
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
