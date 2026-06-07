package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Teacher;
import repository.TeacherRepository;
import views.TeacherForm;

public class FormTeacherController {

    private TeacherForm view;
    private TeacherRepository repository;

    private boolean editing = false;

    public FormTeacherController(TeacherForm view) {

        this.view = view;

        try {

            this.repository = new TeacherRepository();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, "Error de conexión con la base de datos");
        }

        assignListeners();
    }

  //=================================================================================================================================================================
    public void setEditMode() {

        this.editing = true;

        //Bloquear año
        view.getRbFirstYear().setEnabled(false);
        view.getRbSecondYear().setEnabled(false);
        view.getRbThirdYear().setEnabled(false);

        //Bloquear grupo
        view.getRbA().setEnabled(false);
        view.getRbB().setEnabled(false);
    }

  //=================================================================================================================================================================
    private void assignListeners() {

        view.getTxtName().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validateName();
            }

            public void removeUpdate(DocumentEvent e) {
                validateName();
            }

            public void changedUpdate(DocumentEvent e) {
                validateName();
            }
        });

        view.getTxtBirthDate().getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateBirthDate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateBirthDate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateBirthDate();
            }
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

        view.getTxtAge().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validateAge();
            }

            public void removeUpdate(DocumentEvent e) {
                validateAge();
            }

            public void changedUpdate(DocumentEvent e) {
                validateAge();
            }
        });

        view.getTxtMasterDegree().getDocument().addDocumentListener(
                new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                validateMasterDegree();
            }

            public void removeUpdate(DocumentEvent e) {
                validateMasterDegree();
            }

            public void changedUpdate(DocumentEvent e) {
                validateMasterDegree();
            }
        });

        view.getTxtBirthDate().addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                char character = e.getKeyChar();

                if (!Character.isDigit(character)
                        && character != '/'
                        && character != KeyEvent.VK_BACK_SPACE) {

                    e.consume();
                }
            }
        });

        view.getRbMale().addActionListener(
                e -> validateGender()
        );

        view.getRbFemale().addActionListener(
                e -> validateGender()
        );

        view.getRbFirstYear().addActionListener(
                e -> validateYear()
        );

        view.getRbSecondYear().addActionListener(
                e -> validateYear()
        );

        view.getRbThirdYear().addActionListener(
                e -> validateYear()
        );

        view.getRbA().addActionListener(
                e -> validateGroup()
        );

        view.getRbB().addActionListener(
                e -> validateGroup()
        );

        view.getBtnSave().addActionListener(
                e -> validateForm()
        );

        view.getTxtName().addKeyListener(
                new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                char character = e.getKeyChar();

                if (!Character.isLetter(character)
                        && character != ' '
                        && character != KeyEvent.VK_BACK_SPACE
                        && character != KeyEvent.VK_DELETE) {

                    e.consume();

                    view.setNameError("Solo se permiten letras");
                }
            }
        });
    }

  //=================================================================================================================================================================
    private boolean validateName() {

        if (view.getTxtName().getText().trim().isEmpty()) {

            view.setNameError("El nombre es obligatorio");

            return false;
        }

        view.setNameError("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateEmail() {

        String email = view.getTxtEmail().getText().trim();

        if (email.isEmpty()) {

            view.setEmailError("El email es obligatorio");

            return false;
        }

        if (!email.contains("@")) {

            view.setEmailError("Ingrese un email válido");

            return false;
        }

        view.setEmailError("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validatePassword() {

        String password = new String(view.getTxtPassword().getPassword());

        if (password.trim().isEmpty()) {

            view.setPasswordError("La contraseña es obligatoria");

            return false;
        }

        if (password.length() < 4) {

            view.setPasswordError("Mínimo 4 caracteres");

            return false;
        }

        view.setPasswordError("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateAge() {

        String age = view.getTxtAge().getText().trim();

        if (age.isEmpty()) {

            view.setAgeError("La edad es obligatoria");

            return false;
        }

        try {

            int parsedAge = Integer.parseInt(age);

            if (parsedAge < 18) {

                view.setAgeError("Debe ser mayor o igual a 18 años");

                return false;
            }

        } catch (NumberFormatException ex) {

            view.setAgeError("Solo se permiten números");

            return false;
        }

        view.setAgeError("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateMasterDegree() {

        if (view.getTxtMasterDegree().getText().trim().isEmpty()) {

            view.setMasterDegreeError("La maestría es obligatoria");

            return false;
        }

        view.setMasterDegreeError("");

        return true;
    }

    //LocalDate sirve para manejar fechas, dia, anio y mes sin hora  el parse(textp, formato) convierte el string en una fecha real)
    private boolean validateBirthDate() {

        String birthDate = view.getTxtBirthDate().getText().trim();

        if (birthDate.isEmpty()) {

            view.setBirthDateError("Campo obligatorio");

            return false;
        }

        // EXPRESIÓN REGULAR: VA A VALIDAR que el usuario SOLO escriba números y diagonales en orden
        String typingProcess = "^\\d{0,2}(/?\\d{0,2})?(/?\\d{0,4})?$";

        if (!birthDate.matches(typingProcess)) {

            view.setBirthDateError("Formato invalido: DD/MM/AAAA");

            return false;
        }

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd/MM/uuuu")
                            .withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(birthDate, formatter);

            view.setBirthDateError("");

            return true;

        } catch (Exception e) {

            view.setBirthDateError("Fecha irreal o inválida");

            return false;
        }
    }

  //=================================================================================================================================================================
    private boolean validateGender() {

        if (!view.getRbMale().isSelected()
                && !view.getRbFemale().isSelected()) {

            view.setGenderError("Seleccione un sexo");

            return false;
        }

        view.setGenderError("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateYear() {

        if (!view.getRbFirstYear().isSelected()
                && !view.getRbSecondYear().isSelected()
                && !view.getRbThirdYear().isSelected()) {

            view.setYearError("Seleccione un año");

            return false;
        }

        view.setYearError("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateGroup() {

        if (!view.getRbA().isSelected()
                && !view.getRbB().isSelected()) {

            view.setGroupError("Seleccione un grupo");

            return false;
        }

        view.setGroupError("");

        return true;
    }

  //=================================================================================================================================================================
    private boolean validateAvailableGroup() {

        String year =
                view.getRbFirstYear().isSelected() ? "1"
                : view.getRbSecondYear().isSelected() ? "2"
                : "3";

        String group =
                view.getRbA().isSelected() ? "A"
                : "B";

        try {

            boolean occupied =
                    repository.teacherExistsInGroup(year, group);

            if (occupied && !editing) {

                JOptionPane.showMessageDialog(
                        view,
                        "Ya existe un maestro asignado al grupo "
                                + year + group
                );

                return false;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al validar grupo"
            );

            return false;
        }

        return true;
    }

  //=================================================================================================================================================================
    private void validateForm() {

        boolean validation = true;

        if (!validateName()) validation = false;

        if (!validateEmail()) validation = false;

        if (!validatePassword()) validation = false;

        if (!validateBirthDate()) validation = false;

        if (!validateAge()) validation = false;

        if (!validateMasterDegree()) validation = false;

        if (!validateGender()) validation = false;

        if (!validateYear()) validation = false;

        if (!validateGroup()) validation = false;

        if (!validation) {

            return;
        }

        try {

            if (repository.emailExists(
                    view.getTxtEmail().getText().trim())) {

                JOptionPane.showMessageDialog(
                        view,
                        "El correo ya está registrado"
                );

                return;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al validar correo"
            );

            return;
        }

        try {

            if (repository.countTeachers() >= 6 && !editing) {

                JOptionPane.showMessageDialog(
                        view,
                        "Ya existen los 6 maestros permitidos"
                );

                return;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al validar cantidad de maestros"
            );

            return;
        }

        if (!validateAvailableGroup()) {

            return;
        }

        try {

            String userDate = view.getTxtBirthDate().getText().trim();

            String databaseDate = "";

            try {

                DateTimeFormatter inputFormat =
                        DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                .withResolverStyle(ResolverStyle.STRICT);

                LocalDate localDate =
                        LocalDate.parse(userDate, inputFormat);

                databaseDate = localDate.toString();

            } catch (Exception ex) {

                view.setBirthDateError(
                        "Formato inválido (DD/MM/AAAA)"
                );

                return;
            }

            Teacher teacher = new Teacher();

            teacher.setName(
                    view.getTxtName().getText()
            );

            teacher.setEmail(
                    view.getTxtEmail().getText()
            );

            teacher.setPassword(
                    new String(view.getTxtPassword().getPassword())
            );

            teacher.setAge(
                    Integer.parseInt(view.getTxtAge().getText())
            );

            teacher.setBirthDate(databaseDate);

            teacher.setMasterDegree(
                    view.getTxtMasterDegree().getText()
            );

            teacher.setGender(
                    view.getRbMale().isSelected() ? "H" : "M"
            );

            teacher.setYear(
                    view.getRbFirstYear().isSelected() ? "1"
                    : view.getRbSecondYear().isSelected() ? "2"
                    : "3"
            );

            teacher.setGroup(
                    view.getRbA().isSelected() ? "A"
                    : "B"
            );

            teacher.setRole("MAESTRO");

            if (editing) {

                repository.update(teacher);

            } else {

                repository.save(teacher);
            }

            JOptionPane.showMessageDialog(
                    view,
                    editing
                            ? "Maestro actualizado correctamente"
                            : "Maestro registrado correctamente"
            );

            view.dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error al registrar maestro: "
                            + e.getMessage()
            );
        }
    }
}