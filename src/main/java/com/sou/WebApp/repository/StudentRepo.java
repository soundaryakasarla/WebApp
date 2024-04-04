package com.sou.WebApp.repository;

import com.sou.WebApp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//broker
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByDepartment(String department);
}
