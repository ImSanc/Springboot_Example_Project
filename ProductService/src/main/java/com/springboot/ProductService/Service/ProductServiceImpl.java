package com.springboot.ProductService.Service;

import com.springboot.ProductService.Entity.Product;
import com.springboot.ProductService.Exception.ProductException;
import com.springboot.ProductService.Model.ProductRequest;
import com.springboot.ProductService.Model.ProductResponse;
import com.springboot.ProductService.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class ProductServiceImpl implements  ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long addProduct(ProductRequest productRequest) {

        log.info("{} : add product method called", this.getClass().getName());
        var product = Product.builder()
                .productName(productRequest.getName())
                .productQuantity(productRequest.getQuantity())
                .productPrice(productRequest.getPrice()).build();

        productRepository.save(product);
        log.info("product with ID :{} added ", product.getProductId());
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(Long productId)
    {
        log.info("{} : getProductById called", this.getClass().getName());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product with id :" + productId + " doesn't exist", "101"));

        var productResponse = ProductResponse.builder()
                .productName(product.getProductName())
                .price(product.getProductPrice())
                .quantity(product.getProductQuantity()).build();

        return productResponse;
    }
}