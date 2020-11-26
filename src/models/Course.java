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
public class Course {
    private  String id;
    private  String name;
    private  int credits;
    private int semester;
    private Professor professor;
   
    public  Course(String id){
        this.id=id;
    }
    public Course(String id, String name, int credits, int semester, Professor professor) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.professor = professor;
    }
    //todo registration 
     public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
     public int getCredits() {
        return credits;
    }

    public int getSemester() {
        return semester;
    }
    public Professor getProfessor() {
        return professor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", semester=" + semester +
                ", professor=" + professor +
                '}';
    }
}
