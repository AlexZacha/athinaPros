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
public class Professor extends  User{
    
    private Course  [] coursesTaught;
    public Professor(String username,String password, String firstName, String lastName, String email) {
        super(username, password, firstName, lastName, email);
    }
     /* public  ArrayList<Course> getCoursesTaught(){
        int i=0;
        ArrayList<Course> courses =new  ArrayList<>();
       
      while(Account.courses[i]!=null){
            if(Account.courses[i].getProfessor().equals(this))
                courses.add(Account.courses[i])
            i++;
        }
        return courses;
    }*/
    public void setCoursesTaught(Course[] coursesTaught) {
        this.coursesTaught = coursesTaught;
    }
    
    
    
    
     @Override
    public String toString() {
        return super.getUsername();
    }
}
