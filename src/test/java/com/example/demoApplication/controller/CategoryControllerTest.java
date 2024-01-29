package com.example.demoApplication.controller;

import com.example.demoApplication.model.Category;
import com.example.demoApplication.service.BookService;
import com.example.demoApplication.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demoApplication.model.CategoryDTO;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private CategoryService catService;

    @InjectMocks
    private CategoryController categoryController;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getAllCategories() {
        //given
        List<Category> mockCategories = Arrays.asList(new Category(), new Category());
        when(catService.getAllCategories()).thenReturn(new ResponseEntity<>(mockCategories, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        //Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategories, response.getBody());
    }

    @Test
    public void getCategoryById() {
        //given
        Long categoryId = 1L;
        Category mockCategory = new Category();

        when(catService.getCategoryById(categoryId)).thenReturn(new ResponseEntity<>(mockCategory, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        //Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategory, response.getBody());

        }


    @Test
    void getBookCountofCategoryById() {
        //given
        Long categoryId = 1L;
        CategoryDTO mockCategoryDTO = new CategoryDTO(); // Replace with your actual DTO class
        when(catService.getBookCountofCategoryById(categoryId)).thenReturn(new ResponseEntity<>(mockCategoryDTO, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<CategoryDTO> response = categoryController.getBookCountofCategoryById(categoryId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategoryDTO, response.getBody());

    }

    @Test
    void addAllCategory() {
        //given
        List<Category> categoriesToAdd = Arrays.asList(new Category(), new Category());
        when(catService.addAllCategory(categoriesToAdd)).thenReturn(new ResponseEntity<>(categoriesToAdd, HttpStatus.CREATED));

        // Calling the controller method
        ResponseEntity<List<Category>> response = categoryController.addAllCategory(categoriesToAdd);

        // Assertions
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoriesToAdd, response.getBody());
    }

    @Test
    void addCategory() {
        //given
        Category categoryToAdd = new Category();
        when(catService.addCategory(categoryToAdd)).thenReturn(new ResponseEntity<>(categoryToAdd, HttpStatus.CREATED));

        // Calling the controller method
        ResponseEntity<Category> response = categoryController.addCategory(categoryToAdd);

        // Assertions
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryToAdd, response.getBody());

    }

    @Test
    void updateCategoryById() {
        //given
        Long categoryId = 1L;
        Category updatedCategoryData = new Category();
        when(catService.updateCategoryById(categoryId, updatedCategoryData))
                .thenReturn(new ResponseEntity<>(updatedCategoryData, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<Category> response = categoryController.updateCategoryById(categoryId, updatedCategoryData);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCategoryData, response.getBody());

    }

    @Test
    void deleteCategoryById() {
        //given
        Long categoryId = 1L;
        when(catService.deleteCategoryById(categoryId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        // Calling the controller method
        ResponseEntity<HttpStatus> response = categoryController.deleteCategoryById(categoryId);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(catService, times(1)).deleteCategoryById(categoryId); // verify reassignment call
    }
}