package com.itsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupport.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
