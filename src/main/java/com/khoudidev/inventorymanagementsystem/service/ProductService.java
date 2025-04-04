package com.khoudidev.inventorymanagementsystem.service;

import com.khoudidev.inventorymanagementsystem.dto.ProductDTO;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.dto.SupplierDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    Response saveProduct(ProductDTO productDTO, MultipartFile imageFile) throws IOException;

    Response updateProduct(ProductDTO productDTO, MultipartFile imageFile) throws IOException;

    Response getAllProducts();

    Response getProductById(Long id );



    Response deleteProduct(Long id );
}
