package com.basuli.productservice.service;

import java.util.List;


import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.basuli.productservice.dto.ProductRequest;
import com.basuli.productservice.dto.ProductResponse;
import com.basuli.productservice.entity.Product;
import com.basuli.productservice.repository.ProductRepository;


@Service
@Slf4j
public class ProductService {

    private  ProductRepository productRepository;

    
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create product
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                        .name(productRequest.getName())
                        .description(productRequest.getDescription())
                        .price(productRequest.getPrice()).build();
        
        productRepository.saveAndFlush(product);
        log.info("product with id {} saved in the database",product.getId());
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }

    //get list of all products
    public List<ProductResponse> getAllProduct() {
        List<Product>products= productRepository.findAll();
        return products.stream().map(pdt->mapToProductResponse(pdt)).toList();
    }


    public ProductResponse mapToProductResponse(Product pdt){
        return ProductResponse.builder()
                .id(pdt.getId())
                .name(pdt.getName())
                .description(pdt.getDescription())
                .price(pdt.getPrice())
                .build();
    }

}
