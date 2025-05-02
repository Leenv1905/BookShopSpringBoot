package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaDiscountProduct extends JpaRepository<DiscountProduct, DiscountProduct.DiscountProductId> {
    void deleteByDiscount(EntityDiscount discount);
    List<DiscountProduct> findByDiscount(EntityDiscount discount);
    List<DiscountProduct> findByDiscountId(Integer discountId);
    List<DiscountProduct> findByProductId(Integer productId);
}