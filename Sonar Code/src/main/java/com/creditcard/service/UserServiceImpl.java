package com.creditcard.service;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcard.dao.UserRepository;
import com.creditcard.entities.User;
import com.creditcard.exception.DuplicateUserException;
import com.creditcard.exception.InvalidLoginCredentialException;
import com.creditcard.exception.InvalidUserIdException;
import com.creditcard.exception.InvalidUserNameException;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserRepository userRepository;
	
	private Logger logger= Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	public User registerUser(User user) throws DuplicateUserException {

		try {
			  user = this.getUserByUserName(user.getUserName());
			  return user;
		} catch (InvalidUserNameException exception) {
			userRepository.save(user);
			return user;
		}
	//	throw new DuplicateUserException();

	}

	@Override
	public User loginUser(User user) throws InvalidLoginCredentialException {

		List<User> users = userRepository.findAll();
		logger.log((LogRecord) users);
		for (User user1 : users) {
			if (Objects.equals(user.getUserName(), user1.getUserName())
					&& Objects.equals(user.getPassword(), user1.getPassword()))
				return user1;
		}
		throw new InvalidLoginCredentialException();
	}



	@Override
	public User changePassword(User user, String np) throws InvalidLoginCredentialException {
		

		List<User> users = userRepository.findAll();
		for (User user1 : users) {
			if (Objects.equals(user1.getUserName(), user.getUserName())
					&& Objects.equals(user1.getPassword(), user.getPassword())) {
				user1.setPassword(np);
				userRepository.save(user1);
				return user1;
			}
		}

		throw new InvalidLoginCredentialException();
	}

	@Override
	public User getUserById(Integer uid) {

		try {
			Optional<User> user = userRepository.findById(uid);
			if (user.isPresent())
				return user.get();
		} catch (Exception e) {
			throw new InvalidLoginCredentialException();
		}
		throw new InvalidUserIdException();
	}

	@Override
	public User getUserByUserName(String username) {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (Objects.equals(user.getUserName(), username)) {
				return user;
			}
		}
		throw new InvalidUserNameException();
	}

	@Override
	public List<User> getallUsers() {
		return userRepository.findAll();
	}

}
