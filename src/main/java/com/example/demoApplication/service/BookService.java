package com.example.demoApplication.service;

import com.example.demoApplication.model.Book;
import com.example.demoApplication.model.Category;
import com.example.demoApplication.repo.BookRepo;
import com.example.demoApplication.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private final BookRepo bookRepo;
    private final CategoryRepo catRepo;

    @Autowired
    public BookService(BookRepo bookRepo, CategoryRepo catRepo)
    {
        this.bookRepo = bookRepo;
        this.catRepo = catRepo;
    }

    public ResponseEntity<List<Book>> getAllBooks()
    {
        try{
            List<Book> bookList = bookRepo.findAll();
            if(bookList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> bookData = bookRepo.findById(id);
        if(bookData.isPresent())
        {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        Book bookObj;
        //  category Id is given in the requestbody
        Optional<Category> cat = catRepo.findByCategoryByName(book.getCategory().getName());

        // if category exits then add existing category in Book
        if(cat.isPresent()) {
            book.setCategory(cat.get());
        }else {
            // if NOT then add new category in Book
            Category addNewCat = new Category();
            addNewCat.setName(book.getCategory().getName());
            addNewCat.setDescription(book.getCategory().getDescription());
            catRepo.save(addNewCat);
            book.setCategory(addNewCat);
        }
        bookObj = bookRepo.save(book);
        return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> addBookAll(@RequestBody List<Book> bookList)
    {
        List<Book> bookObj = new ArrayList<>();
        for(Book b : bookList) {
            Book book = addBook(b).getBody();
            bookObj.add(book);
        }

        return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }

    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newbookData){

        Optional<Book> oldbookData = bookRepo.findById(id);
        // if book not found then return Book NOT FOUND
        if(oldbookData.isPresent())
        {
            //if Category not found then return NOT FOUND
            Optional<Category> category = catRepo.findById(oldbookData.get().getCategory().getId());
            if (category.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // if category ID exists then update Book Data
            Book updateBookData = oldbookData.get();
            updateBookData.setTitle(newbookData.getTitle());
            updateBookData.setAuthor(newbookData.getAuthor());
            updateBookData.setPublisher(newbookData.getPublisher());
            updateBookData.setPublishingYear(newbookData.getPublishingYear());
            updateBookData.setCategory(newbookData.getCategory());

            Book bookObj =bookRepo.save(updateBookData);
            return new ResponseEntity<Book>(bookObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** When book is being deleted then category associated with book should not get deleted */
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id)
    {
        Optional<Book> bookToBeDeleted = bookRepo.findById(id);
        if(bookToBeDeleted.isPresent())
        {
            Category cat = bookToBeDeleted.get().getCategory();
            if(cat != null){
                cat.removeBook(bookToBeDeleted.get());
                catRepo.save(cat);
            }
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
