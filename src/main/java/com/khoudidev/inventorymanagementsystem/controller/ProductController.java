package com.khoudidev.inventorymanagementsystem.controller;

import com.khoudidev.inventorymanagementsystem.dto.CategoryDTO;
import com.khoudidev.inventorymanagementsystem.dto.ProductDTO;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.enums.Path;
import com.khoudidev.inventorymanagementsystem.service.CategoryService;
import com.khoudidev.inventorymanagementsystem.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> saveProduct(
            @RequestParam("imageFile")MultipartFile imagefile,
            @RequestParam("name") String name,
            @RequestParam("sku") String sku,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stockQuantity") Integer stockQuantity,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value ="description", required = false) String description

    ) throws IOException {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setSku(sku);
        productDTO.setPrice(price);
        productDTO.setStockQuantity(stockQuantity);

        productDTO.setCategoryId(categoryId);
        productDTO.setDescription(description);

        log.info(productDTO.toString());


        return ResponseEntity.ok(productService.saveProduct(productDTO,imagefile));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(
            @RequestParam(value ="imageFile", required = false)MultipartFile imagefile,
            @RequestParam(value ="name", required = false) String name,
            @RequestParam(value ="sku", required = false) String sku,
            @RequestParam(value ="price", required = false) BigDecimal price,
            @RequestParam(value ="stockQuantity", required = false) Integer stockQuantity,
            @RequestParam(value ="id", required = true) Long id,
            @RequestParam(value ="CategoryId", required = false) Long categoryId,
            @RequestParam(value ="description", required = false) String description

    ) throws IOException {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setSku(sku);
        productDTO.setPrice(price);
        productDTO.setStockQuantity(stockQuantity);
        productDTO.setId(id);
        productDTO.setCategoryId(categoryId);
        productDTO.setDescription(description);
        return ResponseEntity.ok(productService.updateProduct(productDTO, imagefile));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    @GetMapping("/images/{id}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable Long id) {
        // Définir le chemin du fichier
        String filePath = Path.PHOTOS_PATH.getPhotosURL() +
                File.separator + id + ".png";
        FileSystemResource file = new FileSystemResource(filePath);
        if(!file.exists()) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body( file);

    }







}