package com.hotel.v2soru.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.AdminDao;
import com.hotel.v2soru.dao.DeliveryBoyDao;
import com.hotel.v2soru.dao.FoodOrderDao;
import com.hotel.v2soru.dao.UserDao;
import com.hotel.v2soru.dto.AdminDto;
import com.hotel.v2soru.dto.DeliveryBoyDto;
import com.hotel.v2soru.entity.Admin;
import com.hotel.v2soru.entity.DeliveryBoy;
import com.hotel.v2soru.entity.FoodOrder;
import com.hotel.v2soru.entity.OrderStatus;
import com.hotel.v2soru.entity.User;
import com.hotel.v2soru.exception.AdminListNotFoundException;
import com.hotel.v2soru.exception.AdminNotFoundException;
import com.hotel.v2soru.exception.DeliveryBoyNotFound;
import com.hotel.v2soru.exception.UserNotFoundException;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeliveryBoyDao deliveryBoyDao;
	
	@Autowired
	private FoodOrderDao foodOrderDao;
	
	
	public ResponseEntity<ResponseStructure<Admin>> findAdmin(long adminId) {
		Admin admin = adminDao.findAdmin(adminId);
		if (admin != null) {
			
			ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(admin);

			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.FOUND);
		}
		throw new AdminNotFoundException("Admin does not exist");
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAlllAdmin() {

		List<Admin> allAdmin = adminDao.findAllAdmin();
		if (!allAdmin.isEmpty()) {
			
			ResponseStructure<List<Admin>> structure = new ResponseStructure<List<Admin>>();
			structure.setMessage("Admin List Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(allAdmin);
			return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.FOUND);
		}
		throw new AdminListNotFoundException("AdminList does not exist");
	}

	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) {

		AdminDto adminDto = new AdminDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(adminDao.saveAdmin(admin), adminDto);

		ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
		structure.setMessage("Admin Created SuccessFully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(adminDto);

		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(long adminId, Admin admin) {
		Admin exadmin = adminDao.findAdmin(adminId);
		if (exadmin != null) {
			AdminDto adminDto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(adminDao.updateAdmin(adminId, admin), adminDto);

			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("Admin update successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(adminDto);

			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
		}

		throw new AdminNotFoundException("Admin does not exist");
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(long adminId) {
		Admin admin = adminDao.findAdmin(adminId);
		if (admin != null) {
			AdminDto adminDto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(adminDao.deleteAdmin(adminId), adminDto);

			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("Admin deleted successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(adminDto);

			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
		}

		throw new AdminNotFoundException("Admin does not exist");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> assignUser(long adminId,long userId){
		
		Admin admin = adminDao.findAdmin(adminId);
		User user = userDao.findUser(userId);
		if(admin !=null && user != null) {
			admin.getManagedUsers().add(user);
			Admin updateAdmin = adminDao.updateAdmin(adminId, admin);
			
			ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
			structure.setMessage("user assigned");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(updateAdmin);
			
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		
		throw new UserNotFoundException("user does not exixt");
	}
	
public ResponseEntity<ResponseStructure<Admin>> assignDeliveryBoy(long adminId,long deliveryBoyId){
		
		Admin admin = adminDao.findAdmin(adminId);
		DeliveryBoy deliveryBoy = deliveryBoyDao.findDeliveryBoy(deliveryBoyId);
		if(admin !=null && deliveryBoy != null) {
			admin.getManagedDeliveryBoys().add(deliveryBoy);
			Admin updateAdmin = adminDao.updateAdmin(adminId, admin);
			
			ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
			structure.setMessage("deliveryboy assigned");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(updateAdmin);
			
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		
		throw new DeliveryBoyNotFound("DeliveyBoy Not Found");
	}

   public ResponseEntity<ResponseStructure<List<FoodOrder>>> orderStatus(OrderStatus orderStatus){
	   
	   
	   System.out.println(orderStatus);
	    List<FoodOrder> allFoodOrder = foodOrderDao.findAllFoodOrder();
	    
	    List<OrderStatus> allOrderStatus = allFoodOrder.stream()
	            .map(FoodOrder::getOrderStatus)
	            .collect(Collectors.toList());
	    
	    List<FoodOrder> orders = null;
	    System.out.println(allOrderStatus);  
	   
	    switch (orderStatus) {
        case ORDER_PROCESS:
            orders = allFoodOrder.stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.ORDER_PROCESS)
                    .collect(Collectors.toList());
            System.out.println(orders);
            break; 
        
        case ORDER_PENDING:
            orders = allFoodOrder.stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.ORDER_PENDING)
                    .collect(Collectors.toList());
            System.out.println(orders);
            break;
            
        case ORDER_CANCEL:
            orders = allFoodOrder.stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.ORDER_CANCEL)
                    .collect(Collectors.toList());
            System.out.println(orders);
            break;
    }
	   
	   ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<List<FoodOrder>>();
	   structure.setData(orders);
	   structure.setMessage("All FoodOrder Status");
	   structure.setStatusCode(HttpStatus.OK.value());
	   
	return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure,HttpStatus.OK);
	
}
   public ResponseEntity<ResponseStructure<DeliveryBoyDto>> assignUserToDeliveryBoy(long userId, long deliveryBoyId) {
	    User user = userDao.findUser(userId);
	    DeliveryBoy deliveryBoy = deliveryBoyDao.findDeliveryBoy(deliveryBoyId);

	    if (user != null && deliveryBoy != null) {
	        FoodOrder foodOrder = foodOrderDao.findFoodOrder(user.getUserId());
	        foodOrder.setItems(null);
	        foodOrderDao.saveFoodOrder(foodOrder);

	        user.setDeliverBoy(deliveryBoy);
	        userDao.updateUser(userId, user);

	        deliveryBoy.setUser(user);
	
	        ModelMapper mapper = new ModelMapper();
	        mapper.getConfiguration().setAmbiguityIgnored(true);
	        DeliveryBoyDto deliveryBoyDto = mapper.map(deliveryBoyDao.updateDeliveryBoy(deliveryBoyId, deliveryBoy), DeliveryBoyDto.class);

	        ResponseStructure<DeliveryBoyDto> structure = new ResponseStructure<>();
	        structure.setMessage("Delivery boy assigned");
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setData(deliveryBoyDto);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    }

	    throw new UserNotFoundException("User does not exist");
	}

   public ResponseEntity<ResponseStructure<Admin>> loginAdmin(Admin admin) {

	    Admin byAdminEmail = adminDao.findByadminEmail(admin.getAdminEmail());

	    if (admin.getAdminEmail() != null && byAdminEmail != null) {
	        
	        if (admin.getAdminEmail().equals(byAdminEmail.getAdminEmail()) &&
	            admin.getAdminPassword().equals(byAdminEmail.getAdminPassword())) {

	            ResponseStructure<Admin> structure = new ResponseStructure<>();
	            structure.setData(byAdminEmail);
	            structure.setMessage("Login successfully");
	            structure.setStatusCode(HttpStatus.OK.value());

	            return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
	        }
	    }
	   
	    ResponseStructure<Admin> errorStructure = new ResponseStructure<>();
	    errorStructure.setMessage("Login failed");
	    errorStructure.setStatusCode(HttpStatus.UNAUTHORIZED.value());

	    return new ResponseEntity<ResponseStructure<Admin>>(errorStructure, HttpStatus.UNAUTHORIZED);
	}

}







