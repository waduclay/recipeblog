package com.system.recipeblog.services;

import com.system.recipeblog.models.Category;
import com.system.recipeblog.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category category1 = categoryRepository.findById(id).get();
        if(id==null){
            System.out.println("Category does not exist");
        }else{
            category1.setDescription(category.getDescription());
            category1.setTitle(category.getTitle());
        }return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
