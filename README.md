# Terminal commands
- docker-compose up -d  (same folder)

# Couchbase: (admin/test123)

Go to url: http://localhost:8091
* Create a bucket: delivery
*  Click Buckets > delivery > Scopes & Collections > _default Add Collection (collection name: Company)
* Create Indexes
  - CREATE PRIMARY INDEX `#primary` ON `delivery`.`_default`.`Company`
  - CREATE PRIMARY INDEX `#primary` ON `delivery`
  - CREATE INDEX `adv_name` ON `delivery`.`_default`.`Company`(`name`)

  
# Data:
Company Creation Json:
```json
{
  "code": "XCOM01",
  "createdBy": "Company X",
  "creationDate": 1690750920787,
  "identityNumber": "XCOMP456",
  "lastModifiedDate": 1690750920787,
  "name": "X-Company"
}
```


Delivery Creation Json:
```json
{
  "company": {
    "identityNumber": "XCOMP456"
  },
  "companyAddress": {
    "Address": "1234 Shopping Street",
    "AddressType": "CompanyWarehouse",
    "City": "Metropolis",
    "CountryName": "Wonderland",
    "CreatorEmail": "john.doe@supermart.com",
    "Email": "contact@supermart.com",
    "FirstName": "John",
    "FreeTradeZone": true,
    "LastName": "Doe",
    "Phone": "+1 123-456-7890",
    "Town": "Retailville"
  },
  "packages": [
    {
      "barcode": "PKG98765",
      "contentId": 123,
      "items": [
        {
          "color": "Red",
          "size": "Medium",
          "variantId": 1
        },
        {
          "color": "Blue",
          "size": "Large",
          "variantId": 2
        }
      ],
      "name": "Electronics Package",
      "quantity": 2
    },
    {
      "barcode": "PKG54321",
      "contentId": 456,
      "items": [
        {
          "color": "Black",
          "size": "Small",
          "variantId": 3
        }
      ],
      "name": "Clothing Package",
      "quantity": 1
    }
  ],
  "shippingAddress": {
    "Address": "9876 Delivery Avenue",
    "AddressType": "Shipping",
    "City": "Cityville",
    "CountryName": "Fantasyland",
    "CreatorEmail": "jane.doe@customer.com",
    "Email": "customer-service@customer.com",
    "FirstName": "Jane",
    "FreeTradeZone": false,
    "LastName": "Doe",
    "Phone": "+1 987-654-3210",
    "Town": "Residentialtown"
  }
}
```
