package models;

public class Bathmologies{
    private float grade;
    private float changedGrade;

    public Bathmologies(){

    }

    public Bathmologies(float grade){
        this.grade = grade;
    }

    public Bathmologies(float grade, float changedGrade) {
        this.grade = grade;
        this.changedGrade = changedGrade;
    }


    public void setGrade(float grade) {
        this.grade = grade;
    }

    public float getGrade() {
        return grade;
    }

}
