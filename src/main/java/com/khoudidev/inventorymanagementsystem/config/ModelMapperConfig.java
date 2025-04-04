package com.khoudidev.inventorymanagementsystem.config;

import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                   .setFieldMatchingEnabled(true)
                   .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
//### **Explication détaillée du code :**
//
//Ce code présente la configuration d'un **`ModelMapper`** dans un projet Spring Boot. Le but de cette configuration est de définir un **bean** `ModelMapper` qui pourra être utilisé dans toute l'application pour effectuer des transformations entre objets, comme convertir des entités en DTO (Data Transfer Object) ou vice versa.
//
//        ---
//
//        ### **Lignes par ligne :**
//
//        1. **`package com.khoudidev.inventorymanagementsystem.config;`**
//        - Cette ligne définit le **package** dans lequel la classe de configuration `ModelMapperConfig` est située. Cela permet d'organiser le code de manière logique et de faciliter la gestion des classes par fonction dans le projet.
//
//        2. **`import org.modelmapper.ModelMapper;`**
//        - Ici, on importe la classe `ModelMapper` qui est fournie par la bibliothèque **ModelMapper**. Cette classe est utilisée pour effectuer les opérations de mappage entre différents types d'objets.
//
//        3. **`import org.modelmapper.convention.MatchingStrategies;`**
//        - Cette importation permet d'utiliser les stratégies de correspondance (matching strategies) pour personnaliser le comportement du mappage. **`MatchingStrategies`** est une classe utilitaire pour définir différents comportements de correspondance des champs lors de la conversion.
//
//        4. **`import org.springframework.context.annotation.Bean;`**
//        - Cette annotation est utilisée pour définir un bean dans le contexte Spring. Un **bean** est un objet qui est géré par le conteneur Spring, ce qui signifie qu'il sera automatiquement injecté là où il est nécessaire dans l'application.
//
//5. **`import org.springframework.context.annotation.Configuration;`**
//        - L'annotation **`@Configuration`** est utilisée pour marquer cette classe comme une classe de configuration Spring. Elle permet à Spring de savoir que cette classe contient des configurations nécessaires à l'initialisation du contexte de l'application.
//
//        6. **`@Configuration`**
//        - Cette annotation indique à Spring que cette classe est responsable de la configuration des beans. Spring va donc scanner cette classe pour voir quels beans elle doit enregistrer dans son contexte d'application.
//
//        7. **`public class ModelMapperConfig {`**
//        - Définition de la classe `ModelMapperConfig`. Cette classe va configurer un **bean** `ModelMapper`.
//
//        8. **`@Bean`**
//        - L'annotation **`@Bean`** est utilisée pour indiquer à Spring que la méthode qui suit crée un **bean**. Ici, la méthode `modelMapper()` crée une instance de `ModelMapper` et la rend disponible dans le contexte Spring pour injection.
//
//        9. **`public ModelMapper modelMapper() {`**
//    - Définition de la méthode `modelMapper()` qui retourne un **bean** de type `ModelMapper`. Ce bean sera ensuite utilisé pour le mappage des objets dans l'application.
//
//    10. **`ModelMapper modelMapper = new ModelMapper();`**
//    - Création d'une nouvelle instance de `ModelMapper`. C'est l'objet principal utilisé pour effectuer des conversions entre différents types d'objets.
//
//    11. **`modelMapper.getConfiguration()`**
//    - Récupération de la configuration actuelle de l'instance `ModelMapper`. Cela permet de personnaliser certains comportements du mappage.
//
//    12. **`.setFieldMatchingEnabled(true)`**
//    - Cette méthode permet d'activer la correspondance des **champs**. Cela signifie que les propriétés des objets seront mappées même si elles ont des noms différents, tant qu'elles sont de types compatibles. Si cette option est désactivée, seuls les champs ayant des noms exactement identiques seront mappés.
//
//    13. **`.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)`**
//    - Cette ligne définit le niveau d'accès des champs. Ici, il est configuré pour autoriser l'accès aux champs **privés** des classes. Cela signifie que même les champs privés seront accessibles pour le mappage, ce qui est particulièrement utile si l'on travaille avec des entités JPA où les propriétés sont souvent privées.
//
//    14. **`.setMatchingStrategy(MatchingStrategies.STANDARD);`**
//    - Cette ligne définit la **stratégie de correspondance**. La stratégie **`STANDARD`** permet d'effectuer des correspondances de manière standard, en essayant de faire correspondre les champs par nom et type.
//            - **Autres stratégies possibles** :
//    - **`STRICT`** : Strictement, il faut que les champs aient le même nom et type.
//    - **`LOOSE`** : Moins strict, permet une plus grande flexibilité dans la correspondance des champs.
//
//    15. **`return modelMapper;`**
//    - Cette ligne retourne l'instance de `ModelMapper` configurée. Spring va maintenant utiliser cette instance de manière centralisée dans toute l'application.
//
//    16. **`}`**
//        - Fin de la méthode `modelMapper()`.
//
//        17. **`}`**
//        - Fin de la classe `ModelMapperConfig`.
//
//        ---
//
//        ### **Pourquoi utiliser ModelMapper et quel est l'avantage ?**
//
//        1. **Automatisation du mappage** :
//ModelMapper est utilisé pour automatiser le processus de conversion entre différents types d'objets. Cela permet de ne pas écrire manuellement du code de mappage chaque fois que vous devez transformer des entités en DTO ou vice versa.
//
//        2. **Réduction du code répétitif** :
//        Sans ModelMapper, vous devrez peut-être créer un constructeur ou des méthodes manuelles pour chaque objet pour effectuer le mappage, ce qui peut entraîner beaucoup de code redondant. ModelMapper simplifie ce processus et réduit le code boilerplate.
//
//3. **Personnalisation** :
//ModelMapper vous permet de personnaliser facilement la stratégie de mappage pour s'adapter à vos besoins. Vous pouvez définir des règles spécifiques pour gérer les noms de champs différents, les types incompatibles, ou d'autres cas particuliers.
//
//4. **Amélioration de la maintenabilité** :
//En utilisant un outil comme ModelMapper, vous pouvez rendre votre code plus propre et plus facile à maintenir. Par exemple, si vous devez modifier la logique de mappage (changer un champ de nom ou de type), vous n'avez qu'à modifier la configuration du mappage au lieu de modifier plusieurs endroits dans votre code.
//
//---
//
//        ### **Utiliser ou ne pas utiliser ModelMapper :**
//
//        #### **Utiliser ModelMapper :**
//        - Lorsque vous avez des objets complexes à mapper (par exemple, des entités JPA avec plusieurs niveaux de relations).
//        - Lorsque vous voulez éviter de répéter du code de mappage entre DTO et entités.
//        - Lorsque vous souhaitez un mappage flexible et facilement personnalisable entre différents types d'objets.
//
//        #### **Ne pas utiliser ModelMapper :**
//        - Lorsque les objets à mapper sont très simples et qu'il n'y a pas beaucoup de champs à mapper (le mappage manuel peut être plus simple).
//        - Si vous préférez avoir un contrôle total sur chaque étape du processus de mappage.
//        - Si vous avez des performances très critiques et que le surcoût d'utiliser ModelMapper (bien que léger) doit être évité.
//
//        ---
//
//        ### **Conclusion :**
//Utiliser **ModelMapper** est très utile pour simplifier et automatiser le processus de mappage entre les objets dans une application. Cependant, il faut faire attention à son utilisation dans des cas où des mappages très spécifiques ou manuels sont nécessaires, ou si la performance est une priorité absolue.