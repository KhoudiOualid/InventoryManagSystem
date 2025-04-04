package com.khoudidev.inventorymanagementsystem.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.khoudidev.inventorymanagementsystem.entity.Product;
import com.khoudidev.inventorymanagementsystem.entity.Supplier;
import com.khoudidev.inventorymanagementsystem.entity.User;
import com.khoudidev.inventorymanagementsystem.enums.TransactionStatus;
import com.khoudidev.inventorymanagementsystem.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    private Long id;


    private Integer totalProducts;

    private BigDecimal totalPrice;


    private TransactionType transactionType;


    private TransactionStatus status;

    private String description;


    private LocalDateTime updateAt;



    private  LocalDateTime createdAt ;


    private UserDTO user;

    private ProductDTO product;

    private SupplierDTO supplier;






}


//
//5. TRANSACTION 💼
//Cette entité représente une transaction (achat ou entrée en stock).
//
//Attribut	Type	Description
//id	Long	Identifiant unique de la transaction.
//totalProducts	Integer	Nombre total de produits dans la transaction.
//totalPrice	Double	Prix total de la transaction.
//transactionType	String	Type de transaction (PURCHASE, RETURN).
//transactionStatus	String	Statut de la transaction (PENDING, COMPLETED).
//description	String	Description de la transaction.
//note	String	Notes supplémentaires.
//updatedAt	Date	Date de mise à jour de la transaction.
//createdAt	Date	Date de création de la transaction.
//        ✅ Relations :
//Une transaction est liée à un produit → Relation Many-to-One avec PRODUCT.
//Une transaction est liée à un utilisateur → Relation Many-to-One avec USER.
//Une transaction est liée à un fournisseur → Relation Many-to-One avec SUPPLIER
//
//
//-------------------------------------
//        5. TRANSACTION 💼 (المعاملة)
//تمثل هذه الكيان المعاملات في النظام (مثل المشتريات أو المرتجعات).
//
//الخاصية	النوع	الوصف
//id	Long	المعرف الفريد للمعاملة.
//totalProducts	Integer	إجمالي عدد المنتجات في المعاملة.
//totalPrice	Double	السعر الإجمالي للمعاملة.
//transactionType	String	نوع المعاملة (PURCHASE أو RETURN).
//transactionStatus	String	حالة المعاملة (PENDING، COMPLETED).
//description	String	وصف المعاملة.
//note	String	ملاحظات إضافية حول المعاملة.
//updatedAt	Date	تاريخ آخر تحديث للمعاملة.
//createdAt	Date	تاريخ إنشاء المعاملة.
//✅ العلاقات:
//كل معاملة ترتبط بمنتج معين → علاقة Many-to-One مع PRODUCT.
//كل معاملة ترتبط بمستخدم معين → علاقة Many-to-One مع USER.
//كل معاملة ترتبط بمورد معين → علاقة Many-to-One مع SUPPLIER.
