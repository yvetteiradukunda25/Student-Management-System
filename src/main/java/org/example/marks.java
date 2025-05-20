package org.example;

public class marks {
    private int student_id;
    private int course_id;
    private int marks;

    public marks (int student_id, int course_id, int marks) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.marks = marks;
    }

    public int getStudent_id() {
        return student_id;
    }
    public int getCourse_id() {

        return course_id;
    }
    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "marks{" +
                "student_id=" + student_id +
                ", course_id=" + course_id +
                ", marks=" + marks +
                '}';
    }
}

