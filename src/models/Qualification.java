package models;

public class Qualification {
	
	private int id;

    private String alumnoMatricula;

    private int materiaId;

    private double calificacion;

    public Qualification() {
    }

    public Qualification(String alumnoMatricula, int materiaId, double calificacion) {

        this.alumnoMatricula = alumnoMatricula;
        this.materiaId = materiaId;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlumnoMatricula() {
        return alumnoMatricula;
    }

    public void setAlumnoMatricula(String alumnoMatricula) {
        this.alumnoMatricula = alumnoMatricula;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

}
