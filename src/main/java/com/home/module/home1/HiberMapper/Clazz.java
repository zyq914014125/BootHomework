package com.home.module.home1.HiberMapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.X
 **/
@Repository
public interface Clazz extends JpaRepository<Clazz,Integer> {
}
