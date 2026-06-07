package controllers;

import java.awt.Desktop;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Teacher;
import models.Subject;
import models.Student;

import repository.StudentsRepository;
import services.PDFExporter;

import tablemodels.StudentsTableModels;
import tablemodels.QualificationTableModel;

import repository.QualificationRepository;
import repository.SubjectRepository;

import views.Form;
import views.LoginWindow;
import views.MainView;

import config.Config;

public class HomeController {

    private MainView view;
    private Teacher currentTeacher;

    public HomeController(MainView view, Teacher teacher) {

        this.view = view;
        this.currentTeacher = teacher;

        new StudentController(view.studentsPanel, teacher);

        new ListTeachersController(view.teachersListPanel);

        registerListeners();
    }

    private void registerListeners() {

        //Salir
        view.logoutItem.addActionListener(e -> handleClose());

        view.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                handleClose();
            }
        });

        //Mostrar Alumnos
        view.studentsListItem.addActionListener(e -> showStudents());

        view.profileItem.addActionListener(e -> showTeacher());

        //Inicio
        view.homeMenu.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {

                view.showView(MainView.HOME);
            }
        });

        view.qualificationsItem.addActionListener(
                e -> showQualifications()
        );

        view.teachersListPanel.getBtnPdf().addActionListener(
                e -> exportTeachersPDF()
        );

        view.adminProfileItem.addActionListener(
                e -> showAdmin()
        );

        view.teachersListItem.addActionListener(
                e -> showTeachersList()
        );

        view.qualificationsPanel.getExportPdfButton().addActionListener(
                e -> exportQualificationsPdf()
        );
    }

    private void exportQualificationsPdf() {

        File file = view.qualificationsPanel.selectPdfFile();

        // Si el usuario canceló
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

            // Validar alumnos
            if (students == null || students.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "No hay alumnos registrados"
                );

                return;
            }

            SubjectRepository subjectRepository =
                    new SubjectRepository();

            List<Subject> subjectsList =
                    subjectRepository.getSubjectsByYear(
                            currentTeacher.getYear()
                    );

            // Validar materias
            if (subjectsList == null || subjectsList.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "No hay materias registradas"
                );

                return;
            }

            List<String> subjects =
                    new ArrayList<>();

            for (Subject subject : subjectsList) {

                subjects.add(subject.getName());
            }

            // Agregar extensión .pdf automáticamente
            if (!file.getName().toLowerCase().endsWith(".pdf")) {

                file = new File(
                        file.getAbsolutePath() + ".pdf"
                );
            }

            PDFExporter exporter =
                    new PDFExporter();

            exporter.exportQualifications(
                    students,
                    subjects,
                    file
            );

            JOptionPane.showMessageDialog(
                    view,
                    "PDF exportado correctamente"
            );

            // Abrir PDF automáticamente
            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop().open(file);
            }

        } catch (IOException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error de archivo o permisos:\n"
                            + e.getMessage()
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error al exportar PDF:\n"
                            + e.getMessage()
            );
        }
    }

    //Exportar maestros
    private void exportTeachersPDF() {

        // Seleccionar archivo
        File file = view.teachersListPanel.selectPdfFile();

        if (file == null) {

            return;
        }

        try {

            // Obtener maestros
            repository.TeacherRepository repository =
                    new repository.TeacherRepository();

            List<Teacher> teachers =
                    repository.getOnlyTeachers();

            // Validar lista vacía
            if (teachers == null || teachers.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "No hay maestros registrados"
                );

                return;
            }

            // Exportar PDF
            PDFExporter exporter =
                    new PDFExporter();

            exporter.exportTeachers(
                    teachers,
                    file
            );

            JOptionPane.showMessageDialog(
                    view,
                    "PDF exportado correctamente"
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error al exportar PDF"
            );
        }
    }

    private void showStudents() {

        StudentsRepository repository =
                new StudentsRepository();

        try {

            /*List<Alumno> alumnos = repository.getAlumnos();

            if (alumnos == null || alumnos.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "No hay alumnos registrados");
                return;
            }

            AlumnoTableModels modelAlumnos =
                    new AlumnoTableModels(alumnos);

            view.alumnosPanel.setTableModel(modelAlumnos);
            new AlumnoController(view.alumnosPanel);

            view.showView(MainView.ALUMNOS);*/

            List<Student> students =
                    repository.getStudentsByGroup(
                            currentTeacher.getYear(),
                            currentTeacher.getGroup()
                    );

            StudentsTableModels studentsModel =
                    new StudentsTableModels(students);

            view.studentsPanel.setTableModel(
                    studentsModel
            );

            //new AlumnoController(view.alumnosPanel);

            view.showView(MainView.STUDENTS);

            if (students == null || students.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "No hay alumnos registrados"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al cargar alumnos: "
                            + e.getMessage()
            );
        }
    }

    private void showTeacher() {

        view.teacherPanel.updateTeacher(
                currentTeacher
        );

        view.showView(MainView.TEACHER);
    }

    private void showQualifications() {

        try {

            StudentsRepository repository =
                    new StudentsRepository();

            // Obtener alumnos del grupo
            List<Student> students =
                    repository.getStudentsByGroup(
                            currentTeacher.getYear(),
                            currentTeacher.getGroup()
                    );

            // Obtener materias
            SubjectRepository subjectRepository =
                    new SubjectRepository();

            List<Subject> subjectsList =
                    subjectRepository.getSubjectsByYear(
                            currentTeacher.getYear()
                    );

            // Convertir Materia a String
            List<String> subjects =
                    new ArrayList<>();

            for (Subject subject : subjectsList) {

                subjects.add(subject.getName());
            }

            if (students == null || students.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "No hay alumnos registrados"
                );

                return;
            }

            // Crear modelo
            QualificationTableModel qualificationModel =
                    new QualificationTableModel(
                            students,
                            subjects
                    );

            // Asignar tabla
            view.qualificationsPanel.setTableModel(
                    qualificationModel
            );

            // Mostrar panel
            view.showView(MainView.QUALIFICATIONS);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error al cargar calificaciones: "
                            + e.getMessage()
            );
        }
    }

    private void showAdmin() {

        //Actualizar datos admin
        view.adminPanel.updateAdmin(
                currentTeacher
        );

        //Mostrar panel admin
        view.showView(MainView.ADMIN_PROFILE);
    }

    private void showTeachersList() {

        view.showView(MainView.TEACHERS_LIST);
    }

    private void handleClose() {

        int option = view.confirmExit();

        if (option == JOptionPane.YES_OPTION) {

            new LoginWindow();

            //view.dispose();

            /*LoginWindow ventana = new LoginWindow();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);*/

            /*LoginWindow login = new LoginWindow();
            login.setLocationRelativeTo(null);
            login.setVisible(true);*/

            view.dispose();
        }
    }

    private void returnToLogin() {

        int option = JOptionPane.showConfirmDialog(
                view,
                "¿Deseas cerrar sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {

            LoginWindow login =
                    new LoginWindow();

            login.setLocationRelativeTo(null);

            login.setVisible(true);

            view.dispose();
        }
    }
}