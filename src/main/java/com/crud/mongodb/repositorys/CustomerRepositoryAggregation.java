package com.crud.mongodb.repositorys;

import com.crud.mongodb.models.Address;
import com.crud.mongodb.models.Customer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryAggregation {

	private final MongoTemplate mongoTemplate;

	public CustomerRepositoryAggregation(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Address> getAllCustomersAddress() {
		ProjectionOperation projectOperation = Aggregation.project()
				.and("address.addressId").as("addressId")
				.and("address.street").as("street")
				.and("address.city").as("city")
				.and("address.state").as("state")
				.and("address.zipCode").as("zipCode");

		Aggregation aggregation = Aggregation.newAggregation(
				projectOperation
		);

		AggregationResults<Address> results = mongoTemplate.aggregate(aggregation, "customer_composition", Address.class);

		return results.getMappedResults();
	}
}
