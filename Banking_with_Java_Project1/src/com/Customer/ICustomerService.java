package com.Customer;

import java.util.ArrayList;

public interface ICustomerService {

    ArrayList<CustomerModel> loadAll();

    CustomerModel findById(String id);

    CustomerModel findByUsername(String username);

    CustomerModel findByUsernamePass(String username, String password);

    void save(CustomerModel customer);
}
