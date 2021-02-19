package com.spring.eproject.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.eproject.controller.UserController;
import com.spring.eproject.dto.ChangePasswordVM;
import com.spring.eproject.entity.ImageLoader;
import com.spring.eproject.entity.User;
import com.spring.eproject.repository.ImageLoaderRepo;
import com.spring.eproject.repository.UserRepo;

import ch.qos.logback.classic.Logger;

@Service
public class UserService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ImageLoaderRepo imgRepo;

	@Autowired
	private ValidationService validationService;

	public User registerUser(User user) {
		return userRepo.save(user);
	}

	public boolean emailOrPhoneDuplicate(String username, String phone) {
		User user = userRepo.findByEmailOrPhone(username, phone);
		if (user != null)
			return false;
		else
			return true;
	}

	public User findOne(long id) {
		return userRepo.getOne(id);
	}

	public String logIn(User user) {
		User user1 = userRepo.findByEmailOrPhone(user.getPhone(), user.getEmail());
		System.out.println(user1.getPhone() + user1.getEmail());
		log.info("user info" + user1.getEmail() + user1.getPhone() + user1.getPassword());
		if (user1.getPassword().equals(user.getPassword()))
			return "login Successful...";
		else
			return "Incorrect Password";

	}

	public String changePassword(ChangePasswordVM changepassword, boolean isChangePassword) {
		String result = "success";

		if (!changepassword.getNewPassword().equals(changepassword.getConfirmPassword())) {
			throw new Error("password not matching");
		}

		if (changepassword.getUserId() == null) {
			throw new Error("userId Mandatory");
		}

		User user = userRepo.getOne(changepassword.getUserId());

		if (isChangePassword) {
			if (user.getId() != changepassword.getUserId()) {
				throw new Error("User id not matching");
			}

			if (!user.getPassword().equals(changepassword.getOldPassword())) {
				throw new Error("User Password wrong");
			}
		}

		user.setPassword(changepassword.getNewPassword());
		userRepo.save(user);
		return result;
	}

	public String uploadProfilePic(MultipartFile file, long userId) {
		try {
		//	InputStream inputstream = new ByteArrayInputStream(file.getBytes());
			String path = "upload/" + userId + "/" + file.getOriginalFilename();
			User user = userRepo.getOne(userId);
			ImageLoader img = new ImageLoader(userId, path, file.getBytes(), file.getContentType());
			if (user != null) {
				user.setImage(path);
				imgRepo.save(img);
			}
			return "success";

		} catch (IOException e) {
			return "Error";
		}

	}

}
