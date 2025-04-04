package com.khoudidev.inventorymanagementsystem.service.impl;

import com.khoudidev.inventorymanagementsystem.dto.CategoryDTO;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.dto.UserDTO;
import com.khoudidev.inventorymanagementsystem.entity.Category;
import com.khoudidev.inventorymanagementsystem.entity.User;
import com.khoudidev.inventorymanagementsystem.exceptions.NotFoundException;
import com.khoudidev.inventorymanagementsystem.repository.CategoryRepository;
import com.khoudidev.inventorymanagementsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;



    @Override
    public Response createCategory(CategoryDTO categoryDTO) {
        Category categoryToSave = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryToSave);

        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }

    @Override
    public Response getAllCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

        List<CategoryDTO> categoryDTOS = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {}.getType());



        return Response.builder()
                .status(200)
                .message("success")
                .categories(categoryDTOS)
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return Response.builder()
                 .status(200)
                 .message("success")
                 .category(categoryDTO)
                 .build();
    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);

        return Response.builder()
                .status(200)
                .message("Category Successfully updated")
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        categoryRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Category Successfully Deleted")
                .build();
    }
}
