package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.response.AddressResponse;
import com.example.demo.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping("/{id}")
	public AddressResponse getAddressById(@PathVariable long id) {
		Address address =  addressService.getAddressById(id);
		return new AddressResponse(address);
	}
}
