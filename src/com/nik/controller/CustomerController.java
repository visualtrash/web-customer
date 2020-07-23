package com.nik.controller;

import com.nik.entity.Customer;
import com.nik.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {

        //get customers from dao
        List<Customer> theCustomers = customerService.getCustomers();

        //add customers to the model
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }
}
