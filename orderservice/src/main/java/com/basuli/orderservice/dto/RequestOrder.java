package com.basuli.orderservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrder {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
