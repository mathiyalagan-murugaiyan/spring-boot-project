package com.hotel.v2soru.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.AdminDao;
import com.hotel.v2soru.dto.AdminDto;
import com.hotel.v2soru.entity.Admin;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
     public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(long adminId){
		Admin admin = adminDao.findAdmin(adminId);
		if(admin != null) {
			AdminDto adminDto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(admin,adminDto);
			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(adminDto);
			
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		return null;// throw new admin does not exist exception
	}
     
     public ResponseEntity<ResponseStructure<List<AdminDto>>> findAlllAdmin(){
    	 
    	 List<Admin> allAdmin = adminDao.findAllAdmin();
    	 if(!allAdmin.isEmpty()) {
    		 List<AdminDto> adminDto = new ArrayList<AdminDto>();
    		 ModelMapper mapper = new ModelMapper();
    		 for(Admin adminlist : allAdmin) {
    			 adminDto.add(mapper.map(adminlist, AdminDto.class));
    		 }
    		 ResponseStructure<List<AdminDto>> structure = new ResponseStructure<List<AdminDto>>();
    		 structure.setMessage("Admin List Found");
    		 structure.setStatusCode(HttpStatus.FOUND.value());
    		 structure.setData(adminDto);
    		return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure,HttpStatus.FOUND); 
    	 }
    	 return null; // throw admin list not found
     }
     
     public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin){
    	 
    	 AdminDto adminDto = new AdminDto();
    	 ModelMapper mapper = new ModelMapper();
    	 mapper.map(adminDao.saveAdmin(admin), adminDto);
    	 
    	 ResponseStructure<AdminDto> structure = new  ResponseStructure<AdminDto>();
    	 structure.setMessage("Admin Created SuccessFully");
    	 structure.setStatusCode(HttpStatus.CREATED.value());
    	 structure.setData(adminDto);
    	 
    	 return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
     }
     
     public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(long adminId, Admin admin){
    	 Admin exadmin = adminDao.findAdmin(adminId);
    	 if(exadmin != null) {
    		 AdminDto adminDto = new AdminDto();
    		 ModelMapper mapper = new ModelMapper();
    		 mapper.map(adminDao.updateAdmin(adminId, admin),adminDto);
    		 
    		 ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
    		 structure.setMessage("Admin update successfully");
    		 structure.setStatusCode(HttpStatus.OK.value());
    		 structure.setData(adminDto);
    		 
    		 return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
    	 }
    	 
    	 return null;//throw  admin does not exist
     }
     
     public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(long adminId){
    	 Admin admin = adminDao.findAdmin(adminId);
    	 if(admin != null) {
    		 AdminDto adminDto = new AdminDto();
    		 ModelMapper mapper = new ModelMapper();
    		 mapper.map(adminDao.deleteAdmin(adminId), adminDto);
    		 
    		 ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
    		 structure.setMessage("Admin deleted successfully");
    		 structure.setStatusCode(HttpStatus.OK.value());
    		 structure.setData(adminDto);
    		 
    		 return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
    	 }
    	 
    	 return null; //throw admin does not exist
     }

}
