    // Hibernate thường ánh xạ các cột số thập phân thành double trong Java, nên dùng Double sẽ tự nhiên hơn.


package t2406e_group1.bookshopspringboot.maketing;

    public class DiscountProductDTO {
        private Integer productId;
        private String name;
        private Float price;
        private Float salePrice;
        private Integer quantity;
    
        // Constructor nhận các tham số
        public DiscountProductDTO(Integer productId, String name, Float price, Float salePrice, Integer quantity) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.salePrice = salePrice;
            this.quantity = quantity;
        }
    
        // Constructor mặc định
        public DiscountProductDTO() {}
    
        // Getters và setters
        public Integer getProductId() { return productId; }
        public void setProductId(Integer productId) { this.productId = productId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Float getPrice() { return price; }
        public void setPrice(Float price) { this.price = price; }
        public Float getSalePrice() { return salePrice; }
        public void setSalePrice(Float salePrice) { this.salePrice = salePrice; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }


// package t2406e_group1.bookshopspringboot.maketing;

// public class DiscountProductDTO {
//     private Integer productId;
//     private String name;
//     private Float price;
//     private Float salePrice;
//     private Integer quantity;

//     // Constructors
//     public DiscountProductDTO() {}

//     public DiscountProductDTO(Integer productId, String name, Float price, Float salePrice, Integer quantity) {
//         this.productId = productId;
//         this.name = name;
//         this.price = price;
//         this.salePrice = salePrice;
//         this.quantity = quantity;
//     }

//     // Getters and setters
//     public Integer getProductId() { return productId; }
//     public void setProductId(Integer productId) { this.productId = productId; }
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
//     public Float getPrice() { return price; }
//     public void setPrice(Float price) { this.price = price; }
//     public Float getSalePrice() { return salePrice; }
//     public void setSalePrice(Float salePrice) { this.salePrice = salePrice; }
//     public Integer getQuantity() { return quantity; }
//     public void setQuantity(Integer quantity) { this.quantity = quantity; }
// }