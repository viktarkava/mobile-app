package com.example.demo.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mockito.ArgumentMatchers.*;
import org.mockito.InjectMocks;

import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.repositories.UserRepository;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.AddressDTO;
import com.example.demo.shared.dto.UserDto;

class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;

	@Mock
	Utils utils;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	String userId = "ase87yhur";
	String encryptedPass = "pass123";
	UserEntity ue;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
		ue = new UserEntity();
		ue.setId(123);
		ue.setFirstName("Viktar");
		ue.setUserId(userId);
		ue.setEncryptedPassword(encryptedPass);
	}

	@Test
	final void testGetUser() {
		when(userRepository.findByEmail(org.mockito.ArgumentMatchers.anyString())).thenReturn(ue);
		UserDto userDto = userService.getUser("test@test.com");
		assertNotNull(userDto);
		assertEquals("Viktar", userDto.getFirstName());
	}
	
	@Test
	final void testGetUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(org.mockito.ArgumentMatchers.anyString())).thenReturn(null);
		assertThrows(UsernameNotFoundException.class, 
				()->{
					userService.getUser("test@test.com");
				});
	}
	
	@Test
	void testCreateUser() {
		when(userRepository.findByEmail(org.mockito.ArgumentMatchers.anyString())).thenReturn(null);
		when(utils.generateAssressId(org.mockito.ArgumentMatchers.anyInt())).thenReturn("asdfasdfasdf887");
		when(utils.generateUserId(org.mockito.ArgumentMatchers.anyInt())).thenReturn(userId);
		when(bCryptPasswordEncoder.encode(org.mockito.ArgumentMatchers.anyString())).thenReturn(encryptedPass);
		when(userRepository.save(org.mockito.ArgumentMatchers.any(UserEntity.class))).thenReturn(ue);

		AddressDTO addressDto = new AddressDTO();
		addressDto.setType("shipping");
		
		List<AddressDTO> addressList = new ArrayList<>();
		addressList.add(addressDto);
		
		UserDto userDto = new UserDto();
		userDto.setAddresses(addressList);
		
		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(ue.getFirstName(), storedUserDetails.getFirstName());
	}

}
