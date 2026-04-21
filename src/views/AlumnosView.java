package views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tablemodels.AlumnoTableModels;

public class AlumnosView extends JPanel {
	
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	
	
    public AlumnosView() {
        setLayout(new BorderLayout());

        tabla = new JTable();

        btnAgregar = new JButton ("Agregar");
        btnEditar = new JButton ("Editar");
        btnEliminar = new JButton ("Eliminar");
        
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        
        add(panelBotones, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        
    }

    public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setTableModel(AlumnoTableModels model) {
        tabla.setModel(model);
    }

    public JTable getTabla() {
        return tabla;
    }

}
