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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tablemodels.CalificacionTableModel;
import utils.AppFont;
import utils.ConfigManager;

public class CalificacionesView extends JPanel{
	
	private JTable tabla;
	private JButton btnPdf;
	private JButton btnEditarCalificaciones;
	private JButton btnBoleta;
	
	public CalificacionesView() {
		setLayout(new BorderLayout());
		
		tabla = new JTable();
		btnPdf = new JButton("Exportar a PDF");
        
        
		JPanel panelBoton = new JPanel();
		panelBoton.add(btnPdf);

		styleTable();
		
		add(panelBoton, BorderLayout.NORTH);
		add(new JScrollPane(tabla), BorderLayout.CENTER);
	}
	
	public void setTableModel(CalificacionTableModel model) {
        tabla.setModel(model);
    }
	
	
	public JButton getBtnPdf() {
	    return btnPdf;
	
	}
	public JButton getBtnEditarCalificaciones() {
	    return btnEditarCalificaciones;
	}

	public JButton getBtnBoletaPdf() {
	    return btnBoleta;
	}
	
	public File selectPdfFile() {

        JFileChooser chooser = new JFileChooser();
        try {
        	String last = ConfigManager.loadLastDirectory();
        	
        	if(last !=null) {
        		chooser.setCurrentDirectory(new File(last));
        	}
        } catch(Exception e) {}
        chooser.setSelectedFile(new File("reporte-calificaciones.pdf"));


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
        tabla.setGridColor(new Color(230,230,230));

        tabla.setBackground(Color.WHITE);
        tabla.setForeground(Color.BLACK);
        tabla.setFont(AppFont.normal());

        tabla.setSelectionBackground(new Color(52,152,219));
        tabla.setSelectionForeground(Color.WHITE);

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = tabla.getTableHeader();

        header.setBackground(new Color(44,62,80));
        header.setForeground(Color.WHITE);
        header.setFont(AppFont.bold());
        header.setPreferredSize(new Dimension(0,40));
        header.setReorderingAllowed(false);

        tabla.setDefaultRenderer(
            Object.class,
            new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(
                        JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column) {

                    Component c =
                            super.getTableCellRendererComponent(
                                    table,
                                    value,
                                    isSelected,
                                    hasFocus,
                                    row,
                                    column);

                    if (!isSelected) {
                        if (row % 2 == 0) {
                            c.setBackground(Color.WHITE);
                        } else {
                            c.setBackground(
                               new Color(245,245,245));
                        }
                        c.setForeground(Color.BLACK);
                    }

                    if (column == 0) {
                        c.setFont(AppFont.bold());
                        if (!isSelected) {
                            c.setForeground(
                               new Color(41,128,185));
                        }
                    } else {
                        c.setFont(AppFont.normal());
                    }

                    return c;
                }
            }
        );
	}
}
