package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import models.Teacher;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeacherView extends JPanel{
	
	private Teacher teacher;
	
	public TeacherView() {
		this(new Teacher());
	}
	
	public TeacherView(Teacher teacher) {
		this.teacher = teacher;
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		setLayout(new BorderLayout());
		removeAll();
		
		JPanel panelImagenContenedor = new JPanel(new GridBagLayout());
        panelImagenContenedor.setPreferredSize(new Dimension(400, 400));
        panelImagenContenedor.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 10));

       
        String rutaImagen;

        if(teacher.getSexo() != null &&teacher.getSexo().equalsIgnoreCase("M")) {
            rutaImagen = "src/img/maestra.png";

        } else {

            rutaImagen = "src/img/maestro.png";
        }


        ImageIcon originalIcon = new ImageIcon(rutaImagen);

        Image imgEscalada = originalIcon.getImage()
                .getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(imgEscalada));
        
	    panelImagenContenedor.add(imageLabel);
	    
	    
		JPanel panelDer = new JPanel(new GridBagLayout());
		panelDer.setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //ESPACIO ENTRE LINEAS DE TEXTO
		gbc.anchor = GridBagConstraints.WEST;
		
		
		gbc.gridx = 0; gbc.gridy = 0;
		panelDer.add(new JLabel("Nombre: "), gbc);
		gbc.gridx = 1;
		panelDer.add(new JLabel(teacher.getNombre() != null ? teacher.getNombre() : "No asignado"), gbc);
		
		gbc.gridx = 0; gbc.gridy = 1;
		panelDer.add(new JLabel("Edad: "), gbc);
		gbc.gridx = 1;
		panelDer.add(new JLabel(String.valueOf(teacher.getEdad())), gbc);
		
		
		gbc.gridx = 0; gbc.gridy = 3;
		panelDer.add(new JLabel("Maestria: "), gbc);
		gbc.gridx = 1;
		String maestriaText = teacher.getMaestria() != null ? teacher.getMaestria() : "N/A";
		panelDer.add(new JLabel(maestriaText), gbc);
				
		gbc.gridx = 0;
        gbc.gridy = 5;
        panelDer.add(new JLabel("Año: "), gbc);
        gbc.gridx = 1;
        panelDer.add(new JLabel(teacher.getAnio() != null? teacher.getAnio(): "No asignado"),gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelDer.add(new JLabel("Grupo: "), gbc);
        gbc.gridx = 1;
        panelDer.add(new JLabel(teacher.getGrupo() != null? teacher.getGrupo(): "No asignado"), gbc);

		
        add(panelImagenContenedor, BorderLayout.WEST);
        add(panelDer, BorderLayout.CENTER);
		
		
	}
	
	public void updateMaestro(Teacher teacher) {
		this.teacher = teacher;
		iniciarComponentes();
		revalidate();
		repaint();
	}

}
