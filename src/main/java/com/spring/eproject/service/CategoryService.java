package com.spring.eproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.eproject.entity.Categories;
import com.spring.eproject.repository.CategoryRepo;
import com.spring.eproject.repository.UserRepo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;

	public Categories addCategory(Categories category) {
		System.out.println(category.getUser().getId());
		category.setUser(userRepo.getOne(category.getUser().getId()));
		return categoryRepo.save(category);
	}
	
	public List<Categories> listCategory(){
		return categoryRepo.findAll();
	}
	
	public Categories getCategory(long id) {
		return categoryRepo.getOne(id);
	}
}
