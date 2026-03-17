package com.productcatalog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String category;

    private Integer stock;

    private LocalDateTime createdAt;
}