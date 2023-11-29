package com.global.delivery.domain.packages;

import com.global.delivery.domain.BaseEntity;
import com.global.delivery.domain.packageitems.PackageItem;
import com.global.delivery.domain.packages.args.CreatePackageArg;
import com.global.delivery.domain.product.Product;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Package extends BaseEntity {
    private String trackingNumber;
    private PackageCancelReason reason;
    private PackageStatus status;
    private List<PackageItem> items;
    private LocalDateTime deliveredDate;
    private Product product;

    private Package(List<PackageItem> items, Product product) {
        this.items = items;
        this.product = product;
    }

    public static Package Create(CreatePackageArg arg) {
        return new Package(new ArrayList<>(), arg.getProduct());
    }

    public void addPackageItem(PackageItem item) {
        items.add(item);
    }

    public void packed() {
        status = PackageStatus.Packed;
    }

    public void shipping(String trackingNumber) {
        status = PackageStatus.Shipping;
        this.trackingNumber = trackingNumber;
    }

    public void delivered() {
        status = PackageStatus.Delivered;
        deliveredDate  = Instant.now().atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    public void cancel(PackageCancelReason cancelReason) {
        status = PackageStatus.Cancelled;
        reason = cancelReason;
    }

    public void removeItem(PackageItem item) {
        items.remove(item);
    }
}
