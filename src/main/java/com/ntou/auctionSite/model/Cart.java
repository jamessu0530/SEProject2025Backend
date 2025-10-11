package com.ntou.auctionSite.model;

import java.util.ArrayList;


public class Cart {
    private ArrayList <Products> products;
    private class Products {
        String ProductID;
        int Quantity;
        public Products(String productID) {
            this.ProductID = productID;
            this.Quantity = 1;
        }
        public String getProductID() { return ProductID; }
        public void setProductID(String productID) { ProductID = productID; }
        public int getQuantity() { return Quantity; }
        public void setQuantity(int quantity) { Quantity = quantity; }
    }

    public Cart() {
        this.products = new ArrayList<Products>();
    }
    //取得購物車內容
    public ArrayList<Products> getProducts() { return products; }
    //加入購物車
    public void addProduct(String productID) {
        for (Products p : products) {
            if (p.getProductID().equals(productID)) {
                p.setQuantity(p.getQuantity() + 1);
                return;
            }
        }
        products.add(new Products(productID));
    }
    //加數量
    public void quantityIncrease(String productID) {
        for (Products p : products) {
            if (p.getProductID().equals(productID)) {
                p.setQuantity(p.getQuantity() + 1);
                return;
            }
        }
    }
    //減數量
    public void quantityDecrease(String productID) {
        for (Products p : products) {
            if (p.getProductID().equals(productID)) {
                if (p.getQuantity() > 1) {
                    p.setQuantity(p.getQuantity() - 1);
                } else {
                    products.remove(p);
                }
                return;
            }
        }
    }
    //移除商品
    public void removeProduct(String productID) {
        products.removeIf(p -> p.getProductID().equals(productID));
    }
    //清空購物車
    public void clearCart() {
        products.clear();
    }

}

