package com.crud.mongodb.controllers;

import com.crud.mongodb.exceptions.ServicesRuntimeException;
import com.crud.mongodb.models.CustomerDTO;
import com.crud.mongodb.services.CustomerServiceAggregation;
import com.crud.mongodb.services.CustomerServiceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceData customerServiceData;

    @Autowired
    private CustomerServiceAggregation customerServiceAggregation;

    @GetMapping(value = "/getAllData")
    public ResponseEntity<?> getAllCustomersData() {
        HashMap hashMapGetAllCustomer = null;
        try{
            hashMapGetAllCustomer = customerServiceData.getAllCustomers();
            if (hashMapGetAllCustomer.isEmpty() || hashMapGetAllCustomer.containsKey("Error")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMapGetAllCustomer);
            } else {
                log.info("retornando info");
                return ResponseEntity.status(HttpStatus.OK).body(hashMapGetAllCustomer);
            }
        } catch(Exception e){
            hashMapGetAllCustomer.put("Error", new ServicesRuntimeException("Error get all customers! Details Error: " + e.getMessage()).getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMapGetAllCustomer);
        }
    }

    @GetMapping(value = "/getAllAggregation")
    public ResponseEntity<?> getAllCustomersAggregation() {
        HashMap hashMapGetAllCustomer = null;
        try{
            hashMapGetAllCustomer = customerServiceAggregation.getAllCustomersAddress();
            if (hashMapGetAllCustomer.isEmpty() || hashMapGetAllCustomer.containsKey("Error")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMapGetAllCustomer);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(hashMapGetAllCustomer);
            }
        } catch(Exception e){
            hashMapGetAllCustomer.put("Error", new ServicesRuntimeException("Error get all customers! Details Error: " + e.getMessage()).getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMapGetAllCustomer);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        HashMap hashMapSaveCustomer = null;
        try{
            hashMapSaveCustomer = customerServiceData.saveCustomer(customerDTO);
            if (hashMapSaveCustomer.isEmpty() || hashMapSaveCustomer.containsKey("Error")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMapSaveCustomer);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(hashMapSaveCustomer);
            }
        } catch(Exception e){
            hashMapSaveCustomer.put("Error", new ServicesRuntimeException("Error save customer! Details Error: " + e.getMessage()).getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMapSaveCustomer);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> remove(@RequestParam Integer customerId) {
        HashMap hashMapDeleteCustomer = null;
        try{
            hashMapDeleteCustomer = customerServiceData.removeCustomer(customerId);
            if (hashMapDeleteCustomer.isEmpty() || hashMapDeleteCustomer.containsKey("Error")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMapDeleteCustomer);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(hashMapDeleteCustomer);
            }
        } catch(Exception e){
            hashMapDeleteCustomer.put("Error", new ServicesRuntimeException("Error remove customer! Details Error: " + e.getMessage()).getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMapDeleteCustomer);
        }
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<?> updateCustomer(@RequestParam Integer customerId, @RequestBody CustomerDTO customerDTO) {
        HashMap hashMapUpdateCustomer = null;
        try{
            hashMapUpdateCustomer = customerServiceData.updateCustomer(customerId, customerDTO);
            if (hashMapUpdateCustomer.isEmpty() || hashMapUpdateCustomer.containsKey("Error")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMapUpdateCustomer);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(hashMapUpdateCustomer);
            }
        } catch(Exception e){
            hashMapUpdateCustomer.put("Error", new ServicesRuntimeException("Error update customer! Details Error: " + e.getMessage()).getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMapUpdateCustomer);
        }
    }

//    @GetMapping("/getByUserName")
//    public ResponseEntity<?> getCustomerByUserName(@RequestParam String customerUserName) {
//        HashMap hashMapGetUserNameCustomer = null;
//        try{
//            hashMapGetUserNameCustomer = customerService.getCustomerByUserName(customerUserName);
//            if (hashMapGetUserNameCustomer.isEmpty() || hashMapGetUserNameCustomer.containsKey("Error")) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMapGetUserNameCustomer);
//            } else {
//                return ResponseEntity.status(HttpStatus.OK).body(hashMapGetUserNameCustomer);
//            }
//        } catch(Exception e){
//            hashMapGetUserNameCustomer.put("Error", new ServicesRuntimeException("Error find by username of costumer! Details Error: " + e.getMessage()).getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMapGetUserNameCustomer);
//        }
//    }

}
