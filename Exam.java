package com.course.c02.c03;

public class Exam {
    Student student;
    Teacher teacher;
    int grade;

    public Exam() {
        this.student = new Student();
        this.teacher = new Teacher();
    }

    String serialize() {
        return student.firstName + "," + student.lastName + "," + teacher.firstName + "," + teacher.lastName + "," + grade + ";";
    }

    void deserialize(String serialized) {
        String[] words = serialized.split(",");
        student.firstName = words[0];
        student.lastName = words[1];
        teacher.firstName = words[2];
        teacher.lastName = words[3];
        grade = Integer.parseInt(words[4]);
    }
}