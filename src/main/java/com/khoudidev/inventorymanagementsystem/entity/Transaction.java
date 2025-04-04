package com.khoudidev.inventorymanagementsystem.entity;


import com.khoudidev.inventorymanagementsystem.enums.TransactionStatus;
import com.khoudidev.inventorymanagementsystem.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer totalProducts;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String description;


    private LocalDateTime updateAt;//change



    private final LocalDateTime createdAt = LocalDateTime.now();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    @Override
    public String toString() {
        return "TransactionDTO [id=" + id + ", totalProducts=" + totalProducts + ", totalPrice=" + totalPrice
                + ", transactionType=" + transactionType + ", status=" + status + ", description=" + description + ", updateAt="
                + updateAt + ", createdAt=" + createdAt +  "]";
    }




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
