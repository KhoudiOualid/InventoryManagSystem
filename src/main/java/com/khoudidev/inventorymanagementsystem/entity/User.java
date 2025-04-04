package com.khoudidev.inventorymanagementsystem.entity;


import com.khoudidev.inventorymanagementsystem.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name  is required")
    private String name;

    @NotBlank(message = "Email  is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password  is required")
    private String password;

    @NotBlank(message = "Phone Number is required")
    @Column(name ="phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @Column(name ="created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();


    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
                + ", phoneNumber=" + phoneNumber + ", role=" + role + ", transactions=" + transactions
                + ", createdAt=" + createdAt + "]";
    }


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
