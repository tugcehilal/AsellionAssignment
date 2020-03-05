package com.asellion.assignment.bootstrap;

import com.asellion.assignment.model.Product;
import com.asellion.assignment.repository.ProductRepository;
import com.asellion.assignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        Product product1 = Product.builder()
                .name("Phone")
                .currentPrice(new BigDecimal(12.20))
                .lastUpdate(new Date())
                .build();

        Product product2 = Product.builder()
                .name("Book")
                .currentPrice(new BigDecimal(16.50))
                .lastUpdate(new Date())
                .build();

        Product product3 = Product.builder()
                .name("Chair")
                .currentPrice(new BigDecimal(31.23))
                .lastUpdate(new Date())
                .build();

        Product product4 = Product.builder()
                .name("Kite")
                .currentPrice(new BigDecimal(22.20))
                .lastUpdate(new Date())
                .build();

        Product product5 = Product.builder()
                .name("Table")
                .currentPrice(new BigDecimal(31.20))
                .lastUpdate(new Date())
                .build();

        Product product6 = Product.builder()
                .name("Kindle")
                .currentPrice(new BigDecimal(32.23))
                .lastUpdate(new Date())
                .build();

        productService.createProduct(product1);
        productService.createProduct(product2);
        productService.createProduct(product3);
        productService.createProduct(product4);
        productService.createProduct(product5);
        productService.createProduct(product6);




        List<Product> productList = productService.getAllProducts();

        productList.forEach(product -> {
            System.out.println(product);
        });
    }
}
