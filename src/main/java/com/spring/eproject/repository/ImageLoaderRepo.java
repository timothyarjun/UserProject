package com.spring.eproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.eproject.entity.ImageLoader;

public interface ImageLoaderRepo extends JpaRepository<ImageLoader, Long> {

}
