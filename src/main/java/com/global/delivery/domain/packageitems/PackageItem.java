package com.global.delivery.domain.packageitems;

import com.global.delivery.domain.BaseEntity;
import com.global.delivery.domain.packageitems.args.CreatePackageItemArg;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PackageItem extends BaseEntity {
    private String color;
    private String size;
    private long variantId;

    public PackageItem() {
    }

    public static PackageItem Create(CreatePackageItemArg arg) {
        return new PackageItem(arg);
    }

    private PackageItem(CreatePackageItemArg arg) {
        this.color = arg.getColor();
        this.size = arg.getSize();
        this.variantId = arg.getVariantId();
    }
}