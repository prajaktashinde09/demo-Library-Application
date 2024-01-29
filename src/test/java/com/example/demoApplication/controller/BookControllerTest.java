package com.example.demoApplication.controller;

import com.example.demoApplication.model.Book;
import com.example.demoApplication.service.BookService;
import com.example.demoApplication.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void test_getAllBooks() {
        // given
        List<Book> mockBooks = Arrays.asList(new Book(), new Book());
        when(bookService.getAllBooks()).thenReturn(new ResponseEntity<>(mockBooks, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    public void test_getBookById() {
        // given
        Long bookId = 1L;
        Book mockBook = new Book();
        when(bookService.getBookById(bookId)).thenReturn(new ResponseEntity<>(mockBook, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<Book> response = bookController.getBookById(bookId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBook, response.getBody());
    }

    @Test
    public void test_addBook() {
        // given
        Book bookToAdd = new Book();
        when(bookService.addBook(bookToAdd)).thenReturn(new ResponseEntity<>(bookToAdd, HttpStatus.CREATED));

        // Calling the controller method
        ResponseEntity<Book> response = bookController.addBook(bookToAdd);

        // Assertions
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookToAdd, response.getBody());
    }

    @Test
    public void test_addBookAll() {
        //given
        List<Book> booksToAdd = Arrays.asList(new Book(), new Book());
        when(bookService.addBookAll(booksToAdd)).thenReturn(new ResponseEntity<>(booksToAdd, HttpStatus.CREATED));

        // Calling the controller method
        ResponseEntity<List<Book>> response = bookController.addBookAll(booksToAdd);

        // Assertions
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(booksToAdd, response.getBody());
    }

    @Test
    public void test_updateBookById() {
        // given
        Long bookId = 1L;
        Book updatedBookData = new Book();
        when(bookService.updateBookById(bookId, updatedBookData)).thenReturn(new ResponseEntity<>(updatedBookData, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<Book> response = bookController.updateBookById(bookId, updatedBookData);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBookData, response.getBody());
    }

    @Test
    public void test_deleteBookById() {
        // given
        Long bookId = 1L;
        when(bookService.deleteBookById(bookId)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<HttpStatus> response = bookController.deleteBookById(bookId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).deleteBookById(bookId); // Verifying that the service method was called
    }
}