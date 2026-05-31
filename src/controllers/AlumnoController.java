package controllers;

import java.awt.Desktop;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File; 
import models.Alumno;
import models.Maestro;
import repository.AlumnoRepository;
import repository.CalificacionRepository;
import services.PDFExporter;
import tablemodels.AlumnoTableModels;
import views.AlumnosView;
import views.CalificarAlumnoView;
import views.Formulario;
import views.DetallesAlumno;

public class AlumnoController {
	
	private AlumnosView view;
	private PDFExporter pdfExporter;
	private Maestro maestroActual;
		
	public AlumnoController(AlumnosView view, Maestro maestro) {
		this.view = view;
		
		this.maestroActual = maestro;
		
		pdfExporter = new PDFExporter();
		
		//AGREGAR
		view.getBtnAgregar().addActionListener(e -> {
			String anio = maestroActual.getAnio();
		    String grupo = maestroActual.getGrupo();
		    
		    AlumnoRepository repo = new AlumnoRepository();
		    int totalAlumnos = repo.contarAlumnosPorGrupo(anio, grupo);
		    
		    if (totalAlumnos >= 10) {
		        JOptionPane.showMessageDialog(view, 
		            "Ya existen los 10 alumnos permitidos para el grupo de " + anio + "° '" + grupo + "'", 
		            "Grupo Lleno", 
		            JOptionPane.INFORMATION_MESSAGE);
		        return; 
		    }
		    
			Formulario form = new Formulario();
	
			FormularioController controller =new FormularioController(form,maestroActual);
			
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

		    AlumnoTableModels model = (AlumnoTableModels) view.getTabla().getModel();

		    Alumno alumno = model.getAlumnoAt(fila);
            CalificacionRepository repo = new CalificacionRepository();

            List<String> materias = repo.getMateriasPorAnio(maestroActual.getAnio());

            CalificarAlumnoView ventana = new CalificarAlumnoView( alumno,materias );
            
            new CalificacionesController(ventana, alumno, fila,  materias );

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
			AlumnoTableModels model = (AlumnoTableModels) view.getTabla().getModel();
			Alumno alumno = model.getAlumnoAt(fila);
			
			Formulario form = new Formulario();
			FormularioController controller =new FormularioController(form,maestroActual);
			
			form.cargarDatos(alumno);
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
					AlumnoRepository repo = new AlumnoRepository();

		            AlumnoTableModels model = (AlumnoTableModels) view.getTabla().getModel();

		            Alumno alumno = model.getAlumnoAt(fila);

		            repo.delete(alumno.getMatricula());
		            
		            
					//List<Alumno> alumnos = repo.getAlumnos();
					List<Alumno> alumnos =
                            repo.getAlumnosPorGrupo(
                                    maestroActual.getAnio(),
                                    maestroActual.getGrupo()
                            );

                    view.setTableModel(
                            new AlumnoTableModels(alumnos)
                    );
					
					view.setTableModel(new AlumnoTableModels(alumnos));
					
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

		    AlumnoTableModels model =
		            (AlumnoTableModels) view.getTabla().getModel();

		    Alumno alumno = model.getAlumnoAt(fila);

		    generarBoleta(alumno);
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

		    AlumnoTableModels model =
		            (AlumnoTableModels) view.getTabla().getModel();

		    Alumno alumno = model.getAlumnoAt(fila);

		    CalificacionRepository repo =
		            new CalificacionRepository();

		    List<String> materias =
		            repo.getMateriasPorAnio(
		                    maestroActual.getAnio()
		            );

		    CalificarAlumnoView ventana =
		            new CalificarAlumnoView(
		                    alumno,
		                    materias
		            );

		    new CalificacionesController(
		            ventana,
		            alumno,
		            fila,
		            materias
		    );

		    ventana.cargarCalificaciones(
		            obtenerCalificaciones(alumno, materias)
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

		    AlumnoTableModels model =
		            (AlumnoTableModels) view.getTabla().getModel();

		    Alumno alumno = model.getAlumnoAt(fila);

		    DetallesAlumno detalles =
		            new DetallesAlumno(alumno);

		    detalles.setVisible(true);
		});
		
		
	}
	private List<Double> obtenerCalificaciones(Alumno alumno, List<String> materias) {

	    List<Double> lista = new java.util.ArrayList<>();

	    CalificacionRepository repo = new CalificacionRepository();

	    for(String materia : materias) {

	        Double calificacion = repo.obtenerCalificacion(alumno.getMatricula(),materia);

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

            AlumnoRepository repo =
                    new AlumnoRepository();

            List<Alumno> alumnos =
                    repo.getAlumnosPorGrupo(
                            maestroActual.getAnio(),
                            maestroActual.getGrupo()
                    );

            view.setTableModel(
                    new AlumnoTableModels(alumnos)
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
			AlumnoRepository repo = new AlumnoRepository();
			
			List<Alumno> alumnos =
                    repo.getAlumnosPorGrupo(
                            maestroActual.getAnio(),
                            maestroActual.getGrupo()
                    );

			pdfExporter.exportAlumnos(alumnos, file);

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al exportar PDF");
		}
	}
		
	    
	    private void generarBoleta(Alumno alumno) {

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

	            pdf.exportBoleta(alumno, file);

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