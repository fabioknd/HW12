package com.example.demo.repository;

import com.example.demo.datamodel.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
