package com.xenby.demo.repository;

import com.xenby.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    public List<Student> findByName(String name);
}
