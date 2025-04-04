package com.khoudidev.inventorymanagementsystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(unique=true)
    private String name;


    @OneToMany(mappedBy = "category")
    private List<Product> products;


    @Override
    public String toString() {
        return "CategoryDTO [id=" + id + ", name=" + name + ", products=" + products + "]";

    }

//2. CATEGORY
//    Cette entité permet de regrouper les produits par catégorie (exemple : Électronique, Vêtements).
//
//    Attribut	Type	Description
//    id	Long	Identifiant unique de la catégorie.
//    name	String	Nom de la catégorie.
//            ✅ Relation :
//    Une catégorie peut contenir plusieurs produits → Relation One-to-Many avec PRODUCT.
//
//        -----------------------------------------2. CATEGORY 🏷️ (الفئة)
//    تمثل هذه الكيان فئات المنتجات (مثل الإلكترونيات، الملابس، إلخ).
//
//    الخاصية	النوع	الوصف
//    id	Long	المعرف الفريد للفئة.
//    name	String	اسم الفئة.
//            ✅ العلاقة:
//    كل فئة يمكن أن تحتوي على عدة منتجات → علاقة One-to-Many مع PRODUCT





}
