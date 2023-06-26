package com.example.TodoList.Spring.service;

import com.example.TodoList.Spring.entity.Todo;

import java.util.List;

public interface TodoService {
    boolean create(Todo todo);
    boolean update(Todo todo);
    boolean delete(Todo todo);
    void delete(int id);
    Todo getId(int id);

    public List<Todo> getAll();

}
