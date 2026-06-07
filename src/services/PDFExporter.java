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

import models.Student;
import models.Teacher;
import repository.QualificationRepository;

public class PDFExporter {

	
    public void exportStudents(List<Student> students, File file) throws IOException {

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
            Cell title = new Cell(1, 9)
                    .add(new Paragraph("Alumnos"))
                    .setFont(font)
                    .setFontSize(14)
                    .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(new DeviceRgb(45, 111, 164))
                    .setTextAlignment(TextAlignment.CENTER);

            table.addHeaderCell(title);

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

                for (Cell cell : headerFooter) {
                    if (i == 0) {
                        table.addHeaderCell(cell);
                    } else {
                        table.addFooterCell(cell);
                    }
                }
            }
            
            //Datos
            for (Student student : students) {
            	
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(student.getEnrollment())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(student.getName())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(student.getFatherLastName())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(student.getMotherLastName())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                	    .add(new Paragraph(
                	        "M".equals(String.valueOf(student.getGender())) ? "Mujer" : "Hombre"
                	)));
                
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(student.getYear())));
                
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(student.getGroup())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(student.getEmergencyContact())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(student.getEmergencyNumber())));
            }

            doc.add(table);
            
            
        }
        
    }
  //=================================================================================================================================================================
    public void exportTeachers(List<Teacher> teachers, File file) throws IOException {

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

            float[] columns = {1, 3, 4, 1, 1, 3, 1, 1};

            Table table = new Table(UnitValue.createPercentArray(columns)).useAllAvailableWidth();

            table.addHeaderCell(createHeader("ID"));
            table.addHeaderCell(createHeader("Nombre"));
            table.addHeaderCell(createHeader("Email"));
            table.addHeaderCell(createHeader("Sexo"));
            table.addHeaderCell(createHeader("Edad"));
            table.addHeaderCell(createHeader("Maestría"));
            table.addHeaderCell(createHeader("Año"));
            table.addHeaderCell(createHeader("Grupo"));

            for (Teacher teacher : teachers) {

                table.addCell(
                    new Cell().add(
                        new Paragraph(String.valueOf(teacher.getId()))
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(teacher.getName())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(teacher.getEmail())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(String.valueOf(teacher.getGender()))
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(String.valueOf(teacher.getAge()))
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(teacher.getMasterDegree())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(teacher.getYear())
                    )
                );

                table.addCell(
                    new Cell().add(
                        new Paragraph(teacher.getGroup())
                    )
                );
            }

            doc.add(table);
        }
    }
    
  //=================================================================================================================================================================
    public void exportQualifications(List<Student> students, List<String> subjects, File file) throws IOException {
        try (

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

            Document doc = new Document(pdfDoc, PageSize.LETTER.rotate())

        ) {

            QualificationRepository qualificationRepository = new QualificationRepository();

            doc.add(
                new Paragraph("Reporte de Calificaciones")
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.CENTER)
            );

            doc.add(new Paragraph(" "));

            int totalColumns = 2 + subjects.size() + 1;

            float[] columns = new float[totalColumns];

            columns[0] = 1;
            columns[1] = 4;

            for (int i = 2; i < totalColumns; i++) {
                columns[i] = 2;
            }

            Table table = new Table(
                    UnitValue.createPercentArray(columns)
            ).useAllAvailableWidth();

            table.addHeaderCell(createHeader("ID"));
            table.addHeaderCell(createHeader("Nombre"));

            for (String subject : subjects) {

                table.addHeaderCell(
                        createHeader(subject)
                );
            }

            table.addHeaderCell(
                    createHeader("Promedio")
            );

            int counter = 1;

            // FILAS
            for (Student student : students) {

                table.addCell(
                        new Cell()
                        .add(new Paragraph(String.valueOf(counter++)))
                        .setTextAlignment(TextAlignment.CENTER)
                );

                table.addCell(
                        new Cell()
                        .add(new Paragraph(student.getName()))
                );

                double total = 0;

                for (String subject : subjects) {

                    Double qualification = 0.0;

                    try {

                        qualification =
                                qualificationRepository.getQualification(
                                        student.getEnrollment(),
                                        subject
                                );

                        if (qualification == null) {
                            qualification = 0.0;
                        }

                    } catch (Exception e) {

                        qualification = 0.0;
                    }

                    total += qualification;

                    table.addCell(
                            new Cell()
                            .add(new Paragraph(String.valueOf(qualification)))
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                double average = total / subjects.size();

                average = Math.round(average * 10.0) / 10.0;

                table.addCell(
                        new Cell()
                        .add(new Paragraph(String.valueOf(average)))
                        .setTextAlignment(TextAlignment.CENTER)
                );
            }

            doc.add(table);
        }
    }
    
    public void exportReportCard(Student student, File file) throws IOException {

        try (

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

            Document doc = new Document(pdfDoc, PageSize.LETTER)

        ) {

            QualificationRepository qualificationRepository = new QualificationRepository();

            // TITULO
            doc.add(
                    new Paragraph("BOLETA ESCOLAR")
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER)
            );

            doc.add(new Paragraph(" "));

            // DATOS ALUMNO
            doc.add(
                new Paragraph(
                    "Alumno: " + student.getName()
                    + " " + student.getFatherLastName()
                    + " " + student.getMotherLastName()
                )
            );

            doc.add(
                    new Paragraph(
                            "Matrícula: " + student.getEnrollment()
                    )
            );

            doc.add(
                    new Paragraph(
                            "Grupo: " + student.getGroup()
                    )
            );

            doc.add(
                    new Paragraph(
                            "Año: " + student.getYear()
                    )
            );

            doc.add(new Paragraph(" "));

            // MATERIAS
            List<String> subjects =
                    qualificationRepository.getSubjectsByYear(
                            student.getYear()
                    );

            float[] columns = {5, 2};

            Table table = new Table(
                    UnitValue.createPercentArray(columns)
            ).useAllAvailableWidth();

            table.addHeaderCell(createHeader("Materia"));
            table.addHeaderCell(createHeader("Calificación"));

            double total = 0;

            int counter = 0;

            for (String subject : subjects) {

                Double qualification =
                        qualificationRepository.getQualification(
                                student.getEnrollment(),
                                subject
                        );

                table.addCell(
                        new Cell().add(new Paragraph(subject))
                );

                if (qualification != null) {

                    table.addCell(
                            new Cell()
                            .add(new Paragraph(String.valueOf(qualification)))
                            .setTextAlignment(TextAlignment.CENTER)
                    );

                    total += qualification;

                    counter++;

                } else {

                    table.addCell(
                            new Cell()
                            .add(new Paragraph("Sin calificación"))
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }
            }

            doc.add(table);

            doc.add(new Paragraph(" "));

            double average = 0;

            if (counter > 0) {

                average = total / counter;
            }

            average = Math.round(average * 10.0) / 10.0;

            doc.add(
                    new Paragraph("PROMEDIO FINAL: " + average)
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.RIGHT)
            );
        }
    }
  //=================================================================================================================================================================
    private Cell createHeader(String text) {

        Cell cell = new Cell();

        cell.add(new Paragraph(text));

        cell.setBackgroundColor(new DeviceRgb(45, 111, 164));

        cell.setFontColor(DeviceGray.WHITE);

        cell.setTextAlignment(TextAlignment.CENTER);

        return cell;
    }
    
}