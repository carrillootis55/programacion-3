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
import models.Maestro;
import repository.CalificacionRepository;

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
            float[] columnsWidth = {2, 3, 3, 3, 2, 2, 2, 3, 3};

            Table table = new Table(UnitValue.createPercentArray(columnsWidth))
                    .useAllAvailableWidth();

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            //encabezado
            Cell titulo = new Cell(1, 9)
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
                    .add(new Paragraph("Año")),

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
                        .add(new Paragraph(a.getAnio())));
                
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
  //=================================================================================================================================================================
    public void exportMaestros(List<Maestro> maestros, File file) throws IOException {

        try (

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

            Document doc = new Document(pdfDoc, PageSize.LETTER.rotate())

        ) {

            doc.add(
                new Paragraph("Reporte de Maestros")
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.CENTER)
            );

            doc.add(new Paragraph(" "));

            float[] columnas = {1, 3, 4, 1, 1, 3, 1, 1};

            Table table = new Table(UnitValue.createPercentArray(columnas) ).useAllAvailableWidth();

            table.addHeaderCell(crearHeader("ID"));
            table.addHeaderCell(crearHeader("Nombre"));
            table.addHeaderCell(crearHeader("Email"));
            table.addHeaderCell(crearHeader("Sexo"));
            table.addHeaderCell(crearHeader("Edad"));
            table.addHeaderCell(crearHeader("Maestría"));
            table.addHeaderCell(crearHeader("Año"));
            table.addHeaderCell(crearHeader("Grupo"));

            for (Maestro m : maestros) {

                table.addCell(
                    new Cell().add(
                        new Paragraph(String.valueOf(m.getId()))
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(m.getNombre())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(m.getEmail())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(String.valueOf(m.getSexo()))
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(String.valueOf(m.getEdad()))
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(m.getMaestria())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(m.getAnio())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(m.getGrupo())
                    )
                );
            }

            doc.add(table);
        }
    }
    
  //=================================================================================================================================================================
    public void exportCalificaciones(List<Alumno> alumnos, List<String> materias, File file) throws IOException {
        try (

            PdfDocument pdfDoc =new PdfDocument(new PdfWriter(file)  );

            Document doc =new Document( pdfDoc,PageSize.LETTER.rotate() )

        ) {
            CalificacionRepository repo =new CalificacionRepository();

            doc.add(new Paragraph( "Reporte de Calificaciones") .setBold() .setFontSize(16)  .setTextAlignment(  TextAlignment.CENTER  ) );

            doc.add(new Paragraph(" "));

            int totalColumnas = 2 + materias.size() + 1;

            float[] columnas =new float[totalColumnas];

            columnas[0] = 1;
            columnas[1] = 4;

            for (int i = 2; i < totalColumnas; i++) {
                columnas[i] = 2;
            }

            Table table = new Table(  UnitValue.createPercentArray( columnas) ).useAllAvailableWidth();

            table.addHeaderCell(crearHeader("ID"));
            table.addHeaderCell(crearHeader("Nombre"));

            for (String materia : materias) {

                table.addHeaderCell(
                        crearHeader(materia)
                );
            }

            table.addHeaderCell(
                    crearHeader("Promedio")
            );

            int contador = 1;

            // FILAS
            for (Alumno alumno : alumnos) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(contador++))).setTextAlignment(TextAlignment.CENTER));

                table.addCell(new Cell() .add(new Paragraph(alumno.getNombre() )) );

                double suma = 0;

                for (String materia : materias) {

                    Double calificacion = 0.0;

                    try {

                        calificacion =repo.obtenerCalificacion( alumno.getMatricula(), materia );

                        if (calificacion == null) {
                            calificacion = 0.0;
                        }

                    } catch (Exception e) {

                        calificacion = 0.0;
                    }

                    suma += calificacion;

                    table.addCell(new Cell().add( new Paragraph(String.valueOf(calificacion ))).setTextAlignment( TextAlignment.CENTER ));
                }

                double promedio =suma / materias.size();

                promedio = Math.round(promedio * 10.0 ) / 10.0;

                table.addCell(new Cell().add(new Paragraph( String.valueOf(promedio ) )).setTextAlignment(TextAlignment.CENTER ) );
            }

            doc.add(table);
        }
    }
    
    public void exportBoleta(Alumno alumno, File file) throws IOException {

        try (

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

            Document doc = new Document(pdfDoc,PageSize.LETTER)) {

            CalificacionRepository repo = new CalificacionRepository();

            // TITULO
            doc.add(new Paragraph("BOLETA ESCOLAR").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER));

            doc.add(new Paragraph(" "));

            // DATOS ALUMNO
            doc.add(
                new Paragraph(
                    "Alumno: " + alumno.getNombre()
                    + " " + alumno.getApellidoPaterno()
                    + " " + alumno.getApellidoMaterno()
                ));

            doc.add(new Paragraph("Matrícula: " + alumno.getMatricula()));

            doc.add(new Paragraph(
                    "Grupo: " + alumno.getGrupo()));

            doc.add(new Paragraph("Año: " + alumno.getAnio()));

            doc.add(new Paragraph(" "));

            // MATERIAS
            List<String> materias = repo.getMateriasPorAnio(alumno.getAnio());

            float[] columnas = {5, 2};

            Table table = new Table(UnitValue.createPercentArray(columnas)).useAllAvailableWidth();

            table.addHeaderCell(crearHeader("Materia"));
            table.addHeaderCell(crearHeader("Calificación"));

            double suma = 0;

            int contador = 0;

            for (String materia : materias) {

                Double calificacion = repo.obtenerCalificacion(alumno.getMatricula(),materia);

                table.addCell(new Cell().add(new Paragraph(materia)));

                if (calificacion != null) {

                    table.addCell( new Cell().add(new Paragraph(String.valueOf(calificacion))).setTextAlignment(TextAlignment.CENTER));

                    suma += calificacion;

                    contador++;

                } else {

                    table.addCell(new Cell().add(new Paragraph("Sin calificación")).setTextAlignment(TextAlignment.CENTER));
                }
            }

            doc.add(table);

            doc.add(new Paragraph(" "));

            double promedio = 0;

            if (contador > 0) {

                promedio = suma / contador;
            }

            promedio = Math.round(promedio * 10.0) / 10.0;

            doc.add(new Paragraph("PROMEDIO FINAL: "+ promedio).setBold().setFontSize(16).setTextAlignment(TextAlignment.RIGHT));
        }
    }
  //=================================================================================================================================================================
    private Cell crearHeader(String texto) {

        return new Cell()
                .add(new Paragraph(texto)).setBackgroundColor(
                        new DeviceRgb(45,111,164)).setFontColor(DeviceGray.WHITE).setTextAlignment(TextAlignment.CENTER);
    }
    
}