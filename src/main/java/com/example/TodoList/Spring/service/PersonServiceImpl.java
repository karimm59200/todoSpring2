package com.example.TodoList.Spring.service;

import com.example.TodoList.Spring.entity.Person;
import com.example.TodoList.Spring.models.Auth;
import com.example.TodoList.Spring.utils.ServiceHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private ServiceHibernate serviceHibernate;
    private Session session;

    public PersonServiceImpl(ServiceHibernate serviceHibernate){
        this.serviceHibernate = new ServiceHibernate();
        session = this.serviceHibernate.getSession();
    }
    @Override
    public Person login(Auth auth) {
        session.beginTransaction();
        String request = "from Person where email = :email and password = :password";
        Person person = (Person) session.createQuery(request)
                .setParameter("email", auth.getEmail())
                .setParameter("password", auth.getPassword())
                .uniqueResult();
            return person;
    }
}
