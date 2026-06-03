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
	private Teacher maestroActual;
		
	public StudentController(StudentsView view, Teacher teacher) {
		this.view = view;
		
		this.maestroActual = teacher;
		
		pdfExporter = new PDFExporter();
		
		//AGREGAR
		view.getBtnAgregar().addActionListener(e -> {
			String anio = maestroActual.getAnio();
		    String grupo = maestroActual.getGrupo();
		    
		    StudentsRepository repo = new StudentsRepository();
		    int totalAlumnos = repo.contarAlumnosPorGrupo(anio, grupo);
		    
		    if (totalAlumnos >= 10) {
		        JOptionPane.showMessageDialog(view, 
		            "Ya existen los 10 alumnos permitidos para el grupo de " + anio + "° '" + grupo + "'", 
		            "Grupo Lleno", 
		            JOptionPane.INFORMATION_MESSAGE);
		        return; 
		    }
		    
			Form form = new Form();
	
			FormController controller =new FormController(form,maestroActual);
			
			//Actualiza tabla
			form.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {

                    actualizarTabla();
                }
            });
			form.setLocationRelativeTo(null);
			form.setVisible(true);
		});
		
		//Calificar
		view.getBtnCalificar().addActionListener(e -> {
		    int fila = view.getSelectedRow();

		    if (fila == -1) {
		        JOptionPane.showMessageDialog(null,
		                "Selecciona un alumno");
		        return;
		    }

		    StudentsTableModels model = (StudentsTableModels) view.getTabla().getModel();

		    Student student = model.getAlumnoAt(fila);
            QualificationRepository repo = new QualificationRepository();

            List<String> materias = repo.getMateriasPorAnio(maestroActual.getAnio());

            StudentsRateView ventana = new StudentsRateView( student,materias );
            
            new ControllerRatings(ventana, student, fila,  materias );

		    ventana.setLocationRelativeTo(null);
		    ventana.setVisible(true);
		});
		
		
		//EDITAR
		view.getBtnEditar().addActionListener(e ->{
			int fila = view.getTabla().getSelectedRow();
			
			if(fila == -1) {
				JOptionPane.showMessageDialog(null, "Selecciona un alumno");
				return;
			}
			try {
			StudentsTableModels model = (StudentsTableModels) view.getTabla().getModel();
			Student student = model.getAlumnoAt(fila);
			
			Form form = new Form();
			FormController controller =new FormController(form,maestroActual);
			
			form.cargarDatos(student);
			controller.setModoEdicion(fila);
			
			//Actualizar tabla
			form.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {

                    actualizarTabla();
                }
            });
			form.setLocationRelativeTo(null);
			form.setVisible(true);
			} catch(Exception ex){
				
				JOptionPane.showMessageDialog(null, "Error al abrir el formulario");
			}
		});
		//ELIMINAR
		
		view.getBtnEliminar().addActionListener(e ->{
			int fila = view.getTabla().getSelectedRow();
			
			if(fila == -1) {
				JOptionPane.showMessageDialog(null, "Selecciona un alumno");
				return;
			}
			
			int confirm = JOptionPane.showConfirmDialog(null, "Eliminar alumno?");
			
			if(confirm == JOptionPane.YES_OPTION) {
				try {
					StudentsRepository repo = new StudentsRepository();

		            StudentsTableModels model = (StudentsTableModels) view.getTabla().getModel();

		            Student student = model.getAlumnoAt(fila);

		            repo.delete(student.getMatricula());
		            
		            
					//List<Alumno> alumnos = repo.getAlumnos();
					List<Student> students =
                            repo.getAlumnosPorGrupo(
                                    maestroActual.getAnio(),
                                    maestroActual.getGrupo()
                            );

                    view.setTableModel(
                            new StudentsTableModels(students)
                    );
					
					view.setTableModel(new StudentsTableModels(students));
					
					JOptionPane.showMessageDialog(null, "Alumno eliminado");
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar");
				}
			}
		});
		
		view.getBtnPdf().addActionListener(e -> generarPdf());
		view.getBtnBoleta().addActionListener(e -> {

		    int fila = view.getSelectedRow();

		    if(fila == -1) {

		        JOptionPane.showMessageDialog(
		                null,
		                "Selecciona un alumno"
		        );

		        return;
		    }

		    StudentsTableModels model =
		            (StudentsTableModels) view.getTabla().getModel();

		    Student student = model.getAlumnoAt(fila);

		    generarBoleta(student);
		});
		
		view.getBtnEditarCalificaciones().addActionListener(e -> {

		    int fila = view.getSelectedRow();

		    if(fila == -1) {

		        JOptionPane.showMessageDialog(
		                null,
		                "Selecciona un alumno"
		        );

		        return;
		    }

		    StudentsTableModels model =
		            (StudentsTableModels) view.getTabla().getModel();

		    Student student = model.getAlumnoAt(fila);

		    QualificationRepository repo =
		            new QualificationRepository();

		    List<String> materias =
		            repo.getMateriasPorAnio(
		                    maestroActual.getAnio()
		            );

		    StudentsRateView ventana =
		            new StudentsRateView(
		                    student,
		                    materias
		            );

		    new ControllerRatings(
		            ventana,
		            student,
		            fila,
		            materias
		    );

		    ventana.cargarCalificaciones(
		            obtenerCalificaciones(student, materias)
		    );

		    ventana.setLocationRelativeTo(null);

		    ventana.setVisible(true);
		});
		
		
		//VER DETALLES
		view.getBtnDetallesA().addActionListener(e -> {

		    int fila = view.getSelectedRow();

		    if (fila == -1) {

		        JOptionPane.showMessageDialog(
		                null,
		                "Selecciona un alumno"
		        );

		        return;
		    }

		    StudentsTableModels model =
		            (StudentsTableModels) view.getTabla().getModel();

		    Student student = model.getAlumnoAt(fila);

		    StudentDetails detalles =
		            new StudentDetails(student);

		    detalles.setVisible(true);
		});
		
		
	}
	private List<Double> obtenerCalificaciones(Student student, List<String> materias) {

	    List<Double> lista = new java.util.ArrayList<>();

	    QualificationRepository repo = new QualificationRepository();

	    for(String materia : materias) {

	        Double calificacion = repo.obtenerCalificacion(student.getMatricula(),materia);

	        if(calificacion == null) {
	            calificacion = 0.0;
	        }

	        lista.add(calificacion);
	    }

	    return lista;
	}
	//=================================================================================================================================================================
	private void actualizarTabla() {

        try {

            StudentsRepository repo =
                    new StudentsRepository();

            List<Student> students =
                    repo.getAlumnosPorGrupo(
                            maestroActual.getAnio(),
                            maestroActual.getGrupo()
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

	private void generarPdf() {

		File file = view.selectPdfFile();

		if (file == null) {
			return;
		}

		try {
			StudentsRepository repo = new StudentsRepository();
			
			List<Student> students =
                    repo.getAlumnosPorGrupo(
                            maestroActual.getAnio(),
                            maestroActual.getGrupo()
                    );

			pdfExporter.exportAlumnos(students, file);

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al exportar PDF");
		}
	}
		
	    
	    private void generarBoleta(Student student) {

	        JFileChooser chooser = new JFileChooser();

	        chooser.setSelectedFile(
	                new File("boleta.pdf")
	        );

	        int option = chooser.showSaveDialog(view);

	        if(option != JFileChooser.APPROVE_OPTION) {
	            return;
	        }

	        File file = chooser.getSelectedFile();

	        try {

	            PDFExporter pdf = new PDFExporter();

	            pdf.exportBoleta(student, file);

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