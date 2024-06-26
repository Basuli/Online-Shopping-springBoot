package com.basuli.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {
    private Integer id;
    private String skuCode;
    private Double price;
    private  Integer quantity;
}
