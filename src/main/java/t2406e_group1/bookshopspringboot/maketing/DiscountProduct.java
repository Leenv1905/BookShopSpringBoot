// entity DiscountProduct để đại diện cho bảng discount_product
// Bảng này đảm nhiệm liên kết dữ liệu giữa discount và product
// Giúp có thể thêm nhiều sản phẩm vào một mã giảm giá

package t2406e_group1.bookshopspringboot.maketing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;

@Entity
@Getter
@Setter
@Table(name = "discount_product")
@IdClass(DiscountProductId.class)
public class DiscountProduct {

    @Id
    @Column(name = "discount_id")
    private Integer discountId;

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "discount_id", insertable = false, updatable = false)
    private EntityDiscount discount;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private EntityProduct product;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "quantity")
    private Integer quantity;

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public EntityDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(EntityDiscount discount) {
        this.discount = discount;
    }

    public EntityProduct getProduct() {
        return product;
    }

    public void setProduct(EntityProduct product) {
        this.product = product;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}