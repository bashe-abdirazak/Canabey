package com.khalid.recipeblog.repositories;

import com.khalid.recipeblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
	
}
