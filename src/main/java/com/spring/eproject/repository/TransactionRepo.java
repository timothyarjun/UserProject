package com.spring.eproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.eproject.dto.UserTransaction;
import com.spring.eproject.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	
	@Query(value = "select * from userdb.transaction inner join userdb.usertbl on userdb.transaction.user_id = userdb.usertbl.id", nativeQuery = true)
	List<UserTransaction> getUserTransaction();

}
