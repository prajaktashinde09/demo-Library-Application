package com.example.demoApplication.controller;

import com.example.demoApplication.model.Category;
import com.example.demoApplication.model.CategoryDTO;
import com.example.demoApplication.service.BookService;
import com.example.demoApplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private BookService bookService;
    private CategoryService catService;

    @Autowired
    public CategoryController(CategoryService catService, BookService bookService){
        this.bookService = bookService;
        this.catService = catService;
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return catService.getAllCategories();
    }

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return catService.getCategoryById(id);
    }

    /**
     * Get count of the Books of specified Category Id
     * */
    @GetMapping("/getBookCountofCategoryById/{categoryId}")
    public ResponseEntity<CategoryDTO> getBookCountofCategoryById(@PathVariable Long categoryId){
        return catService.getBookCountofCategoryById(categoryId);
    }

    @PostMapping("/addAllCategory")
    public ResponseEntity<List<Category>> addAllCategory(@RequestBody List<Category> categoryList){
        return catService.addAllCategory(categoryList);
    }
    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return catService.addCategory(category);
    }

    @PostMapping("/updateCategoryById/{id}")
    public ResponseEntity<Category>  updateCategoryById(@PathVariable Long id,  @RequestBody Category category){
        return catService.updateCategoryById(id, category);
    }

    /**
     * When existing category is deleted then all the books assigned to that categaory will be assigned to default Category
     * @param id
     * @return
     */
    @DeleteMapping("/deleteCategoryById/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long id){
        return catService.deleteCategoryById(id);
    }
}
