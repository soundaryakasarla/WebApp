package com.sou.WebApp.controller;

import com.sou.WebApp.entity.Student;
import com.sou.WebApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webapp/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Map<String, List<Student>> allDetails(@RequestHeader(value = "orderBY", required = false, defaultValue = "descending") String orderByHeader,
                                                 @RequestHeader(value = "sortBY",required = false, defaultValue = "department") String sortByHeader){
     return studentService.allDetails(orderByHeader, sortByHeader);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentByID(@PathVariable long id){
       return studentService.getStudentByID(id);
    }

//    @GetMapping
//    public void groupStudentsByClass() {
//        studentService.groupStudentsByClass();
//    }
    @GetMapping("/class/{department}")
    public ResponseEntity<?> getStudentByDept(@PathVariable String department){
        return studentService.getStudentByDept(department);
    }

    @PostMapping
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }



    @PatchMapping("/{id}")
    public String updateStudent(@RequestBody Student student, @PathVariable long id){
        return studentService.updateStudent(student, id);
    }
//27/3
    //sortby orderby in header for get all request DONE
    //add service layer - done
    //add address table using 1-1 mapping DONE
    //Create another appln and call this application using web client

//28/3
    //Group by class of student

}
