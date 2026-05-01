package services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import models.Alumno;

public class PDFExporter {

    public void exportAlumnos(List<Alumno> alumnos, File file) throws IOException {

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
             Document doc = new Document(pdfDoc, PageSize.LETTER.rotate())) {

            //Titulo tabla
            doc.add(new Paragraph("Reporte de Alumnos")
                    .setBold()
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));

            doc.add(new Paragraph("").setMarginTop(30));

            //columnas
            float[] columnsWidth = {2, 3, 3, 3, 2, 2, 3, 3};

            Table table = new Table(UnitValue.createPercentArray(columnsWidth))
                    .useAllAvailableWidth();

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            //encabezado
            Cell titulo = new Cell(1, 8)
                    .add(new Paragraph("Alumnos"))
                    .setFont(font)
                    .setFontSize(14)
                    .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(new DeviceRgb(45, 111, 164))
                    .setTextAlignment(TextAlignment.CENTER);

            table.addHeaderCell(titulo);

            for (int i = 0; i < 2; i++) {

                Cell[] headerFooter = new Cell[] {

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Matrícula")),

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Nombre")),

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Ap. Paterno")),

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Ap. Materno")),

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Sexo")),

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Grupo")),

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Contacto")),

                    new Cell().setTextAlignment(TextAlignment.CENTER)
                            .setBorderTop(new SolidBorder(1f))
                            .setBackgroundColor(new DeviceGray(0.80f))
                            .add(new Paragraph("Teléfono"))
                };

                for (Cell celda : headerFooter) {
                    if (i == 0) {
                        table.addHeaderCell(celda);
                    } else {
                        table.addFooterCell(celda);
                    }
                }
            }

            //Datos
            for (Alumno a : alumnos) {
            	
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(a.getMatricula())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(a.getNombre())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(a.getApellidoPaterno())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(a.getApellidoMaterno())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                	    .add(new Paragraph(
                	        "M".equals(a.getSexo()) ? "Mujer" : "Hombre"
                	)));
                
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(a.getGrupo())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(a.getContactoEmergencia())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(a.getNumeroEmergencia())));
            }

            doc.add(table);
        }
    }
}