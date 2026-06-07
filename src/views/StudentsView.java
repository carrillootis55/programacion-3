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

import tablemodels.StudentsTableModels;
import utils.AppFont;
import utils.ConfigManager;

public class StudentsView extends JPanel {
	
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnPdf;
	
	private JButton btnRate;
	private JButton btnStudentDetails;
	private JButton btnReportCard;
	private JButton btnEditGrades;
	
	
    public StudentsView() {
        setLayout(new BorderLayout());

        table = new JTable();
        styleTable();

        btnAdd = new JButton ("Agregar");
        btnEdit = new JButton ("Editar");
        btnDelete = new JButton ("Eliminar");
        btnPdf = new JButton("Exportar PDF");
        btnRate = new JButton("Calificar");
        btnStudentDetails = new JButton ("Vista alumno");
        btnReportCard = new JButton("Boleta PDF");
        btnEditGrades = new JButton("Editar calificaciones");
        
        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        
        buttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
        
        buttonPanel.add(btnRate);
        buttonPanel.add(btnPdf);
        buttonPanel.add(btnStudentDetails);
        buttonPanel.add(btnEditGrades);
        buttonPanel.add(btnReportCard);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        
    }

    public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	public JButton getBtnPdf(){
		return btnPdf; 
	} 

	public JButton getBtnRate() {
	    return btnRate;
	}
	
	public JButton getBtnStudentDetails() {
		return btnStudentDetails;
	}
    
    public JTable getTable() {
        return table;
    }
    
    public JButton getBtnReportCard() {
        return btnReportCard;
    }

    public JButton getBtnEditGrades() {
        return btnEditGrades;
    }
    
    public File selectPdfFile() {

        JFileChooser chooser = new JFileChooser();
        
        try {
        	
        	String lastDirectory = ConfigManager.loadLastDirectory();
        	
        	if(lastDirectory != null) {
        		chooser.setCurrentDirectory(new File(lastDirectory));
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
        } catch(Exception e) {}

        return file;
    }
    
    
    private void styleTable() {

        table.setRowHeight(35);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(AppFont.normal());

        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);
        
        //Seleccionar solo una fila
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setFont(AppFont.bold());
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component component = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    
                    if (row % 2 == 0) {
                        component.setBackground(Color.WHITE);
                    } else {
                        component.setBackground(new Color(245, 245, 245));
                    }

                    component.setForeground(Color.BLACK);
                }

                if (column == 0) {
                    
                    component.setFont(AppFont.bold());
                    
                    if (!isSelected) {
                        component.setForeground(new Color(41, 128, 185));
                    }
                    
                } else {
                    component.setFont(AppFont.normal());
                }

                return component;
            }
        });
    }
    
    
    public void setTableModel(StudentsTableModels model) {
        
        table.setModel(model);
        
        //Tamaño de columnas
        if (table.getColumnCount() >= 1) {
            table.getColumnModel().getColumn(0).setPreferredWidth(80);
        }

        if (table.getColumnCount() >= 2) {
            table.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        if (table.getColumnCount() >= 3) {
            table.getColumnModel().getColumn(2).setPreferredWidth(80);
        }
        
        if (table.getColumnCount() >= 4) {
            table.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        if (table.getColumnCount() >= 5) {
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        if (table.getColumnCount() >= 6) {
            table.getColumnModel().getColumn(5).setPreferredWidth(50);
        }
      
        if (table.getColumnCount() >= 7) {
            table.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        if (table.getColumnCount() >= 8) {
            table.getColumnModel().getColumn(7).setPreferredWidth(80);
        }

        if (table.getColumnCount() >= 9) {
            table.getColumnModel().getColumn(8).setPreferredWidth(80);
        }

        if (table.getColumnCount() >= 10) {
            table.getColumnModel().getColumn(9).setPreferredWidth(80);
        }

        if (table.getColumnCount() >= 11) {
            table.getColumnModel().getColumn(10).setPreferredWidth(80);
        
        }
        
        //Centrar
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Centrar columna sexo
        if (table.getColumnCount() >= 5) {
            table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        }
        
        //Central columna grupo
        if (table.getColumnCount() >= 6) {
            table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        }
        
        if (table.getColumnCount() >= 7) {
            table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        }
        
    }
    
    
    public int getSelectedRow() {
        return table.getSelectedRow();
    }
}