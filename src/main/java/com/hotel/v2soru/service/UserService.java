package com.hotel.v2soru.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.FoodOrderDao;
import com.hotel.v2soru.dao.UserDao;
import com.hotel.v2soru.dto.UserDto;
import com.hotel.v2soru.entity.FoodOrder;
import com.hotel.v2soru.entity.User;
import com.hotel.v2soru.exception.FoodOrderNotFound;
import com.hotel.v2soru.exception.UserListNotFoundException;
import com.hotel.v2soru.exception.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private FoodOrderDao foodOrderDao;

	public ResponseEntity<ResponseStructure<UserDto>> findUser(long userId) {
		User user = userDao.findUser(userId);
		if (user != null) {
			UserDto userdto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(userDao.findUser(userId), userdto);
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(userdto);

			return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFoundException("User does not exist");// return null; // throw user id not found exception
	}

	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser() {

		List<User> allUser = userDao.findAllUser();

		if (!allUser.isEmpty()) {
			List<UserDto> userDtoList = new ArrayList<UserDto>();
			ModelMapper mapper = new ModelMapper();
			for (User list : allUser) {
				userDtoList.add(mapper.map(list, UserDto.class));
			}
			ResponseStructure<List<UserDto>> structure = new ResponseStructure<List<UserDto>>();
			structure.setMessage("User List Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(userDtoList);
			return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure, HttpStatus.FOUND);
		}
		throw new UserListNotFoundException("User List does not exist");

	}

	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user) {

		UserDto userDto = new UserDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(userDao.saveUser(user), userDto);

		ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		structure.setMessage("User created");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(userDto);
		return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<UserDto>> updateUser(long userId, User user) {

		User exuser = userDao.findUser(userId);
		if (exuser != null && exuser.getUserId() == userId) {

			ModelMapper mapper = new ModelMapper();
			UserDto userDto = mapper.map(userDao.updateUser(userId, user), UserDto.class);

			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Update SuccesFully");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(userDto);

			return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User does not exist");
	}

	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(long userId) {
		User user = userDao.findUser(userId);
		if (user != null) {
		
			ModelMapper mapper = new ModelMapper();
			UserDto userDto =  mapper.map(userDao.deleteUser(userId), UserDto.class);
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Deleted SuccessFully");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(userDto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User does not exist");
	}
    
	@Transactional
	public ResponseEntity<ResponseStructure<User>> assignOreder(long userId, long foodOrderId) {

		User user = userDao.findUser(userId);
		FoodOrder foodOrder = foodOrderDao.findFoodOrder(foodOrderId);

		if (user != null ){
			
			 user.getFoodOrders().add(foodOrder);
			 User updateUser = userDao.updateUser(userId, user);
			 
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(updateUser );
			structure.setMessage("oredr assigned");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);		
		}
		throw new FoodOrderNotFound("FoodOrder does not exist");

	}

	public ResponseEntity<ResponseStructure<User>> userLogin(User user) {
		 User byUserEmail = userDao.userLogin(user.getUserEmail());

		    if (user.getUserEmail() != null && byUserEmail != null) {
		        
		        if (user.getUserEmail().equals(byUserEmail.getUserEmail()) &&
		            user.getUserPassword().equals(byUserEmail.getUserPassword())) {

		            ResponseStructure<User> structure = new ResponseStructure<User>();
		          
		            structure.setMessage("Login successfully");
		            structure.setStatusCode(HttpStatus.OK.value());
		            return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		        }
		    }
		    ResponseStructure<User> errorStructure = new ResponseStructure<User>();
		    errorStructure.setMessage("Login failed");
		    errorStructure.setStatusCode(HttpStatus.UNAUTHORIZED.value());

		    return new ResponseEntity<ResponseStructure<User>>(errorStructure, HttpStatus.UNAUTHORIZED);
		}

}
