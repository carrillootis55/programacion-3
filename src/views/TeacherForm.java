package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Teacher;

public class TeacherForm extends JFrame {

	private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtAge;
    private JTextField txtMasterDegree;
    private JTextField txtBirthDate;
    
    private JRadioButton rbFemale;
    private JRadioButton rbMale;
    private ButtonGroup bgGender;

    private JRadioButton rbA;
    private JRadioButton rbB;
    private ButtonGroup bgGroup;

    private JRadioButton rbFirstYear;
    private JRadioButton rbSecondYear;
    private JRadioButton rbThirdYear;
    private ButtonGroup bgYear;

    private JLabel lblNameError;
    private JLabel lblEmailError;
    private JLabel lblPasswordError;
    private JLabel lblAgeError;
    private JLabel lblMasterDegreeError;
    private JLabel lblBirthDateError;

    private JLabel lblGenderError;
    private JLabel lblGroupError;
    private JLabel lblYearError;
    private JButton btnSave;
    
    public TeacherForm() {

        setTitle("FORMULARIO MAESTRO");

        setLayout(new BorderLayout());

        //setSize(400, 550);
        
        setSize(450, 600);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Image icon = tk.getImage("src/img/icono.png");

        setIconImage(icon);

        initializeComponents();

        createButtons();
    }

