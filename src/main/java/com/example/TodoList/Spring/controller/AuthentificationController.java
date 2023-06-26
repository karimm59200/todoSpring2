package com.example.TodoList.Spring.controller;

import com.example.TodoList.Spring.entity.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthentificationController {

    @Autowired
    HttpSession _httpsession;

    @GetMapping("/conne")
    public ModelAndView getForm(Model model){
        ModelAndView vm = new ModelAndView("index");
        model.addAttribute("person", new Person() );
        return vm;
    }

    @GetMapping("/connex")
    public String getFormConnexion(){
        return "index";
    }

    @PostMapping("/auth")
    public String postForm(Person utilisateur){
        Person utilisateur1 = new Person();
        if(utilisateur.getEmail().equals(utilisateur1.getEmail()) && utilisateur.getPassword().equals(utilisateur1.getPassword())){
            _httpsession.setAttribute("login", "ok");
            _httpsession.setMaxInactiveInterval(60*60);

            return "pageOK";
        }

        return "index";
    }

}
