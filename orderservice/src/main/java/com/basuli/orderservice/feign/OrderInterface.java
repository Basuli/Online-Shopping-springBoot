package com.basuli.orderservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.basuli.orderservice.dto.InventoryResponse;

@FeignClient("INVENTORY-SERVICE")
public interface OrderInterface {
    @GetMapping("inventory")
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode);
}
