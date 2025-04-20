// class DiscountProductId để hỗ trợ khóa chính composite

package t2406e_group1.bookshopspringboot.maketing;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DiscountProductId implements Serializable {
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