package com.xenby.demo.repository;

import com.xenby.demo.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HomeworkRepository extends JpaRepository<Homework, Integer>, JpaSpecificationExecutor<Homework> {

}
