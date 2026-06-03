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

	private JTextField txtNombre;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtEdad;
    private JTextField txtMaestria;
    private JTextField txtFechaNacimiento;
    
    private JRadioButton rbMujer;
    private JRadioButton rbHombre;
    private ButtonGroup bgSexo;

    private JRadioButton rbA;
    private JRadioButton rbB;
    private ButtonGroup bgGrupo;

    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private ButtonGroup bgAnio;

    private JLabel lblErrorNombre;
    private JLabel lblErrorEmail;
    private JLabel lblErrorPassword;
    private JLabel lblErrorEdad;
    private JLabel lblErrorMaestria;
    private JLabel lblErrorFechaNacimiento;
    private JButton btnGuardar;
    
    public TeacherForm() {

        setTitle("FORMULARIO MAESTRO");

        setLayout(new BorderLayout());

        setSize(400, 550);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Image icono = tk.getImage("src/img/icono.png");

        setIconImage(icono);

        inicializarComponentes();

        crearBotones();
    }

    private void inicializarComponentes() {

        JLabel titulo = new JLabel("REGISTRO DE MAESTRO");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //NOMBRE
        JPanel filaNombre = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        JLabel nombre = new JLabel("Nombre:");
        nombre.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        nombre.setPreferredSize(new Dimension(90, 10)); 
        txtNombre = new JTextField(22);
        estiloFocus(txtNombre);
        filaNombre.add(nombre);
        filaNombre.add(txtNombre);
        panel.add(filaNombre);

        lblErrorNombre = crearError();
        JPanel filaErrorNombre = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0)); 
        filaErrorNombre.add(lblErrorNombre);
        panel.add(filaErrorNombre);
        panel.add(Box.createVerticalStrut(5));

        //FECHA NACIMIENTO
        JPanel filafechaNacimiento = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        JLabel fechaNacimiento = new JLabel("Fecha de nacimiento:");
        fechaNacimiento.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        fechaNacimiento.setPreferredSize(new Dimension(90, 10)); 

        txtFechaNacimiento = new JTextField(22);

        estiloFocus(txtFechaNacimiento);
        filafechaNacimiento.add(fechaNacimiento);
        filafechaNacimiento.add(txtFechaNacimiento);
        panel.add(filafechaNacimiento);

        lblErrorFechaNacimiento = crearError();
        JPanel filaErrorFechaNacimiento = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0)); 
        filaErrorFechaNacimiento.add(lblErrorFechaNacimiento);
        panel.add(filaErrorFechaNacimiento);
        panel.add(Box.createVerticalStrut(5));
        
        
        //EMAIL
        JPanel filaEmail = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        email.setPreferredSize(new Dimension(90, 10));
        txtEmail = new JTextField(22);
        estiloFocus(txtEmail);
        filaEmail.add(email);
        filaEmail.add(txtEmail);
        panel.add(filaEmail);

        lblErrorEmail = crearError();
        JPanel filaErrorEmail = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0));
        filaErrorEmail.add(lblErrorEmail);
        panel.add(filaErrorEmail);
        panel.add(Box.createVerticalStrut(5));

        //CONTRASEÑA
        JPanel filaPassword = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        JLabel password = new JLabel("Contraseña:");
        password.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        password.setPreferredSize(new Dimension(90, 10));
        txtPassword = new JPasswordField(22);
        estiloFocus(txtPassword);
        filaPassword.add(password);
        filaPassword.add(txtPassword);
        panel.add(filaPassword);

        lblErrorPassword = crearError();
        JPanel filaErrorPassword = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0));
        filaErrorPassword.add(lblErrorPassword);
        panel.add(filaErrorPassword);
        panel.add(Box.createVerticalStrut(5));

        //EDAD
        JPanel filaEdad = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        JLabel edad = new JLabel("Edad:");
        edad.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        edad.setPreferredSize(new Dimension(90, 10));
        txtEdad = new JTextField(22);
        estiloFocus(txtEdad);
        filaEdad.add(edad);
        filaEdad.add(txtEdad);
        panel.add(filaEdad);

        lblErrorEdad = crearError();
        JPanel filaErrorEdad = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 55, 0));
        filaErrorEdad.add(lblErrorEdad);
        panel.add(filaErrorEdad);
        panel.add(Box.createVerticalStrut(5));

        //MAESTRÍA
        JPanel filaMaestria = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        JLabel maestria = new JLabel("Maestría:");
        maestria.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        maestria.setPreferredSize(new Dimension(90, 10));
        txtMaestria = new JTextField(22);
        estiloFocus(txtMaestria);
        filaMaestria.add(maestria);
        filaMaestria.add(txtMaestria);
        panel.add(filaMaestria);

        lblErrorMaestria = crearError();
        JPanel filaErrorMaestria = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 70, 0));
        filaErrorMaestria.add(lblErrorMaestria);
        panel.add(filaErrorMaestria);
        
        panel.add(Box.createVerticalStrut(5));

        //SECCIÓN DE RADIO BUTTONS
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.X_AXIS));
        panelOpciones.setAlignmentX(CENTER_ALIGNMENT); 

        JPanel panelSexo = new JPanel();
        panelSexo.setLayout(new BoxLayout(panelSexo, BoxLayout.Y_AXIS));
        JLabel sexo = new JLabel("Sexo:");
        sexo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        rbMujer = new JRadioButton("Mujer");
        rbHombre = new JRadioButton("Hombre");
        bgSexo = new ButtonGroup();
        bgSexo.add(rbMujer);
        bgSexo.add(rbHombre);
        panelSexo.add(sexo);
        panelSexo.add(rbMujer);
        panelSexo.add(rbHombre);

        JPanel panelGrupo = new JPanel();
        panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.Y_AXIS));
        JLabel grupo = new JLabel("Grupo:");
        grupo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        rbA = new JRadioButton("A");
        rbB = new JRadioButton("B");
        bgGrupo = new ButtonGroup();
        bgGrupo.add(rbA);
        bgGrupo.add(rbB);
        panelGrupo.add(grupo);
        panelGrupo.add(rbA);
        panelGrupo.add(rbB);

        JPanel panelAnio = new JPanel();
        panelAnio.setLayout(new BoxLayout(panelAnio, BoxLayout.Y_AXIS));
        JLabel anio = new JLabel("Año:");
        anio.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        rb1 = new JRadioButton("1");
        rb2 = new JRadioButton("2");
        rb3 = new JRadioButton("3");
        bgAnio = new ButtonGroup();
        bgAnio.add(rb1);
        bgAnio.add(rb2);
        bgAnio.add(rb3);
        panelAnio.add(anio);
        panelAnio.add(rb1);
        panelAnio.add(rb2);
        panelAnio.add(rb3);

        panelOpciones.add(panelSexo);
        panelOpciones.add(Box.createHorizontalStrut(40));
        panelOpciones.add(panelGrupo);
        panelOpciones.add(Box.createHorizontalStrut(40));
        panelOpciones.add(panelAnio);

     
        JPanel filaOpciones = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        filaOpciones.add(panelOpciones);
        panel.add(filaOpciones);
        //componente invisible que absorberá el espacio sobrante del scroll y empujará lo demás hacia arriba
        panel.add(Box.createVerticalGlue());
        
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null); 
        add(scroll, BorderLayout.CENTER);
    }
    private JLabel crearError() {

        JLabel lbl = new JLabel(" ");

        lbl.setForeground(Color.RED);

        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 13));

        lbl.setAlignmentX(CENTER_ALIGNMENT);

        return lbl;
    }

    private void estiloFocus(JTextField txt) {
        Dimension tamanoFijo = new Dimension(txt.getPreferredSize().width, 25);
        txt.setMaximumSize(tamanoFijo);
        txt.setPreferredSize(tamanoFijo); 
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
    private void crearBotones() {

        JPanel panelBotones = new JPanel();

        btnGuardar = new JButton("GUARDAR MAESTRO");

        JButton btnCerrar = new JButton("CERRAR");

        btnCerrar.addActionListener(e -> {

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea cerrar?"
            );

            if (opcion == JOptionPane.YES_OPTION) {

                dispose();
            }
        });

        panelBotones.add(btnGuardar);

        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);
    }


    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtEdad() {
        return txtEdad;
    }

    public JTextField getTxtMaestria() {
        return txtMaestria;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
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

    public JRadioButton getRb1() {
        return rb1;
    }

    public JRadioButton getRb2() {
        return rb2;
    }

    public JRadioButton getRb3() {
        return rb3;
    }

	
	 public void setErrorNombre(String msg) {
	
	     lblErrorNombre.setText(msg);
	 }
	
	 public void setErrorEmail(String msg) {
	
	     lblErrorEmail.setText(msg);
	 }
	
	 public void setErrorPassword(String msg) {
	
	     lblErrorPassword.setText(msg);
	 }
	
	 public void setErrorEdad(String msg) {
	
	     lblErrorEdad.setText(msg);
	 }
	
	 public void setErrorMaestria(String msg) {
	
	     lblErrorMaestria.setText(msg);
	 }

	
	 public void setErrorSexo(String msg) {
	 }
	
	public void setErrorGrupo(String msg) {
		
	}
	
	public void setErrorAnio(String msg) {
	}
	public JTextField getFechaNacimiento() { 
		return txtFechaNacimiento; 
	}
	public void setErrorFechaNacimiento(String msg) { 
		lblErrorFechaNacimiento.setText(msg); 
	}
    public void cargarDatos(Teacher teacher) {

        txtNombre.setText(teacher.getNombre());

        txtEmail.setText(teacher.getEmail());

        txtEdad.setText(String.valueOf(teacher.getEdad()));

        txtMaestria.setText(teacher.getMaestria());

        txtFechaNacimiento.setText(teacher.getFechaNacimiento());
        if ("M".equals(teacher.getSexo())) {

            rbMujer.setSelected(true);

        } else {

            rbHombre.setSelected(true);
        }

        if ("A".equals(teacher.getGrupo())) {

            rbA.setSelected(true);

        } else {

            rbB.setSelected(true);
        }

        if ("1".equals(teacher.getAnio())) {

            rb1.setSelected(true);

        } else if ("2".equals(teacher.getAnio())) {

            rb2.setSelected(true);

        } else {

            rb3.setSelected(true);
        }
    }

}