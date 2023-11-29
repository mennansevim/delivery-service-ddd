package com.global.delivery.domain.product;

import com.global.delivery.domain.product.args.CreateProductArg;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {
    private String name;
    private String barcode;
    private int contentId;
    private int quantity;

    private Product(CreateProductArg arg) {
        this.name = arg.getName();
        this.barcode = arg.getBarcode();
        this.contentId = arg.getContentId();
        this.quantity = arg.getQuantity();
    }

    public static Product Create(CreateProductArg createProductArg) {
        return new Product(createProductArg);
    }
}