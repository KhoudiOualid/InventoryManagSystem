package com.khoudidev.inventorymanagementsystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name Number is required")
    private String name;



    @NotBlank(message = "Sku Number is required")
    @Column(unique = true)
    private String sku;


    @Positive(message = "ProductDTO price msut be a positive value")
    private BigDecimal price;


    @Min(value =0, message="Stock quantity cannot be lesser tha zero")
    private Integer stockQuantity;
    private String description;

    private String imageUrl;

    private LocalDateTime expiryDate ;
    private LocalDateTime updateAt;



    private final LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "ProductDTO [id=" + id + ", name=" + name
                + ", sku=" + sku + ", price=" + price + ", stockQuantity="
                + stockQuantity + ", description=" + description
                + ", imageUrl=" + imageUrl + ", expiryDate=" + expiryDate
                + ", updateAt=" + updateAt + ",createdAt=" + createdAt + "]";

    }




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
