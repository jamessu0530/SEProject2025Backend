package com.ntou.auctionSite.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "購物車項目詳細資訊")
public class CartItemDTO {

    @Schema(description = "購物車項目 ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String itemId;           // 購物車項目 ID

    @Schema(description = "商品 ID", example = "P001")
    private String productId;        // 商品 ID

    @Schema(description = "商品名稱", example = "iPhone 15 Pro")
    private String productName;      // 商品名稱

    @Schema(description = "商品價格", example = "35900")
    private Integer price;           // 商品價格

    @Schema(description = "商品圖片 URL", example = "/uploads/iphone15.jpg")
    private String imageUrl;         // 商品圖片

    @Schema(description = "賣家 ID", example = "U002")
    private String sellerId;         // 賣家 ID

    @Schema(description = "賣家名稱", example = "Apple 專賣店")
    private String sellerName;       // 賣家名稱

    @Schema(description = "數量", example = "2")
    private Integer quantity;        // 數量

    @Schema(description = "小計（價格 × 數量）", example = "71800")
    private Integer subtotal;        // 小計（價格 × 數量）
}

