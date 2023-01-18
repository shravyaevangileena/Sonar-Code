package com.creditcard.controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.*;

import com.creditcard.entities.Customer;
import com.creditcard.exception.InvalidCardException;
import com.creditcard.service.ICustomerService;
import java.util.logging.Level;


import java.util.List;

@RestController
public class CustomerController {

    @Autowired
   private ICustomerService customerService;
   private Logger logger =Logger.getLogger(CustomerController.class.getName()); 
   String details="Customer Not Found!";
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable("id") String id) {
    	if(customerService.getCustomer(id)==null) {
    		logger.log(Level.INFO, details );
    		throw new InvalidCardException();
    	}
        return customerService.getCustomer(id);
    }

    @GetMapping("/customers/location/{pincode}")
    public List<Customer> getCustomersByLocation(@PathVariable("pincode") int pincode) {
        return customerService.getCustomersByLocation(pincode);
    }

    @PostMapping(value="/customers/save",consumes = "application/json")
    public Customer addCustomer(@RequestBody Customer customer) {
    	
        return customerService.addCustomer(customer);
    }

    @PutMapping(value ="/customers/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") String id) {
    	if(customerService.getCustomer(id)==null) {
    		logger.log(Level.INFO, details );
    		throw new InvalidCardException();
    	}
        return customerService.updateCustomer(id, customer);
    }
    
    
    @DeleteMapping(value="/customers/{id}")
  public String removeCustomer(@PathVariable("id") String id) {
    if(customerService.getCustomer(id)==null) {
		logger.log(Level.INFO, details);
		throw new InvalidCardException();
	}
    customerService.removeCustomer(id);
	return "Deleted successfully";

    }

}
