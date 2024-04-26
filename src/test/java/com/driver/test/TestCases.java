package com.driver.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.driver.Application;
import com.driver.Teacher;
import com.driver.Student;
import com.driver.StudentController;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

    @Autowired
    StudentController studentController;

    @Test
    @Order(1)
    public void studentTest1(){
        Student student1 = new Student("Alex", 19, 79.9);
        studentController.addStudent(student1);

        Student student2 = new Student("Bob", 12, 88.8);
        studentController.addStudent(student2);

        Student student3 = new Student("Carl Toss", 14, 89.9);
        studentController.addStudent(student3);

        Student student4 = new Student("Dilan", 17, 98.2);
        studentController.addStudent(student4);

        Student student5 = new Student("Wiley", 14, 97.9);
        studentController.addStudent(student5);

        ResponseEntity<Student> response = studentController.getStudentByName(student1.getName());

        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            assertEquals(response.getBody().getAge(), 19);
        }
    }

    @Test
    @Order(2)
    public void studentTest2(){
        Teacher Teacher1 = new Teacher("David Fincher", 21, 42);
        studentController.addTeacher(Teacher1);

        Teacher Teacher2 = new Teacher("Martin Scorcesse", 32, 31);
        studentController.addTeacher(Teacher2);

        ResponseEntity<Teacher> response = studentController.getTeacherByName(Teacher1.getName());

        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            assertEquals(response.getBody().getNumberOfStudents(), 21);
        }
    }

    @Test
    @Order(3)
    public void studentTest3(){

        studentController.addStudentTeacherPair("Alex", "Martin Scorcesse");
        studentController.addStudentTeacherPair("Bob", "Martin Scorcesse");
        studentController.addStudentTeacherPair("Carl Toss", "David Fincher");

        ResponseEntity<List<String>> response = studentController.getStudentsByTeacherName("Martin Scorcesse");
        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            int match = 0;
            for(String student: response.getBody()){
                if(student.equals("Bob")){
                    match++;
                }else if(student.equals("Alex")){
                    match += 4;
                }
            }

            assertEquals(match, 5);
        }
    }

    @Test
    @Order(4)
    public void studentTest4(){
        ResponseEntity<List<String>> response = studentController.getAllStudents();
        List<String> students = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        int match = 0;

        for(String student: students){
            if(student.equals("Alex")){
                match++;
            }else if(student.equals("Bob")){
                match += 4;
            }else if(student.equals("Carl Toss")){
                match += 9;
            }else if(student.equals("Dilan")){
                match += 11;
            }else if(student.equals("Wiley")){
                match += 14;
            }
        }

        assertEquals(match, 39);
    }

    @Test
    @Order(5)
    public void studentTest5(){
        studentController.deleteTeacherByName("David Fincher");

        ResponseEntity<List<String>> response = studentController.getAllStudents();
        List<String> students = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        int match = 0;

        for(String student: students){
            if(student.equals("Alex")){
                match++;
            }else if(student.equals("Bob")){
                match += 4;
            }else if(student.equals("Carl Toss")){
                match += 9;
            }else if(student.equals("Dilan")){
                match += 11;
            }else if(student.equals("Wiley")){
                match += 14;
            }
        }

        assertEquals(match, 30);
    }

    @Test
    @Order(6)
    public void studentTest6(){
        studentController.deleteAllTeachers();
        ResponseEntity<List<String>> response = studentController.getAllStudents();
        List<String> students = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        assertEquals(students.size(), 2);
    }

    @Test
    @Order(7)
    public void studentTest7(){
        studentController.deleteAllTeachers();
        ResponseEntity<List<String>> response = studentController.getAllStudents();
        List<String> students = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        int match = 0;

        for(String student: students){
            if(student.equals("Alex")){
                match++;
            }else if(student.equals("Bob")){
                match += 4;
            }else if(student.equals("Carl Toss")){
                match += 9;
            }else if(student.equals("Dilan")){
                match += 11;
            }else if(student.equals("Wiley")){
                match += 14;
            }
        }

        assertEquals(match, 25);
    }
}