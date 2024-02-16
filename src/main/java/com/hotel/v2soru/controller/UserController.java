package com.hotel.v2soru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dto.UserDto;
import com.hotel.v2soru.entity.User;
import com.hotel.v2soru.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("find")
	public ResponseEntity<ResponseStructure<UserDto>> findUser(@RequestParam long userId) {

		return userService.findUser(userId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser(){
		
		return userService.findAllUser();
	}
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(@Valid @RequestBody User user, BindingResult result){
		
		return userService.saveUser(user);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(@RequestParam long userId, @RequestBody User user){
		
		return userService.updateUser(userId, user);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(@RequestParam long userId){
		
		return userService.deleteUser(userId);
	}
	
	@PutMapping("assignOrder")
	public ResponseEntity<ResponseStructure<User>> assignOrder(@RequestParam long userId,@RequestParam long foodOrderId){
		
		return userService.assignOreder(userId, foodOrderId);
	}
	
	@PostMapping("userLogin")
	public ResponseEntity<ResponseStructure<User>> userLogin(@RequestBody User user){
		return userService.userLogin(user);
	}

}
