package com.megamart.product.controller;

import com.megamart.product.dto.ProductPurchaseResponse;
import com.megamart.product.dto.ProductResponse;
import com.megamart.product.dto.ProductPurchaseRequest;
import com.megamart.product.dto.ProductRequest;
import com.megamart.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<Integer> registerProduct(@RequestBody @Valid ProductRequest request) {
        return null;
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request) {
        return ResponseEntity.ok(productService.purchaseProduct(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAllProduct());
    }

}
