package com.sou.WebApp.service;

import com.sou.WebApp.entity.Student;
import com.sou.WebApp.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
//In usual, we write the body of these methods in service and call it from the service package,
//but since we haven't used it we directly writing it in the body itself

@Service
public class StudentService {

    @Autowired
    private StudentRepo sr;

//    public Map<String, List<Student>> allDetails(String orderByHeader, String sortByHeader) {
//        List<Student> studentList = sr.findAll();
//        Comparator<Student> comparator = null;
//        Map<String, List<Student>> studentMap = null;
//
//        switch (sortByHeader) {
//            case "name":
//                comparator = Comparator.comparing(Student::getName);
//                break;
//            case "emailId":
//                comparator = Comparator.comparing(Student::getEmailId);
//                break;
//            case "department":
//                comparator = Comparator.comparing(Student::getDepartment);
//                break;
//            case "marks":
//                comparator = Comparator.comparing(Student::getMarks);
//                break;
//            default:
//                // Default sorting by name
//                comparator = Comparator.comparing(Student::getName);
//                break;
//        }
//
//        if (orderByHeader != null && orderByHeader.equals("descending")) {
//            if (comparator != null) {
//                comparator = comparator.reversed();
//            }
//        }
//
//        if (comparator != null) {
//            studentMap = studentList.stream()
//                    .sorted(comparator)
//                    .collect(Collectors.groupingBy(Student::getDepartment));
//        }
//
//        return studentMap;
//    }


    public Map<String, List<Student>> allDetails(String orderByHeader, String sortByHeader){
        List<Student> studentList = sr.findAll();
            Comparator<Student> comparator = null;
        Map<String, List<Student>> studentMap = null;

            switch (sortByHeader) {
                case "name":
                    comparator = Comparator.comparing(Student::getName);
                    break;
                case "emailId":
                    comparator = Comparator.comparing(Student::getEmailId);
                    break;
                case "department":
                    comparator = Comparator.comparing(Student::getDepartment);
                    break;
                case "marks":
                    comparator = Comparator.comparing(Student::getMarks);
                    break;
                default:
                    // Default sorting by name
                    comparator = Comparator.comparing(Student::getName);
                    break;
            }
            if(orderByHeader.equals( "descending"))
            {
                studentMap = studentList.stream()
                        .sorted(comparator.reversed())
                        .collect(Collectors.groupingBy(Student::getDepartment));
            }
            else{
                studentMap = studentList.stream()
                        .sorted(comparator)
                        .collect(Collectors.groupingBy(Student::getDepartment));
            }
            return  studentMap;

    }
    public void groupStudentsByClass() {
        List<Student> allStudents = sr.findAll();

        // Grouping students by their class using Java Stream API
        //Map<String, List<Student>> groupOfStudents = allStudents.stream()
                                                    //.collect(Collectors.groupingBy(Student::getStudentClass));

        //return ;
    }

    public ResponseEntity<?> getStudentByID(long id){
        Optional<Student> student = sr.findById(id);
        if(student.isPresent())
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Student not found!!", HttpStatus.OK);
    }
    //Since we need details based on id we are providing parameters inside get mapping
    // and since we are it with id in student we use Pathvariable as id and check with if else conditions
    // If the student id is present so that is the reason we are using Optional
    //And since we are aware that all details are of different type we are providing ResponseEntity<?> instead of void

    public ResponseEntity<?> getStudentByDept(String department){
        List<Student> student = sr.findByDepartment(department);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    public String createStudent(Student student){
        sr.save(student);
        return "Details have been saved !!";
    }
    //Since we are giving details in the body of Postman we are using @RequestBody annotation
    // and later save for saving the details os student that we created in body sr.save(In which ? student)


    public String deleteStudent(long id) {
        Optional<Student> student = sr.findById(id);
        if (student.isPresent()) {
            sr.delete(student.get());
            return "Student details deleted !!";
        } else
            return "Student not found !!";
    }
    //Since both the return types are strings void (Return Type) is changed to string,
    // else if ResponseEntity<?>




    public String updateStudent(Student student, long id){
        Optional<Student> s = sr.findById(id);
        if (s.isPresent()){
            student.setId(id);
            sr.save(student);
            return "Details have been Updated !!";
        }
        else{
            return "No Student with that id";
        }
        //        Get student details by id , so after validating with id ,
//        we set student id with s's id and then save the details

    }

}


