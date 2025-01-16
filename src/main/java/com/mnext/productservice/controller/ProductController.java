package com.mnext.productservice.controller;

import com.mnext.productservice.model.Product;
import com.mnext.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/products/v1")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        String requestId = UUID.randomUUID().toString();
        logger.info("Request ID: {} - Get all products", requestId);

        List<Product> products = productService.getAllProducts();
        logger.info("Request ID: {} - Fetched {} products", requestId, products.size());

        return products;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
}
