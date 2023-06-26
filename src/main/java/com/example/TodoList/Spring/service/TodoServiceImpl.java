package com.example.TodoList.Spring.service;

import com.example.TodoList.Spring.entity.Todo;
import com.example.TodoList.Spring.utils.HibernateUtils;
import com.example.TodoList.Spring.utils.ServiceHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private ServiceHibernate serviceHibernate;

    private Session session;

    public TodoServiceImpl(ServiceHibernate serviceHibernate){
        this.serviceHibernate = new ServiceHibernate();
        session = this.serviceHibernate.getSession();
    }
    @Override
    public boolean create(Todo todo) {
        session.beginTransaction();
        session.save(todo);
        session.getTransaction().commit();
//        session.close();
        return true;
    }

    @Override
    public boolean update(Todo todo) {
        session.beginTransaction();
        session.update(todo);
        session.getTransaction().commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(Todo todo) {
        session.beginTransaction();
        session.delete(todo);
        session.getTransaction().commit();
        //session.close();
        return true;
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Todo todo = session.get(Todo.class, id);
        session.delete(todo);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public Todo getId(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Todo todo = session.get(Todo.class, id);
        session.getTransaction().commit();
        session.close();
        return todo;

    }

    @Override
    public List<Todo> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<Todo> todoList = session.createQuery("from Todo").list();
        session.getTransaction().commit();
        session.close();
        return todoList;

    }
}
