package com.crud.mongodb.services;

import com.crud.mongodb.exceptions.ServicesRuntimeException;
import com.crud.mongodb.models.Customer;
import com.crud.mongodb.models.CustomerDTO;
import com.crud.mongodb.repositorys.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("/customerService/")
public class CustomerServiceData {

    @Autowired
    private CustomerRepository customerRepository;

    public LinkedHashMap getAllCustomers() {
        LinkedHashMap<String, Object> returnGetAllCustomer = new LinkedHashMap<>();
        try{
            List<Customer> getAll = customerRepository.findAll();
            returnGetAllCustomer.put("Customers", getAll);
        } catch(Exception e){
            returnGetAllCustomer.put("Error", new ServicesRuntimeException("Error find by get all customers! Details Error: " + e.getMessage()).getMessage());
        }
        return returnGetAllCustomer;
    }

    public LinkedHashMap saveCustomer(CustomerDTO customerDTO){
        LinkedHashMap<String, Object> returnSaveCustomer = new LinkedHashMap<>();
        Customer customer = customerDTO.toConverterCustomerDTO();
//        Customer existCustomer = null;
//        try{
//            existCustomer = customerRepository.findByCustomerUserName(customer.getCustomerUserName());
//        } catch(Exception e){
//            returnSaveCustomer.put("Error", new ServicesRuntimeException("Error find by get username of customer! Details Error: " + e.getMessage()).getMessage());
//        }
//        if (Objects.isNull(existCustomer)) {
            try{
                customerRepository.save(customer);
                returnSaveCustomer.put("Customer", customer);
            } catch(Exception e){
                returnSaveCustomer.put("Error", new ServicesRuntimeException("Error save customer: " + customer.getCustomerUserName() + "! Details Error: " + e.getMessage()).getMessage());
            }
//        } else {
//            returnSaveCustomer.put("Error", new ServicesRuntimeException("Error save customer: " + customer.getCustomerUserName() + ". Customer already exist!").getMessage());
//        }

        return returnSaveCustomer;
    }

    public LinkedHashMap removeCustomer(Integer customerId) {
        LinkedHashMap<String, Object> returnDeleteCustomer = new LinkedHashMap<>();
        Customer customer = null;
        try{
            customer = getCustomerById(customerId);
        } catch(Exception e){
            returnDeleteCustomer.put("Error", new ServicesRuntimeException("Error find by get id of customer! Details Error: " + e.getMessage()).getMessage());
        }
        try{
            if(!Objects.isNull(customer)){
                customerRepository.deleteById(customerId);
                returnDeleteCustomer.put("Status", "Customer successfully deleted!");
                returnDeleteCustomer.put("Customer", customer);
            } else {
                returnDeleteCustomer.put("Error", new ServicesRuntimeException("Error find by get id of customer!").getMessage());
            }
        } catch(Exception e){
            returnDeleteCustomer.put("Error", new ServicesRuntimeException("Error delete customer! Details Error: " + e.getMessage()).getMessage());
        }
        return returnDeleteCustomer;
    }

    public LinkedHashMap updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        LinkedHashMap<String, Object> returnUpdateCustomer = new LinkedHashMap<>();
        Customer customer = null;
        try{
            customer = getCustomerById(customerId);
        } catch(Exception e){
            returnUpdateCustomer.put("Error", new ServicesRuntimeException("Error find by get id of customer! Details Error: " + e.getMessage()).getMessage());
        }
        try{
            if (!Objects.isNull(customer)) {
                customer.setCustomerFullName(customerDTO.getCustomerFullName());
                customer.setCustomerEmail(customerDTO.getCustomerEmail());
                customerRepository.save(customer);
            }
            returnUpdateCustomer.put("Status", "Customer successfully update!");
            returnUpdateCustomer.put("Customer", customer);
        } catch(Exception e){
            returnUpdateCustomer.put("Error", new ServicesRuntimeException("Error update customer! Details Error: " + e.getMessage()).getMessage());
        }
        return returnUpdateCustomer;
    }

//    public LinkedHashMap getCustomerByUserName(String customerUserName){
//        LinkedHashMap<String, Object> returnGetCustomerUserName = new LinkedHashMap<>();
//        Customer customerTemp = null;
//        try{
//            customerTemp = customerRepository.findGetCostumerUserName(customerUserName);
//            returnGetCustomerUserName.put("Customer", customerTemp);
//        } catch(Exception e){
//            returnGetCustomerUserName.put("Error", new ServicesRuntimeException("Error find by get username of customer! Details Error: " + e.getMessage()).getMessage());
//        }
//        return returnGetCustomerUserName;
//    }

    public Customer getCustomerById(Integer id) {
        Optional<Customer> obj = customerRepository.findById(id);
        return obj.orElse(null);
    }

}
