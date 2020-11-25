/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author dimi44
 */
public class CourseRegistration {
    private Student student;
    private Course course;
    private String registrationSemester;
    private String dateExamined;
    private String dateRegistered;
    private float bathmos;

    public CourseRegistration(Student student, Course course, String registrationSemester,String dateRegistered,  float bathmos) {
        this.student = student;
        this.course = course;
        this.registrationSemester = registrationSemester;
        this.dateRegistered = dateRegistered;
        this.bathmos = bathmos;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public float getBathmos() {
        return bathmos;
    }

    public void setBathmos(float bathmos) {
        this.bathmos = bathmos;
    }

    public void setDateExamined(String date) {
        this.dateExamined = date;
    }
    
    public String getDateExamined() {
        return dateExamined;
    }
    
    public String getDateRegistered() {
                return dateRegistered;
    }


    @Override
    public String toString() {
        return "" +
                "        " + course +
                "        " + registrationSemester + '\'' +
                "        " + bathmos;
    }
}
