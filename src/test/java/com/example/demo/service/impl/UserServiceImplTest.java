package com.example.demo.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentMatchers.*;
import org.mockito.InjectMocks;

import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.repositories.UserRepository;
import com.example.demo.shared.dto.UserDto;

class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetUser() {
		UserEntity ue = new UserEntity();
		ue.setId(123);
		ue.setFirstName("Viktar");
		ue.setUserId("ase87yhur");
		when(userRepository.findByEmail(org.mockito.ArgumentMatchers.anyString())).thenReturn(ue);
		
		UserDto userDto = userService.getUser("test@test.com");
		
		assertNotNull(userDto);
		assertEquals("Viktar", userDto.getFirstName());
	}

}
