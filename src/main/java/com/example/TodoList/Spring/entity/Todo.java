package com.example.TodoList.Spring.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private String description;
//private String date ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    @Column(name="created_date")
    private Date createdDate;
    private Boolean isDone = false;

}
