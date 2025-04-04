package com.khoudidev.inventorymanagementsystem.repository;

import com.khoudidev.inventorymanagementsystem.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("SELECT t FROM Transaction t " +

            "WHERE YEAR(t.createdAt) = :year AND MONTH(t.createdAt) = :month")
    List<Transaction> findAllByMonthAndYear (@Param("month") int month, @Param("year") int year);

//we are searching these field; TransactionDTO's description, note, status, ProductDTO's name, sku



    @Query("SELECT t FROM Transaction t " +

            "LEFT JOIN t.product p " +

           "WHERE (:searchText IS NULL OR " +
            "LOWER(t.description) LIKE LOWER (CONCAT('%', :searchText, '%')) OR " +

         "LOWER(t.status) LIKE LOWER (CONCAT('%', :searchText, '%')) OR "+

        "LOWER(p.name) LIKE LOWER (CONCAT('%', :searchText, '%')) OR " +

        "LOWER(p.sku) LIKE LOWER(CONCAT('%', :searchText, '%')))")

    Page<Transaction> searchTransactions(@Param("searchText") String searchText, Pageable pageable);
}







//
//Ce code définit une **interface `TransactionRepository`** dans un projet **Spring Boot** qui utilise **Spring Data JPA** pour accéder à la base de données. Il permet d'exécuter des requêtes complexes sur l'entité `TransactionDTO` en utilisant le mécanisme de requêtes personnalisées de **JPQL (Java Persistence Query Language)**.
//
//        ---
//
//        ## 📂 **Package et Imports**
//        ```java
//package com.khoudidev.inventorymanagementsystem.repository;
//```
//        👉 Le fichier est placé dans le package `com.khoudidev.inventorymanagementsystem.repository`, qui suit la structure standard de projet Spring Boot (`repository` pour la couche d'accès aux données).
//
//        ### ✅ **Imports**
//        ```java
//        import com.khoudidev.inventorymanagementsystem.entity.TransactionDTO;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//```
//
//        | Import | Description |
//        |--------|-------------|
//        | `TransactionDTO` | L'entité à laquelle ce repository est lié. |
//        | `Page` et `Pageable` | Fournit la pagination et le tri. |
//        | `JpaRepository` | Interface de base de Spring Data JPA pour les opérations CRUD. |
//        | `@Query` | Permet d'écrire des requêtes personnalisées JPQL. |
//        | `@Param` | Permet de passer des paramètres aux requêtes personnalisées. |
//        | `@Repository` | Indique que cette interface est un composant Spring géré pour la couche d'accès aux données. |
//
//        ---
//
//        ## 🚀 **1. Définition de l'interface `TransactionRepository`**
//        ```java
//@Repository
//public interface TransactionRepository extends JpaRepository<TransactionDTO, Long> {
//```
//        ### ✅ **Héritage de `JpaRepository`**
//    L'interface `TransactionRepository` hérite de `JpaRepository<TransactionDTO, Long>`, ce qui permet de bénéficier automatiquement des méthodes CRUD :
//
//            | Méthode héritée | Description |
//            |----------------|-------------|
//            | `save(T entity)` | Enregistre une nouvelle entité ou met à jour une entité existante. |
//            | `findById(Long id)` | Recherche une entité par son `id`. |
//            | `findAll()` | Retourne toutes les entités. |
//            | `deleteById(Long id)` | Supprime une entité par son `id`. |
//            | `count()` | Retourne le nombre total d'entités. |
//
//            ### 🔎 **Signature complète**
//            - `TransactionDTO` → L'entité manipulée par le repository.
//            - `Long` → Type de la clé primaire de l'entité `TransactionDTO`.
//
//            ---
//
//            ## 🎯 **2. Requête personnalisée `findAllByMonthAndYear`**
//            ```java
//    @Query("SELECT t FROM TransactionDTO t " +
//            "WHERE YEAR(t.createdAt) = :year AND MONTH(t.createdAt) = :month")
//    List<TransactionDTO> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);
//```
//
//        ### 🛠️ **Explication détaillée**
//            1. **@Query** → Permet d'écrire une requête personnalisée JPQL.
//            2. **JPQL** → Langage basé sur SQL mais adapté aux entités JPA.
//3. **`SELECT t FROM TransactionDTO t`** → Sélectionne toutes les transactions `t`.
//            4. **`WHERE`** → Filtre les résultats en fonction de deux conditions :
//            - `YEAR(t.createdAt) = :year` → Extrait l'année de `createdAt` et la compare au paramètre `year`.
//            - `MONTH(t.createdAt) = :month` → Extrait le mois de `createdAt` et le compare au paramètre `month`.
//            5. **@Param("month") / @Param("year")** → Lie les paramètres de la méthode à la requête JPQL.
//
//            ### ✅ **Exemple d'appel :**
//            ```java
//    List<TransactionDTO> transactions = transactionRepository.findAllByMonthAndYear(3, 2025);
//transactions.forEach(System.out::println);
//```
//        👉 Retourne une **liste (`List<TransactionDTO>`)** de transactions créées en **mars 2025**.
//
//            ---
//
//            ## 🎯 **3. Recherche avancée avec `searchTransactions`**
//            ```java
//    @Query("SELECT t FROM TransactionDTO t " +
//            "LEFT JOIN t.product p " +
//            "WHERE (:searchText IS NULL OR " +
//            "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(t.status) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(p.sku) LIKE LOWER(CONCAT('%', :searchText, '%')))")
//    Page<TransactionDTO> searchTransactions(@Param("searchText") String searchText, Pageable pageable);
//```
//
//        ### 🛠️ **Explication détaillée**
//            1. **@Query** → Déclare une requête personnalisée JPQL.
//2. **LEFT JOIN** → Effectue une jointure à gauche entre `TransactionDTO` (`t`) et `ProductDTO` (`p`).
//            - `LEFT JOIN` permet d'inclure les transactions même si le produit associé est `null`.
//            3. **WHERE** → Conditions de recherche :
//            - **`:searchText IS NULL`** → Si le paramètre est `null`, retourne tous les résultats.
//            - **`LOWER(...)`** → Convertit en minuscules pour une recherche insensible à la casse.
//            - **`LIKE CONCAT('%', :searchText, '%')`** → Recherche une correspondance partielle dans le texte.
//
//### 🔎 **Champs concernés dans la recherche :**
//            | Champ dans `TransactionDTO` | Champ dans `ProductDTO` |
//            |--------------------------|-----------------------|
//            | `description` | `name` |
//            | `status` | `sku` |
//
//            ### ✅ **Exemple d'appel :**
//            ```java
//    Pageable pageable = PageRequest.of(0, 10);
//    Page<TransactionDTO> result = transactionRepository.searchTransactions("test", pageable);
//
//result.forEach(System.out::println);
//```
//        👉 Effectue une recherche paginée en recherchant le mot-clé `"test"` dans :
//            - Description de la transaction
//- Statut de la transaction
//- Nom du produit associé
//- Code SKU du produit associé
//
//### 🧠 **Pourquoi utiliser `Page` ?**
//            ✔️ Permet de gérer la **pagination** des résultats.
//            ✔️ Fournit des méthodes pratiques comme :
//            - `getTotalPages()` → Nombre total de pages.
//            - `getTotalElements()` → Nombre total d'éléments.
//            - `getContent()` → Liste des résultats dans la page.
//
//            ---
//
//            ## 🚀 **4. Pourquoi utiliser `JpaRepository` ?**
//            ### ✅ **Avantages de `JpaRepository` :**
//            ✔️ Hérite de méthodes CRUD (`save`, `findById`, `deleteById`, etc.).
//            ✔️ Gestion simplifiée des transactions et de la connexion à la base de données.
//✔️ Prise en charge de la **pagination** et du **tri** nativement.
//✔️ Personnalisation des requêtes avec `@Query`.
//            ✔️ Simplification de la gestion des `null` grâce à `Optional<T>`.
//
//            ---
//
//            ## 🌟 **5. Bonnes pratiques**
//            ✅ Utiliser `Optional<T>` pour éviter les `NullPointerException`.
//            ✅ Privilégier `LEFT JOIN` lorsqu'une relation peut être `null`.
//            ✅ Toujours utiliser `Pageable` dans les recherches paginées pour améliorer les performances.
//            ✅ Éviter d'utiliser `SELECT *` → Spécifier les colonnes nécessaires pour optimiser la requête.
//            ✅ Éviter les requêtes complexes dans le repository → Si trop complexe, déplacer dans le `Service`.
//
//            ---
//
//            ## 🔥 **6. Exemple complet d'appel dans le Service**
//            ```java
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    public Page<TransactionDTO> searchTransactions(String searchText, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return transactionRepository.searchTransactions(searchText, pageable);
//    }
//```
//
//        ---
//
//        ## ✅ **➡️ Résumé**
//            ✔️ `JpaRepository` → Fournit les opérations CRUD automatiquement.
//✔️ `@Query` → Personnalisation des requêtes JPQL.
//            ✔️ `@Param` → Lien entre le paramètre de méthode et la requête.
//✔️ `Page` et `Pageable` → Gestion automatique de la pagination.
//✔️ `LEFT JOIN` → Jointure même si la relation est nulle. 😎
//
//    هذا الكود يعرّف **واجهة `TransactionRepository`** في مشروع **Spring Boot** باستخدام **Spring Data JPA** للوصول إلى قاعدة البيانات. يوفر هذا الكود طريقة لتنفيذ استعلامات مخصصة على كيان **TransactionDTO** باستخدام **JPQL (Java Persistence Query Language)**.
//
//            ---
//
//            ## 📂 **الحزمة (Package) والاستيرادات (Imports)**
//            ```java
//package com.khoudidev.inventorymanagementsystem.repository;
//```
//        👉 الملف موجود في الحزمة `com.khoudidev.inventorymanagementsystem.repository`، وهي البنية القياسية في مشاريع **Spring Boot** (مجلد `repository` مخصص للوصول إلى البيانات).
//
//            ### ✅ **الاستيرادات (Imports)**
//            ```java
//import com.khoudidev.inventorymanagementsystem.entity.TransactionDTO;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//```
//
//        | الاستيراد | الوصف |
//            |-----------|--------|
//            | `TransactionDTO` | الكيان (Entity) الذي سيتم التعامل معه في هذا المستودع. |
//            | `Page` و `Pageable` | يوفران دعمًا لتقسيم النتائج إلى صفحات وفرزها. |
//            | `JpaRepository` | واجهة أساسية في Spring Data JPA توفر عمليات CRUD مباشرة. |
//            | `@Query` | تُمكنك من كتابة استعلامات مخصصة باستخدام JPQL. |
//            | `@Param` | تربط المعاملات (Parameters) بالاستعلامات المخصصة. |
//            | `@Repository` | تشير إلى أن هذه الواجهة جزء من طبقة الوصول إلى البيانات (Data Access Layer). |
//
//            ---
//
//            ## 🚀 **1. تعريف واجهة `TransactionRepository`**
//            ```java
//    @Repository
//    public interface TransactionRepository extends JpaRepository<TransactionDTO, Long> {
//```
//        ### ✅ **وراثة `JpaRepository`**
//        واجهة `TransactionRepository` ترث من `JpaRepository<TransactionDTO, Long>`، مما يوفر دعمًا مباشرًا لتنفيذ العمليات التالية:
//
//                | العملية | الوصف |
//                |---------|--------|
//                | `save(T entity)` | حفظ كيان جديد أو تحديث كيان موجود. |
//                | `findById(Long id)` | البحث عن كيان باستخدام معرفه (ID). |
//                | `findAll()` | استرجاع جميع الكيانات. |
//                | `deleteById(Long id)` | حذف كيان باستخدام معرفه. |
//                | `count()` | إرجاع العدد الإجمالي للكيانات. |
//
//                ### 🔎 **شرح كامل:**
//                - `TransactionDTO` → يمثل الكيان (Entity) الذي سيتم التعامل معه.
//                - `Long` → يمثل نوع المفتاح الأساسي (ID) للكيان.
//
//                ---
//
//                ## 🎯 **2. الاستعلام المخصص `findAllByMonthAndYear`**
//                ```java
//        @Query("SELECT t FROM TransactionDTO t " +
//                "WHERE YEAR(t.createdAt) = :year AND MONTH(t.createdAt) = :month")
//        List<TransactionDTO> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);
//```
//
//        ### 🛠️ **شرح مفصل:**
//                1. **@Query** → تُستخدم لتعريف استعلام مخصص باستخدام JPQL.
//                2. **JPQL** → لغة مشابهة لـ SQL لكنها مخصصة للتعامل مع الكيانات (Entities).
//                3. **`SELECT t FROM TransactionDTO t`** → اختيار كل المعاملات (`TransactionDTO`).
//                4. **`WHERE`** → شروط البحث:
//                - **YEAR(t.createdAt) = :year** → استخراج السنة من تاريخ الإنشاء (`createdAt`).
//                - **MONTH(t.createdAt) = :month** → استخراج الشهر من تاريخ الإنشاء (`createdAt`).
//                5. **@Param("month") / @Param("year")** → ربط المعاملات بالاستعلام.
//
//### ✅ **مثال للاستخدام:**
//                ```java
//        List<TransactionDTO> transactions = transactionRepository.findAllByMonthAndYear(3, 2025);
//transactions.forEach(System.out::println);
//```
//        👉 يُرجع قائمة بجميع المعاملات التي تم إنشاؤها في **مارس 2025**.
//
//                ---
//
//                ## 🎯 **3. البحث باستخدام `searchTransactions`**
//                ```java
//        @Query("SELECT t FROM TransactionDTO t " +
//                "LEFT JOIN t.product p " +
//                "WHERE (:searchText IS NULL OR " +
//                "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//                "LOWER(t.status) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//                "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//                "LOWER(p.sku) LIKE LOWER(CONCAT('%', :searchText, '%')))")
//        Page<TransactionDTO> searchTransactions(@Param("searchText") String searchText, Pageable pageable);
//```
//
//        ### 🛠️ **شرح مفصل:**
//                1. **@Query** → تعريف استعلام مخصص باستخدام JPQL.
//2. **LEFT JOIN** → ربط الجدول `TransactionDTO` مع الجدول `ProductDTO`.
//                - `LEFT JOIN` يسمح بإظهار النتائج حتى إذا لم يكن هناك منتج مرتبط بالمعاملة.
//3. **WHERE** → شروط البحث:
//                - **`:searchText IS NULL`** → إذا لم يتم إدخال نص البحث، يتم عرض كل النتائج.
//                - **`LOWER(...)`** → لتحويل النص إلى أحرف صغيرة (لإجراء بحث غير حساس لحالة الأحرف).
//                - **`LIKE CONCAT('%', :searchText, '%')`** → للبحث عن نص معين داخل الحقل.
//
//                ### 🔎 **البحث يشمل:**
//                | الحقل في `TransactionDTO` | الحقل في `ProductDTO` |
//                |-----------------------|---------------------|
//                | `description` | `name` |
//                | `status` | `sku` |
//
//                ### ✅ **مثال للاستخدام:**
//                ```java
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<TransactionDTO> result = transactionRepository.searchTransactions("test", pageable);
//
//result.forEach(System.out::println);
//```
//        👉 يتم البحث باستخدام الكلمة `"test"` في:
//                - وصف المعاملة
//- حالة المعاملة
//- اسم المنتج
//- كود المنتج (SKU)
//
//### 🧠 **لماذا نستخدم `Page`؟**
//                ✔️ لتقسيم النتائج على عدة صفحات.
//✔️ توفير دعم كامل للفرز والتصفية.
//
//---
//
//        ## 🚀 **4. لماذا نستخدم `JpaRepository`؟**
//                ### ✅ **مزايا `JpaRepository`:**
//                ✔️ توفير جميع العمليات الأساسية (CRUD) تلقائيًا.
//                ✔️ دعم كامل للصفحات (Pagination) والفرز (Sorting).
//                ✔️ إدارة تلقائية للمعاملات (Transactions).
//                ✔️ كتابة استعلامات مخصصة بسهولة باستخدام **@Query**.
//                ✔️ التعامل مع البيانات المرتبطة (Relationships) بطريقة سهلة.
//
//                ---
//
//                ## 🌟 **5. أفضل الممارسات (Best Practices)**
//                ✅ استخدم **Optional<T>** عند البحث لتجنب `NullPointerException`.
//                ✅ استخدم **LEFT JOIN** عند البحث في جداول مرتبطة لتجنب النتائج غير المكتملة.
//✅ استخدم `Pageable` للحصول على نتائج مهيكلة في صفحات.
//                ✅ لا تستخدم `SELECT *` → حدد الأعمدة المطلوبة فقط لتحسين الأداء.
//                ✅ إذا أصبحت الاستعلامات معقدة → انقلها إلى الطبقة الخدمية (`Service Layer`).
//
//                ---
//
//                ## 🔥 **6. مثال كامل في الطبقة الخدمية (Service Layer)**
//                ```java
//        @Autowired
//        private TransactionRepository transactionRepository;
//
//        public Page<TransactionDTO> searchTransactions(String searchText, int page, int size) {
//            Pageable pageable = PageRequest.of(page, size);
//            return transactionRepository.searchTransactions(searchText, pageable);
//        }
//```
//
//        ---
//
//        ## ✅ **➡️ الملخص**
//                ✔️ **JpaRepository** → يوفر دعمًا تلقائيًا لعمليات CRUD.
//✔️ **@Query** → يسمح بكتابة استعلامات مخصصة.
//                ✔️ **@Param** → لتمرير المعاملات للاستعلام.
//✔️ **Page و Pageable** → لإدارة نتائج البحث والفرز.
//                ✔️ **LEFT JOIN** → لربط الجداول حتى مع القيم الفارغة. 😎
