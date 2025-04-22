package t2406e_group1.bookshopspringboot.maketing;
// entity DiscountProduct để đại diện cho bảng discount_product
// Bảng này đảm nhiệm liên kết dữ liệu giữa discount và product
// Giúp có thể thêm nhiều sản phẩm vào một mã giảm giá


import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;

@Entity
@Getter
@Setter
@Table(name = "discount_product")
public class DiscountProduct {

    @EmbeddedId
    private DiscountProductId id = new DiscountProductId();

    @ManyToOne
    @MapsId("discountId")
    @JoinColumn(name = "discount_id")
    @JsonBackReference
    private EntityDiscount discount;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    // @JsonIgnore
    private EntityProduct product;

    @Column(name = "sale_price")
    private Float salePrice;

    @Column(name = "quantity")
    private Integer quantity;
}