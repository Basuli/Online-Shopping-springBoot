package com.basuli.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basuli.orderservice.dto.InventoryResponse;
import com.basuli.orderservice.dto.OrderLineItemsDto;
import com.basuli.orderservice.dto.RequestOrder;
import com.basuli.orderservice.entity.Order;
import com.basuli.orderservice.entity.OrderLineItems;
import com.basuli.orderservice.feign.OrderInterface;
import com.basuli.orderservice.repository.OrderRepository;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderInterface orderInterface;

   
    public String placeOrder(RequestOrder requestOrder) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = requestOrder.getOrderLineItemsDtoList().stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItems(orderLineItemsList);

        // Get list of skuCodes from requestOrder object
        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // Call inventory service to check if product is available or not, if available place order
        List<InventoryResponse> inventoryResponsesList = orderInterface.isInStock(skuCodes);
        boolean allInStock = inventoryResponsesList.stream().allMatch(InventoryResponse::getIsInStock);

        if (allInStock) {
            orderRepository.saveAndFlush(order);
           return  "order placed successfully";
        } else {
            throw new IllegalArgumentException("Some items are not in stock.");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }

    // // Implementing fallback method for circuit breaker
    // public String fallbackMethod(RequestOrder requestOrder, RuntimeException runtimeException) {
    //     //throw new RuntimeException("Inventory service is currently unavailable. Please try again later.", throwable);
    //     return "inventory Service is currently unavailable. Please try again later.";
    // }
}
