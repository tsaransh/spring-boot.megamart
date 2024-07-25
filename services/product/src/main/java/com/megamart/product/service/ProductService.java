package com.megamart.product.service;

import com.megamart.product.dto.ProductPurchaseResponse;
import com.megamart.product.dto.ProductRequest;
import com.megamart.product.dto.ProductResponse;
import com.megamart.product.dto.ProductPurchaseRequest;

import java.util.List;

public interface ProductService {

    public Integer addProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAllProduct();
}
