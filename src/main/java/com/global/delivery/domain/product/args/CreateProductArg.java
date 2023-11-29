package com.global.delivery.domain.product.args;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductArg {
    private String name;
    private String barcode;
    private int contentId;
    private int quantity;
}
