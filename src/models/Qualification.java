package models;

public class Qualification {
	
	private int id;

    private String studentEnrollment;

    private int subjectId;

    private double qualification;

    public Qualification() {
    }

    public Qualification(String studentEnrollment, int subjectId, double qualification) {

        this.studentEnrollment = studentEnrollment;
        this.subjectId = subjectId;
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentEnrollment() {
        return studentEnrollment;
    }

    public void setStudentEnrollment(String studentEnrollment) {
        this.studentEnrollment = studentEnrollment;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getQualification() {
        return qualification;
    }

    public void setQualification(double qualification) {
        this.qualification = qualification;
    }

}