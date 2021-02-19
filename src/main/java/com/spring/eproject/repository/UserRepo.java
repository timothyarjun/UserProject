package com.spring.eproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.eproject.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	@Query(value = "select * from user where phone = :phone or email = :email", nativeQuery = true)
	User findByEmailOrPhone(@Param("phone") String phone, @Param("email") String email);
	
}
