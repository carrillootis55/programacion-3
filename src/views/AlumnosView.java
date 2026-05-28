package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tablemodels.AlumnoTableModels;
import utils.AppFont;
import utils.ConfigManager;

public class AlumnosView extends JPanel {
	
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnPdf;
	
	private JButton btnCalificar;
	private JButton btnDetallesA;
	private JButton btnBoleta;
	private JButton btnEditarCalificaciones;
	
	
    public AlumnosView() {
        setLayout(new BorderLayout());

        tabla = new JTable();
        styleTable();

        btnAgregar = new JButton ("Agregar");
        btnEditar = new JButton ("Editar");
        btnEliminar = new JButton ("Eliminar");
        btnPdf = new JButton("Exportar PDF");
        btnCalificar = new JButton("Calificar");
        btnDetallesA = new JButton ("Vista alumno");
        btnBoleta = new JButton("Boleta PDF");
        btnEditarCalificaciones = new JButton("Editar calificaciones");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        
        panelBotones.add(new JSeparator(SwingConstants.VERTICAL));
        panelBotones.add(btnCalificar);
        panelBotones.add(btnPdf);
        panelBotones.add(btnDetallesA);
        panelBotones.add(btnEditarCalificaciones);
        panelBotones.add(btnBoleta);

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
	
	public JButton getBtnPdf(){
		return btnPdf; 
	} 

	public JButton getBtnCalificar() {
	    return btnCalificar;
	}
	public JButton getBtnDetallesA() {
		return btnDetallesA;
	}
    public JTable getTabla() {
        return tabla;
    }
    public JButton getBtnBoleta() {
        return btnBoleta;
    }

    public JButton getBtnEditarCalificaciones() {
        return btnEditarCalificaciones;
    }
    
    public File selectPdfFile() {

        JFileChooser chooser = new JFileChooser();
        try {
        	String last = ConfigManager.loadLastDirectory();
        	
        	if(last !=null) {
        		chooser.setCurrentDirectory(new File(last));
        	}
        } catch(Exception e) {}
        chooser.setSelectedFile(new File("reporte-alumnos.pdf"));


        int option = chooser.showSaveDialog(this);

        if (option != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File file = chooser.getSelectedFile();

        try {
        	ConfigManager.saveLastDirectory(file.getParent());
        }catch(Exception e) {}

        return file;
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
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
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

        if (tabla.getColumnCount() >= 11) {
            tabla.getColumnModel().getColumn(10).setPreferredWidth(80);
        
        }
        //Centrar
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Centrar columna sexo
        if (tabla.getColumnCount() >= 5) {
            tabla.getColumnModel().getColumn(4).setCellRenderer(center);
        }
        
        //Central columna grupo
        if (tabla.getColumnCount() >= 6) {
            tabla.getColumnModel().getColumn(5).setCellRenderer(center);
        }
        
        if (tabla.getColumnCount() >= 7) {
            tabla.getColumnModel().getColumn(6).setCellRenderer(center);
        }
        
    }
    
    
    public int getSelectedRow() {
        return tabla.getSelectedRow();
    }
    
    

}
