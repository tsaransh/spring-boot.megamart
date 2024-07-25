package com.megamart.product.service.impl;

import com.megamart.product.dto.ProductPurchaseResponse;
import com.megamart.product.dto.ProductRequest;
import com.megamart.product.dto.ProductResponse;
import com.megamart.product.dto.ProductPurchaseRequest;
import com.megamart.product.exception.ProductPurchaseException;
import com.megamart.product.repository.ProductRepository;
import com.megamart.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Integer addProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        var productIds = requests.stream()
                .map(ProductPurchaseRequest::productId).toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more product does not exist");
        }

        var storedRequest = requests.stream()
                    .sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product id: " + product.getId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchasedProducts;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId).map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the Id: "+productId));
    }

    @Override
    public List<ProductResponse> findAllProduct() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }


}
