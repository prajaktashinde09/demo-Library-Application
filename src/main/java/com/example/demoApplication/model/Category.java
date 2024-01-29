package com.example.demoApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long  id;
    private String name;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Book> books;
   public Book addBook(Book book){
     getBooks().add(book);
        return book;
   }
    public Book removeBook(Book book) {
        getBooks().remove(book);
        book.setCategory(null);
        return book;
    }
}
