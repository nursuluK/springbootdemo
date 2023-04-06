package com.springboot.springbootdemo.repositories;

import com.springboot.springbootdemo.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Categories,Long> {
}
