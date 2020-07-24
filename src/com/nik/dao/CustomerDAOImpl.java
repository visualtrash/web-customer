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

    @Override
    public void deleteCustomer(int id) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //delete object with id=id
        Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");

        theQuery.setParameter("customerId", id);

        theQuery.executeUpdate();

    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        } else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;

    }
}
