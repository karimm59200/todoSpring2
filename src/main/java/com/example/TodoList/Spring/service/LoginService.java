package com.example.TodoList.Spring.service;

import com.example.TodoList.Spring.entity.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private HttpSession _httpSession;



    public boolean login(Person person) {
        String email = "";
        String password = "";
        try {
            email = _httpSession.getAttribute("email").toString();
            password = _httpSession.getAttribute("password").toString();
            if (email.equals(person.getEmail()) && password.equals(person.getPassword())) {
                _httpSession.setAttribute("isLogged", "OK");
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isLogged() {
        try {
            String attrIsLogged = _httpSession.getAttribute("isLogged").toString();
            return attrIsLogged.equals("OK");

        } catch (Exception ex) {
            return false;
        }
    }

}