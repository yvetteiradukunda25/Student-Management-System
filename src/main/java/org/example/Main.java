package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        while (true) {
            System.out.println("Select one of these options: ");
            System.out.println("a.add a student ");
            System.out.println("b. view all students ");
            System.out.println("c.update student ");
            System.out.println("d.delete a student ");
            System.out.println("e.Add a course ");
            System.out.println("f.Get ALl courses ");
            System.out.println("g.View courses ");
            System.out.println("h.Update Course ");
            System.out.println("i.delete a course");
            System.out.println("j.Add Mark [student id, course id, marks]");
            System.out.println("k.View Student Marks [student_id]");
            System.out.println("l.Update Student Mark in a course");
            System.out.println("m.Delete Student Mark in a certain course ");


            String choice = sc.nextLine();
            switch (choice) {
                case "a":
                    System.out.println("Enter the firstname");
                    String firstname = sc.nextLine();
                    System.out.println("Enter the lastname");
                    String lastname = sc.nextLine();
                    System.out.println("Enter your email");
                    String email = sc.nextLine();
                    System.out.println("Date of birth");
                    String dob = sc.nextLine();

                    studentService.create(new Students( firstname, lastname, email, dob));


                break;

                case "b":
                    for( Students Std : studentService.findAll())
                    System.out.println( Std.toString());

                break;
                case "c":
                    //codes


                break;

                case "d":

                    //codes

                break;
                case "e":
//codes
                    break;

                case "f":
            //codes


                break;

                case "g":
           //codes


                break;

                case "h":
             //codes

                break;

                case "i":
              //codes

                break;

                case "j":
//codes
                break;

                case "k":

                    //codes
                    break;

                case "l": {

                }
                case "m": {

                }

                break;
            }

        }
    }
}