package com.example.demo.response;

import com.example.demo.entity.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

	private Long id;

	private String street;

	private String city;

	private Long studentId;

	private String firstName;

	private String lastName;

	private String email;

	public AddressResponse(Address address) {
		this.id = address.getId();
		this.street = address.getStreet();
		this.city = address.getCity();
		
		//Get student info only if the corresponding address_id
		//exists in the student table.
		if(address.getStudent()!=null) {
			this.studentId = address.getStudent().getId();
			this.firstName = address.getStudent().getFirstName();
			this.lastName = address.getStudent().getLastName();
			this.email = address.getStudent().getEmail();
		}
		
	}
}
