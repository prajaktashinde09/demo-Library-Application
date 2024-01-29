package com.example.demoApplication.controller;

import com.example.demoApplication.model.Book;
import com.example.demoApplication.model.Category;
import com.example.demoApplication.service.BookService;
import com.example.demoApplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService)
    {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/addBookAll")
    public ResponseEntity<List<Book>> addBookAll(@RequestBody List<Book> bookList) {
        return bookService.addBookAll(bookList);
    }

    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newbookData){
        return bookService.updateBookById(id,newbookData);
    }

    /**
     * When book is being deleted then category associated with book should not get deleted
     * */

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
    }

}
