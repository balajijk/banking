package com.eltropy.bank.ebank.service.impl;


import com.eltropy.bank.ebank.model.User;
import com.eltropy.bank.ebank.model.UserDto;
import com.eltropy.bank.ebank.repository.RoleRepository;
import com.eltropy.bank.ebank.repository.UserDao;
import com.eltropy.bank.ebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).isPresent() ? userDao.findById(id).get() : null;
	}

	@Override
    public User save(UserDto user) {

		/*User userFromDB = userDao.findByUsername(user.getUsername());
		if(userFromDB !=null ) {
			throw new RuntimeException("User already exists");
		}*/


		User newUser = new User();
	    newUser.setUsername(user.getUsername());
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());



		newUser.setRoles(Collections.singleton(roleRepository.findByName(user.getRoleEnum().name())));

		return userDao.save(newUser);
    }
}
