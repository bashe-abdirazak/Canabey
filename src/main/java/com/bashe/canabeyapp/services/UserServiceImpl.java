package com.khalid.recipeblog.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.khalid.recipeblog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.khalid.recipeblog.entities.Role;
import com.khalid.recipeblog.entities.UserRegistration;
import com.khalid.recipeblog.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User save(UserRegistration userRegistration) {
		User user = new User(userRegistration.getFirstName(), userRegistration.getLastName(),userRegistration.getEmail(),
				passwordEncoder.encode(userRegistration.getPassword()));
		if(userRegistration.getRole()==null){
			user.addRole(new Role("ADMIN"));
		}
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password![1]");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
