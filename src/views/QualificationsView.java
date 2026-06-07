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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tablemodels.QualificationTableModel;
import utils.AppFont;
import utils.ConfigManager;

public class QualificationsView extends JPanel {

    private JTable qualificationsTable;

    private JButton exportPdfButton;
    private JButton editQualificationsButton;
    private JButton reportCardButton;

    public QualificationsView() {

        setLayout(new BorderLayout());

        qualificationsTable = new JTable();

        exportPdfButton = new JButton("Exportar a PDF");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(exportPdfButton);

        styleTable();

        add(buttonPanel, BorderLayout.NORTH);

        add(new JScrollPane(qualificationsTable), BorderLayout.CENTER);
    }

    public void setTableModel(QualificationTableModel tableModel) {

        qualificationsTable.setModel(tableModel);
    }

    public JButton getExportPdfButton() {

        return exportPdfButton;
    }

    public JButton getEditQualificationsButton() {

        return editQualificationsButton;
    }

    public JButton getReportCardButton() {

        return reportCardButton;
    }

    public File selectPdfFile() {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Guardar PDF");

        fileChooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Archivos PDF",
                        "pdf"
                )
        );

        try {

            String lastDirectory =
                    ConfigManager.loadLastDirectory();

            if (lastDirectory != null) {

                fileChooser.setCurrentDirectory(
                        new File(lastDirectory)
                );
            }

        } catch (Exception exception) {

            exception.printStackTrace();
        }

        fileChooser.setSelectedFile(
                new File("reporte-calificaciones.pdf")
        );

        int option =
                fileChooser.showSaveDialog(this);

        if (option != JFileChooser.APPROVE_OPTION) {

            return null;
        }

        File selectedFile =
                fileChooser.getSelectedFile();

        if (!selectedFile.getName()
                .toLowerCase()
                .endsWith(".pdf")) {

            selectedFile =
                    new File(
                            selectedFile.getAbsolutePath()
                                    + ".pdf"
                    );
        }

        try {

            ConfigManager.saveLastDirectory(
                    selectedFile.getParent()
            );

        } catch (Exception exception) {

            exception.printStackTrace();
        }

        return selectedFile;
    }

    private void styleTable() {

        qualificationsTable.setRowHeight(35);

        qualificationsTable.setShowGrid(true);

        qualificationsTable.setGridColor(
                new Color(230, 230, 230)
        );

        qualificationsTable.setBackground(Color.WHITE);

        qualificationsTable.setForeground(Color.BLACK);

        qualificationsTable.setFont(AppFont.normal());

        qualificationsTable.setSelectionBackground(
                new Color(52, 152, 219)
        );

        qualificationsTable.setSelectionForeground(
                Color.WHITE
        );

        qualificationsTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JTableHeader tableHeader =
                qualificationsTable.getTableHeader();

        tableHeader.setBackground(
                new Color(44, 62, 80)
        );

        tableHeader.setForeground(Color.WHITE);

        tableHeader.setFont(AppFont.bold());

        tableHeader.setPreferredSize(
                new Dimension(0, 40)
        );

        tableHeader.setReorderingAllowed(false);

        qualificationsTable.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    ) {

                        Component component =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        if (!isSelected) {

                            if (row % 2 == 0) {

                                component.setBackground(
                                        Color.WHITE
                                );

                            } else {

                                component.setBackground(
                                        new Color(245, 245, 245)
                                );
                            }

                            component.setForeground(
                                    Color.BLACK
                            );
                        }

                        if (column == 0) {

                            component.setFont(
                                    AppFont.bold()
                            );

                            if (!isSelected) {

                                component.setForeground(
                                        new Color(41, 128, 185)
                                );
                            }

                        } else {

                            component.setFont(
                                    AppFont.normal()
                            );
                        }

                        return component;
                    }
                }
        );
    }
}