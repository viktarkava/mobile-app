package com.example.demo.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.shared.dto.AddressDTO;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.ui.model.response.UserRest;

class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userService;
	
	UserDto userDto;
	
	final String USER_ID = "123";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		userDto = new UserDto();
		userDto.setFirstName("John");
		userDto.setUserId(USER_ID);
		//TODO: init the rest of the fields
		
	}

	@Test
	void testGetUser() {
		when(userService.getUserByUserId(org.mockito.ArgumentMatchers.anyString())).thenReturn(userDto);
		
		UserRest userRest = userController.getUser(USER_ID);
		
		assertNotNull(userRest);
		assertEquals(USER_ID, userRest.getUserId());
	}

	private List<AddressDTO> getAddressesDto(){
		AddressDTO addressDto = new AddressDTO();
		addressDto.setType("shipping");
		
		List<AddressDTO> addressList = new ArrayList<>();
		addressList.add(addressDto);
		
		return addressList;
	}
}
