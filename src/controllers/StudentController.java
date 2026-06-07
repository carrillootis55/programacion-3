package controllers;

import java.awt.Desktop;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;

import models.Student;
import models.Teacher;
import repository.StudentsRepository;
import repository.QualificationRepository;
import services.PDFExporter;
import tablemodels.StudentsTableModels;
import views.StudentsView;
import views.StudentsRateView;
import views.Form;
import views.StudentDetails;

public class StudentController {

	private StudentsView view;
	private PDFExporter pdfExporter;
	private Teacher currentTeacher;

	public StudentController(StudentsView view, Teacher teacher) {

		this.view = view;

		this.currentTeacher = teacher;

		pdfExporter = new PDFExporter();

		//AGREGAR
		view.getBtnAdd().addActionListener(e -> {

			String year = currentTeacher.getYear();
		    String group = currentTeacher.getGroup();

		    StudentsRepository repository = new StudentsRepository();

		    int totalStudents =
		            repository.countStudentsByGroup(year, group);

		    if (totalStudents >= 10) {

		        JOptionPane.showMessageDialog(
		        		view,
		                "Ya existen los 10 alumnos permitidos para el grupo de " + year + "° '" + group + "'",
		                "Grupo Lleno",
		                JOptionPane.INFORMATION_MESSAGE
		        );

		        return;
		    }

			Form form = new Form();

			FormController controller =
					new FormController(form, currentTeacher);

			//Actualiza tabla
			form.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {

                    updateTable();
                }
            });

			form.setLocationRelativeTo(null);
			form.setVisible(true);
		});

		//Calificar
		view.getBtnRate().addActionListener(e -> {

		    int row = view.getSelectedRow();

		    if (row == -1) {

		        JOptionPane.showMessageDialog(
		        		null,
		                "Selecciona un alumno"
		        );

		        return;
		    }

		    StudentsTableModels model =
		            (StudentsTableModels) view.getTable().getModel();

		    Student student = model.getStudentAt(row);

            QualificationRepository repository =
            		new QualificationRepository();

            List<String> subjects =
                    repository.getSubjectsByYear(
                    		currentTeacher.getYear()
                    );

            StudentsRateView window =
            		new StudentsRateView(student, subjects);

            new ControllerRatings(
            		window,
            		student,
            		row,
            		subjects
            );

		    window.setLocationRelativeTo(null);
		    window.setVisible(true);
		});

		//EDITAR
		view.getBtnEdit().addActionListener(e -> {

			int row = view.getTable().getSelectedRow();

			if(row == -1) {

				JOptionPane.showMessageDialog(
						null,
						"Selecciona un alumno"
				);

				return;
			}

			try {

				StudentsTableModels model =
						(StudentsTableModels) view.getTable().getModel();

				Student student = model.getStudentAt(row);

				Form form = new Form();

				FormController controller =
						new FormController(form, currentTeacher);

				form.loadData(student);

				controller.setEditMode(row);

				//Actualizar tabla
				form.addWindowListener(new java.awt.event.WindowAdapter() {

	                @Override
	                public void windowClosed(java.awt.event.WindowEvent e) {

	                    updateTable();
	                }
	            });

				form.setLocationRelativeTo(null);
				form.setVisible(true);

			} catch(Exception ex){

				JOptionPane.showMessageDialog(
						null,
						"Error al abrir el formulario"
				);
			}
		});

		//ELIMINAR
		view.getBtnDelete().addActionListener(e -> {

			int row = view.getTable().getSelectedRow();

			if(row == -1) {

				JOptionPane.showMessageDialog(
						null,
						"Selecciona un alumno"
				);

				return;
			}

			int confirm =
					JOptionPane.showConfirmDialog(
							null,
							"Eliminar alumno?"
					);

			if(confirm == JOptionPane.YES_OPTION) {

				try {

					StudentsRepository repository =
							new StudentsRepository();

		            StudentsTableModels model =
		            		(StudentsTableModels) view.getTable().getModel();

		            Student student =
		            		model.getStudentAt(row);

		            repository.delete(
		            		student.getEnrollment()
		            );

					List<Student> students =
                            repository.getStudentsByGroup(
                                    currentTeacher.getYear(),
                                    currentTeacher.getGroup()
                            );

                    view.setTableModel(
                            new StudentsTableModels(students)
                    );

					view.setTableModel(
							new StudentsTableModels(students)
					);

					JOptionPane.showMessageDialog(
							null,
							"Alumno eliminado"
					);

				} catch(Exception ex) {

					JOptionPane.showMessageDialog(
							null,
							"Error al eliminar"
					);
				}
			}
		});

		view.getBtnPdf().addActionListener(e -> generatePdf());

		view.getBtnReportCard().addActionListener(e -> {

		    int row = view.getSelectedRow();

		    if(row == -1) {

		        JOptionPane.showMessageDialog(
		                null,
		                "Selecciona un alumno"
		        );

		        return;
		    }

		    StudentsTableModels model =
		            (StudentsTableModels) view.getTable().getModel();

		    Student student = model.getStudentAt(row);

		    generateReportCard(student);
		});

		view.getBtnEditGrades().addActionListener(e -> {

		    int row = view.getSelectedRow();

		    if(row == -1) {

		        JOptionPane.showMessageDialog(
		                null,
		                "Selecciona un alumno"
		        );

		        return;
		    }

		    StudentsTableModels model =
		            (StudentsTableModels) view.getTable().getModel();

		    Student student = model.getStudentAt(row);

		    QualificationRepository repository =
		            new QualificationRepository();

		    List<String> subjects =
		            repository.getSubjectsByYear(
		                    currentTeacher.getYear()
		            );

		    StudentsRateView window =
		            new StudentsRateView(
		                    student,
		                    subjects
		            );

		    new ControllerRatings(
		            window,
		            student,
		            row,
		            subjects
		    );

		    window.loadGrades(
		            getQualifications(student, subjects)
		    );

		    window.setLocationRelativeTo(null);

		    window.setVisible(true);
		});

		//VER DETALLES
		view.getBtnStudentDetails().addActionListener(e -> {

		    int row = view.getSelectedRow();

		    if (row == -1) {

		        JOptionPane.showMessageDialog(
		                null,
		                "Selecciona un alumno"
		        );

		        return;
		    }

		    StudentsTableModels model =
		            (StudentsTableModels) view.getTable().getModel();

		    Student student = model.getStudentAt(row);

		    StudentDetails details =
		            new StudentDetails(student);

		    details.setVisible(true);
		});
	}

	private List<Double> getQualifications(
			Student student,
			List<String> subjects
	) {

	    List<Double> qualifications =
	    		new java.util.ArrayList<>();

	    QualificationRepository repository =
	    		new QualificationRepository();

	    for(String subject : subjects) {

	        Double qualification =
	        		repository.getQualification(
	        				student.getEnrollment(),
	        				subject
	        		);

	        if(qualification == null) {
	            qualification = 0.0;
	        }

	        qualifications.add(qualification);
	    }

	    return qualifications;
	}

	//=================================================================================================================================================================

	private void updateTable() {

        try {

            StudentsRepository repository =
                    new StudentsRepository();

            List<Student> students =
                    repository.getStudentsByGroup(
                            currentTeacher.getYear(),
                            currentTeacher.getGroup()
                    );

            view.setTableModel(
                    new StudentsTableModels(students)
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al actualizar tabla"
            );
        }
    }

	//=================================================================================================================================================================

	private void generatePdf() {

		File file = view.selectPdfFile();

		if (file == null) {
			return;
		}

		try {

			StudentsRepository repository =
					new StudentsRepository();

			List<Student> students =
                    repository.getStudentsByGroup(
                            currentTeacher.getYear(),
                            currentTeacher.getGroup()
                    );

			pdfExporter.exportStudents(students, file);

			if (Desktop.isDesktopSupported()) {

				Desktop.getDesktop().open(file);
			}

		} catch (Exception ex) {

			ex.printStackTrace();

			JOptionPane.showMessageDialog(
					null,
					"Error al exportar PDF"
			);
		}
	}

	private void generateReportCard(Student student) {

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new File("report_card.pdf")
        );

        int option = chooser.showSaveDialog(view);

        if(option != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();

        try {

            PDFExporter pdfExporter = new PDFExporter();

            pdfExporter.exportReportCard(student, file);

            Desktop.getDesktop().open(file);

        } catch(Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error al generar boleta"
            );
        }
    }
}