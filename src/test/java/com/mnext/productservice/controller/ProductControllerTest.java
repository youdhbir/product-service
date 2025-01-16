package com.mnext.productservice.controller;

import com.mnext.productservice.model.Product;
import com.mnext.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private List<Product> mockProducts;

    @BeforeEach
    void setUp(){
        // Create mock products
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Laptop");
        product1.setDescription("Gaming Laptop");
        product1.setPrice(1500.0);
        product1.setQuantity(10);

        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Mobile");
        product2.setDescription("Gaming Mobile");
        product2.setPrice(1000.0);
        product2.setQuantity(20);

        mockProducts = Arrays.asList(product1, product2);
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(mockProducts);

        mockMvc.perform(get("/api/products/v1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockProducts.size()))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].description").value("Gaming Laptop"))
                .andExpect(jsonPath("$[0].price").value(1500.0))
                .andExpect(jsonPath("$[0].quantity").value(10))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Mobile"))
                .andExpect(jsonPath("$[1].description").value("Gaming Mobile"))
                .andExpect(jsonPath("$[1].price").value(1000.0))
                .andExpect(jsonPath("$[1].quantity").value(20));

        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }
}