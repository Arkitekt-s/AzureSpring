package com.example.azurespring.Service;

import com.example.azurespring.Modle.Product;
import com.example.azurespring.Repo.ProductRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Should throw an exception when the product does not exist")
    void updateProductWhenProductDoesNotExistThenThrowException() {
        Long id = 1L;
        Product product = new Product();
        when(productRepo.findById(id)).thenReturn(null);
        Throwable exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> productService.updateProduct(id, product));
        assertEquals("Product with id " + id + " does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("Should update the product when the product exists")
    void updateProductWhenProductExistsThenUpdateTheProduct() {
        Product product = new Product();
//        product.setId(1L);
        product.setName("test");
        product.setPrice(100);
        product.setWeight(100);
        when(productRepo.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(productRepo.save(product)).thenReturn(product);
        Product updatedProduct = productService.updateProduct(1L, product);
        assertEquals(product, updatedProduct);
    }

    @Test
    @DisplayName("Should throw an exception when the product is invalid")
    void createProductWhenProductIsInvalidThenThrowException() {
        Product product = new Product();
        assertThrows(IllegalStateException.class, () -> productService.createProduct(product));
    }

    @Test
    @DisplayName("Should save the product when the product is valid")
    void createProductWhenProductIsValid() {
        Product product = new Product();
        product.setName("test");
        product.setPrice(100);
        product.setWeight(100);
        when(productRepo.save(product)).thenReturn(product);
        Product savedProduct = productService.createProduct(product);
        assertEquals(product, savedProduct);
    }

    @Test
    @DisplayName("Should throw an exception when the product does not exist")
    void deleteProductWhenProductDoesNotExistThenThrowException() {
        Long id = 1L;
        when(productRepo.findById(id)).thenReturn(null);
        assertThrows(IllegalStateException.class, () -> productService.deleteProduct(id));
    }

    @Test
    @DisplayName("Should delete the product when the product exists")
    void deleteProductWhenProductExists() {
        Product product = new Product();
//        product.setId(1L);
        product.setName("test");
        product.setPrice(100);
        product.setWeight(100);
        when(productRepo.findById(1L)).thenReturn(java.util.Optional.of(product));
        productService.deleteProduct(1L);
        verify(productRepo, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should return the product when the product exists")
    void getProductByIdWhenProductExists() {
        Product product = new Product(1L, "test", 100, 100);
        when(productRepo.findById(1L)).thenReturn(java.util.Optional.of(product));
        Product result = productService.getProductById(1L);
        assertEquals(product, result);
    }

    @Test
    @DisplayName("Should throw an exception when the product does not exist")
    void getProductByIdWhenProductDoesNotExistThenThrowException() {
        Long id = 1L;
        when(productRepo.findById(id)).thenReturn(java.util.Optional.empty());
        Throwable exception =
                assertThrows(IllegalStateException.class, () -> productService.getProductById(id));
        assertEquals("Product with id 1 does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("Should return all products")
    void getAllProductsShouldReturnAllProducts() {
        Product product1 = Product.builder().id(1L).name("product1").price(100).weight(100).build();
        Product product2 = Product.builder().id(2L).name("product2").price(200).weight(200).build();
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepo.findAll()).thenReturn(products);
        List<Product> result = productService.getAllProducts();
        assertEquals(products, result);
    }
}
