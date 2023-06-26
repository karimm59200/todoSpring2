package com.example.TodoList.Spring.service;

import com.example.TodoList.Spring.entity.Person;
import com.example.TodoList.Spring.models.Auth;

public interface PersonService {

    Person login(Auth auth);
}
