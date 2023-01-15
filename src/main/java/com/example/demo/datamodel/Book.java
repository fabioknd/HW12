package com.example.demo.datamodel;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String author;
    private Integer dateYear;
    private Double price;

    public Book() {

    }

    public Book(String name, String author, Integer dateYear, Double price) {
        this.name = name;
        this.author = author;
        this.dateYear = dateYear;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getDateYear() {
        return dateYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDateYear(Integer dateYear) {
        this.dateYear = dateYear;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