    private void initializeComponents() {

        JLabel title = new JLabel("REGISTRO DE MAESTRO");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //NOMBRE
        JPanel rowName = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        JLabel name = new JLabel("Nombre:");
        name.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        //name.setPreferredSize(new Dimension(90, 10)); 
        name.setPreferredSize(new Dimension(140, 25));
        
        txtName = new JTextField(22);
        
        focusStyle(txtName);
        
        rowName.add(name);
        rowName.add(txtName);
        panel.add(rowName);

        lblNameError = createError();
        
        JPanel rowNameError = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0)); 
        
        rowNameError.add(lblNameError);
        
        panel.add(rowNameError);
        panel.add(Box.createVerticalStrut(5));

        //FECHA NACIMIENTO
        JPanel rowBirthDate = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        
        JLabel birthDate = new JLabel("Fecha de nacimiento:");
        
        birthDate.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        //birthDate.setPreferredSize(new Dimension(90, 10)); 
        
        birthDate.setPreferredSize(new Dimension(140, 25));

        txtBirthDate = new JTextField(22);

        focusStyle(txtBirthDate);
        
        rowBirthDate.add(birthDate);
        rowBirthDate.add(txtBirthDate);
        
        panel.add(rowBirthDate);

        lblBirthDateError = createError();
        
        JPanel rowBirthDateError = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0)); 
        
        rowBirthDateError.add(lblBirthDateError);
        
        panel.add(rowBirthDateError);
        panel.add(Box.createVerticalStrut(5));
        
        
        //EMAIL
        JPanel rowEmail = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        
        JLabel email = new JLabel("Email:");
        
        email.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        //email.setPreferredSize(new Dimension(90, 10));
        
        email.setPreferredSize(new Dimension(140, 25));
        
        txtEmail = new JTextField(22);
        
        focusStyle(txtEmail);
        
        rowEmail.add(email);
        rowEmail.add(txtEmail);
        
        panel.add(rowEmail);

        lblEmailError = createError();
        
        JPanel rowEmailError = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0));
        
        rowEmailError.add(lblEmailError);
        
        panel.add(rowEmailError);
        panel.add(Box.createVerticalStrut(5));

        //CONTRASEÑA
        JPanel rowPassword = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        
        JLabel password = new JLabel("Contraseña:");
        
        password.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        //password.setPreferredSize(new Dimension(90, 10));
        
        password.setPreferredSize(new Dimension(140, 25));
        
        txtPassword = new JPasswordField(22);
        
        focusStyle(txtPassword);
        
        rowPassword.add(password);
        rowPassword.add(txtPassword);
        
        panel.add(rowPassword);

        lblPasswordError = createError();
        
        JPanel rowPasswordError = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0));
        
        rowPasswordError.add(lblPasswordError);
        
        panel.add(rowPasswordError);
        panel.add(Box.createVerticalStrut(5));

        //EDAD
        JPanel rowAge = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        
        JLabel age = new JLabel("Edad:");
        
        age.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        //age.setPreferredSize(new Dimension(90, 10));
        
        age.setPreferredSize(new Dimension(140, 25));
        
        txtAge = new JTextField(22);
        
        focusStyle(txtAge);
        
        rowAge.add(age);
        rowAge.add(txtAge);
        
        panel.add(rowAge);

        lblAgeError = createError();
        
        JPanel rowAgeError = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0));
        
        rowAgeError.add(lblAgeError);
        
        panel.add(rowAgeError);
        panel.add(Box.createVerticalStrut(5));

        //MAESTRÍA
        JPanel rowMasterDegree = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        
        JLabel masterDegree = new JLabel("Maestría:");
        
        masterDegree.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        //masterDegree.setPreferredSize(new Dimension(90, 10));
        
        masterDegree.setPreferredSize(new Dimension(140, 25));
        
        txtMasterDegree = new JTextField(22);
        
        focusStyle(txtMasterDegree);
        
        rowMasterDegree.add(masterDegree);
        rowMasterDegree.add(txtMasterDegree);
        
        panel.add(rowMasterDegree);

        lblMasterDegreeError = createError();
        
        JPanel rowMasterDegreeError = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 70, 0));
        
        rowMasterDegreeError.add(lblMasterDegreeError);
        
        panel.add(rowMasterDegreeError);
        
        panel.add(Box.createVerticalStrut(5));

        //SECCIÓN DE RADIO BUTTONS
        JPanel optionsPanel = new JPanel();
        
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.setAlignmentX(CENTER_ALIGNMENT); 

        JPanel genderPanel = new JPanel();
        
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));
        
        JLabel gender = new JLabel("Sexo:");
        
        gender.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        rbFemale = new JRadioButton("Mujer");
        rbMale = new JRadioButton("Hombre");
        
        bgGender = new ButtonGroup();
        
        bgGender.add(rbFemale);
        bgGender.add(rbMale);
        
        genderPanel.add(gender);
        genderPanel.add(rbFemale);
        genderPanel.add(rbMale);

        JPanel groupPanel = new JPanel();
        
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
        
        JLabel group = new JLabel("Grupo:");
        
        group.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        rbA = new JRadioButton("A");
        rbB = new JRadioButton("B");
        
        bgGroup = new ButtonGroup();
        
        bgGroup.add(rbA);
        bgGroup.add(rbB);
        
        groupPanel.add(group);
        groupPanel.add(rbA);
        groupPanel.add(rbB);

        JPanel yearPanel = new JPanel();
        
        yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));
        
        JLabel year = new JLabel("Año:");
        
        year.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        
        rbFirstYear = new JRadioButton("1");
        rbSecondYear = new JRadioButton("2");
        rbThirdYear = new JRadioButton("3");
        
        bgYear = new ButtonGroup();
        
        bgYear.add(rbFirstYear);
        bgYear.add(rbSecondYear);
        bgYear.add(rbThirdYear);
        
        yearPanel.add(year);
        yearPanel.add(rbFirstYear);
        yearPanel.add(rbSecondYear);
        yearPanel.add(rbThirdYear);

        optionsPanel.add(genderPanel);
        optionsPanel.add(Box.createHorizontalStrut(40));
        optionsPanel.add(groupPanel);
        optionsPanel.add(Box.createHorizontalStrut(40));
        optionsPanel.add(yearPanel);

     
        JPanel rowOptions = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        
        rowOptions.add(optionsPanel);
        
        panel.add(rowOptions);
        
        lblGenderError = createError();
        lblGroupError = createError();
        lblYearError = createError();
        
        JPanel rowOptionsErrors = new JPanel();
        
        rowOptionsErrors.setLayout(new BoxLayout(rowOptionsErrors, BoxLayout.Y_AXIS));

        rowOptionsErrors.add(lblGenderError);
        rowOptionsErrors.add(lblGroupError);
        rowOptionsErrors.add(lblYearError);

        panel.add(rowOptionsErrors);

        
        //componente invisible que absorberá el espacio sobrante del scroll y empujará lo demás hacia arriba
        panel.add(Box.createVerticalGlue());
        
        JScrollPane scroll = new JScrollPane(panel);
        
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null); 
        
        add(scroll, BorderLayout.CENTER);
    }
    
    private JLabel createError() {

        JLabel lbl = new JLabel(" ");

        lbl.setForeground(Color.RED);

        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 13));

        lbl.setAlignmentX(CENTER_ALIGNMENT);

        return lbl;
    }

    private void focusStyle(JTextField txt) {
    	
        Dimension fixedSize = new Dimension(txt.getPreferredSize().width, 25);
        
        txt.setMaximumSize(fixedSize);
        txt.setPreferredSize(fixedSize); 
        
        txt.addFocusListener(new FocusAdapter() {
        	
            @Override
            public void focusGained(FocusEvent e) {
                txt.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                txt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }
        });
    }
    
    private void createButtons() {

        JPanel buttonPanel = new JPanel();

        btnSave = new JButton("GUARDAR MAESTRO");

        JButton btnClose = new JButton("CERRAR");

        btnClose.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea cerrar?"
            );

            if (option == JOptionPane.YES_OPTION) {

                dispose();
            }
        });

        buttonPanel.add(btnSave);

        buttonPanel.add(btnClose);

        add(buttonPanel, BorderLayout.SOUTH);
    }


    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtAge() {
        return txtAge;
    }

    public JTextField getTxtMasterDegree() {
        return txtMasterDegree;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JRadioButton getRbFemale() {
        return rbFemale;
    }

    public JRadioButton getRbMale() {
        return rbMale;
    }

    public JRadioButton getRbA() {
        return rbA;
    }

    public JRadioButton getRbB() {
        return rbB;
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
    

	
	public void setNameError(String msg) {
	    lblNameError.setText(msg);
	}
	
	public void setEmailError(String msg) {
	    lblEmailError.setText(msg);
	}
	
	public void setPasswordError(String msg) {
	    lblPasswordError.setText(msg);
	}
	
	public void setAgeError(String msg) {
	    lblAgeError.setText(msg);
	}
	
	public void setMasterDegreeError(String msg) {
	    lblMasterDegreeError.setText(msg);
	}

	public void setGenderError(String msg) {
		lblGenderError.setText(msg);
	}
	
	public void setGroupError(String msg) {
		lblGroupError.setText(msg);
	}
	
	public void setYearError(String msg) {
		lblYearError.setText(msg);
	}
	
	public JTextField getTxtBirthDate() { 
		return txtBirthDate; 
	}
	
	public void setBirthDateError(String msg) { 
		lblBirthDateError.setText(msg); 
	}
	
	
    public void loadData(Teacher teacher) {

        txtName.setText(teacher.getName());

        txtEmail.setText(teacher.getEmail());

        txtAge.setText(String.valueOf(teacher.getAge()));

        txtMasterDegree.setText(teacher.getMasterDegree());

        String formattedDate = teacher.getBirthDate();

        try {

            java.time.LocalDate date = java.time.LocalDate.parse(formattedDate);

            formattedDate = date.format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
            );

        } catch (Exception e) {
        }

        txtBirthDate.setText(formattedDate);
        
        if ("M".equals(teacher.getGender())) {

            rbFemale.setSelected(true);

        } else {

            rbMale.setSelected(true);
        }

        if ("A".equals(teacher.getGroup())) {

            rbA.setSelected(true);

        } else {

            rbB.setSelected(true);
        }

        if ("1".equals(teacher.getYear())) {

            rbFirstYear.setSelected(true);

        } else if ("2".equals(teacher.getYear())) {

            rbSecondYear.setSelected(true);

        } else {

            rbThirdYear.setSelected(true);
        }
    }

}