package com.crud.mongodb.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "customer_composition")
public class Address {

	private Integer addressId;
	private String street;
	private String city;
	private String state;
	private Integer zipCode;

}
