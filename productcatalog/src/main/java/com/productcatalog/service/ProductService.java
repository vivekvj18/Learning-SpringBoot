package com.productcatalog.service;

import com.productcatalog.dto.PaginatedResponseDTO;
import com.productcatalog.dto.ProductRequestDTO;
import com.productcatalog.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO request);

    ProductResponseDTO getProductById(Long id);

    PaginatedResponseDTO getAllProducts(int page, int size, String sort);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO request);

    void deleteProduct(Long id);
}