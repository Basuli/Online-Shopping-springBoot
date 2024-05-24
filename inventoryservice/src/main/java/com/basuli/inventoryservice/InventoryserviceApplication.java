package com.basuli.inventoryservice;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

//import com.basuli.inventoryservice.entity.Inventory;
//import com.basuli.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner LoadData(InventoryRepository inventoryRepository){
	// 	return args ->{
	// 		Inventory inventory = new Inventory();
	// 		inventory.setSkuCode("iphone_13");
	// 		inventory.setQuantity(100);

	// 		Inventory inventory1 = new Inventory();
	// 		inventory1.setSkuCode("iphone_13_red");
	// 		inventory1.setQuantity(0);

	// 		inventoryRepository.saveAndFlush(inventory);
	// 		inventoryRepository.saveAndFlush(inventory1);
	// 	};
	// }

}
