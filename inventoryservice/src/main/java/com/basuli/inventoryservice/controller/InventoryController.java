package com.basuli.inventoryservice.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.basuli.inventoryservice.dto.InventoryResponse;
import com.basuli.inventoryservice.entity.Inventory;
import com.basuli.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("inventory")
@RequiredArgsConstructor
public class InventoryController {
    
    private  final InventoryService inventoryService;
    
    //http://localhost:8082/inventory?sku-code=iphone-13&sku-code=iphone-13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
    @PostMapping("/addStock")
    public Inventory addStock(@RequestBody Inventory inventory){
        return inventoryService.addStock(inventory);
    }
}
