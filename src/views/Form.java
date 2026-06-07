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
import models.Student;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Form extends JFrame{
	
	private JTextField txtEnrollment;
	private JTextField txtMotherLastName;
	private JTextField txtFatherLastName;
	private JTextField txtName;
	
	private JTextField txtEmergencyContact;
	private JTextField txtEmergencyNumber;
	private JTextField txtBirthDate;
	
	private JRadioButton rbFemale;
	private JRadioButton rbMale;
	private ButtonGroup bgGender;
	
	private JRadioButton rbGroupA;
	private JRadioButton rbGroupB;
	private ButtonGroup bgGroup;
	
	private JRadioButton rbFirstYear;
	private JRadioButton rbSecondYear;
	private JRadioButton rbThirdYear;
	private ButtonGroup bgYear;
	
	private JComboBox<String> cbRelationship;
	private JComboBox<String> cbAddress;

	private JLabel lblEnrollmentError;
	private JLabel lblMotherLastNameError;
	private JLabel lblFatherLastNameError;
	private JLabel lblNameError;
	
	private JLabel lblEmergencyContactError;
	private JLabel lblEmergencyNumberError;
	private JLabel lblGenderError;
	private JLabel lblGroupError;
	private JLabel lblBirthDateError;
	private JLabel lblYearError;
	
	private JLabel lblRelationshipError;
	private JLabel lblAddressError;
	
	private JButton btnRegister;
	
	public Form() {
		
		setLayout(new BorderLayout());
		setSize(400, 600); //lo reemplazo por pack
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(100,100);
		setResizable(false);
		setTitle("LISTA DE ALUMNOS");

		setLocationRelativeTo(null);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image icon = toolkit.getImage("src/img/icono.png");
		setIconImage(icon);
		
		initializeComponents();
		createButtons();
		
		//setVisible(true);
	}
	
		
	public JTextField getTxtEnrollment() {
		return txtEnrollment;
	}

	public JTextField getTxtMotherLastName() {
		return txtMotherLastName;
	}

	public JTextField getTxtFatherLastName() {
		return txtFatherLastName;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtEmergencyContact() {
		return txtEmergencyContact;
	}

	public JTextField getTxtEmergencyNumber() {
		return txtEmergencyNumber;
	}
	
	public JTextField getTxtBirthDate() {
		return txtBirthDate;
	}
	
	public JRadioButton getRbFemale() {
		return rbFemale;
	}

	public JRadioButton getRbMale() {
		return rbMale;
	}

	public JRadioButton getRbGroupA() {
		return rbGroupA;
	}

	public JRadioButton getRbGroupB() {
		return rbGroupB;
	}	
	
	public JRadioButton getRbFirstYear() {
		return rbFirstYear;
	}
	
	public JRadioButton getRbSecondYear() {
		return rbSecondYear;
	}
	
	public JRadioButton getRbThirdYear() {
		return rbThirdYear;
	}

	public JComboBox<String> getCbRelationship() {
		return cbRelationship;
	}

	public JComboBox<String> getCbAddress() {
		return cbAddress;
	}
	
	public JButton getBtnRegister(){
	    return btnRegister;
	}

	public void setEnrollmentError(String msg) {
	    lblEnrollmentError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblEnrollmentError.setText(msg);
	}

	public void setNameError(String msg) {
	    lblNameError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblNameError.setText(msg);
	}

	public void setFatherLastNameError(String msg) {
	    lblFatherLastNameError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblFatherLastNameError.setText(msg);
	}

	public void setMotherLastNameError(String msg) {
	    lblMotherLastNameError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblMotherLastNameError.setText(msg);
	}

	public void setGenderError(String msg) {
	    lblGenderError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblGenderError.setText(msg);
	}

	public void setGroupError(String msg) {
	    lblGroupError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblGroupError.setText(msg);
	}
	
	public void setYearError(String msg) {
	    lblYearError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblYearError.setText(msg);
	}

	public void setRelationshipError(String msg) {
	    lblRelationshipError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblRelationshipError.setText(msg);
	}

	public void setAddressError(String msg) {
		lblAddressError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblAddressError.setText(msg);
	}

	public void setEmergencyContactError(String msg) {
		lblEmergencyContactError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblEmergencyContactError.setText(msg);
	}

	public void setBirthDateError(String msg) {
		lblBirthDateError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblBirthDateError.setText(msg);
	}

	public void setEmergencyNumberError(String msg) {
		lblEmergencyNumberError.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	    lblEmergencyNumberError.setText(msg);
	}
	
	//=================================================================================================================================================================
	public void resetErrors() {
		lblEnrollmentError.setText("");
		lblMotherLastNameError.setText("");
		lblFatherLastNameError.setText("");
		lblNameError.setText("");
		lblEmergencyNumberError.setText("");
		lblGenderError.setText("");
		lblGroupError.setText("");
		lblYearError.setText("");
		lblEmergencyContactError.setText("");
		lblRelationshipError.setText("");
		lblAddressError.setText("");
		lblBirthDateError.setText("");
	}
	//=================================================================================================================================================================
	public void initializeComponents() {
		
		
		JLabel lblTitle = new JLabel("REGISTRO DEL ALUMNO");
		add(lblTitle, BorderLayout.NORTH);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitle.setFont(new Font("Times New Roman", Font.ITALIC, 15));


		JPanel componentsPanel = new JPanel();
		componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.Y_AXIS));
		componentsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//JCheckBox chkAceptoCondiciones = new JCheckBox("", true);
		//(chkAceptoCondiciones);
		JLabel lblEnrollment = new JLabel("Matricula:");
		lblEnrollment.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblEnrollment);
	    lblEnrollment.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtEnrollment = new JTextField(30);
		
		txtEnrollment.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        txtEnrollment.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        txtEnrollment.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
		txtEnrollment.setMaximumSize(txtEnrollment.getPreferredSize()); 
		txtEnrollment.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(txtEnrollment);
		
		lblEnrollmentError = new JLabel();
		lblEnrollmentError.setForeground(Color.RED);
		lblEnrollmentError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblEnrollmentError);
			
		JLabel lblFatherLastName = new JLabel("Apellido Paterno:");
		lblFatherLastName.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblFatherLastName);
	    lblFatherLastName.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtFatherLastName = new JTextField(30);
		txtFatherLastName.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtFatherLastName.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtFatherLastName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
		txtFatherLastName.setMaximumSize(txtFatherLastName.getPreferredSize()); 
		txtFatherLastName.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(txtFatherLastName);
		
		
		lblFatherLastNameError = new JLabel();
		lblFatherLastNameError.setForeground(Color.RED);
		lblFatherLastNameError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblFatherLastNameError);
		
		
		JLabel lblMotherLastName = new JLabel("Apellido Materno:");
		lblMotherLastName.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblMotherLastName);
	    lblMotherLastName.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtMotherLastName = new JTextField(30);
		txtMotherLastName.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtMotherLastName.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtMotherLastName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
		txtMotherLastName.setMaximumSize(txtMotherLastName.getPreferredSize()); 
		txtMotherLastName.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(txtMotherLastName);
		
		lblMotherLastNameError = new JLabel();
		lblMotherLastNameError.setForeground(Color.RED);
		lblMotherLastNameError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblMotherLastNameError);
		
		
		
	
		JLabel lblStudentName = new JLabel("Nombre");
		lblStudentName.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblStudentName);
	    lblStudentName.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtName = new JTextField(30);
		txtName.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        txtName.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        txtName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
		txtName.setMaximumSize(txtName.getPreferredSize()); 
		txtName.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(txtName);
		
		lblNameError = new JLabel();
		lblNameError.setForeground(Color.RED);
		lblNameError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblNameError);

		JLabel lblBirthDate = new JLabel("Fecha de nacimiento:");
		lblBirthDate.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblBirthDate);
		lblBirthDate.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		
		txtBirthDate = new JTextField(30);
		txtBirthDate.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtBirthDate.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtBirthDate.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});
		
		txtBirthDate.setMaximumSize(txtBirthDate.getPreferredSize()); 
		txtBirthDate.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(txtBirthDate);
		
		lblBirthDateError = new JLabel();
		lblBirthDateError.setForeground(Color.RED);
		lblBirthDateError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblBirthDateError);
		
		JPanel genderPanel = new JPanel();
		genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));

		JPanel genderGroupPanel = new JPanel();
		genderGroupPanel.setLayout(new GridLayout(4, 3, 10, 5));

		JLabel gender = new JLabel("Sexo:");
		JLabel group = new JLabel("Grupo:");
		JLabel year = new JLabel("Año:");

		gender.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		group.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		year.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		rbFemale = new JRadioButton("Mujer");
		rbMale = new JRadioButton("Hombre");

		rbFemale.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		rbMale.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		rbGroupA = new JRadioButton("A");
		rbGroupB = new JRadioButton("B");

		rbGroupA.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		rbGroupB.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		rbFirstYear = new JRadioButton("1");
		rbSecondYear = new JRadioButton("2");
		rbThirdYear = new JRadioButton("3");

		rbFirstYear.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		rbSecondYear.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		rbThirdYear.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		bgGender = new ButtonGroup();
		bgGender.add(rbFemale);
		bgGender.add(rbMale);

		bgGroup = new ButtonGroup();
		bgGroup.add(rbGroupA);
		bgGroup.add(rbGroupB);

		bgYear = new ButtonGroup();
		bgYear.add(rbFirstYear);
		bgYear.add(rbSecondYear);
		bgYear.add(rbThirdYear);

		genderGroupPanel.add(gender);
		genderGroupPanel.add(group);
		genderGroupPanel.add(year);

		genderGroupPanel.add(rbFemale);
		genderGroupPanel.add(rbGroupA);
		genderGroupPanel.add(rbFirstYear);

		genderGroupPanel.add(rbMale);
		genderGroupPanel.add(rbGroupB);
		genderGroupPanel.add(rbSecondYear);

		genderGroupPanel.add(new JLabel(""));
		genderGroupPanel.add(new JLabel(""));
		genderGroupPanel.add(rbThirdYear);

		genderGroupPanel.setAlignmentX(LEFT_ALIGNMENT);

		componentsPanel.add(genderGroupPanel);

		lblGenderError = new JLabel();
		lblGenderError.setForeground(Color.RED);
		lblGenderError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblGenderError);

		lblGroupError = new JLabel();
		lblGroupError.setForeground(Color.RED);
		lblGroupError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblGroupError);

		lblYearError = new JLabel();
		lblYearError.setForeground(Color.RED);
		lblYearError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblYearError);

		genderGroupPanel.setMaximumSize(genderGroupPanel.getPreferredSize());
		componentsPanel.add(Box.createVerticalStrut(15));

		JLabel emergencyContactName = new JLabel("Nombre del Contacto de Emergencia:");
		emergencyContactName.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(emergencyContactName);

		emergencyContactName.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		txtEmergencyContact = new JTextField(30);

		txtEmergencyContact.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtEmergencyContact.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtEmergencyContact.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});

		txtEmergencyContact.setMaximumSize(txtEmergencyContact.getPreferredSize());
		txtEmergencyContact.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(txtEmergencyContact);

		lblEmergencyContactError = new JLabel();
		lblEmergencyContactError.setForeground(Color.RED);
		lblEmergencyContactError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblEmergencyContactError);



		JLabel emergencyContactNumber = new JLabel("Numero del Contacto de Emergencia:");
		emergencyContactNumber.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(emergencyContactNumber);

		emergencyContactNumber.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		txtEmergencyNumber = new JTextField(30);

		txtEmergencyNumber.addFocusListener(new FocusAdapter() {

		    @Override
		    public void focusGained(FocusEvent e) {
		    	txtEmergencyNumber.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	txtEmergencyNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		    }

		});

		lblEmergencyNumberError = new JLabel();
		lblEmergencyNumberError.setForeground(Color.RED);
		lblEmergencyNumberError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblEmergencyNumberError);

		txtEmergencyNumber.setMaximumSize(txtEmergencyNumber.getPreferredSize());
		txtEmergencyNumber.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(txtEmergencyNumber);

		JLabel relationship = new JLabel("Parentesco con el alumno:");
		relationship.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(relationship);

		relationship.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		String[] relationshipOptions = {
				"Seleccione",
				"Padre",
				"Madre",
				"Hermana/Hermano",
				"Tio/Tia",
				"Abuelo/Abuela",
				"Otro"
		};

		cbRelationship = new JComboBox<String>(relationshipOptions);

		cbRelationship.setSelectedIndex(0);
		cbRelationship.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		lblRelationshipError = new JLabel();
		lblRelationshipError.setForeground(Color.RED);
		lblRelationshipError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblRelationshipError);

		cbRelationship.setMaximumSize(txtEmergencyNumber.getPreferredSize());
		cbRelationship.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(cbRelationship);

		JLabel address = new JLabel("Domicilio (calle y numero):");
		address.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(address);

		address.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		String[] addressOptions = {
				"Seleccione",
				"Calle Altamirano Allende 1398",
				"Calle Gomez Farias 4367",
				"Calle Rosa Maria 2345",
				"Calle Diana Laura 2365",
				"Calle Ficus 3489",
				"Calle Aquiles Serdan 1993",
				"Calle Francisco y Madero 3740",
				"Calle Colosio 2521",
				"Calle 3 de Mayo 9757"
		};

		cbAddress = new JComboBox<String>(addressOptions);

		cbAddress.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		lblAddressError = new JLabel();
		lblAddressError.setForeground(Color.RED);
		lblAddressError.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(lblAddressError);

		cbAddress.setSelectedIndex(0);
		cbAddress.setMaximumSize(txtEmergencyNumber.getPreferredSize());
		cbAddress.setAlignmentX(LEFT_ALIGNMENT);
		componentsPanel.add(cbAddress);

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

		JScrollPane scroll = new JScrollPane(componentsPanel);
		scroll.setHorizontalScrollBar(null);

		//add(scroll);
		add(scroll, BorderLayout.CENTER);

		}
		//=================================================================================================================================================================
		public void clearForm() {

		    txtEnrollment.setText("");
		    txtEnrollment.setEditable(true);

		    txtName.setText("");
		    txtFatherLastName.setText("");
		    txtMotherLastName.setText("");
		    txtEmergencyContact.setText("");
		    txtEmergencyNumber.setText("");

		    bgGender.clearSelection();
		    bgGroup.clearSelection();
		    bgYear.clearSelection();

		    cbRelationship.setSelectedIndex(0);
		    cbAddress.setSelectedIndex(0);
		}
		//=================================================================================================================================================================
		public void loadData(Student student) {

			txtEnrollment.setText(student.getEnrollment());
			txtEnrollment.setEditable(false);

			txtEnrollment.addMouseListener(new java.awt.event.MouseAdapter() {

			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent e) {

			        if (!txtEnrollment.isEditable()) {

			            JOptionPane.showMessageDialog(
			                null,
			                "La matrícula no se puede editar",
			                "Edición bloqueada",
			                JOptionPane.WARNING_MESSAGE
			            );
			        }
			    }
			});

		    txtName.setText(student.getName());
		    txtFatherLastName.setText(student.getFatherLastName());
		    txtMotherLastName.setText(student.getMotherLastName());

		    try {

		        LocalDate birthDate = LocalDate.parse(student.getBirthDate());

		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		        txtBirthDate.setText(birthDate.format(formatter));

		    } catch (Exception e) {

		        txtBirthDate.setText(
		                student.getBirthDate()
		        );
		    }

		    txtEmergencyContact.setText(student.getEmergencyContact());
		    txtEmergencyNumber.setText(student.getEmergencyNumber());

		    /*Se utiliza equals PORQUE en FormularioController tenemos view.getRbA().isSelected() ? "A" : "B"
		    Y en formulario si se pone grupo = char  se mezclaria un string y un char, lo que marca error*/

		    if ("M".equals(student.getGender())) {
		    	rbFemale.setSelected(true);
		    } else {
		    	rbMale.setSelected(true);
		    }

		    if ("A".equals(student.getGroup())) {
		    	rbGroupA.setSelected(true);
		    } else {
		    	rbGroupB.setSelected(true);
		    }

		    if ("1".equals(student.getYear())) {
		    	rbFirstYear.setSelected(true);
		    } else if ("2".equals(student.getYear())) {
		    	rbSecondYear.setSelected(true);
		    } else {
		    	rbThirdYear.setSelected(true);
		    }

		    cbRelationship.setSelectedItem(student.getRelationship());
		    cbAddress.setSelectedItem(student.getAddress());

		}
		//=================================================================================================================================================================
		private void createButtons() {

		    JPanel buttonPanel = new JPanel();
		    buttonPanel.setBackground(Color.WHITE);

		    btnRegister = new JButton("REGISTRAR ALUMNO");
		    btnRegister.setBackground(Color.WHITE);
		    btnRegister.setForeground(Color.BLACK);

		    buttonPanel.add(btnRegister);

		    add(buttonPanel, BorderLayout.SOUTH);


			JButton btnClose = new JButton("CERRAR");

			btnClose.setBackground(Color.WHITE);
			btnClose.setForeground(Color.BLACK);

			btnClose.setToolTipText("Cierre de ventana");

			btnClose.addActionListener(e -> {

				int closeOption = JOptionPane.showConfirmDialog(
						this,
						"¿Estas seguro que deseas cerrar?"
				);

				if(closeOption == JOptionPane.YES_OPTION) {
					dispose();
				}

			});

			buttonPanel.add(btnClose);
		}

}