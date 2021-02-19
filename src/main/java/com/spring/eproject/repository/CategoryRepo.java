package com.spring.eproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.eproject.entity.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Long> {

}
