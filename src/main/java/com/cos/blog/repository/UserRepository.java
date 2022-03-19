package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	//SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}



//JPA Naming 쿼리
	//SELECT * FROM user WHERE username = ?1 AND Password = 2?;
	//User findByUsernameAndPassword(String username, String password);
	
	//	@Query(value="SELECT * FROM user WHERE username = ?1 AND Password = ?2",nativeQuery = true)
	//	User login(String username, String password);