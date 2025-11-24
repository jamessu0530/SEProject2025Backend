package com.ntou.auctionSite.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "購物車完整回應")
public class CartResponseDTO {

    @Schema(description = "使用者 ID", example = "U001")
    private String userId;                  // 使用者 ID

    @Schema(description = "購物車項目列表")
    private List<CartItemDTO> items;        // 購物車項目列表

    @Schema(description = "總金額", example = "79790")
    private Integer totalAmount;            // 總金額

    @Schema(description = "總項目數", example = "2")
    private Integer totalItems;             // 總項目數
}

