package org.example;

import java.time.LocalDate;
import java.util.Scanner;
//SPRINGBOOT ANNOTATIONS
//REST APIS
//SPRING DATA ANNOTATIONS
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        CoursesServices coursesServices = new CoursesServices();
        marksServices marksServices = new marksServices();
        while (true) {
            System.out.println("Select one of these options: ");
            System.out.println("a.add a student ");
            System.out.println("b. view all students ");
            System.out.println("c.update student ");
            System.out.println("d.delete a student ");
            System.out.println("e.Add a course ");
            System.out.println("f.Get ALl courses ");
            System.out.println("g.Update a course ");
            System.out.println("h.delete a course");
            System.out.println("i.Add Mark [student id, course id, marks]");
            System.out.println("j.View Student Marks [student_id]");
            System.out.println("k.Update Student Mark in a course");
            System.out.println("l.Delete Student Mark in a certain course ");
            System.out.println("m.Exit ");

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

                    studentService.create(new Students( 0, firstname, lastname, email, dob));


                break;

                case "b":
                    for( Students Std : studentService.findAll()){
                    System.out.println(Std);
                    }

                break;
                case "c":
                    System.out.print("Enter the ID you want to update: ");
                    int id = Integer.parseInt(sc.nextLine());

                    Students student = studentService.findById(id);

                    if (student == null) {
                        System.out.println("Student not found.");
                        break; // Exit the case to avoid NullPointerException
                    }

                    // Prompt for new values
                    System.out.print("New first name (" + student.getFirst_name() + "): ");
                    String newFirst = sc.nextLine();

                    System.out.print("New last name (" + student.getLast_name() + "): ");
                    String newLast = sc.nextLine();

                    System.out.print("New email (" + student.getEmail() + "): ");
                    String newEmail = sc.nextLine();

                    System.out.print("New date of birth (" + student.getDate_of_birth() + ") [yyyy-mm-dd]: ");
                    String newDob = sc.nextLine();

                    // Update with non-empty values
                    student.setFirst_name(!newFirst.isEmpty() ? newFirst : student.getFirst_name());
                    student.setLast_name(!newLast.isEmpty() ? newLast : student.getLast_name());
                    student.setEmail(!newEmail.isEmpty() ? newEmail : student.getEmail());
                    if (!newDob.isEmpty()) {
                        student.setDate_of_birth(newDob);
                    }

                    studentService.update(student);
                    System.out.println("Student updated.");
                    break;


                case "d":
                    System.out.println("Enter the id you want to delete");
                    int student_id = Integer.parseInt(sc.nextLine());
                    studentService.deleteById(student_id);
                    System.out.println("The student with id " + student_id + " was deleted.");

                break;
                case "e":
                    System.out.println("Enter a course name");
                    String course_name = sc.nextLine();
                    System.out.println("Enter course description");
                    String course_description = sc.nextLine();
                    coursesServices.create(new courses(0, course_name, course_description));
                    break;

                case "f":

                    for(courses course: coursesServices.findAll()){
                        System.out.println(course.toString());
                    }

                break;

                case "g":

                    System.out.println("Enter the id you want to update");
                    int courseId = Integer.parseInt(sc.nextLine());
                    courses course = coursesServices.findById(courseId);
                    course = new courses(course.getId(), course.getCourse_name(), course.getCourse_description() );
                    coursesServices.findById(course.getId());

                break;

                case "h":
                    System.out.println("Enter the id you want to delete");
                    int course_id = Integer.parseInt(sc.nextLine());
                    coursesServices.deleteById(course_id);
                    System.out.println("The course with id " + course_id + " was deleted.");
                break;

                case "i":

                    System.out.print("Student ID: ");
                    int sid = Integer.parseInt(sc.nextLine());
                    System.out.print("Course ID: ");
                    int cid = Integer.parseInt(sc.nextLine());
                    System.out.print("Marks: ");
                    int m = Integer.parseInt(sc.nextLine());
                    marksServices.create(new marks(sid, cid, m));
                    System.out.println("Mark added/updated.");

                break;

                case "j":
                        for( marks mark : marksServices.findAll()){
                             System.out.println( mark.toString());
                             }
                break;

                case "k":
                    System.out.println("Enter the id you want to update");
                    int s_id = Integer.parseInt(sc.nextLine());
                    marks mark1 = marksServices.findById(s_id);
                    mark1 = new marks(mark1.getStudent_id(), mark1.getCourse_id(), mark1.getMarks());
                    marksServices.update(mark1);

                    break;

                case "l":
                    System.out.println("Enter the student id you want to delete");
                    int st_id = Integer.parseInt(sc.nextLine());
                    marksServices.deleteById(st_id);
                    System.out.println("The student with id " + st_id + " was deleted.");


                    break;
                case "m":

                    System.out.println("This is the end of the program");
                break;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
}