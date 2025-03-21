package t2406e_group1.bookshopspringboot.maketing;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaDiscount extends JpaRepository<EntityDiscount, Integer>
{
Optional<EntityDiscount> findBySalePrice(Float salePrice);


}
  
