package com.ntou.auctionSite.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.ntou.auctionSite.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//open postman and select POST
// http://localhost:8080/products/add
// {
//      "productID": "P001",
//      "sellerID": "S001",
//      "productName": "餅乾",
//      "productPrice": 100,
//      "productType": "DIRECT"
// }
@RestController
public class ProductController { // 負責處理商品新增、上下架、查看、修改的class
    private static final Map<String, Product> productMap = new HashMap<>(); // hashmap存商品id or name和商品

    @GetMapping("/products/")
    public ResponseEntity<Collection<Product>> getAllProduct() {
        try {
            return ResponseEntity.ok(productMap.values());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") String productId) {
        try {
            Product product = productMap.get(productId);
            return ResponseEntity.ok(product == null ? new Product() : product);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/products/add") // 新增商品
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        try {
            if (product.getProductID() == null) {
                System.out.println("id:" + product.getProductID());
                return ResponseEntity.badRequest().build();
            }
            if (productMap.containsKey(product.getProductID())) {
                return ResponseEntity.unprocessableEntity().build();
            }
            productMap.put(product.getProductID(), product);
            return ResponseEntity.status(201).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/products/edit/{id}") // 修改商品
    public ResponseEntity<Void> editProduct(@PathVariable String id, @RequestBody Product request) {
        try {
            System.out.println(request.getProductID());
            Product product = productMap.get(id);
            if (product == null) {
                return ResponseEntity.status(404).build(); // not found
            }

            product.setProductID(request.getProductID());
            product.setSellerID(request.getSellerID());
            product.setProductName(request.getProductName());
            product.setProductDescription(request.getProductDescription());
            product.setProductPrice(request.getProductPrice());
            product.setProductImage(request.getProductImage());
            product.setProductType(request.getProductType());
            product.setProductStock(request.getProductStock());
            product.setProductCategory(request.getProductCategory());
            product.setProductStatus(request.getProductStatus());
            product.setCreatedTime(request.getCreatedTime());
            product.setUpdatedTime(request.getUpdatedTime());
            product.setAuctionEndTime(request.getAuctionEndTime());
            product.setNowHighestBid(request.getNowHighestBid());
            product.setHighestBidderID(request.getHighestBidderID());
            product.setViewCount(request.getViewCount());
            product.setAverageRating(request.getAverageRating());
            product.setReviewCount(request.getReviewCount());
            product.setTotalSales(request.getTotalSales());

            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/products/upload/{id}") // 上架商品
    public ResponseEntity<Void> publishProduct(@PathVariable(value = "id") String productId) {
        try {
            Product product = productMap.get(productId);
            if (product == null) {
                return ResponseEntity.status(404).build(); // not found
            }
            product.setProductStatus(Product.ProductStatuses.ACTIVE);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/products/withdraw/{id}") // 下架商品
    public ResponseEntity<Void> withdrawProduct(@PathVariable(value = "id") String productId) {
        try {
            Product product = productMap.get(productId);
            if (product == null) {
                return ResponseEntity.status(404).build(); // not found
            }
            product.setProductStatus(Product.ProductStatuses.INACTIVE);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
