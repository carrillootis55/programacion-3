package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Maestro;

public class AdminView extends JPanel {

    private Maestro admin;

    public AdminView() {
        this(new Maestro());
    }

    public AdminView(Maestro admin) {

        this.admin = admin;

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout());

        removeAll();

        JPanel panelImagenContenedor =new JPanel(new GridBagLayout());

        panelImagenContenedor.setPreferredSize(new Dimension(400, 400));

        panelImagenContenedor.setBorder(BorderFactory.createEmptyBorder(0,100, 0,10));
        String rutaImagen;

        if (admin.getSexo() != null&&admin.getSexo().equalsIgnoreCase("M")) {
            rutaImagen = "src/img/admin1.png";

        } else {
            rutaImagen = "src/img/admin2.png";
        }

        ImageIcon originalIcon =new ImageIcon(rutaImagen);

        Image imgEscalada =originalIcon.getImage().getScaledInstance(300, 300,Image.SCALE_SMOOTH );

        JLabel imageLabel =new JLabel(new ImageIcon(imgEscalada));

        panelImagenContenedor.add(imageLabel);
        
        JPanel panelDer =new JPanel(new GridBagLayout());
        panelDer.setOpaque(false);

        GridBagConstraints gbc =new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titulo = new JLabel("ADMINISTRADOR");
        titulo.setFont(new java.awt.Font(  "Times New Roman",java.awt.Font.BOLD, 24  ) );

        panelDer.add(titulo, gbc);

        gbc.gridwidth = 1;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDer.add(new JLabel("Nombre: "),gbc);
        gbc.gridx = 1;
        panelDer.add(new JLabel( admin.getNombre() != null? admin.getNombre(): "No asignado"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelDer.add( new JLabel("Edad: "),gbc);
        gbc.gridx = 1;
        panelDer.add(new JLabel(String.valueOf(admin.getEdad() )),gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        panelDer.add( new JLabel("Sexo: "),gbc);
        gbc.gridx = 1;
        panelDer.add(new JLabel( admin.getSexo() != null? admin.getSexo(): "No asignado"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelDer.add(new JLabel("Email: "),gbc );
        gbc.gridx = 1;
        panelDer.add( new JLabel(admin.getEmail() != null ? admin.getEmail() : "No asignado" ), gbc );

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelDer.add(new JLabel("Maestría: "), gbc);
        
        gbc.gridx = 1;
        String maestriaText =admin.getMaestria() != null? admin.getMaestria(): "N/A";
        panelDer.add(new JLabel(maestriaText),gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelDer.add(new JLabel("Rol: "),gbc);
        gbc.gridx = 1;
        panelDer.add( new JLabel(admin.getRole() != null? admin.getRole(): "ADMIN" ), gbc);


        add(panelImagenContenedor,BorderLayout.WEST);

        add(panelDer,BorderLayout.CENTER);
        
    }


    public void updateAdmin(Maestro admin) {

        this.admin = admin;

        iniciarComponentes();

        revalidate();

        repaint();
    }
}
