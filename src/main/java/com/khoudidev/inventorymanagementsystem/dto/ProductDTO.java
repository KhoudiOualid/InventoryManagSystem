package com.khoudidev.inventorymanagementsystem.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private Long id;

    private Long categoryId;

    private Long supplierId;

    private String name;


    private String sku;



    private BigDecimal price;



    private Integer stockQuantity;
    private String description;

    private String imageUrl;

    private LocalDateTime expiryDate ;
    private LocalDateTime updateAt;



    private  LocalDateTime createdAt ;







//
//    PRODUCT 📦
//    Cette entité représente les produits disponibles en stock.
//
//    Attribut	Type	Description
//    id	Long	Identifiant unique du produit.
//    name	String	Nom du produit.
//    sku	String	Code SKU (Stock Keeping Unit) du produit.
//    price	Double	Prix du produit.
//    stockQuantity	Integer	Quantité disponible en stock.
//    description	String	Description du produit.
//    expiryDate	Date	Date d'expiration (si applicable).
//    imageUrl	String	URL de l'image du produit.
//    createdAt	Date	Date de création du produit.
//✅ Relations :
//    Un produit appartient à une catégorie → Relation Many-to-One avec CATEGORY.
//    Un produit peut être impliqué dans plusieurs transactions → Relation One-to-Many avec TRANSACTION.
//-------------------------
//    4. PRODUCT 📦 (المنتج)
//    تمثل هذه الكيان المنتجات المتاحة في المخزون.
//
//    الخاصية	النوع	الوصف
//    id	Long	المعرف الفريد للمنتج.
//    name	String	اسم المنتج.
//    sku	String	رمز SKU (معرّف فريد للمنتج).
//    price	Double	سعر المنتج.
//    stockQuantity	Integer	كمية المنتج المتوفرة في المخزون.
//    description	String	وصف المنتج.
//    expiryDate	Date	تاريخ انتهاء صلاحية المنتج (إن وجد).
//    imageUrl	String	رابط صورة المنتج.
//    createdAt	Date	تاريخ إدخال المنتج إلى النظام.
//✅ العلاقات:
//    كل منتج ينتمي إلى فئة واحدة → علاقة Many-to-One مع CATEGORY.
//    كل منتج يمكن أن يكون مرتبطًا بعدة معاملات → علاقة One-to-Many مع TRANSACTION.



}
