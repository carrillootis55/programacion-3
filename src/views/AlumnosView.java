package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tablemodels.AlumnoTableModels;
import utils.AppFont;

public class AlumnosView extends JPanel {
	
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	
	
    public AlumnosView() {
        setLayout(new BorderLayout());

        tabla = new JTable();
        styleTable();

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

	/*public void setTableModel(AlumnoTableModels model) {
        tabla.setModel(model);
    }*/

    public JTable getTabla() {
        return tabla;
    }
    
    
    
    private void styleTable() {

        tabla.setRowHeight(35);
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(230, 230, 230));
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(Color.BLACK);
        tabla.setFont(AppFont.normal());

        tabla.setSelectionBackground(new Color(52, 152, 219));
        tabla.setSelectionForeground(Color.WHITE);
        
        //Seleccionar solo una fila
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setFont(AppFont.bold());
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(245, 245, 245));
                    }

                    c.setForeground(Color.BLACK);
                }

                if (column == 0) {
                    c.setFont(AppFont.bold());
                    if (!isSelected) {
                        c.setForeground(new Color(41, 128, 185));
                    }
                } else {
                    c.setFont(AppFont.normal());
                }

                return c;
            }
        });
    }
    
    
    public void setTableModel(AlumnoTableModels model) {
        tabla.setModel(model);
        
        //Tamaño de columnas
        if (tabla.getColumnCount() >= 1) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
        }

        if (tabla.getColumnCount() >= 2) {
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        if (tabla.getColumnCount() >= 3) {
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        }
        
        if (tabla.getColumnCount() >= 4) {
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        if (tabla.getColumnCount() >= 5) {
            tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        if (tabla.getColumnCount() >= 6) {
            tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        if (tabla.getColumnCount() >= 7) {
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        if (tabla.getColumnCount() >= 8) {
            tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
        }

        if (tabla.getColumnCount() >= 9) {
            tabla.getColumnModel().getColumn(8).setPreferredWidth(80);
        }

        if (tabla.getColumnCount() >= 10) {
            tabla.getColumnModel().getColumn(9).setPreferredWidth(80);
        }
        
        //Centrar
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        /*if (tabla.getColumnCount() >= 1) {
            tabla.getColumnModel().getColumn(0).setCellRenderer(center);
        }*/
        
        //Centrar columna sexo
        if (tabla.getColumnCount() >= 5) {
            tabla.getColumnModel().getColumn(4).setCellRenderer(center);
        }
        
        //Central columna grupo
        if (tabla.getColumnCount() >= 6) {
            tabla.getColumnModel().getColumn(5).setCellRenderer(center);
        }
    }
    
    
    public int getSelectedRow() {
        return tabla.getSelectedRow();
    }
    
    

}
