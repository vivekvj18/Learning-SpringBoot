package com.productcatalog.dto;

import lombok.Data;
import java.util.List;

@Data
public class PaginatedResponseDTO {

    private List<ProductResponseDTO> products;

    private int page;

    private int size;

    private int totalPages;

    private long totalElements;
}