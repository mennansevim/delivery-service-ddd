package com.global.delivery.domain.packageitems.args;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePackageItemArg {
    private String color;
    private String size;
    private int variantId;

    public CreatePackageItemArg(String color, String size, int variantId) {
        this.color = color;
        this.size = size;
        this.variantId = variantId;
    }
}