package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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

import models.Maestro;

public class FormularioMaestro extends JFrame {

    private JTextField txtNombre;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtEdad;
    private JTextField txtMaestria;

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

    private JButton btnGuardar;

    public FormularioMaestro() {

        setTitle("FORMULARIO MAESTRO");

        setLayout(new BorderLayout());

        setSize(500, 650);

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

        titulo.setFont(new Font("Times New Roman", Font.BOLD, 18));

        add(titulo, BorderLayout.NORTH);

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        JLabel nombre = new JLabel("Nombre:");

        nombre.setFont(new Font("Times New Roman", Font.BOLD, 15));

        panel.add(nombre);

        txtNombre = new JTextField();

        estiloFocus(txtNombre);

        panel.add(txtNombre);

        lblErrorNombre = crearError();

        panel.add(lblErrorNombre);



        JLabel email = new JLabel("Email:");

        email.setFont(new Font("Times New Roman", Font.BOLD, 15));

        panel.add(email);

        txtEmail = new JTextField();

        estiloFocus(txtEmail);

        panel.add(txtEmail);

        lblErrorEmail = crearError();

        panel.add(lblErrorEmail);

  

        JLabel password = new JLabel("Contraseña:");

        password.setFont(new Font("Times New Roman", Font.BOLD, 15));

        panel.add(password);

        txtPassword = new JPasswordField();

        estiloFocus(txtPassword);

        panel.add(txtPassword);

        lblErrorPassword = crearError();

        panel.add(lblErrorPassword);



        JLabel edad = new JLabel("Edad:");

        edad.setFont(new Font("Times New Roman", Font.BOLD, 15));

        panel.add(edad);

        txtEdad = new JTextField();

        estiloFocus(txtEdad);

        panel.add(txtEdad);

        lblErrorEdad = crearError();

        panel.add(lblErrorEdad);

  

        JLabel maestria = new JLabel("Maestría:");

        maestria.setFont(new Font("Times New Roman", Font.BOLD, 15));

        panel.add(maestria);

        txtMaestria = new JTextField();

        estiloFocus(txtMaestria);

        panel.add(txtMaestria);

        lblErrorMaestria = crearError();

        panel.add(lblErrorMaestria);

        panel.add(Box.createVerticalStrut(15));

 

        JPanel panelOpciones = new JPanel();

        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.X_AXIS));

    

        JPanel panelSexo = new JPanel();

        panelSexo.setLayout(new BoxLayout(panelSexo, BoxLayout.Y_AXIS));

        JLabel sexo = new JLabel("Sexo:");

        sexo.setFont(new Font("Times New Roman", Font.BOLD, 15));

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

        grupo.setFont(new Font("Times New Roman", Font.BOLD, 15));

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

        anio.setFont(new Font("Times New Roman", Font.BOLD, 15));

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

        panel.add(panelOpciones);

        JScrollPane scroll = new JScrollPane(panel);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        add(scroll, BorderLayout.CENTER);
    }

    private JLabel crearError() {

        JLabel lbl = new JLabel(" ");

        lbl.setForeground(Color.RED);

        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 13));

        lbl.setAlignmentX(LEFT_ALIGNMENT);

        return lbl;
    }

    private void estiloFocus(JTextField txt) {

        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        txt.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                txt.setBorder(
                        BorderFactory.createLineBorder(Color.BLUE, 2)
                );
            }

            @Override
            public void focusLost(FocusEvent e) {

                txt.setBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1)
                );
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


    public void cargarDatos(Maestro maestro) {

        txtNombre.setText(maestro.getNombre());

        txtEmail.setText(maestro.getEmail());

        txtEdad.setText(String.valueOf(maestro.getEdad()));

        txtMaestria.setText(maestro.getMaestria());

        if ("M".equals(maestro.getSexo())) {

            rbMujer.setSelected(true);

        } else {

            rbHombre.setSelected(true);
        }

        if ("A".equals(maestro.getGrupo())) {

            rbA.setSelected(true);

        } else {

            rbB.setSelected(true);
        }

        if ("1".equals(maestro.getAnio())) {

            rb1.setSelected(true);

        } else if ("2".equals(maestro.getAnio())) {

            rb2.setSelected(true);

        } else {

            rb3.setSelected(true);
        }
    }
}