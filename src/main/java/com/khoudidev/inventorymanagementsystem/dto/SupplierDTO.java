package com.khoudidev.inventorymanagementsystem.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierDTO {



    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String address;

//
//    3. SUPPLIER 🏢
//    Cette entité représente les fournisseurs qui approvisionnent le stock.
//
//    Attribut	Type	Description
//    id	Long	Identifiant unique du fournisseur.
//    name	String	Nom du fournisseur.
//    contactInfo	String	Informations de contact du fournisseur.
//    address	String	Adresse du fournisseur.
//✅ Relation :
//    Un fournisseur peut être impliqué dans plusieurs transactions → Relation One-to-Many avec TRANSACTION.
//        -------------------------------------
//                3. SUPPLIER 🏢 (المورد)
//    تمثل هذه الكيان الموردين الذين يقومون بتوريد المنتجات للنظام.
//
//    الخاصية	النوع	الوصف
//    id	Long	المعرف الفريد للمورد.
//    name	String	اسم المورد.
//    contactInfo	String	معلومات الاتصال الخاصة بالمورد.
//    address	String	عنوان المورد.
//            ✅ العلاقة:
//    المورد يمكن أن يكون لديه عدة معاملات (Transactions) → علاقة One-to-Many مع TRANSACTION

}
