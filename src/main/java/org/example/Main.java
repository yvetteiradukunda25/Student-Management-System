package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        while (true) {
            System.out.println("a.add student ");
            System.out.println("b. view student ");
            System.out.println("c.delete student ");
            System.out.println("Select: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "a": {
                    System.out.println("Enter the firstname");
                    String firstname = sc.nextLine();
                    System.out.println("Enter the lastname");
                    String lastname = sc.nextLine();
                    System.out.println("Enter your email");
                    String email = sc.nextLine();
                    System.out.println("Date of birth");
                    String dob = sc.nextLine();

                    studentService.create(new Students(0, firstname, lastname, email, dob));


                }

                case "b": {
                    System.out.println("View all students");
                    studentService.findAll();
                }
            }

        }
    }
}