package com.xenby.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "homework")
@Entity
public class Homework implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "due", nullable = false)
    private Date due;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public String toString() {
      return "Homework{id=" + id + 
        ", name=" + name + 
        ", due=" + due + 
        "}";
    }
}
