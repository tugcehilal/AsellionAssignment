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
                .currentPrice(new BigDecimal(14.20))
                .lastUpdate(new Date())
                .build();

        Product product2 = Product.builder()
                .name("Book")
                .currentPrice(new BigDecimal(15.50))
                .lastUpdate(new Date())
                .build();

        Product product3 = Product.builder()
                .name("Chair")
                .currentPrice(new BigDecimal(30.23))
                .lastUpdate(new Date())
                .build();

        Product product4 = Product.builder()
                .name("Kite")
                .currentPrice(new BigDecimal(20.20))
                .lastUpdate(new Date())
                .build();

        Product product5 = Product.builder()
                .name("Table")
                .currentPrice(new BigDecimal(30.20))
                .lastUpdate(new Date())
                .build();

        Product product6 = Product.builder()
                .name("Kindle")
                .currentPrice(new BigDecimal(30.23))
                .lastUpdate(new Date())
                .build();


        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);


        List<Product> productList = productService.getAllProducts();

        productList.forEach(product -> {
            System.out.println(product);
        });
    }
}
