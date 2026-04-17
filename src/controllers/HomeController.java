package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.Alumno;
import repository.AlumnoRepository;
import tablemodels.AlumnoTableModels;
import views.Formulario;
import views.LoginWindow;
import views.MainView;

public class HomeController {

    private MainView view;

    public HomeController(MainView view) {
        this.view = view;
        registerListeners();
    }

    private void registerListeners() {

        //Salir
        view.salir.addActionListener(e -> handleClose());
       
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClose();
            }
        });

        //Abrir Formulario
        view.mItemRegistrar.addActionListener(e -> {
            Formulario formulario = new Formulario();
            new FormularioController(formulario);
            
            formulario.setLocationRelativeTo(null);
            formulario.setVisible(true);
        });

        //Mostrar Alumnos
        view.mItemListaAlumnos.addActionListener(e -> mostrarAlumnos());
        
        //Inicio
        view.inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                view.showView(MainView.HOME);
            }
        });
        
    }

    private void mostrarAlumnos() {

        AlumnoRepository repository = new AlumnoRepository();

        try {
            List<Alumno> alumnos = repository.getAlumnos();

            if (alumnos.isEmpty()) {
                JOptionPane.showMessageDialog(view, "No hay alumnos registrados");
                return;
            }
            
            AlumnoTableModels model = new AlumnoTableModels(alumnos);

            view.alumnosPanel.setTableModel(model);

            view.showView(MainView.ALUMNOS);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
            

            
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
    
    
    private void volverLogin() {

        int option = JOptionPane.showConfirmDialog(
                view,
                "¿Deseas cerrar sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {

            LoginWindow login = new LoginWindow();
            login.setLocationRelativeTo(null);
            login.setVisible(true);

            view.dispose();
        }
    }
}