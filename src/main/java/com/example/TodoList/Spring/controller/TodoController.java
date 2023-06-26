package com.example.TodoList.Spring.controller;

import com.example.TodoList.Spring.entity.Todo;
import com.example.TodoList.Spring.service.TodoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoServiceImpl _todoService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("todos", _todoService.getAll());
        return "list";
    }

//    @GetMapping("/form")
//    public String getForm(Model model){
//        model.addAttribute("todo", new Todo());
//        return "ajout";
//    }


   @GetMapping("/add")
    public String add( Model model){
        model.addAttribute("todo", new Todo());
        return "ajout";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Todo todo){

        if (todo.getId() == null) {
            if (_todoService.create(todo)) {
                return "redirect:liste";
            }
            return "error";
        } else {
            Todo existTodo = _todoService.getId(todo.getId());
            if (existTodo != null) {
                existTodo.setTitre(todo.getTitre());
                existTodo.setDescription(todo.getDescription());
                existTodo.setCreatedDate(todo.getCreatedDate());
//                existTodo.setDone(todo.isDone());
                if (_todoService.update(existTodo)) {
                    return "redirect:liste";
                }
            }
            return "error";
        }
    }

    @GetMapping ("/liste")
    public ModelAndView liste(){
        ModelAndView vm = new ModelAndView();
        vm.addObject("todos", _todoService.getAll());
        if (_todoService.getAll().isEmpty()) {
            vm.setViewName("error");
        } else {
            vm.setViewName("list");
            vm.addObject("todos", _todoService.getAll());
        }
        return vm;
    }
    @GetMapping("/edit/{id}")
    public String update(@PathVariable Integer id, Model model){
        Todo t = _todoService.getId(id);
        model.addAttribute("todo", t);

        return "ajout";
    }

    @PostMapping("/update/{id}")
    public Todo update(@PathVariable Integer id, @ModelAttribute Todo todo){
        Todo t = _todoService.getId(id);
        if (t != null) {
            t.setTitre(todo.getTitre());
            t.setDescription(todo.getDescription());
            t.setCreatedDate(todo.getCreatedDate());
            t.setIsDone(todo.getIsDone());
            _todoService.update(t);
        }
        return t;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Todo t = _todoService.getId(id);
        if (t != null && _todoService.delete(t) ) {

            return "redirect:/todo/list";
        }
        return "Aucune todo avec cet id";
    }
}
