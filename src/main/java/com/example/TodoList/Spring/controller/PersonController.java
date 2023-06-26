package com.example.TodoList.Spring.controller;

import com.example.TodoList.Spring.entity.Person;
import com.example.TodoList.Spring.models.Auth;
import com.example.TodoList.Spring.service.PersonServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class PersonController {

    @Autowired
    private PersonServiceImpl _personService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/auth")
    public String login(@ModelAttribute("auth") Auth auth, HttpServletRequest request){
        Person person = _personService.login(auth);
        if(person!=null){
            request.getSession().setAttribute("person", person);
            request.getSession().setAttribute("isLogged", true);
        }
        else {
            request.getSession().setAttribute("isLogged", false);
        }
        return "redirect:/todo/list";
    }
}
