package com.example.demoApplication.service;

import com.example.demoApplication.model.Book;
import com.example.demoApplication.model.Category;
import com.example.demoApplication.model.CategoryDTO;
import com.example.demoApplication.repo.BookRepo;
import com.example.demoApplication.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final BookRepo bookRepo;
    private final CategoryRepo catRepo;

    @Autowired
    public CategoryService(BookRepo bookRepo, CategoryRepo catRepo)
    {
        this.bookRepo = bookRepo;
        this.catRepo = catRepo;
    }

    public ResponseEntity<List<Category>> getAllCategories() {
        try{
            List<Category> catList = catRepo.findAll();
            if(catList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(catList, HttpStatus.OK);
            }catch (Exception ex) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    public ResponseEntity<Category> getCategoryById(Long id) {
        Optional<Category> category = catRepo.findById(id);
        if(category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Category> addCategory(Category category) {
        Category catObj;
        // find if category already exists
        Optional<Category> cat = catRepo.findByCategoryByName(category.getName());
        if(cat.isPresent()) {
            catObj = catRepo.save(cat.get());
        }
        else {
            // if NOT then add new category
            Category addNewCat = new Category();
            addNewCat.setName(category.getName());
            addNewCat.setDescription(category.getDescription());
            catObj = catRepo.save(addNewCat);
        }
        return new ResponseEntity<>(catObj, HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> addAllCategory(List<Category> categoryList) {

        List<Category> catList = new ArrayList<>();
        for(Category c : categoryList) {
            Category category = addCategory(c).getBody();
            catList.add(category);
        }

        List<Category> catObjList = catRepo.saveAll(catList);
        return new ResponseEntity<>(catObjList, HttpStatus.OK);
    }

    public ResponseEntity<Category> updateCategoryById(Long id, Category newCategory) {
        Optional<Category> oldCategory = catRepo.findById(id);
        if(oldCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //If category exists then update the fields of Category
        Category categorytobeUpdated = oldCategory.get();
        categorytobeUpdated.setName(newCategory.getName());
        categorytobeUpdated.setDescription(newCategory.getDescription());
        Category catObj = catRepo.save(categorytobeUpdated);
        return new ResponseEntity<>(catObj, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteCategoryById(Long id) {
        Optional<Category> catToBeDeleted = catRepo.findById(id);
        if(catToBeDeleted.isPresent())
        {
            Category defualtCategory = new Category();
            defualtCategory.setName("Defualt Category");
            defualtCategory.setDescription("Defualt Category");
            List<Book> bookList = bookRepo.findByCategoryId(id);
            for(Book b : bookList) {
                b.setCategory(defualtCategory);
            }
            bookRepo.saveAll(bookList);
            catRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<CategoryDTO> getBookCountofCategoryById(Long categoryId) {
        Optional<Category> category = catRepo.findById(categoryId);
        if(category.isPresent())
        {
            List<Book> bookList = bookRepo.findByCategoryId(categoryId);
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(category.get().getName());
            categoryDTO.setBookCount(bookList.size());

            return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
