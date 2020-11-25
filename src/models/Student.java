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
public class Student extends User{
     private int currentSemester;
     private String dateEnrolled;
    
    public Student(String username,String password, String firstName,
        String lastName,int currentSemester,String dateEnrolled) {
        super(username, password, firstName, lastName);
        this.currentSemester = currentSemester;
        this.dateEnrolled = dateEnrolled;
    }
    
    /*public ArrayList<CourseRegistration> getRegistrations(){
        int i=0;
        ArrayList <CourseRegistration> currentRegistrations=new ArrayList<>();
        
    }*/
    
    
     public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(String dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }
}
