package com.Customer;

import com.FileHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService implements ICustomerService {

 FileHelper<CustomerModel> fileHelper=new FileHelper<>(
         "src/com/data/customers.json",
         CustomerModel[].class
 );

    public ArrayList<CustomerModel> loadAll() {

        return fileHelper.readAll();
    }
    // Get a customer by ID
    public CustomerModel findById(String id) {
        return loadAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().orElse(null);
    }
    public CustomerModel findByUsername(String username) {
        return loadAll().stream()
                .filter(c -> c.getUserName().equals(username))
                .findFirst().orElse(null);
    }
    public CustomerModel findByUsernamePass(String username,String password) {
        return loadAll().stream()
                .filter(c -> c.getUserName().equals(username))
                .filter(c -> c.getPasswordHash().equals(password))
                .findFirst().orElse(null);
    }
    public void save(CustomerModel customer) {
         ArrayList<CustomerModel> customers = loadAll();

        //customers.removeIf(c -> c.getId().equals(customer.getId()));
        customers.add(customer);
        fileHelper.saveAll(customers);
    }
}
