package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Maestro;
import repository.MaestroRepository;
import views.FormularioMaestro;

public class FormularioMaestroController {

    private FormularioMaestro view;
    private MaestroRepository repository;

    private boolean editando = false;

    public FormularioMaestroController(FormularioMaestro view) {

        this.view = view;

        try {

            this.repository = new MaestroRepository();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog( view, "Error de conexión con la base de datos");
        }

        assignListeners();

        bloquearGruposOcupados();
    }
  //=================================================================================================================================================================
    public void setModoEdicion() {

        this.editando = true;

        //Bloquear año
        view.getRb1().setEnabled(false);
        view.getRb2().setEnabled(false);
        view.getRb3().setEnabled(false);

        //Bloquear grupo
        view.getRbA().setEnabled(false);
        view.getRbB().setEnabled(false);
    }
  //=================================================================================================================================================================
    private void assignListeners() {

        view.getTxtNombre().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validateNombre();
            }

            public void removeUpdate(DocumentEvent e) {
                validateNombre();
            }

            public void changedUpdate(DocumentEvent e) {
                validateNombre();
            }
        });
        
        view.getFechaNacimiento().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validateFechaNacimiento(); }
            public void removeUpdate(DocumentEvent e) { validateFechaNacimiento(); }
            public void changedUpdate(DocumentEvent e) { validateFechaNacimiento(); }
        });

        view.getTxtEmail().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validateEmail();
            }

            public void removeUpdate(DocumentEvent e) {
                validateEmail();
            }

            public void changedUpdate(DocumentEvent e) {
                validateEmail();
            }
        });

        view.getTxtPassword().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validatePassword();
            }

            public void removeUpdate(DocumentEvent e) {
                validatePassword();
            }

            public void changedUpdate(DocumentEvent e) {
                validatePassword();
            }
        });


        view.getTxtEdad().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validateEdad();
            }

            public void removeUpdate(DocumentEvent e) {
                validateEdad();
            }

            public void changedUpdate(DocumentEvent e) {
                validateEdad();
            }
        });


        view.getTxtMaestria().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validateMaestria();
            }

            public void removeUpdate(DocumentEvent e) {
                validateMaestria();
            }

            public void changedUpdate(DocumentEvent e) {
                validateMaestria();
            }
        });


        view.getRbHombre().addActionListener(
                e -> validateSexo()
        );

        view.getRbMujer().addActionListener(
                e -> validateSexo()
        );

        view.getRb1().addActionListener(
                e -> validateAnio()
        );

        view.getRb2().addActionListener(
                e -> validateAnio()
        );

        view.getRb3().addActionListener(
                e -> validateAnio()
        );

        view.getRbA().addActionListener(
                e -> validateGrupo()
        );

        view.getRbB().addActionListener(
                e -> validateGrupo()
        );



        view.getBtnGuardar().addActionListener(
                e -> validarFormulario()
        );



        view.getTxtNombre().addKeyListener(
                new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();

                if (!Character.isLetter(c)&& c != ' '&& c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {

                    e.consume();

                    view.setErrorNombre("Solo se permiten letras");
                }
            }
        });
    }
  //=================================================================================================================================================================
    private boolean validateNombre() {

        if (view.getTxtNombre().getText().trim().isEmpty()) {

            view.setErrorNombre( "El nombre es obligatorio");

            return false;
        }

        view.setErrorNombre("");

        return true;
    }
  //=================================================================================================================================================================
    private boolean validateEmail() {

        String email =view.getTxtEmail().getText() .trim();

        if (email.isEmpty()) {

            view.setErrorEmail("El email es obligatorio" );

            return false;
        }

        if (!email.contains("@")) {
            view.setErrorEmail("Ingrese un email válido");

            return false;
        }

        view.setErrorEmail("");

        return true;
    }
  //=================================================================================================================================================================
    private boolean validatePassword() {

        String password = new String(view.getTxtPassword() .getPassword());

        if (password.trim().isEmpty()) {
            view.setErrorPassword( "La contraseña es obligatoria" );

            return false;
        }

        if (password.length() < 4) {

            view.setErrorPassword("Mínimo 4 caracteres" );

            return false;
        }

        view.setErrorPassword("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateEdad() {

        String edad =view.getTxtEdad().getText() .trim();

        if (edad.isEmpty()) {
            view.setErrorEdad("La edad es obligatoria");

            return false;
        }

        try {

            int e = Integer.parseInt(edad);

            if (e < 18) {
                view.setErrorEdad("Debe ser mayor o igual a 18 años");
                return false;
            }

        } catch (NumberFormatException ex) {

            view.setErrorEdad( "Solo se permiten números");

            return false;
        }

        view.setErrorEdad("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateMaestria() {

        if (view.getTxtMaestria().getText().trim().isEmpty()) {

            view.setErrorMaestria("La maestría es obligatoria");

            return false;
        }

        view.setErrorMaestria("");

        return true;
    }
    
    private boolean validateFechaNacimiento() {
        if (view.getFechaNacimiento().getText().trim().isEmpty()) {
            view.setErrorFechaNacimiento("La fecha de nacimiento es obligatoria");
            return false;
        }
        view.setErrorFechaNacimiento("");
        return true;
    }
    
  //=================================================================================================================================================================
    private boolean validateSexo() {

        if (!view.getRbHombre().isSelected()&& !view.getRbMujer().isSelected()) {

            view.setErrorSexo( "Seleccione un sexo" );

            return false;
        }

        view.setErrorSexo("");

        return true;
    }
  //=================================================================================================================================================================
    private boolean validateAnio() {

        if (!view.getRb1().isSelected()&& !view.getRb2().isSelected()&& !view.getRb3().isSelected()) {

            view.setErrorAnio("Seleccione un año");

            return false;
        }

        view.setErrorAnio("");

        return true;
    }
  //=================================================================================================================================================================
    private boolean validateGrupo() {

        if (!view.getRbA().isSelected()&& !view.getRbB().isSelected()) {

            view.setErrorGrupo("Seleccione un grupo");

            return false;
        }

        view.setErrorGrupo("");

        return true;
    }
  //=================================================================================================================================================================
    private boolean validarGrupoDisponible() {

        String anio =view.getRb1().isSelected() ? "1": view.getRb2().isSelected() ? "2": "3";

        String grupo =view.getRbA().isSelected() ? "A" : "B";

        try {

            boolean ocupado =repository.existeMaestroEnGrupo(anio,grupo);

            if (ocupado && !editando) {

                JOptionPane.showMessageDialog(view,"Ya existe un maestro asignado al grupo "+ anio + grupo);

                return false;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view,"Error al validar grupo");

            return false;
        }

        return true;
    }

  //=================================================================================================================================================================
    private void bloquearGruposOcupados() {

        try {

            if (repository.existeMaestroEnGrupo("1", "A")) {

                view.getRbA().setEnabled(false);
            }

            if (repository.existeMaestroEnGrupo("1", "B")) {

                view.getRbB().setEnabled(false);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, "Error al cargar grupos");
        }
    }
    
    
  //=================================================================================================================================================================
    private void validarFormulario() {

        boolean validacion = true;

        if (!validateNombre()) validacion = false;

        if (!validateEmail()) validacion = false;

        if (!validatePassword()) validacion = false;
        if (!validateFechaNacimiento()) validacion = false;

        if (!validateEdad()) validacion = false;

        if (!validateMaestria()) validacion = false;

        if (!validateSexo()) validacion = false;

        if (!validateAnio()) validacion = false;

        if (!validateGrupo()) validacion = false;

        if (!validacion) {

            return;
        }

        try {

            if (repository.contarMaestros() >= 6 && !editando) {

                JOptionPane.showMessageDialog(view,"Ya existen los 6 maestros permitidos");

                return;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog( view, "Error al validar cantidad de maestros" );

            return;
        }

        if (!validarGrupoDisponible()) {

            return;
        }

        try {

            Maestro maestro = new Maestro();

            maestro.setNombre(view.getTxtNombre().getText());

            maestro.setEmail( view.getTxtEmail().getText());

            maestro.setPassword( new String(view.getTxtPassword() .getPassword()) );

            maestro.setEdad(Integer.parseInt(view.getTxtEdad().getText() ));

            maestro.setFechaNacimiento(view.getFechaNacimiento().getText());
            maestro.setMaestria( view.getTxtMaestria().getText());

            maestro.setSexo(view.getRbHombre().isSelected()? "H": "M");

            maestro.setAnio( view.getRb1().isSelected() ? "1": view.getRb2().isSelected() ? "2": "3");

            maestro.setGrupo( view.getRbA().isSelected() ? "A": "B");

            maestro.setRole("MAESTRO");


            if (editando) {
                repository.actualizar(maestro);

            } else {

                repository.guardar(maestro);
            }

            JOptionPane.showMessageDialog(view, editando? "Maestro actualizado correctamente": "Maestro registrado correctamente");

            view.dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(view,"Error al registrar maestro: "+ e.getMessage());
        }
    }
    
    
}