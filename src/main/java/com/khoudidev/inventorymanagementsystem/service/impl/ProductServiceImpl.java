package com.khoudidev.inventorymanagementsystem.service.impl;

import com.khoudidev.inventorymanagementsystem.dto.ProductDTO;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.entity.Category;
import com.khoudidev.inventorymanagementsystem.entity.Product;
import com.khoudidev.inventorymanagementsystem.enums.Path;
import com.khoudidev.inventorymanagementsystem.exceptions.NotFoundException;
import com.khoudidev.inventorymanagementsystem.repository.CategoryRepository;
import com.khoudidev.inventorymanagementsystem.repository.ProductRepository;
import com.khoudidev.inventorymanagementsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//?????????????????????????????????
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    private static final String IMAGE_DIRECTORY = System.getProperty("user.dir")+ "/product-images/";

    @Override
    public Response saveProduct(ProductDTO productDTO, MultipartFile imageFile) throws IOException {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Product productToSave = Product.builder()
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .description(productDTO.getDescription())
                .category(category)
                .build();

        // Enregistrement du produit
        productToSave = productRepository.save(productToSave);

        // Sauvegarde de l'image associée au produit
        if (imageFile != null && !imageFile.isEmpty()) {
            if (!imageFile.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("Only image files are allowed");
            }

            File directory = new File(Path.PHOTOS_PATH.getPhotosURL());
            if (!directory.exists()) {
                directory.mkdirs(); // Crée le dossier s'il n'existe pas
            }

            // Définir le chemin du fichier
            String filePath = Path.PHOTOS_PATH.getPhotosURL() +
                    File.separator + productToSave.getId() + ".png";

            imageFile.transferTo(Paths.get(filePath));

            String imageUrl = "http://localhost:5050/api/products/images/" + productToSave.getId();
            productToSave.setImageUrl(imageUrl);

            // Mise à jour du produit avec l'URL de l'image
            productRepository.save(productToSave);
        }

        return Response.builder()
                .status(200)
                .message("Product successfully saved")
                .build();
    }


    @Override
    public Response updateProduct(ProductDTO productDTO, MultipartFile imageFile) throws IOException {
        Product existingProduct = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        // 🔥 Gestion de l'image directement dans updateProduct()
        if (imageFile != null && !imageFile.isEmpty()) {
            if (!imageFile.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("Only image files are allowed");
            }

            File directory = new File(Path.PHOTOS_PATH.getPhotosURL());
            if (!directory.exists()) {
                directory.mkdirs(); // Crée le répertoire s'il n'existe pas
            }

            // Définir le chemin du fichier
            String filePath = Path.PHOTOS_PATH.getPhotosURL() +
                    File.separator + existingProduct.getId() + ".png";

            // Transférer le fichier vers le chemin défini
            imageFile.transferTo(Paths.get(filePath));

            // Définir l'URL de l'image
            String imageUrl = "http://localhost:5050/api/products/images/" + existingProduct.getId();
            existingProduct.setImageUrl(imageUrl);
        }

        // 🔥 Vérification et mise à jour des champs
        if (productDTO.getCategoryId() != null && productDTO.getCategoryId() > 0) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found"));
            existingProduct.setCategory(category);
        }

        if (productDTO.getName() != null && !productDTO.getName().isBlank()) {
            existingProduct.setName(productDTO.getName());
        }

        if (productDTO.getSku() != null && !productDTO.getSku().isBlank()) {
            existingProduct.setSku(productDTO.getSku());
        }

        if (productDTO.getDescription() != null && !productDTO.getDescription().isBlank()) {
            existingProduct.setDescription(productDTO.getDescription());
        }

        if (productDTO.getPrice() != null && productDTO.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            existingProduct.setPrice(productDTO.getPrice());
        }

        if (productDTO.getStockQuantity() != null && productDTO.getStockQuantity() >= 0) {
            existingProduct.setStockQuantity(productDTO.getStockQuantity());
        }

        // 🔥 Mise à jour du produit dans la base de données
        productRepository.save(existingProduct);

        return Response.builder()
                .status(200)
                .message("Product successfully updated")
                .build();
    }


    @Override
    public Response getAllProducts() {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<ProductDTO> productDTOS = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("success")
                .products(productDTOS)
                .build();
    }

    @Override
    public Response getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return Response.builder()
                .status(200)
                .message("success")
                .product(modelMapper.map(product,ProductDTO.class))
                .build();
    }

    @Override
    public Response deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Product successfully deleted")
                .build();
    }

    private String saveImage(MultipartFile imageFile) {
        if (!imageFile.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        File directory = new File(IMAGE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
            log.info("Directory was created");
        }

        // Générer un nom unique
        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        String imagePath = IMAGE_DIRECTORY + uniqueFileName;
        try {
            File destinationFile = new File(imagePath);
            imageFile.transferTo(destinationFile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error occurred while saving image: " + e.getMessage());
        }

        // Retourner l'URL HTTP au lieu du chemin absolu
        return "/api/images/" + uniqueFileName;
    }
}
