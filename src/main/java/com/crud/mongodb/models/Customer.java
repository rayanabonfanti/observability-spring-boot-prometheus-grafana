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
public class Customer {

    private Integer customerId;
    private String customerFullName;
    private String customerEmail;
    private String customerUserName;
    private String customerPassword;
    private Address address;

}
