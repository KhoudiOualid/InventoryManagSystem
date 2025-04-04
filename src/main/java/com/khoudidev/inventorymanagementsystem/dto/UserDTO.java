package com.khoudidev.inventorymanagementsystem.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.khoudidev.inventorymanagementsystem.entity.Transaction;
import com.khoudidev.inventorymanagementsystem.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {


    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private String phoneNumber;


    private UserRole role;


    private List<TransactionDTO> transactions;


    private  LocalDateTime createdAt;





//    5. TRANSACTION 💼
//    Cette entité représente une transaction (achat ou entrée en stock).
//
//    Attribut	Type	Description
//    id	Long	Identifiant unique de la transaction.
//    totalProducts	Integer	Nombre total de produits dans la transaction.
//    totalPrice	Double	Prix total de la transaction.
//    transactionType	String	Type de transaction (PURCHASE, RETURN).
//    transactionStatus	String	Statut de la transaction (PENDING, COMPLETED).
//    description	String	Description de la transaction.
//    note	String	Notes supplémentaires.
//    updatedAt	Date	Date de mise à jour de la transaction.
//    createdAt	Date	Date de création de la transaction.
//            ✅ Relations :
//    Une transaction est liée à un produit → Relation Many-to-One avec PRODUCT.
//    Une transaction est liée5. TRANSACTION 💼 (المعاملة)
//    تمثل هذه الكيان المعاملات في النظام (مثل المشتريات أو المرتجعات).
//---------------------------------------------
//    الخاصية	النوع	الوصف
//    id	Long	المعرف الفريد للمعاملة.
//    totalProducts	Integer	إجمالي عدد المنتجات في المعاملة.
//    totalPrice	Double	السعر الإجمالي للمعاملة.
//    transactionType	String	نوع المعاملة (PURCHASE أو RETURN).
//    transactionStatus	String	حالة المعاملة (PENDING، COMPLETED).
//    description	String	وصف المعاملة.
//    note	String	ملاحظات إضافية حول المعاملة.
//    updatedAt	Date	تاريخ آخر تحديث للمعاملة.
//    createdAt	Date	تاريخ إنشاء المعاملة.
//✅ العلاقات:
//    كل معاملة ترتبط بمنتج معين → علاقة Many-to-One مع PRODUCT.
//    كل معاملة ترتبط بمستخدم معين → علاقة Many-to-One مع USER.
//    كل معاملة ترتبط بمورد معين → علاقة Many-to-One مع SUPPLIER. à un utilisateur → Relation Many-to-One avec USER.
//    Une transaction est liée à un fournisseur → Relation Many-to-One avec SUPPLIER.
//



}
