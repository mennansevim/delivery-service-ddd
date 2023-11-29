package com.global.delivery.domain.packages.args;

import com.global.delivery.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePackageArg {
    private Product product;
}