package com.nik.controller;

import com.nik.dao.CustomerDAO;
import com.nik.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject customer dao
    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/list")
    public String listCustomers(Model model) {

        //get customers from dao
        List<Customer> theCustomers = customerDAO.getCustomers();

        //add customers to the model
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }
}
