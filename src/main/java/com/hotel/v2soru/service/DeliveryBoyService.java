package com.hotel.v2soru.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.DeliveryBoyDao;
import com.hotel.v2soru.dto.DeliveryBoyDto;
import com.hotel.v2soru.entity.DeliveryBoy;
import com.hotel.v2soru.exception.DeliveryBoyListNotFound;
import com.hotel.v2soru.exception.DeliveryBoyNotFound;

@Service
public class DeliveryBoyService {
	
	@Autowired
	private DeliveryBoyDao deliveryBoyDao;
	
	
	public ResponseEntity<ResponseStructure<DeliveryBoyDto>> findDeliveryBoy(long deliveryBoyId){
		DeliveryBoy deliveryBoy = deliveryBoyDao.findDeliveryBoy(deliveryBoyId);
		if(deliveryBoy != null) {
			
			ModelMapper mapper = new ModelMapper();
			DeliveryBoyDto deliveryBoyDto = mapper.map(deliveryBoy, DeliveryBoyDto.class);
			ResponseStructure<DeliveryBoyDto> structure = new ResponseStructure<>();
			structure.setMessage("DeliveryBoy found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(deliveryBoyDto);
			
			return new ResponseEntity<ResponseStructure<DeliveryBoyDto>>(structure,HttpStatus.FOUND);
		}
		throw new DeliveryBoyNotFound("DeliveryBoy does not exist");
	}
	
	public ResponseEntity<ResponseStructure<List<DeliveryBoyDto>>> findAllDeliveyBoy(){
		List<DeliveryBoy> allDeliveryBoy = deliveryBoyDao.findAllDeliveryBoy();
		if(!allDeliveryBoy.isEmpty()) {
			List<DeliveryBoyDto> deliveryBoyDto = new ArrayList<DeliveryBoyDto>();
			ModelMapper mapper = new ModelMapper();
			for (DeliveryBoy deliveryBoyListlist : allDeliveryBoy) {
				deliveryBoyDto.add(mapper.map(deliveryBoyListlist, DeliveryBoyDto.class));
			}
			ResponseStructure<List<DeliveryBoyDto>> structure = new ResponseStructure<List<DeliveryBoyDto>>();
			structure.setMessage("DeliverBoy List Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(deliveryBoyDto);
			
			return new ResponseEntity<ResponseStructure<List<DeliveryBoyDto>>>(structure,HttpStatus.FOUND);
		}
		throw new DeliveryBoyListNotFound("DeliveryBoy List does not exist");
	}
	
	public ResponseEntity<ResponseStructure<DeliveryBoy>> saveDeliveryBoy(DeliveryBoy deliveryBoy){
		  ResponseStructure<DeliveryBoy> structure = new ResponseStructure<DeliveryBoy>();
		  structure.setMessage("DeliveryBoy saved");
		  structure.setStatusCode(HttpStatus.CREATED.value());
		  structure.setData(deliveryBoyDao.saveDeliveryBoy(deliveryBoy));
		
		return new ResponseEntity<ResponseStructure<DeliveryBoy>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<DeliveryBoy>> updateDeliveryBoy(long deliverBoyId, DeliveryBoy deliveryBoy){
		  DeliveryBoy exdeliveryBoy = deliveryBoyDao.findDeliveryBoy(deliverBoyId);
		  if(exdeliveryBoy != null) {
			  ResponseStructure<DeliveryBoy> structure = new ResponseStructure<DeliveryBoy>();
			  structure.setMessage("DeliveryBoy Updated");
			  structure.setStatusCode(HttpStatus.OK.value());
			  structure.setData(deliveryBoyDao.updateDeliveryBoy(deliverBoyId, deliveryBoy));
			  
			  return new ResponseEntity<ResponseStructure<DeliveryBoy>>(structure,HttpStatus.OK);
		  }
		  throw new DeliveryBoyNotFound("DeliveryBoy does not exist");
	}
	
	public ResponseEntity<ResponseStructure<DeliveryBoy>> deleteDeliveryBoy(long deliveryBoyId){
		  DeliveryBoy deliveryBoy = deliveryBoyDao.findDeliveryBoy(deliveryBoyId);
		  if(deliveryBoy != null) {
			  ResponseStructure<DeliveryBoy> structure = new ResponseStructure<DeliveryBoy>();
			  structure.setMessage("DeliveryBoy deleted");
			  structure.setStatusCode(HttpStatus.OK.value());
			  structure.setData(deliveryBoyDao.deleteDeliveryBoy(deliveryBoyId));
			  
			  return new ResponseEntity<ResponseStructure<DeliveryBoy>>(structure,HttpStatus.OK);
		  }
		  throw new DeliveryBoyNotFound("DeliveryBoy does not exist");
	}

}
