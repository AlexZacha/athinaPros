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
    private Bathmologies bathmologies;

    public CourseRegistration(Student student, Course course, String registrationSemester,String dateRegistered, Bathmologies bathmologies) {
        this.student = student;
        this.course = course;
        this.registrationSemester = registrationSemester;
        this.dateRegistered = dateRegistered;
        this.bathmologies = bathmologies;
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
        return "CourseRegistration{" + "student=" + student + ", course=" + course + ", registrationSemester=" + registrationSemester + '}';
    }
}
