package com.example.demo.Controller;


import com.example.demo.datamodel.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequestMapping("/bookStore")
@RestController
public class BookController {

   @Autowired
   BookService bookSer;

   @GetMapping
   public ResponseEntity<List<Book>> getAllBooks() {
       List<Book> books = new ArrayList<Book>();
       books = bookSer.findBooks();

       return new ResponseEntity<>(books, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Book> getBookById(@PathVariable int id){
       return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<Book> addBook(@RequestBody Book book) {
        bookSer.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
   }

    @PostMapping("/addMultiple")
    public ResponseEntity<Book> addMultipleBooks(@RequestBody List<Book> books) {
        for (Book book:books) {
            bookSer.addBook(book);
        }
        return new ResponseEntity<>(books.get(0), HttpStatus.CREATED);
    }

   @DeleteMapping("/{id}")
   public ResponseEntity<Book> removeBookById(@PathVariable int id) {
       Book book = bookSer.fetchBook(id).get();

       if(bookSer.deleteBook(id)) {
            return new ResponseEntity<>(book, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
       }
   }

    @GetMapping("/oldest")
    public ResponseEntity<Book> getOldestBook(){
       List<Book> books = bookSer.findBooks();
        Optional<Book> minBook = books.stream()
                .min(Comparator.comparingInt(Book::getDateYear));
        return new ResponseEntity<>(minBook.get(), HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<Book> getRecentBook(){
        List<Book> books = bookSer.findBooks();
        Optional<Book> maxBook = books.stream()
                .max(Comparator.comparingInt(Book::getDateYear));
        return new ResponseEntity<>(maxBook.get(), HttpStatus.OK);
    }
}
