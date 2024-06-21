package com.system.recipeblog.services;

import com.system.recipeblog.exception.ex.CategoryNotFoundException;
import com.system.recipeblog.models.Category;
import com.system.recipeblog.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.system.recipeblog.util.Constants.CATEGORY_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(CATEGORY_NOT_FOUND, id)));
    }

    @Override
    public Category updateCategory(Category category) {
        Category category1 = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new CategoryNotFoundException(String.format(CATEGORY_NOT_FOUND, category.getId())));

            category1.setDescription(category.getDescription());
            category1.setTitle(category.getTitle());
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(String.format(CATEGORY_NOT_FOUND, id));
        }
        categoryRepository.deleteById(id);
    }
}
