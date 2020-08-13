package com.home.module.home1.HiberMapper;

import com.home.module.home1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.X
 **/
@Repository
public interface StudentMapper extends JpaRepository<Student,Integer> {
}
