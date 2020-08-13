package com.home.module.home1.HiberMapper;

import com.home.module.home1.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.X
 **/
@Repository
public  interface SchoolMapper extends JpaRepository<School,Integer> {
}
