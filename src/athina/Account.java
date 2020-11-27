package athina;
import models.*;

public class Account {
    public static User[] users;
    public static Student[] students;
    public static Professor[] professors;
    public static Admin[] admins;
    public static Course[] courses;
    public static CourseRegistration[] registrations;
    
    public Account() {
        students = new Student[50];
        professors = new Professor[20];
        admins = new Admin[10];
        courses = new Course[30];
        registrations = new CourseRegistration[50];
    }
    
    public void initializeData(){
        students[0] = new Student("aalexiou","123", "Alexis", "Alexiou", "aalexiou@it.teithe.gr",3 , "");
        students[1] = new Student("ddimitriou","123", "Dimitris", "Dimitriou", "ddimitriou@it.teithe.gr",5 , "");
        students[2] = new Student("nnikou", "123","Nikos", "Nikou","nnikou@it.teithe.gr",3 , "");
        
        professors[0] = new Professor("vkostogl","123", "Vasileios", "Kostoglou", "vkostogl@teithe.gr");
        professors[1] = new Professor("idel", "123","Ignatios", "Deligianis", "idel@teithe.gr");
        professors[2] = new Professor("stoug", "123","Stefanos", "Ougiaroglou", "stoug@teithe.gr");
        
        admins[0] = new Admin("gkakou", "123","Georgia", "Kakou", "gkakou@it.teithe.gr");
        
        courses[0] = new Course("072", "Epixeirisiaki Ereuna", 6, 7, professors[0]);
        courses[1] = new Course("023", "Antikeimenostrefis Programmatismos", 6, 2, professors[0]);
        courses[2] = new Course("071", "Mhxaniki Logismikou", 6,7, professors[1]);
        courses[3] = new Course("044", "Sxediash Leitourgikwn Systimatwn", 40, 4, professors[1]);
        
        registrations[0] = new CourseRegistration(students[0], courses[0], "2018-19 ΕΑΡ","20/5/2019");
        registrations[1] = new CourseRegistration(students[1], courses[1], "2018-19 XEIM","20/11/2020");
        registrations[2] = new CourseRegistration(students[2], courses[2], "2018-19 ΕΑΡ","20/5/2019");
        registrations[3] = new CourseRegistration(students[2], courses[3], "2018-19 XEIM","20/11/2020");
        registrations[4] = new CourseRegistration(students[0], courses[2], "2018-19 ΕΑΡ","20/5/2019");
        registrations[5] = new CourseRegistration(students[0], courses[3], "2018-19 ΕΑΡ","20/5/2019");

        registrations[0].setBathmos(6);
        registrations[1].setBathmos(4);
        registrations[3].setBathmos(7);
        registrations[4].setBathmos(8);
        registrations[5].setBathmos(3);
        registrations[0].setKainBathmos(9);
    }
    public static void insertAdmin(Admin admin) {
        for (int i=0; i<admins.length; i++){
            if (admins[i] == null)
                admins[i] = admin;
                SMTP.sendEmail(Account.admins[i].getFirstName(), Account.admins[i].getLastName(), Account.admins[i].getUsername(),Account.admins[i].getPassword(), Account.admins[i].getEmail());
                break;
        }
    }
    
    
    public static void insertProfessor(Professor professor) {
        for (int i=0; i<professors.length; i++){
            if (professors[i] == null)
                professors[i] = professor;
                SMTP.sendEmail(Account.professors[i].getFirstName(), Account.professors[i].getLastName(), Account.professors[i].getUsername(), Account.professors[i].getPassword(), Account.professors[i].getEmail());
        }
    }
     public static void insertStudent(Student student) {
        for (int i=0; i<students.length; i++) {
            if (students[i] == null)
                students[i] = student;

                SMTP.sendEmail(Account.students[i].getFirstName(), Account.students[i].getLastName(), Account.students[i].getUsername(), Account.students[i].getPassword(), Account.students[i].getEmail());
        }
    }
         public static void insertCourse(Course course) {
        for (int i=0; i<courses.length; i++) {
            if (courses[i] == null)
            {
                courses[i] = course;
                return;
            }
        }
    }
         
     public static void insertRegistration(Student student, Course course) {
        for(int i=0; i<registrations.length; i++){
            if (registrations[i] == null) {
                registrations[i] = new CourseRegistration(student, course, "2018-19 XEIM","20/11/2020");
            }
        }
        
    }
    public static Course[] getFullCourse() {
        Course fullCourse[]=new Course[30];
        int i =0;
        for(Course course : courses){
            
            if (course == null)
                break;
            fullCourse[i] = course;
            i++;
               
        }
        return fullCourse;
    }
    
    private static boolean courseExists(String id)
    {
        for(Course course : courses)
        {
            if (course == null)
                break;
            
            if (course.getId().equals(id))
                return true;
        }
        return false;
    }
    
}
