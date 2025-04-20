package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaDiscountProduct extends JpaRepository<DiscountProduct, DiscountProductId> {
    void deleteByDiscount(EntityDiscount discount);
    List<DiscountProduct> findByDiscount(EntityDiscount discount);
}