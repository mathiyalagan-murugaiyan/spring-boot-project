package com.hotel.v2soru.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.UserDao;
import com.hotel.v2soru.dto.UserDto;
import com.hotel.v2soru.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<UserDto>> findUser(long userId){
		User user = userDao.findUser(userId);
		if(user != null) {
			UserDto userDto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(user , userDto);
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(userDto);
			
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		return null; // throw user id not found exception
	}
	
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser(){
		
		List<User> allUser = userDao.findAllUser();
		
		if(!allUser.isEmpty()) {
			List<UserDto> userDto = new ArrayList<UserDto>();
			ModelMapper mapper = new ModelMapper();
			for(User list: allUser){
				userDto.add(mapper.map(list, UserDto.class));
			}
			ResponseStructure<List<UserDto>> structure = new ResponseStructure<List<UserDto>>();
			structure.setMessage("User List Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(userDto);
			
			return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure,HttpStatus.FOUND);
		}
		
		return null;// throw user list not found exception
		
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user){
		
		UserDto userDto = new UserDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(userDao.saveUser(user), userDto);
		
		ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		structure.setMessage("User created");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(userDto);
		
		return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(long userId,User user){
		
		User exuser = userDao.findUser(userId);
		if(exuser != null) {
			UserDto userDto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(userDao.updateUser(userId, user), userDto);
		
			
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Update SuccesFully");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(userDto);
			
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
			
		}
		
		return null; // throw user does not exist
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(long userId){
		User user = userDao.findUser(userId);
		if(user != null) {
			UserDto userDto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(userDao.deleteUser(userId),userDto);
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Deleted SuccessFully");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(userDto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
		}
		
		return null; // throw user id does not exist
	}

}
