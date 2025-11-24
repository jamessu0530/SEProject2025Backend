package com.ntou.auctionSite.controller.cart;

import com.ntou.auctionSite.dto.cart.AddToCartRequest;
import com.ntou.auctionSite.dto.cart.CartResponseDTO;
import com.ntou.auctionSite.dto.cart.UpdateCartQuantityRequest;
import com.ntou.auctionSite.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "購物車管理", description = "購物車相關 API - 需要 JWT 驗證")
@SecurityRequirement(name = "Bearer Authentication")
public class CartController {
    private final CartService cartService;

    /**
     * 取得使用者購物車（含完整商品資訊）
     */
    @GetMapping
    @Operation(
            summary = "取得購物車",
            description = "取得當前登入使用者的購物車，包含商品完整資訊（名稱、價格、圖片、賣家等）"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "成功取得購物車",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CartResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "未授權")
    })
    public ResponseEntity<CartResponseDTO> getCart(Authentication authentication) {
        String userId = authentication.getName();  // 從 JWT 取得 userId
        CartResponseDTO cart = cartService.getUserCart(userId);
        return ResponseEntity.ok(cart);
    }

    /**
     * 加入商品到購物車
     */
    @PostMapping("/items")
    @Operation(
            summary = "加入商品到購物車",
            description = "將指定商品以指定數量加入購物車，若已存在則累加數量"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功加入購物車"),
            @ApiResponse(responseCode = "400", description = "請求參數錯誤"),
            @ApiResponse(responseCode = "404", description = "商品不存在")
    })
    public ResponseEntity<String> addToCart(
            Authentication authentication,
            @Valid @RequestBody AddToCartRequest request
    ) {
        String userId = authentication.getName();
        cartService.addToCart(userId, request.productId(), request.quantity());
        return ResponseEntity.ok("商品已加入購物車");
    }

    /**
     * 更新購物車商品數量（透過 itemId）
     */
    @PutMapping("/items/{itemId}")
    @Operation(
            summary = "更新購物車商品數量",
            description = "更新購物車中指定項目的數量，數量為 0 時將移除該項目"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "404", description = "購物車項目不存在")
    })
    public ResponseEntity<String> updateQuantity(
            Authentication authentication,
            @PathVariable String itemId,
            @Valid @RequestBody UpdateCartQuantityRequest request
    ) {
        String userId = authentication.getName();
        cartService.updateQuantity(userId, itemId, request.quantity());
        return ResponseEntity.ok("數量已更新");
    }

    /**
     * 從購物車移除商品（透過 itemId）
     */
    @DeleteMapping("/items/{itemId}")
    @Operation(
            summary = "從購物車移除商品",
            description = "從購物車中移除指定的項目"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功移除商品"),
            @ApiResponse(responseCode = "404", description = "購物車項目不存在")
    })
    public ResponseEntity<String> removeFromCart(
            Authentication authentication,
            @Parameter(description = "購物車項目 ID", required = true)
            @PathVariable String itemId
    ) {
        String userId = authentication.getName();
        cartService.removeItem(userId, itemId);
        return ResponseEntity.ok("商品已移除");
    }

    /**
     * 清空購物車
     */
    @DeleteMapping
    @Operation(
            summary = "清空購物車",
            description = "清空當前使用者的購物車，移除所有商品"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "成功清空購物車"),
            @ApiResponse(responseCode = "401", description = "未授權")
    })
    public ResponseEntity<Void> clearCart(Authentication authentication) {
        String userId = authentication.getName();
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
