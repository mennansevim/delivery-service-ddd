package com.global.delivery.domain.delivery;

import com.global.delivery.domain.BaseEntity;
import com.global.delivery.domain.ISoftDelete;
import com.global.delivery.domain.address.Address;
import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.delivery.args.CreateDeliveryArg;
import com.global.delivery.domain.packages.Package;
import lombok.Getter;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Getter
@Document
public class Delivery extends BaseEntity implements ISoftDelete {

    private Company company;
    public List<Address> addresses;

    // 3 t-shirts, 3 unique barcodes -> Package
    // BarcodeA has 2 items (Blue, Green) -> Package Item
    // BarcodeB has 1 item (Blue) -> Package Item
    // BarcodeC has 3 item (64GB, 128GB, 256GB) -> Package Item
    private List<Package> packages;
    public Delivery(){}
    private Delivery(Company company) {
        this.company = company;
        this.packages = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public static Delivery Create(CreateDeliveryArg deliveryArg) {
        Delivery delivery = new Delivery(
                deliveryArg.getCompany()
        );

        delivery.AddAddress(deliveryArg.getCompanyAddress());
        delivery.AddAddress(deliveryArg.getShippingAddress());
        return delivery;
    }

    public void AddPackage(Package pck)
    {
        this.packages.add(pck);
    }

    private void AddAddress(Address address)
    {
        this.addresses.add(address);
    }
}
