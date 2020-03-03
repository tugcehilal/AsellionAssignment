package com.asellion.assignment.service;

import com.asellion.assignment.exception.ResourceNotFoundException;
import com.asellion.assignment.model.Product;
import com.asellion.assignment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldRead() {
        //given
        Product product = Product.builder()
                .id(1L)
                .name("product-1")
                .currentPrice(new BigDecimal(10))
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        //when
        Product actual = productService.getProductById(1L);

        //then
        verify(productRepository, times(1)).findById(any(Long.class));
        assertThat(actual.getId(), is(notNullValue()));
        assertThat(actual.getName(), equalTo("product-1"));
        assertThat(actual, equalTo(product));
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        //given
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        RuntimeException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> productService.getProductById(1L)
        );

        //then
        assertTrue(thrown.getMessage().contains("Resource not found"));
    }

    @Test
    void shouldFindAll() {
        //given
        Product product1 = Product.builder().id(1L).name("product-1").build();
        Product product2 = Product.builder().id(2L).name("product-2").build();
        Product product3 = Product.builder().id(3L).name("product-3").build();

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2, product3));

        //when
        List<Product> actual = productService.getAllProducts();

        //then
        assertThat(actual, hasItems(product1, product2, product3));
    }

    @Test
    void shouldCreateProduct() {
        //given
        Product product = Product.builder()
                .id(1L)
                .name("product-1")
                .currentPrice(new BigDecimal(10))
                .build();

        when(productRepository.save(product)).thenReturn(product);

        //when
        Product actual = productService.createProduct(product);

        //then
        verify(productRepository, times(1)).save(any(Product.class));
        assertThat(actual.getId(), is(notNullValue()));
        assertThat(actual.getName(), equalTo("product-1"));
        assertThat(actual, equalTo(product));
    }

    @Test
    void shouldUpdateProductWithNewValues() {
        //given
        Product product = Product.builder()
                .id(1L)
                .name("product-1")
                .currentPrice(new BigDecimal(10))
                .build();

        Product productUpdated = Product.builder()
                .id(1L)
                .name("product-15")
                .currentPrice(new BigDecimal(15))
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(productUpdated);

        //when
        Product actual = productService.updateProduct(1L, productUpdated);

        //then
        verify(productRepository, times(1)).save(any(Product.class));
        assertThat(actual.getId(), is(notNullValue()));
        assertThat(actual.getName(), equalTo(productUpdated.getName()));
        assertThat(actual.getCurrentPrice(), equalTo(productUpdated.getCurrentPrice()));
        assertThat(actual, equalTo(productUpdated));
    }

    @Test
    void shouldThrowExceptionWhenNotFoundForUpdate() {
        //given
        Product product = Product.builder()
                .id(1L)
                .name("product-1")
                .currentPrice(new BigDecimal(10))
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        RuntimeException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> productService.updateProduct(1L, product)
        );

        //then
        assertTrue(thrown.getMessage().contains("Resource not found"));
    }

    @Test
    void shouldDeleteProduct() {
        //when
        productService.deleteProduct(1L);

        //then
        verify(productRepository, times(1)).deleteById(1L);
    }

}