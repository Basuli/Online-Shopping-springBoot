package com.basuli.productservice.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.basuli.productservice.dto.ProductRequest;
import com.basuli.productservice.dto.ProductResponse;
import com.basuli.productservice.service.ProductService;




@RestController
@RequestMapping("product")
public class ProductController {
    
    private  ProductService productservice;

    
    public ProductController(ProductService productservice) {
        this.productservice = productservice;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productservice.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
        return productservice.getAllProduct();
    }
}
