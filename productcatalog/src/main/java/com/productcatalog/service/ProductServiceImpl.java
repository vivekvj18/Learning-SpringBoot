package com.productcatalog.service;

import com.productcatalog.dto.PaginatedResponseDTO;
import com.productcatalog.dto.ProductRequestDTO;
import com.productcatalog.dto.ProductResponseDTO;
import com.productcatalog.entity.Product;
import com.productcatalog.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return mapToResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToResponseDTO(product);
    }

    @Override
    public PaginatedResponseDTO getAllProducts(int page, int size, String sort) {

        Pageable pageable;

        if (sort != null && !sort.isEmpty()) {

            String[] sortParams = sort.split(",");

            if (sortParams.length == 2 && sortParams[1].equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(page, size, Sort.by(sortParams[0]).descending());
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sortParams[0]).ascending());
            }

        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponseDTO> productList = productPage.getContent()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();

        PaginatedResponseDTO response = new PaginatedResponseDTO();

        response.setProducts(productList);
        response.setPage(productPage.getNumber());
        response.setSize(productPage.getSize());
        response.setTotalPages(productPage.getTotalPages());
        response.setTotalElements(productPage.getTotalElements());

        return response;
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());

        Product updatedProduct = productRepository.save(product);

        return mapToResponseDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setStock(product.getStock());
        dto.setCreatedAt(product.getCreatedAt());

        return dto;
    }
}