package com.khoudidev.inventorymanagementsystem.service;

import com.khoudidev.inventorymanagementsystem.dto.CategoryDTO;
import com.khoudidev.inventorymanagementsystem.dto.Response;

public interface CategoryService {
    Response createCategory(CategoryDTO categoryDTO);
    Response getAllCategories();
    Response getCategoryById(Long id );
    Response updateCategory(Long id,CategoryDTO categoryDTO);
    Response deleteCategory(Long id );
}
