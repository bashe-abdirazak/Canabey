package com.khalid.recipeblog.services;

import com.khalid.recipeblog.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.khalid.recipeblog.entities.UserRegistration;

public interface UserService extends UserDetailsService{

	User save(UserRegistration userRegistration);
}
