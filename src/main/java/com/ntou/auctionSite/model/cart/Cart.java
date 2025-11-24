package com.ntou.auctionSite.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;              // 購物車 ID（通常等於 userId）
    private String userId;          // 使用者 ID
    private List<CartItem> items = new ArrayList<>();  // 購物車項目列表

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItem {
        private String itemId;      // 項目 ID（用於前端操作）
        private String productId;   // 商品 ID
        private Integer quantity;   // 數量

        // 建構子：只傳入 productId 和 quantity，自動生成 itemId
        public CartItem(String productId, Integer quantity) {
            this.itemId = UUID.randomUUID().toString();
            this.productId = productId;
            this.quantity = quantity;
        }
    }
}

