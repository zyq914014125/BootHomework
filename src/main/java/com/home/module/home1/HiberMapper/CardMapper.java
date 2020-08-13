package com.home.module.home1.HiberMapper;

import com.home.module.home1.entity.Card;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author @Mr.x
 */
@Repository
public interface CardMapper extends JpaRepository<Card,Integer> {
}
