// package t2406e_group1.bookshopspringboot.order;

// public class JpaOrder {
    
// }


package t2406e_group1.bookshopspringboot.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrder extends JpaRepository<EntityOrder, Integer> {
}
