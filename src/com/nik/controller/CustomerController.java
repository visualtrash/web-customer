package com.nik.controller;

import com.nik.entity.Customer;
import com.nik.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //create model
        Customer theCustomer = new Customer();

        model.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        //save customer
        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id,
                                    Model model) {

        //get customer by id
        Customer theCustomer = customerService.getCustomer(id);

        //set customer as model
        model.addAttribute("customer", theCustomer);

        //send over to form
        return "customer-form";
    }
}
