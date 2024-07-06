package com.crud.mongodb.services;

import com.crud.mongodb.exceptions.ServicesRuntimeException;
import com.crud.mongodb.models.Address;
import com.crud.mongodb.models.Customer;
import com.crud.mongodb.repositorys.CustomerRepositoryAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CustomerServiceAggregation {

	@Autowired
	private CustomerRepositoryAggregation customerRepositoryAggregation;

//	public LinkedHashMap getAllCustomers() {
//		LinkedHashMap<String, Object> returnGetAllCustomer = new LinkedHashMap<>();
//		List<Customer> getAllCustomers;
//		try{
//			getAllCustomers = customerRepositoryAggregation.getAllCustomersCustomer();
//			if(getAllCustomers.isEmpty()){
//				returnGetAllCustomer.put("Error", new ServicesRuntimeException("Error find by get all customers!"));
//			}
//			returnGetAllCustomer.put("Customers", getAllCustomers);
//		} catch(Exception e){
//			returnGetAllCustomer.put("Error", new ServicesRuntimeException("Error find by get all customers! Details Error: " + e.getMessage()).getMessage());
//		}
//		return returnGetAllCustomer;
//	}

	public LinkedHashMap getAllCustomersAddress() {
		LinkedHashMap<String, Object> returnGetAllCustomer = new LinkedHashMap<>();
		List<Address> getAllCustomers;
		try{
			getAllCustomers = customerRepositoryAggregation.getAllCustomersAddress();
			if(getAllCustomers.isEmpty()){
				returnGetAllCustomer.put("Error", new ServicesRuntimeException("Error find by get all customers!"));
			}
			returnGetAllCustomer.put("Address", getAllCustomers);
		} catch(Exception e){
			returnGetAllCustomer.put("Error", new ServicesRuntimeException("Error find by get all customers! Details Error: " + e.getMessage()).getMessage());
		}
		return returnGetAllCustomer;
	}

}
