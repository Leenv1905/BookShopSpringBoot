package t2406e_group1.bookshopspringboot.maketing;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;

import java.io.Serializable;
import java.util.Objects;

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
    private EntityProduct product;

    @Column(name = "sale_price")
    private Float salePrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Embeddable
    public static class DiscountProductId implements Serializable {
        private Integer discountId;
        private Integer productId;

        public DiscountProductId() {}

        public DiscountProductId(Integer discountId, Integer productId) {
            this.discountId = discountId;
            this.productId = productId;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DiscountProductId that = (DiscountProductId) o;
            return Objects.equals(discountId, that.discountId) &&
                   Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(discountId, productId);
        }
    }
}