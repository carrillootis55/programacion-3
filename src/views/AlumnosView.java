package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tablemodels.AlumnoTableModels;

public class AlumnosView extends JPanel {
	
	private JTable tabla;

    public AlumnosView() {
        setLayout(new BorderLayout());

        tabla = new JTable();

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void setTableModel(AlumnoTableModels model) {
        tabla.setModel(model);
    }

    public JTable getTabla() {
        return tabla;
    }

}
