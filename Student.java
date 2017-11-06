package com.course.c02.c03;

public class Student {
    String firstName;
    String lastName;

    String serialize() {
        return firstName + "," + lastName + ";";
    }

    void deserialize(String serialized) {
        String[] words = serialized.split(",");
        firstName = words[0];
        lastName = words[1];
    }
}