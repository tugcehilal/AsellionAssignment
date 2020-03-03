package com.asellion.assignment.bootstrap;

import com.asellion.assignment.model.Product;
import com.asellion.assignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

//@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product product1 = Product.builder()
                .name("Product 1")
                .currentPrice(new BigDecimal(10))
                .lastUpdate(new Date())
                .build();

        Product product2 = Product.builder()
                .name("Product 2")
                .currentPrice(new BigDecimal(20))
                .lastUpdate(new Date())
                .build();

        Product product3 = Product.builder()
                .name("Product 3")
                .currentPrice(new BigDecimal(30.23))
                .lastUpdate(new Date())
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
}
