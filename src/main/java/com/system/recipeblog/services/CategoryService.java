package com.system.recipeblog.services;

import com.system.recipeblog.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategories();
    Category findById(Long id);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
