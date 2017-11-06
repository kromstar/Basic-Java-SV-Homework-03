package com.course.c02.c03;

import java.io.*;
import java.util.Arrays;

public class Test {
    Student[] students = new Student[100];
    Teacher[] teachers = new Teacher[100];
    Exam[] exams = new Exam[100];
    int studentsCount = 0;
    int teachersCount = 0;
    int examsCount = 0;

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.startMenu();
    }

    private void startMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int exitCode = 1;
        while (exitCode == 1) {
            System.out.println("\nPLEASE SELECT WHAT DO YOU WANT TO DO:\n");
            System.out.println("Enter 'student' to add a new student.");
            System.out.println("Enter 'teacher' to add a new teacher.");
            System.out.println("Enter 'exam' to add a new exam.");
            System.out.println("Enter 'print' to print all teachers, students and exams.");
            System.out.println("Enter 'clear' to clear the entire information.");
            System.out.println("Enter 'save' to save all teachers, students and exams in the given filename.");
            System.out.println("Enter 'load' to clear all lists and load teachers, students and exams from the given filename.");
            System.out.println("Enter 'stop' to leave the menu.");
            String option = reader.readLine();
            switch (option) {
                case "student": {
                    readStudent(reader);
                    break;
                }
                case "teacher": {
                    readTeacher(reader);
                    break;
                }
                case "exam": {
                    readExam(reader);
                    break;
                }
                case "print": {
                    printAll();
                    break;
                }
                case "clear": {
                    clearAll();
                    break;
                }
                case "save": {
                    save(reader);
                    break;
                }
                case "load": {
                    load(reader);
                    break;
                }
                case "stop": {
                    exitCode = 0;
                    break;
                }
                default:
                    System.out.println("\nYou didn't enter a right choice!\n");
            }
        }
        reader.close();
    }

    private void load(BufferedReader reader) throws IOException {
        clearAll();
        System.out.println("Enter the file name:");
        String fileName = reader.readLine();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String line = fileReader.readLine();
        if (!line.equals("N/A")) {
            String[] instances = line.split(";");
            for (String instance : instances) {
                Teacher teacher = new Teacher();
                teacher.deserialize(instance);
                teachers[teachersCount++] = teacher;
            }
        }
        line = fileReader.readLine();
        if (!line.equals("N/A")) {
            String[] instances = line.split(";");
            for (String instance : instances) {
                Student student = new Student();
                student.deserialize(instance);
                students[studentsCount++] = student;
            }
        }
        line = fileReader.readLine();
        if (!line.equals("N/A")) {
            String[] instances = line.split(";");
            for (String instance : instances) {
                Exam exam = new Exam();
                exam.deserialize(instance);
                exams[examsCount++] = exam;
            }
        }
        fileReader.close();
    }

    private void save(BufferedReader reader) throws IOException {
        System.out.println("Enter the file name:");
        String fileName = reader.readLine();
        try {
            FileWriter writer = new FileWriter(fileName);
            if (teachersCount > 0) {
                for (int i = 0; i < teachersCount; i++) {
                    writer.write(teachers[i].serialize());
                }
            } else {
                writer.write("N/A");
            }
            writer.write("\n");
            if (studentsCount > 0) {
                for (int i = 0; i < studentsCount; i++) {
                    writer.write(students[i].serialize());
                }
            } else {
                writer.write("N/A");
            }
            writer.write("\n");
            if (examsCount > 0) {
                for (int i = 0; i < examsCount; i++) {
                    writer.write(exams[i].serialize());
                }
            } else {
                writer.write("N/A");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearAll() {
        Arrays.fill(students, null);
        Arrays.fill(teachers, null);
        Arrays.fill(exams, null);
        teachersCount = 0;
        studentsCount = 0;
        examsCount = 0;
        System.out.println("\nThe entire information has been deleted.\n");
    }

    private void printAll() {
        if (teachersCount > 0) {
            System.out.println("\nThe teachers are:");
            for (int i = 0; i < teachersCount; i++) {
                System.out.print(teachers[i].serialize());
            }
        } else {
            System.out.println("\nThere are no teachers registered.\n");
        }
        if (studentsCount > 0) {
            System.out.println("\n\nThe students are:");
            for (int i = 0; i < studentsCount; i++) {
                System.out.print(students[i].serialize());
            }
        } else {
            System.out.println("\nThere are no students registered.\n");
        }
        if (examsCount > 0) {
            System.out.println("\n");
            for (int i = 0; i < examsCount; i++) {
                System.out.println("Teacher " + exams[i].teacher.firstName + " " + exams[i].teacher.lastName + " graded " + exams[i].student.firstName + " " + exams[i].student.lastName + " with " + exams[i].grade);
            }
        } else {
            System.out.println("There are no exams registered.");
        }
    }

    private void readExam(BufferedReader reader) throws IOException {
        Exam exam = new Exam();
        System.out.println("Enter the exam in the following format: <Student's first name>,<Student's last name>,<Teacher's first name>,<Teacher's last name>,<Grade>");
        String name = reader.readLine();
        exam.deserialize(name);
        exams[examsCount++] = exam;
    }

    private void readTeacher(BufferedReader reader) throws IOException {
        Teacher teacher = new Teacher();
        System.out.println("Enter the name in the following format: <First name>,<Last name>");
        String name = reader.readLine();
        teacher.deserialize(name);
        teachers[teachersCount++] = teacher;
    }

    private void readStudent(BufferedReader reader) throws IOException {
        Student student = new Student();
        System.out.println("Enter the name in the following format: <First name>,<Last name>");
        String name = reader.readLine();
        student.deserialize(name);
        students[studentsCount++] = student;
    }
}
