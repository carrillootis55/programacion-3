package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Maestro;

public class MaestroTableModel extends AbstractTableModel {

    private List<Maestro> maestros;

    private final String[] columnas = {
        "ID",
        "Nombre",
        "Email",
        "Sexo",
        "Edad",
        "Maestría",
        "Año",
        "Grupo"
    };

    public MaestroTableModel(List<Maestro> maestros) {

        this.maestros = maestros;
    }

    @Override
    public int getRowCount() {

        return maestros.size();
    }

    @Override
    public int getColumnCount() {

        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {

        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Maestro maestro = maestros.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return maestro.getId();

            case 1:
                return maestro.getNombre();

            case 2:
                return maestro.getEmail();

            case 3:
                return maestro.getSexo();

            case 4:
                return maestro.getEdad();

            case 5:
                return maestro.getMaestria();

            case 6:
                return maestro.getAnio();

            case 7:
                return maestro.getGrupo();

            default:
                return null;
        }
    }

    public Maestro getMaestro(int row) {

        return maestros.get(row);
    }
}