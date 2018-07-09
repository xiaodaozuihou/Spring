package com.spring.service;

import com.spring.model.Book;
import com.spring.model.Category;

import java.util.List;

public interface BookService {
    List<Category> getAllCategories();
    Category getCategory(int id);
    List<Book> getAllBooks();
    Book save(Book book);
    Book update(Book book);
    Book get(long id);
    long getNextId();
}
