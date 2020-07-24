package com.nik.dao;

import com.nik.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class CustomerDAOImpl implements CustomerDAO {

    //inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        //get hibernate current session
        Session currentSession = sessionFactory.getCurrentSession();

        //create query
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName",
                        Customer.class);

        //execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        //return result
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //save/update customer
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int id) {
        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //retrieve/read from database using primary key
        Customer theCustomer = currentSession.get(Customer.class, id);

        return theCustomer;
    }
}
