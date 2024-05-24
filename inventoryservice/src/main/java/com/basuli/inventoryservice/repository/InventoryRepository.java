package com.basuli.inventoryservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basuli.inventoryservice.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

   

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
    
}
