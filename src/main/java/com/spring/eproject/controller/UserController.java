package com.spring.eproject.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.eproject.dto.ChangePasswordVM;
import com.spring.eproject.dto.UserTransaction;
import com.spring.eproject.entity.Categories;
import com.spring.eproject.entity.Transaction;
import com.spring.eproject.entity.User;
import com.spring.eproject.service.CategoryService;
import com.spring.eproject.service.TransactionService;
import com.spring.eproject.service.UserService;
import com.spring.eproject.service.ValidationService;

@RestController
@RequestMapping("api/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ValidationService validationService;

	public User mapUserToEntityModel(User user) {
		User users = null;
		if (user.getId() == 0) {
			users = new User();
			log.info("create new user " + user.getId());
		} else {
			users = userService.findOne(user.getId());
			users.setId(user.getId());
			log.info("update user " + user.getId());
		}

		users.setAge(user.getAge());
		users.setDob(user.getDob());
		users.setImage(user.getImage());

		if (validationService.validateName(user.getFirstName()) && validationService.validateName(user.getLastName())) {
			users.setFirstName(user.getFirstName());
			users.setLastName(user.getLastName());
			log.info("validate user name " + user.getFirstName() + " and " + user.getLastName());
		}

		if (validationService.validateEmail(user.getEmail()) && validationService.validatePhone(user.getPhone())) {
			if (userService.emailOrPhoneDuplicate(user.getEmail(), user.getPhone())) {
				users.setEmail(user.getEmail());
				users.setPhone(user.getPhone());
				log.info("Email and phone " + user.getEmail() + " and phone " + user.getPhone());

				if (validationService.validatePassword(user.getPassword())) {
					users.setPassword(user.getPassword());
					log.info("validate password " + user.getPassword());
					return users;
				}

			}

			log.info("validate Email and phone " + user.getEmail() + " and phone " + user.getPhone());
		}
		
		log.info("user fields are incorrect");
		return null;
	}

	@PostMapping("/login")
	public ResponseEntity<String> logIn(@RequestBody User user) {
		return ResponseEntity.ok().body(userService.logIn(user));
	}

	@PostMapping("/register")
	public ResponseEntity<User> userRegister(@RequestBody User user) {
		User toUser = mapUserToEntityModel(user);
		log.info("register successfully" + toUser.getEmail());
		return ResponseEntity.ok().body(userService.registerUser(toUser));
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordVM changepassword){
		return ResponseEntity.ok().body(userService.changePassword(changepassword, true));
	}
	
	@PostMapping("/createPassword")
	public ResponseEntity<String> createPassword(@RequestBody ChangePasswordVM changepassword){
		return ResponseEntity.ok().body(userService.changePassword(changepassword, false));
	}
	
	@PostMapping("profile-pic-upload")
	public void uploadProfilePic(@RequestParam("file") MultipartFile file, @RequestParam("id") long userId){
		userService.uploadProfilePic(file, userId);
	}

	@GetMapping("/getuser/{id}")
	public User getOne(@PathVariable long id) {
		return userService.findOne(id);
	}

	@PostMapping("/addCategory")
	public ResponseEntity<Categories> addCategory(@RequestBody Categories category) {
		return ResponseEntity.ok().body(categoryService.addCategory(category));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Categories>> listCategory() {
		return ResponseEntity.ok().body(categoryService.listCategory());
	}

	@GetMapping("/getCategory/{id}")
	public ResponseEntity<Categories> getCategory(@PathVariable long id) {
		return ResponseEntity.ok().body(categoryService.getCategory(id));
	}

	@SuppressWarnings("unused")
	@PostMapping("/{categoryId}/transaction")
	public ResponseEntity<Transaction> transaction(@PathVariable long categoryId,
			@RequestBody Transaction transaction) {
		Categories c = categoryService.getCategory(categoryId);
		if (c.getTitle() != null) {
			Transaction t = new Transaction();
			t.setTransactionId(transaction.getTransactionId());
			t.setUserId(transaction.getUserId());
			t.setCategoryId(categoryId);
			t.setAmount(transaction.getAmount());
			t.setNote(transaction.getNote());
			t.setTransactionDate(transaction.getTransactionDate());
			return ResponseEntity.ok().body(transactionService.transaction(t));
		}

		return ResponseEntity.ok().body(new Transaction());
	}

	@GetMapping("/getTransaction/{id}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable long id) {
		return ResponseEntity.ok().body(transactionService.getTransaction(id));
	}

	@PutMapping("/updateTransaction/{transId}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable long transId,
			@RequestBody Transaction transaction) {
		Transaction t = transactionService.getTransaction(transId);
		Transaction ts = new Transaction();
		if (t != null) {
			ts.setTransactionId(t.getTransactionId());
			ts.setCategoryId(t.getCategoryId());
			ts.setUserId(t.getUserId());
			ts.setAmount(transaction.getAmount());
			ts.setNote(transaction.getNote());
			ts.setTransactionDate(transaction.getTransactionDate());
		}
		return ResponseEntity.ok().body(transactionService.transaction(ts));
	}

	@GetMapping("/transByUser")
	public ResponseEntity<List<UserTransaction>> getTransactionByUser() {
		return ResponseEntity.ok().body(transactionService.getUserTransaction());
	}

}
